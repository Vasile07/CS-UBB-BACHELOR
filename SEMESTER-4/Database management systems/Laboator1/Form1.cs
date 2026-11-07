using System.Windows.Forms;
using System.Threading.Tasks;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.Configuration;
using System.Data.Common;

namespace Laboator1
{
    public partial class Form1 : Form
    {

        readonly SqlDataAdapter Adapter = new SqlDataAdapter();
        readonly DataSet DataSetParent = new DataSet();
        readonly DataSet DataSetChild = new DataSet();
        int idParent;
        int idChild;


        public Form1()
        {
            InitializeComponent();
        }

        private void ClearFields()
        {
            List<string> ColumnNameList = new List<string>(ConfigurationManager.AppSettings["ChildColumnNames"].Split(","));
            foreach (string colum in ColumnNameList)
            {
                TextBox text = (TextBox)panelTextBoxes.Controls[colum];
                text.Clear();
                text.Text = text.Name;
            }
        }


        private void Incarcare_Click(object sender, EventArgs e)
        {
            string connectionString = ConfigurationManager.ConnectionStrings["connectionDBMusicStream"].ConnectionString;

            try
            {
                SqlConnection sqlConnection = new SqlConnection(connectionString);
                string selectCommand = ConfigurationSettings.AppSettings["selectParent"];
                Adapter.SelectCommand = new SqlCommand(selectCommand, sqlConnection);

                DataSetParent.Clear();
                Adapter.Fill(DataSetParent);
                dataGridViewParent.DataSource = DataSetParent.Tables[0];
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void insertButton_Click(object sender, EventArgs e)
        {
            string connectionString = ConfigurationManager.ConnectionStrings["connectionDBMusicStream"].ConnectionString;
            try
            {
                SqlConnection sqlConnection = new SqlConnection(connectionString);
                string childTableName = ConfigurationSettings.AppSettings["ChildTableName"];
                string childColumnNames = ConfigurationSettings.AppSettings["ChildColumnNames"];
                List<string> ColumnNameList = new List<string>(ConfigurationSettings.AppSettings["ChildColumnNames"].Split(","));

                string insertCommand = ConfigurationSettings.AppSettings["InsertQuery"];
                SqlCommand sqlCommand = new SqlCommand(insertCommand, sqlConnection);

                sqlCommand.Parameters.AddWithValue("@idParent", idParent);

                foreach (string colum in ColumnNameList)
                {
                    TextBox text = (TextBox)panelTextBoxes.Controls[colum];
                    sqlCommand.Parameters.AddWithValue("@" + colum, text.Text);
                }

                sqlConnection.Open();
                sqlCommand.ExecuteNonQuery();
                DataSetChild.Clear();
                Adapter.Fill(DataSetChild);
                dataGridViewChild.DataSource = DataSetChild.Tables[0];
                MessageBox.Show("Insert succesfully!");
                sqlConnection.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            ClearFields();
        }

        private void updateButton_Click(object sender, EventArgs e)
        {
            string connectionString = ConfigurationManager.ConnectionStrings["connectionDBMusicStream"].ConnectionString;
            try
            {
                SqlConnection sqlConnection = new SqlConnection(connectionString);
                string childTableName = ConfigurationSettings.AppSettings["ChildTableName"];
                string childColumnNames = ConfigurationSettings.AppSettings["ChildColumnNames"];
                List<string> ColumnNameList = new List<string>(ConfigurationSettings.AppSettings["ChildColumnNames"].Split(","));

                string updateCommand = ConfigurationSettings.AppSettings["UpdateQuery"];
                SqlCommand sqlCommand = new SqlCommand(updateCommand, sqlConnection);



                sqlCommand.Parameters.AddWithValue("@idChild", idChild);

                foreach (string colum in ColumnNameList)
                {
                    TextBox text = (TextBox)panelTextBoxes.Controls[colum];
                    sqlCommand.Parameters.AddWithValue("@" + colum, text.Text);
                }

                sqlConnection.Open();
                sqlCommand.ExecuteNonQuery();
                DataSetChild.Clear();
                Adapter.Fill(DataSetChild);
                dataGridViewChild.DataSource = DataSetChild.Tables[0];
                MessageBox.Show("Update succesfully!");
                sqlConnection.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            ClearFields();
        }

        private void deleteButton_Click(object sender, EventArgs e)
        {
            string connectionString = ConfigurationManager.ConnectionStrings["connectionDBMusicStream"].ConnectionString;
            try
            {
                SqlConnection sqlConnection = new SqlConnection(connectionString);

                string insertCommand = ConfigurationSettings.AppSettings["DeleteQuery"];
                SqlCommand sqlCommand = new SqlCommand(insertCommand, sqlConnection);

                sqlCommand.Parameters.AddWithValue("@idChild", idChild);

                sqlConnection.Open();
                sqlCommand.ExecuteNonQuery();
                DataSetChild.Clear();
                Adapter.Fill(DataSetChild);
                dataGridViewChild.DataSource = DataSetChild.Tables[0];
                MessageBox.Show("Delete succesfully!");
                sqlConnection.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            ClearFields();
        }

        private void dataGridViewParent_RowHeaderMouseClick(object sender, DataGridViewCellMouseEventArgs e)
        {
            idParent = int.Parse(dataGridViewParent.SelectedRows[0].Cells[0].Value.ToString());
            string connectionString = ConfigurationManager.ConnectionStrings["connectionDBMusicStream"].ConnectionString;
            try
            {
                SqlConnection sqlConnection = new SqlConnection(connectionString);
                string selectCommand = ConfigurationSettings.AppSettings["selectChild"];
                Adapter.SelectCommand = new SqlCommand(selectCommand, sqlConnection);
                Adapter.SelectCommand.Parameters.AddWithValue("@idParent", idParent);
                DataSetChild.Clear();
                Adapter.Fill(DataSetChild);
                dataGridViewChild.DataSource = DataSetChild.Tables[0];
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private int getColumnIndexForHeader(string header)
        {
            int columnIndex = 0;
            foreach (DataGridViewColumn col in dataGridViewChild.Columns)
            {
                if (col.HeaderText.Equals(header, StringComparison.OrdinalIgnoreCase))
                {
                    columnIndex = col.Index;
                    break;
                }
            }
            return columnIndex;
        }

        private void dataGridViewChild_RowHeaderMouseClick(object sender, DataGridViewCellMouseEventArgs e)
        {
            try
            {
                idChild = int.Parse(dataGridViewChild.SelectedRows[0].Cells[0].Value.ToString());


                List<string> ColumnNameList = new List<string>(ConfigurationSettings.AppSettings["ChildColumnNames"].Split(","));

                foreach (string columnName in ColumnNameList)
                {
                    TextBox textBox = (TextBox)panelTextBoxes.Controls[columnName];
                    int columnIndex = getColumnIndexForHeader(columnName);
                    string value = dataGridViewChild.SelectedRows[0].Cells[columnIndex].Value.ToString();
                    textBox.Text = value;
                }


            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void genereazaTextBox_Click(object sender, EventArgs e)
        {

            try
            {
                List<string> ColumnNames = new List<string>(ConfigurationSettings.AppSettings["ChildColumnNames"].Split(','));
                int pointX = 30;
                int pointY = 30;

                int numberOfColumns = int.Parse(ConfigurationSettings.AppSettings["ChildNumberOfColumns"]);
                panelTextBoxes.Controls.Clear();

                foreach (string column in ColumnNames)
                {
                    TextBox textBox = new TextBox();
                    textBox.Width = 400;
                    textBox.Text = column;
                    textBox.Name = column;
                    textBox.Location = new Point(pointX, pointY);
                    textBox.Visible = true;
                    textBox.Parent = panelTextBoxes;
                    panelTextBoxes.Show();
                    pointY += 30;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}
