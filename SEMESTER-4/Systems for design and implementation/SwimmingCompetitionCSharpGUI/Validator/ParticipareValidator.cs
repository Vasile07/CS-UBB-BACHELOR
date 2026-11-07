using SwimmingCompetition.Domain;

namespace SwimmingCompetition.Validator;

public class ParticipareValidator: IAbstractValidator<Participare>
{
    public void Validate(Participare e)
    {
        String errors = "";
        
        if (!String.IsNullOrEmpty(errors))
            throw new Exception(errors);
    }
}