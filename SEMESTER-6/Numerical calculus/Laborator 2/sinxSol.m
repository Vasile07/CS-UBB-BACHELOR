function valoareAproximata = sinxSol(x)
valoareAproximata=0;
termen=x;
rang=1;
while valoareAproximata+termen~=valoareAproximata
    valoareAproximata = valoareAproximata+termen;
    termen = -termen*x^2/((rang+1)*(rang+2));
    rang = rang + 2;
end
