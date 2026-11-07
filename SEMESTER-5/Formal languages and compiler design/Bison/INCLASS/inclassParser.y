%{
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
extern int yylex();
extern FILE* yyin;

void yyerror(const char* s);
void verificaPalindrom(const char* s1, const char* s2);
%}

%union {
    char* str; 
}

%token <str> SECVENTA

%%
input:  SECVENTA '#' SECVENTA '\n' { verificaPalindrom($1,$3); }

%%

#include <ctype.h>

void eliminaSpatii(const char* src, char* dest) {
    while (*src) {
        if (!isspace(*src)) {
            *dest++ = *src;
        }
        src++;
    }
    *dest = '\0';
}

void verificaPalindrom(const char* s1, const char* s2) {
    char s1FaraSpatii[256];
    char s2FaraSpatii[256];
    
    eliminaSpatii(s1, s1FaraSpatii);
    eliminaSpatii(s2, s2FaraSpatii);

    size_t len1 = strlen(s1FaraSpatii);
    size_t len2 = strlen(s2FaraSpatii);

    if (len1 != len2) {
        printf("Secventa %s#%s nu este palindrom\n", s1, s2);
        return;
    }

    for (size_t i = 0; i < len1; ++i) {
        if (s1FaraSpatii[i] != s2FaraSpatii[len1 - 1 - i]) {
            printf("Secventa %s#%s nu este palindrom\n", s1, s2);
            return;
        }
    }

    printf("Secventa %s#%s este palindrom\n", s1, s2);
}

void yyerror(const char* s) {
    fprintf(stderr, "Eroare de sintaxa: %s\n", s);
}

int main() {
    yyin = stdin;
    printf("Introdu secventa:\n");
    yyparse();
    return 0;   
}