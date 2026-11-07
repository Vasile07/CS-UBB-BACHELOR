using SwimmingCompetition.Domain;

namespace SwimmingCompetition.Validator;

public class ProbaValidator: IAbstractValidator<Proba>
{
    public void Validate(Proba e)
    {
        String errors = "";

        if (e.Distanta != 50 && e.Distanta != 200 && e.Distanta != 800 && e.Distanta != 1500)
            errors += "Distanta invalida!\n";
        if (e.Stil != null)
            errors += "Stil invalid!\n";
        if (e.DataDesfasurarii < DateTime.Now)
            errors += "Data desfasurarii invalida!\n";
        if (String.IsNullOrEmpty(e.Locatie))
            errors += "Locatie invalida!\n";
        
        if (String.IsNullOrEmpty(errors))
            throw new Exception(errors);
    }
}