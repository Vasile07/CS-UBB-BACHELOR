using Microsoft.VisualBasic.CompilerServices;

namespace SwimmingCompetition.Domain;

public class Angajat: Entity<int>
{
    public String Nume { get; set; }
    public String Prenume { get; set; }
    public String Locatie { get; set; }
    public String Parola { get; set; }

    public Angajat(string nume, string prenume, string locatie, string parola)
    {
        this.Nume = nume;
        this.Prenume = prenume;
        this.Locatie = locatie;
        this.Parola = parola;
    }

    protected bool Equals(Angajat other)
    {
        return base.Equals(other) && Nume == other.Nume && Prenume == other.Prenume && Locatie == other.Locatie && Parola == other.Parola;
    }

    public override bool Equals(object? obj)
    {
        if (ReferenceEquals(null, obj)) return false;
        if (ReferenceEquals(this, obj)) return true;
        if (obj.GetType() != this.GetType()) return false;
        return Equals((Angajat)obj);
    }

    public override int GetHashCode()
    {
        return HashCode.Combine(base.GetHashCode(), Nume, Prenume, Locatie, Parola);
    }

    public override string ToString()
    {
        return $"{base.ToString()}, {nameof(Nume)}: {Nume}, {nameof(Prenume)}: {Prenume}, {nameof(Locatie)}: {Locatie}, {nameof(Parola)}: {Parola}";
    }
}