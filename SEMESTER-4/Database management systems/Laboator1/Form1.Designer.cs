namespace Laboator1
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
            dataGridViewParent = new DataGridView();
            dataGridViewChild = new DataGridView();
            insertButton = new Button();
            updateButton = new Button();
            deleteButton = new Button();
            label1 = new Label();
            Incarcare = new Button();
            buttonGenereazaTextBox = new Button();
            panelTextBoxes = new Panel();
            ((System.ComponentModel.ISupportInitialize)dataGridViewParent).BeginInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewChild).BeginInit();
            SuspendLayout();
            // 
            // dataGridViewParent
            // 
            dataGridViewParent.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewParent.Location = new Point(26, 64);
            dataGridViewParent.Name = "dataGridViewParent";
            dataGridViewParent.RowHeadersWidth = 51;
            dataGridViewParent.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dataGridViewParent.Size = new Size(612, 391);
            dataGridViewParent.TabIndex = 0;
            dataGridViewParent.RowHeaderMouseClick += dataGridViewParent_RowHeaderMouseClick;
            // 
            // dataGridViewChild
            // 
            dataGridViewChild.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewChild.Location = new Point(726, 64);
            dataGridViewChild.Name = "dataGridViewChild";
            dataGridViewChild.RowHeadersWidth = 51;
            dataGridViewChild.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dataGridViewChild.Size = new Size(539, 188);
            dataGridViewChild.TabIndex = 1;
            dataGridViewChild.RowHeaderMouseClick += dataGridViewChild_RowHeaderMouseClick;
            // 
            // insertButton
            // 
            insertButton.Location = new Point(717, 491);
            insertButton.Name = "insertButton";
            insertButton.Size = new Size(94, 29);
            insertButton.TabIndex = 2;
            insertButton.Text = "Insert";
            insertButton.UseVisualStyleBackColor = true;
            insertButton.Click += insertButton_Click;
            // 
            // updateButton
            // 
            updateButton.Location = new Point(945, 491);
            updateButton.Name = "updateButton";
            updateButton.Size = new Size(94, 29);
            updateButton.TabIndex = 3;
            updateButton.Text = "Update";
            updateButton.UseVisualStyleBackColor = true;
            updateButton.Click += updateButton_Click;
            // 
            // deleteButton
            // 
            deleteButton.Location = new Point(1162, 491);
            deleteButton.Name = "deleteButton";
            deleteButton.Size = new Size(94, 29);
            deleteButton.TabIndex = 4;
            deleteButton.Text = "Delete";
            deleteButton.UseVisualStyleBackColor = true;
            deleteButton.Click += deleteButton_Click;
            // 
            // label1
            // 
            label1.Location = new Point(0, 0);
            label1.Name = "label1";
            label1.Size = new Size(100, 23);
            label1.TabIndex = 7;
            // 
            // Incarcare
            // 
            Incarcare.Location = new Point(244, 12);
            Incarcare.Name = "Incarcare";
            Incarcare.Size = new Size(123, 29);
            Incarcare.TabIndex = 11;
            Incarcare.Text = "Incarcare Date";
            Incarcare.UseVisualStyleBackColor = true;
            Incarcare.Click += Incarcare_Click;
            // 
            // buttonGenereazaTextBox
            // 
            buttonGenereazaTextBox.Location = new Point(896, 12);
            buttonGenereazaTextBox.Name = "buttonGenereazaTextBox";
            buttonGenereazaTextBox.Size = new Size(229, 29);
            buttonGenereazaTextBox.TabIndex = 12;
            buttonGenereazaTextBox.Text = "Genreaza text boxes";
            buttonGenereazaTextBox.UseVisualStyleBackColor = true;
            buttonGenereazaTextBox.Click += genereazaTextBox_Click;
            // 
            // panelTextBoxes
            // 
            panelTextBoxes.Location = new Point(726, 272);
            panelTextBoxes.Name = "panelTextBoxes";
            panelTextBoxes.Size = new Size(539, 183);
            panelTextBoxes.TabIndex = 13;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1319, 532);
            Controls.Add(panelTextBoxes);
            Controls.Add(buttonGenereazaTextBox);
            Controls.Add(Incarcare);
            Controls.Add(label1);
            Controls.Add(deleteButton);
            Controls.Add(updateButton);
            Controls.Add(insertButton);
            Controls.Add(dataGridViewChild);
            Controls.Add(dataGridViewParent);
            Name = "Form1";
            Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)dataGridViewParent).EndInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewChild).EndInit();
            ResumeLayout(false);
        }

        #endregion

        private DataGridView dataGridViewParent;
        private DataGridView dataGridViewChild;
        private Button insertButton;
        private Button updateButton;
        private Button deleteButton;
        private Label label1;
        private Button Incarcare;
        private Button buttonGenereazaTextBox;
        private Panel panelTextBoxes;
    }
}
