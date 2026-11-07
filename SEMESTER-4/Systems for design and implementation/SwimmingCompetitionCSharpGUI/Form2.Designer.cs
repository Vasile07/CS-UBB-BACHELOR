namespace SwimmingCompetitionCSharpGUI
{
    partial class Form2
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            dataGridViewProbe = new DataGridView();
            idColumn = new DataGridViewTextBoxColumn();
            DistantaColumn = new DataGridViewTextBoxColumn();
            StilColumn = new DataGridViewTextBoxColumn();
            DataDesfasurariiColumn = new DataGridViewTextBoxColumn();
            LocatieColumn = new DataGridViewTextBoxColumn();
            NrInscrieriColumn = new DataGridViewTextBoxColumn();
            dataGridViewParticipanti = new DataGridView();
            NumeColumn = new DataGridViewTextBoxColumn();
            PrenumeColumn = new DataGridViewTextBoxColumn();
            ProbeColumn = new DataGridViewTextBoxColumn();
            labelNumeAngajat = new Label();
            label2 = new Label();
            label3 = new Label();
            label4 = new Label();
            textBoxNume = new TextBox();
            textBoxPrenume = new TextBox();
            buttonInregistreaza = new Button();
            buttonSee = new Button();
            logOutButton = new Button();
            ((System.ComponentModel.ISupportInitialize)dataGridViewProbe).BeginInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewParticipanti).BeginInit();
            SuspendLayout();
            // 
            // dataGridViewProbe
            // 
            dataGridViewProbe.BackgroundColor = Color.FromArgb(205, 229, 244);
            dataGridViewProbe.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewProbe.Columns.AddRange(new DataGridViewColumn[] { idColumn, DistantaColumn, StilColumn, DataDesfasurariiColumn, LocatieColumn, NrInscrieriColumn });
            dataGridViewProbe.Location = new Point(12, 112);
            dataGridViewProbe.Name = "dataGridViewProbe";
            dataGridViewProbe.RowHeadersWidth = 51;
            dataGridViewProbe.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dataGridViewProbe.Size = new Size(718, 299);
            dataGridViewProbe.TabIndex = 0;
            // 
            // idColumn
            // 
            idColumn.HeaderText = "ID";
            idColumn.MinimumWidth = 6;
            idColumn.Name = "idColumn";
            idColumn.Width = 40;
            // 
            // DistantaColumn
            // 
            DistantaColumn.HeaderText = "Distanta";
            DistantaColumn.MinimumWidth = 6;
            DistantaColumn.Name = "DistantaColumn";
            DistantaColumn.Width = 125;
            // 
            // StilColumn
            // 
            StilColumn.HeaderText = "Stil";
            StilColumn.MinimumWidth = 6;
            StilColumn.Name = "StilColumn";
            StilColumn.Width = 125;
            // 
            // DataDesfasurariiColumn
            // 
            DataDesfasurariiColumn.HeaderText = "Data Desfasurarii";
            DataDesfasurariiColumn.MinimumWidth = 6;
            DataDesfasurariiColumn.Name = "DataDesfasurariiColumn";
            DataDesfasurariiColumn.Width = 125;
            // 
            // LocatieColumn
            // 
            LocatieColumn.HeaderText = "Locatie";
            LocatieColumn.MinimumWidth = 6;
            LocatieColumn.Name = "LocatieColumn";
            LocatieColumn.Width = 125;
            // 
            // NrInscrieriColumn
            // 
            NrInscrieriColumn.HeaderText = "Nr Inscrieri";
            NrInscrieriColumn.MinimumWidth = 6;
            NrInscrieriColumn.Name = "NrInscrieriColumn";
            NrInscrieriColumn.Width = 125;
            // 
            // dataGridViewParticipanti
            // 
            dataGridViewParticipanti.BackgroundColor = Color.FromArgb(205, 229, 244);
            dataGridViewParticipanti.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewParticipanti.Columns.AddRange(new DataGridViewColumn[] { NumeColumn, PrenumeColumn, ProbeColumn });
            dataGridViewParticipanti.GridColor = SystemColors.InactiveCaptionText;
            dataGridViewParticipanti.Location = new Point(791, 112);
            dataGridViewParticipanti.Name = "dataGridViewParticipanti";
            dataGridViewParticipanti.RowHeadersWidth = 51;
            dataGridViewParticipanti.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dataGridViewParticipanti.Size = new Size(479, 299);
            dataGridViewParticipanti.TabIndex = 1;
            // 
            // NumeColumn
            // 
            NumeColumn.HeaderText = "Nume";
            NumeColumn.MinimumWidth = 6;
            NumeColumn.Name = "NumeColumn";
            NumeColumn.Width = 125;
            // 
            // PrenumeColumn
            // 
            PrenumeColumn.HeaderText = "Prenume";
            PrenumeColumn.MinimumWidth = 6;
            PrenumeColumn.Name = "PrenumeColumn";
            PrenumeColumn.Width = 125;
            // 
            // ProbeColumn
            // 
            ProbeColumn.HeaderText = "Probe";
            ProbeColumn.MinimumWidth = 6;
            ProbeColumn.Name = "ProbeColumn";
            ProbeColumn.Width = 160;
            // 
            // labelNumeAngajat
            // 
            labelNumeAngajat.AutoSize = true;
            labelNumeAngajat.Font = new Font("Arial", 18F, FontStyle.Bold, GraphicsUnit.Point, 238);
            labelNumeAngajat.ForeColor = Color.FromArgb(9, 89, 20);
            labelNumeAngajat.Location = new Point(12, 15);
            labelNumeAngajat.Name = "labelNumeAngajat";
            labelNumeAngajat.Size = new Size(120, 35);
            labelNumeAngajat.TabIndex = 2;
            labelNumeAngajat.Text = "Hello ...";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Font = new Font("Arial", 18F, FontStyle.Bold, GraphicsUnit.Point, 238);
            label2.ForeColor = Color.FromArgb(9, 89, 20);
            label2.Location = new Point(12, 489);
            label2.Name = "label2";
            label2.Size = new Size(142, 35);
            label2.TabIndex = 3;
            label2.Text = "Inscrieri:";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Font = new Font("Segoe UI", 9F, FontStyle.Bold, GraphicsUnit.Point, 238);
            label3.Location = new Point(12, 552);
            label3.Name = "label3";
            label3.Size = new Size(52, 20);
            label3.TabIndex = 4;
            label3.Text = "Nume";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Font = new Font("Segoe UI", 9F, FontStyle.Bold, GraphicsUnit.Point, 238);
            label4.Location = new Point(12, 634);
            label4.Name = "label4";
            label4.Size = new Size(72, 20);
            label4.TabIndex = 5;
            label4.Text = "Prenume";
            // 
            // textBoxNume
            // 
            textBoxNume.Location = new Point(12, 575);
            textBoxNume.Name = "textBoxNume";
            textBoxNume.Size = new Size(298, 27);
            textBoxNume.TabIndex = 6;
            // 
            // textBoxPrenume
            // 
            textBoxPrenume.Location = new Point(12, 657);
            textBoxPrenume.Name = "textBoxPrenume";
            textBoxPrenume.Size = new Size(298, 27);
            textBoxPrenume.TabIndex = 7;
            // 
            // buttonInregistreaza
            // 
            buttonInregistreaza.BackColor = Color.FromArgb(205, 229, 244);
            buttonInregistreaza.Font = new Font("Arial", 9F, FontStyle.Bold, GraphicsUnit.Point, 238);
            buttonInregistreaza.Location = new Point(12, 746);
            buttonInregistreaza.Name = "buttonInregistreaza";
            buttonInregistreaza.Size = new Size(120, 29);
            buttonInregistreaza.TabIndex = 8;
            buttonInregistreaza.Text = "Inregistreaza";
            buttonInregistreaza.UseVisualStyleBackColor = false;
            buttonInregistreaza.Click += buttonInregistreaza_Click;
            // 
            // buttonSee
            // 
            buttonSee.BackColor = Color.FromArgb(205, 229, 244);
            buttonSee.Font = new Font("Arial", 9F, FontStyle.Bold, GraphicsUnit.Point, 238);
            buttonSee.Location = new Point(1176, 417);
            buttonSee.Name = "buttonSee";
            buttonSee.Size = new Size(94, 29);
            buttonSee.TabIndex = 9;
            buttonSee.Text = "See";
            buttonSee.UseVisualStyleBackColor = false;
            buttonSee.Click += buttonSee_Click;
            // 
            // logOutButton
            // 
            logOutButton.BackColor = Color.FromArgb(205, 229, 244);
            logOutButton.Font = new Font("Arial", 9F, FontStyle.Bold, GraphicsUnit.Point, 238);
            logOutButton.Location = new Point(1176, 15);
            logOutButton.Name = "logOutButton";
            logOutButton.Size = new Size(94, 29);
            logOutButton.TabIndex = 10;
            logOutButton.Text = "Log out";
            logOutButton.UseVisualStyleBackColor = false;
            logOutButton.Click += logOutButton_Click;
            // 
            // Form2
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(229, 244, 221);
            ClientSize = new Size(1282, 853);
            Controls.Add(logOutButton);
            Controls.Add(buttonSee);
            Controls.Add(buttonInregistreaza);
            Controls.Add(textBoxPrenume);
            Controls.Add(textBoxNume);
            Controls.Add(label4);
            Controls.Add(label3);
            Controls.Add(label2);
            Controls.Add(labelNumeAngajat);
            Controls.Add(dataGridViewParticipanti);
            Controls.Add(dataGridViewProbe);
            FormBorderStyle = FormBorderStyle.FixedToolWindow;
            Name = "Form2";
            Text = "Swimming Competition";
            ((System.ComponentModel.ISupportInitialize)dataGridViewProbe).EndInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewParticipanti).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private DataGridView dataGridViewProbe;
        private DataGridView dataGridViewParticipanti;
        private Label labelNumeAngajat;
        private Label label2;
        private Label label3;
        private Label label4;
        private TextBox textBoxNume;
        private TextBox textBoxPrenume;
        private Button buttonInregistreaza;
        private Button buttonSee;
        private Button logOutButton;
        private DataGridViewTextBoxColumn idColumn;
        private DataGridViewTextBoxColumn DistantaColumn;
        private DataGridViewTextBoxColumn StilColumn;
        private DataGridViewTextBoxColumn DataDesfasurariiColumn;
        private DataGridViewTextBoxColumn LocatieColumn;
        private DataGridViewTextBoxColumn NrInscrieriColumn;
        private DataGridViewTextBoxColumn NumeColumn;
        private DataGridViewTextBoxColumn PrenumeColumn;
        private DataGridViewTextBoxColumn ProbeColumn;
    }
}