using SwimmingCompetition.Domain;

namespace SwimmingCompetition.Validator;

public class AngajatValidator: IAbstractValidator<Angajat>
{
    public void Validate(Angajat e)
    {
        String errors = "";
        if (String.IsNullOrEmpty(e.Nume))
            errors += "Nume invalid!\n";
        if (String.IsNullOrEmpty(e.Prenume))
            errors += "Prenume invalid!\n";
        if (String.IsNullOrEmpty(e.Parola))
            errors += "Parola invalida!\n";
        if (String.IsNullOrEmpty(e.Locatie))
            errors += "Locatie invalida!\n";
        if(String.IsNullOrEmpty(errors))
            throw new Exception(errors);
    }
}