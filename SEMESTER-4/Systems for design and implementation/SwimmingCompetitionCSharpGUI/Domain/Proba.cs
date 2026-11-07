using System.Runtime.InteropServices.JavaScript;

namespace SwimmingCompetition.Domain;

public class Proba:Entity<int>
{
    public Int32 Distanta { get; set; }
    public StilInot Stil { get; set; }
    public DateTime DataDesfasurarii { get; set; }
    public String Locatie { get; set; }

    public Proba(int distanta, StilInot stil, DateTime dataDesfasurarii, string locatie)
    {
        Distanta = distanta;
        Stil = stil;
        DataDesfasurarii = dataDesfasurarii;
        Locatie = locatie;
    }

    protected bool Equals(Proba other)
    {
        return base.Equals(other) && Distanta == other.Distanta && Stil == other.Stil && DataDesfasurarii.Equals(other.DataDesfasurarii) && Locatie == other.Locatie;
    }

    public override bool Equals(object? obj)
    {
        if (ReferenceEquals(null, obj)) return false;
        if (ReferenceEquals(this, obj)) return true;
        if (obj.GetType() != this.GetType()) return false;
        return Equals((Proba)obj);
    }

    public override int GetHashCode()
    {
        return HashCode.Combine(base.GetHashCode(), Distanta, (int)Stil, DataDesfasurarii, Locatie);
    }

    public override string ToString()
    {
        return $"{base.ToString()}, {nameof(Distanta)}: {Distanta}, {nameof(Stil)}: {Stil}, {nameof(DataDesfasurarii)}: {DataDesfasurarii}, {nameof(Locatie)}: {Locatie}";
    }
}

public enum StilInot
{
    liber, spate, fluture, mixt
}