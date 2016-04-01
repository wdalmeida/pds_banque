package pds_banque.Controller;

import java.awt.Color;
import pds_banque.Model.AccessDB;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import pds_banque.View.ScreenHome;
import pds_banque.View.Window;

public class ControllerScreenConnection implements ActionListener {

    private JTextField identifiant;
    private transient JPasswordField password;
    private JLabel lblError;
    private Window fen;
    private AccessDB bdd;

    public ControllerScreenConnection(Window fen0, JTextField identifiant0, JPasswordField password0, JLabel labelError) {
        this.identifiant = identifiant0;
        this.password = password0;
        this.fen = fen0;
        lblError = labelError;
        bdd = AccessDB.getAccessDB();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resetAfterError();
        if (!"".equals(identifiant.getText().trim()) && !"".equals(password.getText().trim())) {
            System.out.println("pds_banque.Controller.ControllerScreenConnection.actionPerformed()");
            try {
                signIn();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ControllerScreenConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if ("".equals(identifiant.getText().trim())) {
                identifiant.requestFocus();
                identifiant.setBorder(BorderFactory.createLineBorder(Color.RED));
            } else if ("".equals(password.getText().trim())) {
                password.requestFocus();
                password.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            lblError.setText("Veuillez remplir tous les champs");
            lblError.setForeground(Color.red);
        }
    }

    public void signIn() throws NoSuchAlgorithmException {
        int idConsultant = bdd.getConnexion(identifiant.getText(), password.getText());
        if (idConsultant != 0) {
            this.fen.dispose();
            fen.setVisible(false);
            ScreenHome fen2 = new ScreenHome(idConsultant);
            fen2.changeScreen(identifiant.getText(), password.getText());
        } else {
            lblError.setText("identifiant ou mot de passe invalide");
            lblError.setForeground(Color.red);
        }
    }

    public void resetAfterError() {
        lblError.setText("");
        identifiant.setBorder(UIManager.getBorder("TextField.border"));
        password.setBorder(UIManager.getBorder("TextField.border"));
    }
}
