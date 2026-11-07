namespace SwimmingCompetition.Validator;

public interface IAbstractValidator<TE>
{
    public void Validate(TE e);
}