namespace SwimmingCompetition.Domain;

public class ParticipantDTO
{
    public string nume{ get; set; }
    public string prenume{ get; set; }
    public int varsta{ get; set; }
    public List<int> probe { get; set; }

    public ParticipantDTO(string nume, string prenume, int varsta, List<int> probe)
    {
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.probe = probe;
    }

    protected bool Equals(ParticipantDTO other)
    {
        return nume == other.nume && prenume == other.prenume && varsta == other.varsta && probe.Equals(other.probe);
    }

    public override bool Equals(object? obj)
    {
        if (ReferenceEquals(null, obj)) return false;
        if (ReferenceEquals(this, obj)) return true;
        if (obj.GetType() != this.GetType()) return false;
        return Equals((ParticipantDTO)obj);
    }

    public override int GetHashCode()
    {
        return HashCode.Combine(nume, prenume, varsta, probe);
    }
    
}