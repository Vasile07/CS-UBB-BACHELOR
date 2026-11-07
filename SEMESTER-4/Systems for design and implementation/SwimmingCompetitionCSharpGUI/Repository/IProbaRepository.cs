using SwimmingCompetition.Domain;

namespace SwimmingCompetition.Repository;

public interface IProbaRepository: IAbstractRepository<int,Proba>
{
    List<ProbaDTO> getAllProbaAfterToday();
}