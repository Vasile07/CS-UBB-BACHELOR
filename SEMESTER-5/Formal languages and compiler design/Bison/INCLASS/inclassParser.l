%{
#include "inclassParser.tab.h"
#include <string.h>
#include <stdlib.h>
%}

%%
[a-zA-Z ]*  { yylval.str = strdup(yytext); return SECVENTA; }

\# {return '#';}
\n { return '\n'; }

[ \t]+  { /* Ignorăm spațiile și taburile */ }

. { /* Ignorăm alte caractere */ }

%%

int yywrap() {
    return 1;
}
