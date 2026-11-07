%{
#include "attrib.h"
#include "parser.tab.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "analizator.h"

int num_lines = 1, num_chars = 0;

struct {
    char atom[20];
    int cod;
} cod_atomi[100];
int num_atoms = 0;

void readFromFile() {
    FILE *file = fopen("atomi.csv", "r");
    if (file == NULL) {
        perror("Error opening file");
        return;
    }

    char line[100];
    while (fgets(line, sizeof(line), file) != NULL) {
        char atom[20];
        int cod;

        if (sscanf(line, "%19[^~]~%d", atom, &cod) == 2) {
                strncpy(cod_atomi[num_atoms].atom, atom, sizeof(cod_atomi[num_atoms].atom) - 1);
                cod_atomi[num_atoms].atom[sizeof(cod_atomi[num_atoms].atom) - 1] = '\0';
                cod_atomi[num_atoms].cod = cod;
                num_atoms++;
        } else {
            printf("Invalid line format: %s", line);
        }
    }

    fclose(file);
}

struct {
    int cod_atom;
    int ts_line;
} fip[10000];
int fip_len = 0;


struct {
    char atom[20];
    int number;
} ts[10000];
int ts_len = 0;

int getCodAtom(char atom[]){
    for(int i = 0 ; i < num_atoms ; i ++)
        if(strcmp(cod_atomi[i].atom, atom) == 0)
            return cod_atomi[i].cod;
    return -1;
}

int addInTs(char atom[]){
    for(int i = 0 ; i < ts_len; i ++)
        if(strcmp(ts[i].atom,atom) == 0)
            return ts[i].number;
    strncpy(ts[ts_len].atom, atom, sizeof(ts[ts_len].atom) - 1);
    ts[ts_len].atom[sizeof(ts[ts_len].atom) - 1] = '\0';
    ts[ts_len].number = ts_len;
    ts_len++;
    return ts_len-1;
}

void printFip() {
    printf("FIP (File Input Pair):\n");
    for (int i = 0; i < fip_len; i++) {
        printf("Index %d: Cod Atom = %d, TS Line = %d\n", i, fip[i].cod_atom, fip[i].ts_line);
    }
}

// Function to print the ts array
void printTs() {
    printf("TS (Symbol Table):\n");
    for (int i = 0; i < ts_len; i++) {
        printf("Index %d: Atom = %s\n", i, ts[i].atom);
    }
}


%}

digit  [0-9]
letter [a-zA-Z]

%%

"main"            { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return MAIN; }
"read"            { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return READ; }
"write"           { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return WRITE; }
"int"             { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return TIP; }
"bool"            { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return TIP; }
"char"            { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return TIP; }
"double"          { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return TIP; }

"{"               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return LBRACE; }
"}"               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return RBRACE; }
"("               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return LPAREN; }
")"               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return RPAREN; }
";"               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return SEMICOLON; }
"="               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return ASSIGN; }
"+"               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++; strcpy(yylval.opType, yytext);return PLUS; }
","               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return COMMA; }
"-"               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++; strcpy(yylval.opType, yytext);return MINUS; }
"*"               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;strcpy(yylval.opType, yytext);return MULT; }
"/"               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;strcpy(yylval.opType, yytext);return DIV; }
"%"               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;strcpy(yylval.opType, yytext);return MOD; }
"<"               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return LT; }
">"               { fip[fip_len].cod_atom = getCodAtom(yytext);fip[fip_len].ts_line=-1;fip_len++;return GT; }

{digit}+          {int line = addInTs(yytext);fip[fip_len].cod_atom = getCodAtom("CONST");fip[fip_len].ts_line=line;fip_len++; strcpy(yylval.varname, yytext);return CONST;}
{letter}+         {int line=addInTs(yytext);fip[fip_len].cod_atom = getCodAtom("ID");fip[fip_len].ts_line=line;fip_len++; strcpy(yylval.varname, yytext); return ID;}
[ \t\r]+          /* Ignore whitespace characters */
\n                { num_lines++; }
.                 { errors = 1; printf("Error at line %d: Unknown character %s\n", num_lines, yytext); }

%%

int yywrap() {
    return 1;
}
