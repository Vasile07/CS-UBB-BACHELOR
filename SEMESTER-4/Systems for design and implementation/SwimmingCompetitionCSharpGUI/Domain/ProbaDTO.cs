namespace SwimmingCompetition.Domain;

public class ProbaDTO
{
    public int idProba { get; set; }
    public int distanta{ get; set; }
    public StilInot stil{ get; set; }
    public DateTime dataDesfasurarii{ get; set; }
    public string locatie{ get; set; }
    public int nrInscrieri{ get; set; }

    public ProbaDTO(int idProba, int distanta, StilInot stil, DateTime dataDesfasurarii, string locatie, int nrInscrieri)
    {
        this.idProba = idProba;
        this.distanta = distanta;
        this.stil = stil;
        this.dataDesfasurarii = dataDesfasurarii;
        this.locatie = locatie;
        this.nrInscrieri = nrInscrieri;
    }

    protected bool Equals(ProbaDTO other)
    {
        return idProba == other.idProba && distanta == other.distanta && stil == other.stil && dataDesfasurarii.Equals(other.dataDesfasurarii) && locatie == other.locatie && nrInscrieri == other.nrInscrieri;
    }

    public override bool Equals(object? obj)
    {
        if (ReferenceEquals(null, obj)) return false;
        if (ReferenceEquals(this, obj)) return true;
        if (obj.GetType() != this.GetType()) return false;
        return Equals((ProbaDTO)obj);
    }

    public override int GetHashCode()
    {
        return HashCode.Combine(idProba, distanta, (int)stil, dataDesfasurarii, locatie, nrInscrieri);
    }
}