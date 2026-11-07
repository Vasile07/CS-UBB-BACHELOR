using SwimmingCompetition.Business;
using SwimmingCompetition.Domain;

namespace SwimmingCompetitionCSharpGUI
{
    public partial class Form1 : Form
    {

        private readonly IServiceInterface service;
        public Form1(IServiceInterface service)
        {
            InitializeComponent();
            this.service = service;
        }

        private void buttonLogIn_Click(object sender, EventArgs e)
        {
            try
            {
                string firstname = textBoxPrenume.Text;
                string lastname = textBoxNume.Text;
                string password = textBoxParola.Text;

                Angajat angajat = service.findAngajatByFirstnameLastnamePassword(firstname, lastname, password);

                Form2 form2 = new Form2(angajat, service);
                form2.TopLevel = false;
                form2.FormBorderStyle = FormBorderStyle.None;
                form2.Dock = DockStyle.Fill;

                this.Controls.Clear();
                this.Controls.Add(form2);
                form2.Show();
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}
