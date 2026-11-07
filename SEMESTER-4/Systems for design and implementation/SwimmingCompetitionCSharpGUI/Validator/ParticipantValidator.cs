using SwimmingCompetition.Domain;

namespace SwimmingCompetition.Validator;

public class ParticipantValidator: IAbstractValidator<Participant>
{
    public void Validate(Participant e)
    {
        String errors = "";

        if (String.IsNullOrEmpty(e.Nume))
            errors += "Nume invalid!\n";
        if (String.IsNullOrEmpty(e.Prenume))
            errors += "Prenume invalid!\n";
        if (e.Varsta < 18)
            errors += "Varsta invalida!\n";
        
        if (String.IsNullOrEmpty(errors))
            throw new Exception(errors);
    }
}