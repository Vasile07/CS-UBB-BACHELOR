namespace SwimmingCompetitionCSharpGUI
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            panel1 = new Panel();
            buttonLogIn = new Button();
            tableLayoutPanel1 = new TableLayoutPanel();
            panel2 = new Panel();
            textBoxNume = new TextBox();
            label2 = new Label();
            panel3 = new Panel();
            textBoxPrenume = new TextBox();
            label5 = new Label();
            panel4 = new Panel();
            textBoxParola = new TextBox();
            label7 = new Label();
            label1 = new Label();
            label3 = new Label();
            textBox2 = new TextBox();
            tableLayoutPanel2 = new TableLayoutPanel();
            tableLayoutPanel3 = new TableLayoutPanel();
            label4 = new Label();
            textBox3 = new TextBox();
            panel1.SuspendLayout();
            tableLayoutPanel1.SuspendLayout();
            panel2.SuspendLayout();
            panel3.SuspendLayout();
            panel4.SuspendLayout();
            tableLayoutPanel2.SuspendLayout();
            tableLayoutPanel3.SuspendLayout();
            SuspendLayout();
            // 
            // panel1
            // 
            panel1.BackColor = Color.White;
            panel1.Controls.Add(buttonLogIn);
            panel1.Controls.Add(tableLayoutPanel1);
            panel1.Controls.Add(label1);
            panel1.Location = new Point(403, 153);
            panel1.Name = "panel1";
            panel1.Padding = new Padding(10, 15, 10, 15);
            panel1.Size = new Size(476, 547);
            panel1.TabIndex = 0;
            // 
            // buttonLogIn
            // 
            buttonLogIn.BackColor = Color.Silver;
            buttonLogIn.Font = new Font("Arial", 9F, FontStyle.Bold, GraphicsUnit.Point, 238);
            buttonLogIn.Location = new Point(16, 500);
            buttonLogIn.Name = "buttonLogIn";
            buttonLogIn.Size = new Size(94, 29);
            buttonLogIn.TabIndex = 5;
            buttonLogIn.Text = "Log in";
            buttonLogIn.UseVisualStyleBackColor = false;
            buttonLogIn.Click += buttonLogIn_Click;
            // 
            // tableLayoutPanel1
            // 
            tableLayoutPanel1.ColumnCount = 1;
            tableLayoutPanel1.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100F));
            tableLayoutPanel1.Controls.Add(panel2, 0, 0);
            tableLayoutPanel1.Controls.Add(panel3, 0, 2);
            tableLayoutPanel1.Controls.Add(panel4, 0, 4);
            tableLayoutPanel1.Location = new Point(13, 91);
            tableLayoutPanel1.Name = "tableLayoutPanel1";
            tableLayoutPanel1.RowCount = 5;
            tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Percent, 28.5714283F));
            tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Percent, 7.142857F));
            tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Percent, 28.5714283F));
            tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Percent, 7.142857F));
            tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Percent, 28.5714283F));
            tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Absolute, 20F));
            tableLayoutPanel1.Size = new Size(450, 302);
            tableLayoutPanel1.TabIndex = 4;
            // 
            // panel2
            // 
            panel2.Controls.Add(textBoxNume);
            panel2.Controls.Add(label2);
            panel2.Dock = DockStyle.Fill;
            panel2.Location = new Point(3, 3);
            panel2.Name = "panel2";
            panel2.Size = new Size(444, 80);
            panel2.TabIndex = 0;
            // 
            // textBoxNume
            // 
            textBoxNume.Font = new Font("Cambria", 12F, FontStyle.Regular, GraphicsUnit.Point, 238);
            textBoxNume.Location = new Point(3, 49);
            textBoxNume.Name = "textBoxNume";
            textBoxNume.Size = new Size(211, 31);
            textBoxNume.TabIndex = 1;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Font = new Font("Arial", 12F, FontStyle.Bold, GraphicsUnit.Point, 238);
            label2.ForeColor = Color.FromArgb(3, 33, 114);
            label2.Location = new Point(3, 3);
            label2.Name = "label2";
            label2.Size = new Size(64, 24);
            label2.TabIndex = 0;
            label2.Text = "Nume";
            // 
            // panel3
            // 
            panel3.Controls.Add(textBoxPrenume);
            panel3.Controls.Add(label5);
            panel3.Dock = DockStyle.Fill;
            panel3.Location = new Point(3, 110);
            panel3.Name = "panel3";
            panel3.Size = new Size(444, 80);
            panel3.TabIndex = 1;
            // 
            // textBoxPrenume
            // 
            textBoxPrenume.Font = new Font("Cambria", 12F, FontStyle.Regular, GraphicsUnit.Point, 238);
            textBoxPrenume.Location = new Point(3, 49);
            textBoxPrenume.Name = "textBoxPrenume";
            textBoxPrenume.Size = new Size(211, 31);
            textBoxPrenume.TabIndex = 1;
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Font = new Font("Arial", 12F, FontStyle.Bold, GraphicsUnit.Point, 238);
            label5.ForeColor = Color.FromArgb(3, 33, 114);
            label5.Location = new Point(3, 0);
            label5.Name = "label5";
            label5.Size = new Size(94, 24);
            label5.TabIndex = 0;
            label5.Text = "Prenume";
            // 
            // panel4
            // 
            panel4.Controls.Add(textBoxParola);
            panel4.Controls.Add(label7);
            panel4.Dock = DockStyle.Fill;
            panel4.Location = new Point(3, 217);
            panel4.Name = "panel4";
            panel4.Size = new Size(444, 82);
            panel4.TabIndex = 2;
            // 
            // textBoxParola
            // 
            textBoxParola.Font = new Font("Cambria", 12F, FontStyle.Regular, GraphicsUnit.Point, 238);
            textBoxParola.Location = new Point(3, 51);
            textBoxParola.Name = "textBoxParola";
            textBoxParola.Size = new Size(211, 31);
            textBoxParola.TabIndex = 1;
            // 
            // label7
            // 
            label7.AutoSize = true;
            label7.Font = new Font("Arial", 12F, FontStyle.Bold, GraphicsUnit.Point, 238);
            label7.ForeColor = Color.FromArgb(3, 33, 114);
            label7.Location = new Point(3, 0);
            label7.Name = "label7";
            label7.Size = new Size(70, 24);
            label7.TabIndex = 0;
            label7.Text = "Parola";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Arial", 28.8000011F, FontStyle.Bold, GraphicsUnit.Point, 238);
            label1.ForeColor = Color.FromArgb(3, 33, 114);
            label1.Location = new Point(91, 15);
            label1.Name = "label1";
            label1.Size = new Size(296, 56);
            label1.TabIndex = 0;
            label1.Text = "WELCOME!";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Font = new Font("Arial", 12F, FontStyle.Bold, GraphicsUnit.Point, 238);
            label3.ForeColor = Color.FromArgb(3, 33, 114);
            label3.Location = new Point(3, 0);
            label3.Name = "label3";
            label3.Size = new Size(64, 20);
            label3.TabIndex = 0;
            label3.Text = "Nume";
            // 
            // textBox2
            // 
            textBox2.Location = new Point(3, 27);
            textBox2.Name = "textBox2";
            textBox2.Size = new Size(194, 27);
            textBox2.TabIndex = 1;
            // 
            // tableLayoutPanel2
            // 
            tableLayoutPanel2.ColumnCount = 1;
            tableLayoutPanel2.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50F));
            tableLayoutPanel2.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50F));
            tableLayoutPanel2.Controls.Add(label3, 0, 0);
            tableLayoutPanel2.Location = new Point(0, 0);
            tableLayoutPanel2.Name = "tableLayoutPanel2";
            tableLayoutPanel2.RowCount = 2;
            tableLayoutPanel2.RowStyles.Add(new RowStyle(SizeType.Absolute, 20F));
            tableLayoutPanel2.RowStyles.Add(new RowStyle(SizeType.Absolute, 20F));
            tableLayoutPanel2.Size = new Size(200, 100);
            tableLayoutPanel2.TabIndex = 0;
            // 
            // tableLayoutPanel3
            // 
            tableLayoutPanel3.ColumnCount = 1;
            tableLayoutPanel3.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50F));
            tableLayoutPanel3.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50F));
            tableLayoutPanel3.Controls.Add(label4, 0, 0);
            tableLayoutPanel3.Location = new Point(0, 0);
            tableLayoutPanel3.Name = "tableLayoutPanel3";
            tableLayoutPanel3.RowCount = 2;
            tableLayoutPanel3.RowStyles.Add(new RowStyle(SizeType.Absolute, 20F));
            tableLayoutPanel3.RowStyles.Add(new RowStyle(SizeType.Absolute, 20F));
            tableLayoutPanel3.Size = new Size(200, 100);
            tableLayoutPanel3.TabIndex = 0;
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Font = new Font("Arial", 12F, FontStyle.Bold, GraphicsUnit.Point, 238);
            label4.ForeColor = Color.FromArgb(3, 33, 114);
            label4.Location = new Point(3, 0);
            label4.Name = "label4";
            label4.Size = new Size(64, 20);
            label4.TabIndex = 0;
            label4.Text = "Nume";
            // 
            // textBox3
            // 
            textBox3.Location = new Point(3, 27);
            textBox3.Name = "textBox3";
            textBox3.Size = new Size(194, 27);
            textBox3.TabIndex = 1;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(221, 241, 244);
            ClientSize = new Size(1282, 853);
            Controls.Add(panel1);
            FormBorderStyle = FormBorderStyle.FixedToolWindow;
            Name = "Form1";
            Text = "Swimming Competition";
            panel1.ResumeLayout(false);
            panel1.PerformLayout();
            tableLayoutPanel1.ResumeLayout(false);
            panel2.ResumeLayout(false);
            panel2.PerformLayout();
            panel3.ResumeLayout(false);
            panel3.PerformLayout();
            panel4.ResumeLayout(false);
            panel4.PerformLayout();
            tableLayoutPanel2.ResumeLayout(false);
            tableLayoutPanel2.PerformLayout();
            tableLayoutPanel3.ResumeLayout(false);
            tableLayoutPanel3.PerformLayout();
            ResumeLayout(false);
        }

        #endregion

        private Panel panel1;
        private Label label1;
        private Label label3;
        private TextBox textBox2;
        private TableLayoutPanel tableLayoutPanel2;
        private TableLayoutPanel tableLayoutPanel3;
        private Label label4;
        private TextBox textBox3;
        private TableLayoutPanel tableLayoutPanel1;
        private Panel panel2;
        private TextBox textBoxNume;
        private Label label2;
        private Panel panel3;
        private TextBox textBoxPrenume;
        private Label label5;
        private Panel panel4;
        private TextBox textBoxParola;
        private Label label7;
        private Button buttonLogIn;
    }
}
