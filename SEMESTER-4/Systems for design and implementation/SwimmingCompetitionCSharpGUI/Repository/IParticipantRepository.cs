using SwimmingCompetition.Domain;

namespace SwimmingCompetition.Repository;

public interface IParticipantRepository: IAbstractRepository<int,Participant>
{
    List<ParticipantDTO> getAllParticipantiFromProba(int probaId);
    
    Participant? findByFirstnameAndLastname(string firstname, string lastname);
}