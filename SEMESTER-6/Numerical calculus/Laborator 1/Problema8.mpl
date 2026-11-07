

# Problema 6
restart;
f := x -> arctan(x);
taylor(f(x), x = 0, 7);
# Problema 7
aproximare_maclaurin := proc(f, n) local c, ind, T; description "aproximare MacLaurin"; T := x -> sum(c[i]*x^i, i = 0 .. n); for ind from 0 to n do c[ind] := coeftayl(f(x), x = 0., ind); end do; return T(x); end proc;
f := exp(x);
T2f := aproximare_maclaurin(f, 2);
T3f := aproximare_maclaurin(f, 3);
T4f := aproximare_maclaurin(f, 4);
T5f := aproximare_maclaurin(f, 5);
plot([f(x), T2f, T3f, T4f, T5f], x = -1 .. 1, legend = ["f(x)", "T2f", "T3f", "T4f", "T5f"]);
g := x -> ln(x + 1);
T2g := aproximare_maclaurin(g, 2);
T3g := aproximare_maclaurin(g, 3);
T4g := aproximare_maclaurin(g, 4);
T5g := aproximare_maclaurin(g, 5);
plot([g(x), T2g, T3g, T4g, T5g], x = -1 .. 1, legend = ["g(x)", "T2g", "T3g", "T4g", "T5g"]);
# Problema 8. Aproximarea Pade
# `&approx;`(f(x), p[m](x)/q[k](x)) and p[m](x)/q[k](x) = sum(a[i]*x^i, i = 0 .. m)/sum(b[j]*x^j, j = 0 .. k) and sum(a[i]*x^i, i = 0 .. m)/sum(b[j]*x^j, j = 0 .. k) = R[m, k]*x;
# b[0] = 1;
# c[i] = (D@@i)(f)(0)*si*c[i]/i! and (D@@i)(f)(0)*si*c[i]/i! = 0*pt*i and 0*pt*i < 0;
# Pentru*a*obtine*coeficientii*b[i], i = 1 .. k, se*rezolva*sistemul;
# Typesetting[delayDotProduct](Matrix(5, 5, [[c[m], c[m - 1], () .. (), c[m - k + 2], c[m - k + 1]], [c[m + 1], c[m], () .. (), c[m - k + 3], c[m - k + 2]], [() .. (), () .. (), () .. (), () .. (), () .. ()], [c[m + k - 2], c[m + k - 3], () .. (), c[m], c[m - 1]], [c[m + k - 1], c[m + k - 2], () .. (), c[m + 1], c[m]]]), Vector[column](5, [b[1], b[2], () .. (), b[k - 1], b[k]]), true) = Typesetting[delayDotProduct](Vector[column](5, [-c[m + 1], -c[m + 2], () .. (), -c[m + k - 1], -c[m + k]]), Pentru, true)*a*obtine*coeficientii*a[j];
# a[j] = sum((c[j] - b[l])*pentru*j, l = 0 .. j) and sum((c[j] - b[l])*pentru*j, l = 0 .. j) = 0 .. m;
aproximarea_pade := proc(f, m, k) local c, p, C, dreapta, matrice_b, i, j, a, b, l, R; description "aproximare pade"; for i from 0 to m + k do c[i] := coeftayl(f(x), x = 0, i); end do; for i from -k to -1 do c[i] := 0; end do; C := LinearAlgebra:-ToeplitzMatrix([seq(c[m + i], i = -k + 1 .. k - 1)]); dreapta := Vector([seq(-c[m + i], i = 1 .. k)]); if LinearAlgebra:-Determinant(C) = 0 then error "Matrix is singular, cannot solve the system."; end if; matrice_b := LinearAlgebra:-LinearSolve(C, dreapta); b[0] := 1; for i to k do b[i] := matrice_b[i]; end do; if k <= m then for i from k + 1 to m do b[i] := 0; end do; end if; for j from 0 to m do a[j] := sum(c[j - l]*b[l], l = 0 .. j); end do; R := x -> sum(a[ind_i]*x^ind_i, ind_i = 0 .. m)/sum(b[ind_j]*x^ind_j, ind_j = 0 .. k); return R(x); end proc;
f := x -> exp(x);
R11f := aproximarea_pade(f, 1, 1);
R22f := aproximarea_pade(f, 1, 1);
plot([f(x), R11f, R22f], x = -1 .. 1, legend = [f(x), "R11", "R22"]);
g := x -> ln(x + 1);
R22g := aproximarea_pade(g, 2, 2);
R31g := aproximarea_pade(g, 3, 1);
plot([g(x), R22g, R31g], x = -1 .. 1, legend = [g(x), "R22", "R31"]);
# Problema 9
# 
BesselJ02x := x -> int(cos(2*x*sin(t)), t = 0 .. Pi)/Pi;
maclaureenBesselJ02x := taylor(BesselJ02x(x), x = 0, 7);
R22 := aproximarea_pade(BesselJ02x, 2, 2);
R43 := aproximarea_pade(BesselJ02x, 4, 3);
R24 := aproximarea_pade(BesselJ02x, 2, 4);
plot([BesselJ02x(x), maclaureenBesselJ02x, R22, R43, R24], x = -2 .. 2, legend = ["BesselJ02x", "MacLauren", "R22", "R43", "R24"]);
# Problema 10
f := x -> (exp(x) - 1 - x)/x^2;
limit(f(x), x = 0);
plot(f(x), x = -10^(-5) .. 10^(-5), title = "f");
taylor(f(x), x = 0, 5);
Tf := x -> 1/2 + 1/6*x + 1/24*x^2;
epsilon := 10^(-8);
y := x -> piecewise(abs(x) <= epsilon, Tf(x), f(x));
plot(y(x), x = -10^(-6) .. 10^(-6), title = "f");
# Problema 11
# (T[n] f)(x)=f(a) + (x-a)/(1!)*f'(a) + ... + ((x-a)^(n))/(n!)*f^((n))(a) = (&sum;)((x-a)^(i))/(i!)*f^((i))(a);
# (R[n]*f;)(x) = (x - a)^(n + 1)*(D@@(n + 1))(f)(xi)/(n + 1)!;
# `in`(xi, a, x);
# 
formulaLuiTaylorCuRestLagrange := proc(f, a, n) local Tnf, Rnf, indice; description "Se returneaza aproximarea taylor cu rest in forma Lagrange pentru functia f data si rangul n"; Tnf := x -> sum((x - a)^i*eval(diff(f(x), x $ i), x = a)/i!, i = 0 .. n); Rnf := x -> (x - a)^(n + 1)*eval(diff(f(x), x $ (n + 1)), x = xi)/(n + 1)!; return Tnf(x) + Rnf(x); end proc;
f := x -> exp(x);
formulaLuiTaylorCuRestLagrange(f, 0, 5);
NULL;
