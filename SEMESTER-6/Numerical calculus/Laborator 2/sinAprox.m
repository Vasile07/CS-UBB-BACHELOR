function valoareAproximata = sinAprox(x)
    [xRedus, ~] = NgReduction(x);
    valoareAproximata = sinxSol(xRedus);
end
