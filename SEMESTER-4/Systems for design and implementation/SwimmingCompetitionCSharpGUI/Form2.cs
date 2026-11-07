using SwimmingCompetition.Business;
using SwimmingCompetition.Domain;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.DataFormats;

namespace SwimmingCompetitionCSharpGUI
{
    public partial class Form2 : Form
    {
        private readonly Angajat angajat;
        private readonly IServiceInterface service;

        public Form2(Angajat angajat, IServiceInterface service)
        {
            InitializeComponent();
            this.angajat = angajat;
            this.service = service;
            labelNumeAngajat.Text = "Hello " + angajat.Nume + " " + angajat.Prenume;
            initTables();
            initDataSourceProbe();
        }


        private void dataGridViewParticipanti_CellFormatting(object sender, DataGridViewCellFormattingEventArgs e)
        {
            if (dataGridViewParticipanti.Columns[e.ColumnIndex].Name == "ProbeColumn" && e.Value != null)
            {
                // Check if the current cell is in the ProbeColumn and the value is not null
                List<int> probeList = e.Value as List<int>;

                if (probeList != null && probeList.Count > 0)
                {
                    // Convert the list of integers to a comma-separated string
                    string probeString = string.Join(", ", probeList);

                    // Set the formatted string as the cell value
                    e.Value = probeString;
                }
            }
        }

        private void initTables()
        {
            dataGridViewParticipanti.AutoGenerateColumns = false;
            dataGridViewParticipanti.Columns["NumeColumn"].DataPropertyName = "nume";
            dataGridViewParticipanti.Columns["PrenumeColumn"].DataPropertyName = "prenume";
            dataGridViewParticipanti.Columns["ProbeColumn"].DataPropertyName = "probe";
            dataGridViewParticipanti.CellFormatting += dataGridViewParticipanti_CellFormatting;


            dataGridViewProbe.AutoGenerateColumns = false;
            dataGridViewProbe.Columns["idColumn"].DataPropertyName = "idProba";
            dataGridViewProbe.Columns["DistantaColumn"].DataPropertyName = "distanta";
            dataGridViewProbe.Columns["StilColumn"].DataPropertyName = "stil";
            dataGridViewProbe.Columns["DataDesfasurariiColumn"].DataPropertyName = "dataDesfasurarii";
            dataGridViewProbe.Columns["LocatieColumn"].DataPropertyName = "locatie";
            dataGridViewProbe.Columns["NrInscrieriColumn"].DataPropertyName = "nrInscrieri";
        }

        private void initDataSourceProbe()
        {
            List<ProbaDTO> probe = service.getAllProbeAfterToday();

            BindingList<ProbaDTO> bindingList = new BindingList<ProbaDTO>(probe);

            dataGridViewProbe.DataSource = bindingList;
        }

        private void buttonSee_Click(object sender, EventArgs e)
        {
            DataGridViewRow selectedRow = dataGridViewProbe.SelectedRows[0];
            int id = int.Parse(selectedRow.Cells["idColumn"].Value.ToString());

            List<ParticipantDTO> participants = service.getAllParticipantiFromProba(id);

            BindingList<ParticipantDTO> bindingList = new BindingList<ParticipantDTO>(participants);

            dataGridViewParticipanti.DataSource = bindingList;
        }

        private void logOutButton_Click(object sender, EventArgs e)
        {
            Form1 form1 = new Form1(service);
            form1.TopLevel = false;
            form1.FormBorderStyle = FormBorderStyle.None;
            form1.Dock = DockStyle.Fill;

            this.Controls.Clear();
            this.Controls.Add(form1);
            form1.Show();
        }

        private void buttonInregistreaza_Click(object sender, EventArgs e)
        {
            try{
                string firstname = textBoxPrenume.Text;
                string lastname = textBoxNume.Text;
                List<int> idProbe = new List<int>();
                foreach(DataGridViewRow selectedRow in dataGridViewProbe.SelectedRows)
                {
                    int id = int.Parse(selectedRow.Cells["idColumn"].Value.ToString());
                    idProbe.Add(id);
                }
                service.addParticipari(firstname, lastname, idProbe);
            
                initDataSourceProbe();

                textBoxNume.Text = "";
                textBoxPrenume.Text = ""; 
            }catch(Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}