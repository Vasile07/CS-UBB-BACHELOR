%{
#include <stdio.h>
#include <stdlib.h>
#include "analizator.h"

extern int yylex();
extern FILE* yyin;
int errors = 0;

void yyerror(const char* s);

%}


%token TIP MAIN LPAREN RPAREN LBRACE RBRACE ID CONST SEMICOLON ASSIGN
%token IF ELSE WHILE READ WRITE EQ NEQ AND OR LT GT PLUS MINUS MULT DIV MOD DOT COMMA
%token CATTIMP EXECUTA SFCATTIMP
%token STRUCT

%left PLUS MINUS
%left MULT DIV MOD
%left AND OR

%%

program: TIP MAIN LPAREN RPAREN LBRACE lista_instr RBRACE;

lista_instr: instr lista_instr
           | instr
           ;

instr: atr SEMICOLON
     | io SEMICOLON
     | cond
     | rep
     | decl SEMICOLON
     | predefinit
     ;

atr: ID ASSIGN expr;

expr: ID
    | CONST
    | ID DOT ID
    | ID op expr
    | ID DOT ID op expr
    | CONST op expr
    ;

op: PLUS
  | MINUS
  | DIV
  | MOD
  | MULT
  ;

io: READ LPAREN lista_params RPAREN
  | WRITE LPAREN lista_expr RPAREN
  ;

lista_params: ID COMMA lista_params
            | ID DOT ID COMMA lista_params
            | ID
            | ID DOT ID
            ;

lista_expr: expr COMMA lista_expr
          | expr
          ;

cond: IF LPAREN logic RPAREN LBRACE lista_instr RBRACE
    | IF LPAREN logic RPAREN LBRACE lista_instr RBRACE ELSE LBRACE lista_instr RBRACE
    ;

logic: expr_logica
     | expr_logica op_logic logic
     ;

expr_logica: expr comp expr;

comp: LT
    | GT
    | EQ
    | NEQ
    ;

op_logic: AND
        | OR
        ;

rep: WHILE logic LBRACE lista_instr RBRACE;

decl: TIP lista_id;

lista_id: ID
        | ID COMMA lista_id
        ;

predefinit: STRUCT LBRACE decl SEMICOLON RBRACE lista_id SEMICOLON;

%%
void yyerror(const char* s) {
    errors = 1;
    fprintf(stderr, "Syntax error on line %d\n", num_lines);
    exit(EXIT_FAILURE);
}

int main(int argc, char **argv) 
	{ 	++argv, --argc; 		/* skip over program name */ 
		if ( argc > 0 ) 
			yyin = fopen( argv[0], "r" ); 
		else 
			yyin = stdin; 

        
        readFromFile();
		yyparse(); 
        if(errors == 0){
            printFip();
            printTs();
        }
        
	} 
