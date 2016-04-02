/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.View;

import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JFrame;
import pds_banque.Controller.ControllerScreenCreateCust;
import pds_banque.Model.AccessDB;
import pds_banque.Model.ModifiedFocusPolicy;

/**
 *
 * @author florent
 */
public class ScreenCreateCust extends JFrame {

    private AccessDB db;
    private ArrayList<String> choiceStatus;
    private int idC;

    /**
     * Creates new form ScreenCreateCust
     *
     * @param idC0
     * @throws java.sql.SQLException
     */
    public ScreenCreateCust(int idC0) throws SQLException {
        initComponents();
        //System.out.println(Arrays.toString(jPanel1.getComponents()));
        Component[] allFields = jPanel1.getComponents();
        ArrayList<String> fieldNames = new ArrayList<>();
        ArrayList<Component> fieldList = new ArrayList<>();
        int i = 1;
        for (Component field : allFields) {
            if (field.getName() != null) {
                fieldNames.add(field.getName());
                i++;
            }
        }
        fieldNames.sort(Comparator.naturalOrder());
        for (int y = 0; y < fieldNames.size(); y++) {
            for (Component field : allFields) {
                if (fieldNames.get(y).equals(field.getName()) && y != 3) {
                    //System.out.println(fieldNames.get(y) + "-" + field.getName());
                    fieldList.add(field);
                }
            }
        }
        this.idC = idC0;
        this.db = AccessDB.getAccessDB();
        this.choiceStatus = db.getStatus();
        choiceStatus.stream().forEach((choiceStatu) -> {
            status.addItem(choiceStatu);
        });
        btnSubmit.addActionListener(new ControllerScreenCreateCust(this, title, lastName, firstName, birthday, nationality, phoneNumber, email, owner, salary, status, street, city, postalCode, idC0, btnSubmit, btnBack));
        btnBack.addActionListener(new ControllerScreenCreateCust(this, idC, btnBack, btnSubmit));

        setFocusTraversalPolicy(new ModifiedFocusPolicy(fieldList));
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        panel1 = new java.awt.Panel();
        lblTitle = new javax.swing.JLabel();
        title = new javax.swing.JComboBox();
        lblLN = new javax.swing.JLabel();
        lastName = new javax.swing.JTextField();
        lblFN = new javax.swing.JLabel();
        firstName = new javax.swing.JTextField();
        lblBirth = new javax.swing.JLabel();
        birthday = new org.jdesktop.swingx.JXDatePicker();
        lblNationality = new javax.swing.JLabel();
        nationality = new javax.swing.JTextField();
        lblPN = new javax.swing.JLabel();
        phoneNumber = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        panel2 = new java.awt.Panel();
        lblOwner = new javax.swing.JLabel();
        owner = new javax.swing.JCheckBox();
        lblSalary = new javax.swing.JLabel();
        salary = new javax.swing.JTextField();
        lblStatut = new javax.swing.JLabel();
        status = new javax.swing.JComboBox();
        lblStreet = new javax.swing.JLabel();
        street = new javax.swing.JTextField();
        lblCity = new javax.swing.JLabel();
        city = new javax.swing.JTextField();
        lblPC = new javax.swing.JLabel();
        postalCode = new javax.swing.JTextField();
        lblCreationClient = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(400, 100));

        btnBack.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnBack.setText("Retour");
        btnBack.setName("14btnBack"); // NOI18N

        btnSubmit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSubmit.setText("Valider");
        btnSubmit.setName("15BtnSubmit"); // NOI18N

        panel1.setBackground(new java.awt.Color(240, 240, 240));

        lblTitle.setText("Civilité *");

        title.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "selectionner", "M", "Mme", "Autre" }));
        title.setName("01title"); // NOI18N

        lblLN.setText("Nom *");

        lastName.setName("02lastName"); // NOI18N

        lblFN.setText("Prenom *");

        firstName.setName("03firstName"); // NOI18N

        lblBirth.setText("Date de Naissance *");

        birthday.setName("04birthday"); // NOI18N

        lblNationality.setText("Nationalité *");

        nationality.setName("05nationality"); // NOI18N

        lblPN.setText("Telephone *");

        phoneNumber.setName("06phoneNumber"); // NOI18N

        lblEmail.setText("Email *");

        email.setName("07email"); // NOI18N

        jLabel2.setForeground(new java.awt.Color(200, 0, 0));
        jLabel2.setText("* Champs obligatoires");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle)
                            .addComponent(lblLN)
                            .addComponent(lblFN)
                            .addComponent(lblBirth)
                            .addComponent(lblNationality)
                            .addComponent(lblPN)
                            .addComponent(lblEmail))
                        .addGap(28, 28, 28)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email)
                            .addComponent(nationality)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lastName)
                                    .addComponent(firstName)
                                    .addComponent(title, 0, 100, Short.MAX_VALUE)
                                    .addComponent(birthday, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(phoneNumber))
                        .addGap(195, 195, 195))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLN)
                    .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFN)
                    .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBirth)
                    .addComponent(birthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNationality)
                    .addComponent(nationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumber))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        panel2.setBackground(new java.awt.Color(240, 240, 240));

        lblOwner.setText("Proprietaire");

        owner.setName("08owner"); // NOI18N

        lblSalary.setText("Salaire *");

        salary.setToolTipText("");
        salary.setName("09salary"); // NOI18N

        lblStatut.setText("Statut *");

        status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "selectionner" }));
        status.setName("10status"); // NOI18N

        lblStreet.setText("Rue *");

        street.setName("11street"); // NOI18N

        lblCity.setText("Ville *");

        city.setName("12city"); // NOI18N

        lblPC.setText("Code Postal *");

        postalCode.setName("13postalCode"); // NOI18N

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOwner)
                    .addComponent(lblSalary)
                    .addComponent(lblStatut)
                    .addComponent(lblStreet)
                    .addComponent(lblCity)
                    .addComponent(lblPC))
                .addGap(75, 75, 75)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(owner)
                        .addComponent(salary)
                        .addComponent(status, 0, 100, Short.MAX_VALUE)
                        .addComponent(street))
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(city, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(postalCode)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(owner)
                    .addComponent(lblOwner))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSalary)
                    .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatut)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStreet)
                    .addComponent(street, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCity)
                    .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPC)
                    .addComponent(postalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblCreationClient.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblCreationClient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCreationClient.setText("Création Client");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
            .addComponent(lblCreationClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblCreationClient, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker birthday;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JTextField city;
    private javax.swing.JTextField email;
    private javax.swing.JTextField firstName;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lastName;
    private javax.swing.JLabel lblBirth;
    private javax.swing.JLabel lblCity;
    private javax.swing.JLabel lblCreationClient;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFN;
    private javax.swing.JLabel lblLN;
    private javax.swing.JLabel lblNationality;
    private javax.swing.JLabel lblOwner;
    private javax.swing.JLabel lblPC;
    private javax.swing.JLabel lblPN;
    private javax.swing.JLabel lblSalary;
    private javax.swing.JLabel lblStatut;
    private javax.swing.JLabel lblStreet;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField nationality;
    private javax.swing.JCheckBox owner;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JTextField postalCode;
    private javax.swing.JTextField salary;
    private javax.swing.JComboBox status;
    private javax.swing.JTextField street;
    private javax.swing.JComboBox title;
    // End of variables declaration//GEN-END:variables
}
