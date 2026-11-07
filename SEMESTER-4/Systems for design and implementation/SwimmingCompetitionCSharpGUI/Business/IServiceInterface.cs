using SwimmingCompetition.Domain;

namespace SwimmingCompetition.Business;

public interface IServiceInterface {
    Angajat findAngajatByFirstnameLastnamePassword(string firstname, string lastname, string password);
    List<ProbaDTO> getAllProbeAfterToday();
    void addParticipari(String firstname, String lastname,List<int> probe);
    List<ParticipantDTO> getAllParticipantiFromProba(int probaId);
}