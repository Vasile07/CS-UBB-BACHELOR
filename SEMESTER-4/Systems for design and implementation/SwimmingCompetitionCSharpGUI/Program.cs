using log4net.Config;
using SwimmingCompetition.Business;
using SwimmingCompetition.Repository;
using SwimmingCompetition.Validator;
using System.Configuration;

namespace SwimmingCompetitionCSharpGUI
{
    internal static class Program
    {
        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {


            XmlConfigurator.Configure();

            IDictionary<string, string> props = new SortedDictionary<string, string>();
            props.Add("ConnectionString", ConfigurationManager.AppSettings["ConnectionString"]);

            IAngajatRepository angajatRepository = new AngajatRepository(new AngajatValidator(), props);
            IParticipantRepository participantRepository = new ParticipantRepository(new ParticipantValidator(), props);
            IParticipareRepository participareRepository = new ParticipareRepository(new ParticipareValidator(), props);
            IProbaRepository probaRepository = new ProbaRepository(new ProbaValidator(), props);

            IServiceInterface service = new Service(angajatRepository, participantRepository, participareRepository, probaRepository);

            // To customize application configuration such as set high DPI settings or default font,
            // see https://aka.ms/applicationconfiguration.
            ApplicationConfiguration.Initialize();
            Application.Run(new Form1(service));
        }
    }
}