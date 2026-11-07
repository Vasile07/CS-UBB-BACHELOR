namespace SwimmingCompetition.Domain;

public class Participare:Entity<Pair<int, int>>
{
    public DateOnly DataInscrierii { get; }

    public Participare(DateOnly dataInscrierii)
    {
        DataInscrierii = dataInscrierii;
    }

    protected bool Equals(Participare other)
    {
        return base.Equals(other) && DataInscrierii.Equals(other.DataInscrierii);
    }

    public override bool Equals(object? obj)
    {
        if (ReferenceEquals(null, obj)) return false;
        if (ReferenceEquals(this, obj)) return true;
        if (obj.GetType() != this.GetType()) return false;
        return Equals((Participare)obj);
    }

    public override int GetHashCode()
    {
        return HashCode.Combine(base.GetHashCode(), DataInscrierii);
    }

    public override string ToString()
    {
        return $"{base.ToString()}, {nameof(DataInscrierii)}: {DataInscrierii}";
    }
}