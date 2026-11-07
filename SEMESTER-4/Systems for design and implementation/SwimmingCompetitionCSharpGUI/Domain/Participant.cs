namespace SwimmingCompetition.Domain;

public class Participant: Entity<int>
{
    public String Nume { get; set; }
    public String Prenume { get; set; }
    public Int32 Varsta { get; set; }

    public Participant(string nume, string prenume, int varsta)
    {
        Nume = nume;
        Prenume = prenume;
        Varsta = varsta;
    }

    protected bool Equals(Participant other)
    {
        return base.Equals(other) && Nume == other.Nume && Prenume == other.Prenume && Varsta == other.Varsta;
    }

    public override bool Equals(object? obj)
    {
        if (ReferenceEquals(null, obj)) return false;
        if (ReferenceEquals(this, obj)) return true;
        if (obj.GetType() != this.GetType()) return false;
        return Equals((Participant)obj);
    }

    public override int GetHashCode()
    {
        return HashCode.Combine(base.GetHashCode(), Nume, Prenume, Varsta);
    }

    public override string ToString()
    {
        return $"{base.ToString()}, {nameof(Nume)}: {Nume}, {nameof(Prenume)}: {Prenume}, {nameof(Varsta)}: {Varsta}";
    }
}