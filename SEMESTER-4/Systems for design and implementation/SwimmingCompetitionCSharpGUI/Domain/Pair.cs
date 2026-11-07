namespace SwimmingCompetition.Domain;

public class Pair<TId1, TId2>
{
    public TId1 Id1 { get; set; }
    public TId2 Id2 { get; set; }

    public Pair(TId1 id1, TId2 id2)
    {
        Id1 = id1;
        Id2 = id2;
    }

    protected bool Equals(Pair<TId1, TId2> other)
    {
        return EqualityComparer<TId1>.Default.Equals(Id1, other.Id1) && EqualityComparer<TId2>.Default.Equals(Id2, other.Id2);
    }

    public override bool Equals(object? obj)
    {
        if (ReferenceEquals(null, obj)) return false;
        if (ReferenceEquals(this, obj)) return true;
        if (obj.GetType() != this.GetType()) return false;
        return Equals((Pair<TId1, TId2>)obj);
    }

    public override int GetHashCode()
    {
        return HashCode.Combine(Id1, Id2);
    }

    public override string ToString()
    {
        return $"{nameof(Id1)}: {Id1}, {nameof(Id2)}: {Id2}";
    }
}