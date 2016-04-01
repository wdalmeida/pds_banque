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

    private JTextField Identifiant;
    private transient JPasswordField Password;
    private JLabel error;
    private Window fen;
    private AccessDB bdd;

    public ControllerScreenConnection(Window fen0, JTextField Identifiant0, JPasswordField password0, JLabel labelError) {
        this.Identifiant = Identifiant0;
        this.Password = password0;
        this.fen = fen0;
        error = labelError;
        bdd = AccessDB.getAccessDB();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resetAfterError();
        if (!"".equals(Identifiant.getText().trim()) && !"".equals(Password.getText().trim())) {
            System.out.println("pds_banque.Controller.ControllerScreenConnection.actionPerformed()");
            try {
                signIn();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ControllerScreenConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if ("".equals(Identifiant.getText().trim())) {
                Identifiant.requestFocus();
                Identifiant.setBorder(BorderFactory.createLineBorder(Color.RED));
            } else if ("".equals(Password.getText().trim())) {
                Password.requestFocus();
                Password.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            error.setText("Veuillez remplir tous les champs");
            error.setForeground(Color.red);
        }
    }

    public void signIn() throws NoSuchAlgorithmException {
        int idConsultant = bdd.getConnexion(Identifiant.getText(), Password.getText());
        if (idConsultant != 0) {
            this.fen.dispose();
            fen.setVisible(false);
            ScreenHome fen2 = new ScreenHome(idConsultant);
            fen2.changeScreen(Identifiant.getText(), Password.getText());
        } else {
            error.setText("Identifiant ou mot de passe invalide");
            error.setForeground(Color.red);
        }
    }

    public void resetAfterError() {
        error.setText("");
        Identifiant.setBorder(UIManager.getBorder("TextField.border"));
        Password.setBorder(UIManager.getBorder("TextField.border"));
    }
}
