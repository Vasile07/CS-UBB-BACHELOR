%{
#include "attrib.h"
#include <stdio.h>
#include <stdlib.h>
#include "analizator.h"
#include <string.h>
#include <ctype.h>

extern int yylex();
extern FILE* yyin;
int errors = 0;

void yyerror(const char* s);


char declbuffer[2500];
char codebuffer[2500];

int tempnr = 1;
void newTempName(char* s){
  sprintf(s,"temp%d", tempnr);
  tempnr++;
  sprintf(declbuffer, "%s%s dd 0,\n", declbuffer,s); 
}

char tempbuffer[250];

void parametrizare(char* str, char* copie){
  int is_number = 1; // Flag to check if the string is a number

    // Check if the string is a number
    for (int i = 0; str[i] != '\0'; i++) {
        if (!isdigit(str[i])) {
            is_number = 0; // If any character is not a digit, it's not a number
            break;
        }
    }

  if(is_number == 0)
    sprintf(copie, "[%s]", str);
  else
    sprintf(copie, str);
}

void copiazaTransformarea(char* operatie, char* cod, char* var1, char* var2, char* var){
  char copie_var1[100], copie_var2[100], copie_var[100];
  parametrizare(var1, copie_var1);
  parametrizare(var2, copie_var2);
  parametrizare(var, copie_var);
  if(strcmp("+", operatie) == 0){
    sprintf(tempbuffer, ADD_ASM_FORMAT, copie_var1, copie_var2, copie_var);
    strcat(cod, tempbuffer);
  } else if(strcmp("-", operatie) == 0){
    sprintf(tempbuffer, SUBTRACT_ASM_FORMAT, copie_var1, copie_var2, copie_var);
    strcat(cod, tempbuffer);
  } else if(strcmp("*", operatie) == 0){
    sprintf(tempbuffer, MULTIPLY_ASM_FORMAT, copie_var1, copie_var2, var, var);
    strcat(cod, tempbuffer);
  } else if(strcmp("/", operatie) == 0){
    sprintf(tempbuffer, DIVISION_ASM_FORMAT, var1, var1, copie_var2, copie_var);
    strcat(cod, tempbuffer);
  }else if(strcmp("%", operatie) == 0){
    sprintf(tempbuffer, MODULO_ASM_FORMAT, var1, var1, copie_var2, copie_var);
    strcat(cod, tempbuffer);
  }
}


%}

%union {
  char varname[10];
  attributes attrib;
  char opType[10];
  char codType[2500];
}

%token TIP MAIN LPAREN RPAREN LBRACE RBRACE SEMICOLON ASSIGN
%token READ WRITE EQ NEQ LT GT COMMA
%token <varname> ID
%token <varname> CONST
%type <attrib> expr
%type <codType> instr
%type <codType> lista_instr
%type <attrib> term
%type <attrib> factor
%type <codType> atr
%type <codType> io
%type <codType> lista_id



%left PLUS MINUS
%left MULT DIV MOD


%%

program: TIP MAIN LPAREN RPAREN LBRACE decl SEMICOLON lista_instr RBRACE {
      printf(PROGRAM_STRUCTURE, declbuffer, $8);
  };

lista_instr: instr lista_instr {
              sprintf($$, "%s\n%s", $1, $2);
            }
           | instr {strcpy($$, $1);}
           ;

instr: atr SEMICOLON {sprintf($$, "%s\n", $1);}
     | io SEMICOLON {sprintf($$, "%s\n", $1);}
     ;

atr: ID ASSIGN expr {
      sprintf($$, "%s\n mov eax, [%s]\n mov [%s], eax\n",$3.cod,  $3.varn, $1);
  };


expr: term                           { strcpy($$.cod, $1.cod); strcpy($$.varn, $1.varn); }
    | expr PLUS term                 { 
                                        newTempName($$.varn); 
                                        sprintf($$.cod, "%s\n%s\n", $1.cod, $3.cod); 
                                        copiazaTransformarea("+", $$.cod, $1.varn, $3.varn, $$.varn);
                                      }
    | expr MINUS term                { 
                                        newTempName($$.varn); 
                                        sprintf($$.cod, "%s\n%s\n", $1.cod, $3.cod); 
                                        copiazaTransformarea("-",$$.cod, $1.varn, $3.varn, $$.varn);
                                      }
    ;

term: factor                          { strcpy($$.cod, $1.cod); strcpy($$.varn, $1.varn); }
    | term MULT factor                { 
                                        newTempName($$.varn); 
                                        sprintf($$.cod, "%s\n%s\n", $1.cod, $3.cod); 
                                        copiazaTransformarea("*",$$.cod, $1.varn, $3.varn, $$.varn);
                                      }
    | term DIV factor                 { 
                                        newTempName($$.varn); 
                                        sprintf($$.cod, "%s\n%s\n", $1.cod, $3.cod); 
                                        copiazaTransformarea("/",$$.cod, $1.varn, $3.varn, $$.varn);
                                      }
    | term MOD factor                 { 
                                        newTempName($$.varn); 
                                        sprintf($$.cod, "%s\n%s\n", $1.cod, $3.cod); 
                                        copiazaTransformarea("%",$$.cod, $1.varn, $3.varn, $$.varn);
                                      }
    ;

factor: ID                             { strcpy($$.cod, ""); strcpy($$.varn, $1); }
      | CONST                          { strcpy($$.cod, ""); strcpy($$.varn, $1); }
      ;

io: READ LPAREN ID RPAREN {sprintf($$, CITIRE_VARIABILA, $3);}
  | WRITE LPAREN ID RPAREN {char copie[100]; parametrizare($3, copie);sprintf($$, SCRIERE_VARIABILA, copie);}
  ;

decl: TIP lista_id {sprintf(declbuffer, "%s", $2)};

lista_id: ID {sprintf($$, "%s dd 0,\n", $1); strcat($$, "format db '%d', 0,\n");}
        | ID COMMA lista_id {sprintf($$, "%s dd 0,\n%s\n", $1, $3);}
        ;

%%
void yyerror(const char* s) {
    errors = 1;
    fprintf(stderr, "Syntax error on line %d\n", num_lines);
    exit(EXIT_FAILURE);
}

int main(int argc, char **argv) 
	{ 	++argv, --argc;
		if ( argc > 0 ) 
			yyin = fopen( argv[0], "r" ); 
		else 
			yyin = stdin; 

        
        readFromFile();
		yyparse(); 
        if(errors == 0){
            //printFip();
            //printTs();
        }
        
	} 
