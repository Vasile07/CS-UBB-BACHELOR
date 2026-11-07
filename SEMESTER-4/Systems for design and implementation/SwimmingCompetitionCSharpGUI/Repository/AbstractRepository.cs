using SwimmingCompetition.Domain;

namespace SwimmingCompetition.Repository;

public interface IAbstractRepository<TId,TE> where TE: Entity<TId>
{
    public TE? Add(TE entity);
    public TE? Update(TE entity);
    public TE? DeleteById(TId id);
    public TE? FindById(TId id);
    public List<TE> FindAll();
}