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
        title = new javax.swing.JComboBox();
        lastName = new javax.swing.JTextField();
        lblLN = new javax.swing.JLabel();
        firstName = new javax.swing.JTextField();
        lblFN = new javax.swing.JLabel();
        lblBirth = new javax.swing.JLabel();
        birthday = new org.jdesktop.swingx.JXDatePicker();
        lblNationality = new javax.swing.JLabel();
        nationality = new javax.swing.JTextField();
        street = new javax.swing.JTextField();
        lblStreet = new javax.swing.JLabel();
        city = new javax.swing.JTextField();
        lblCity = new javax.swing.JLabel();
        lblPC = new javax.swing.JLabel();
        salary = new javax.swing.JTextField();
        lblSalary = new javax.swing.JLabel();
        phoneNumber = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        postalCode = new javax.swing.JTextField();
        lblformatBirth = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        lblEmail = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        lblStatut = new javax.swing.JLabel();
        status = new javax.swing.JComboBox();
        lblOwner = new javax.swing.JLabel();
        owner = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPN = new javax.swing.JLabel();
        lblCreationClient = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(400, 100));

        title.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "selectionner", "M", "Mme", "Autre" }));
        title.setName("01title"); // NOI18N

        lastName.setName("02lastName"); // NOI18N

        lblLN.setText("Nom *");

        firstName.setName("03firstName"); // NOI18N

        lblFN.setText("Prenom *");

        lblBirth.setText("Date de Naissance *");

        birthday.setToolTipText("Format : JJ/MM/AAAA");
        birthday.setName("04birthday"); // NOI18N

        lblNationality.setText("Nationalité *");

        nationality.setName("05nationality"); // NOI18N

        street.setToolTipText("Format : N° + rue");
        street.setName("11street"); // NOI18N

        lblStreet.setText("Rue *");

        city.setName("12city"); // NOI18N

        lblCity.setText("Ville *");

        lblPC.setText("Code Postal *");

        salary.setToolTipText("Format : 5000.00");
        salary.setName("09salary"); // NOI18N

        lblSalary.setText("Salaire *");

        phoneNumber.setToolTipText("Format : 0611223344");
        phoneNumber.setName("06phoneNumber"); // NOI18N

        btnBack.setText("Retour");
        btnBack.setName("14btnBack"); // NOI18N

        postalCode.setName("13postalCode"); // NOI18N

        lblformatBirth.setForeground(new java.awt.Color(192, 192, 192));
        lblformatBirth.setText("Format : JJ/MM/AAAA");

        btnSubmit.setText("Valider");
        btnSubmit.setName("15BtnSubmit"); // NOI18N

        lblEmail.setText("Email *");

        email.setToolTipText("Format : adresse@email.fr");
        email.setName("07email"); // NOI18N

        lblTitle.setText("Civilité *");

        lblStatut.setText("Statut *");

        status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "selectionner un statut" }));
        status.setName("10status"); // NOI18N

        lblOwner.setText("Proprietaire");

        owner.setName("08owner"); // NOI18N

        jLabel1.setForeground(new java.awt.Color(192, 192, 192));
        jLabel1.setText("Format: N° voie + Rue ");

        jLabel2.setForeground(new java.awt.Color(200, 0, 0));
        jLabel2.setText("* Champs obligatoires");

        jLabel3.setText("Exemple : 1234.56");

        lblPN.setText("Telephone *");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(lblEmail)
                            .addGap(141, 141, 141)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblFN)
                                        .addComponent(lblLN)
                                        .addComponent(lblBirth)
                                        .addComponent(lblTitle))
                                    .addGap(76, 76, 76))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblNationality, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblPN, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblformatBirth)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(phoneNumber, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nationality, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lastName, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(firstName, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(birthday, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblOwner)
                        .addGap(18, 18, 18)
                        .addComponent(owner))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblSalary)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(salary)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblStatut))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPC)
                                    .addComponent(lblCity)
                                    .addComponent(lblStreet))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(city, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(postalCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                                    .addComponent(street, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(18, 18, 18)
                .addComponent(btnSubmit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(owner))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitle)
                            .addComponent(lblOwner))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLN, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFN)
                            .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(birthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBirth))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblformatBirth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNationality)
                            .addComponent(nationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSalary)
                            .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStatut)
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(street, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStreet))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCity)
                            .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPC)
                            .addComponent(postalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnSubmit))
                .addGap(41, 41, 41))
        );

        lblCreationClient.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCreationClient.setText("Création Client");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblCreationClient)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblCreationClient)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(43, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel lblformatBirth;
    private javax.swing.JTextField nationality;
    private javax.swing.JCheckBox owner;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JTextField postalCode;
    private javax.swing.JTextField salary;
    private javax.swing.JComboBox status;
    private javax.swing.JTextField street;
    private javax.swing.JComboBox title;
    // End of variables declaration//GEN-END:variables
}
