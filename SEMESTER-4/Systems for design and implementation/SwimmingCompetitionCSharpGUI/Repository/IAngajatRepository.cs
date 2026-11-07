using SwimmingCompetition.Domain;

namespace SwimmingCompetition.Repository;

public interface IAngajatRepository: IAbstractRepository<int,Angajat>
{
    Angajat? findByFirstnameLastnamePassword(string firstname, string lastname, string password);   
}