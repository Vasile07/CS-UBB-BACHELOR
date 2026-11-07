using SwimmingCompetition.Domain;
using SwimmingCompetition.Repository;

namespace SwimmingCompetition.Business;

public class Service : IServiceInterface
{
    private readonly IAngajatRepository _angajatRepository;
    private readonly IParticipantRepository _participantRepository;
    private readonly IParticipareRepository _participareRepository;
    private readonly IProbaRepository _probaRepository;

    public Service(IAngajatRepository angajatRepository, IParticipantRepository participantRepository, IParticipareRepository participareRepository, IProbaRepository probaRepository)
    {
        _angajatRepository = angajatRepository;
        _participantRepository = participantRepository;
        _participareRepository = participareRepository;
        _probaRepository = probaRepository;
    }

    public Angajat findAngajatByFirstnameLastnamePassword(string firstname, string lastname, string password)
    {
        Angajat? angajat = _angajatRepository.findByFirstnameLastnamePassword(firstname, lastname, password);
        if (angajat == null)
            throw new Exception("Date invalide!");
        return angajat;
    }

    public List<ProbaDTO> getAllProbeAfterToday()
    {
        return _probaRepository.getAllProbaAfterToday();
    }

    public void addParticipari(string firstname, string lastname, List<int> probe)
    {
        Participant? participant = _participantRepository.findByFirstnameAndLastname(firstname, lastname);
        if (participant == null)
            throw new Exception("Participant inexistent!\n");
        foreach (var idProba in probe)
        {
            Participare participare = new Participare(DateOnly.FromDateTime(DateTime.Now));
            participare.Id = new Pair<int, int>(participant.Id, idProba);
            _participareRepository.Add(participare);
        }
    }

    public List<ParticipantDTO> getAllParticipantiFromProba(int probaId)
    {
        return _participantRepository.getAllParticipantiFromProba(probaId);
    }
}