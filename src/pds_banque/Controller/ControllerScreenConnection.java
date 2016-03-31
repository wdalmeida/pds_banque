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
    private transient JTextField Password;
    private JLabel error;
    private Window fen;
    private AccessDB bdd;

    public ControllerScreenConnection(Window fen0, JTextField Identifiant0, JTextField password0, JLabel labelError) {
        this.Identifiant = Identifiant0;
        this.Password = password0;
        this.fen = fen0;
        error = labelError;
        bdd = AccessDB.getAccessDB();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!"".equals(Identifiant.getText().trim()) && !"".equals(Password.getText().trim())) {
            System.out.println("pds_banque.Controller.ControllerScreenConnection.actionPerformed()");
            try {
                signIn();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ControllerScreenConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            if("".equals(Identifiant.getText().trim())){
                Identifiant.requestFocus();
            }
            else if("".equals(Password.getText().trim())){
                Password.requestFocus();
            }
            error.setText("Veuillez remplir tous les champs");
            error.setForeground(Color.red);
        }
    }

    public void signIn() throws NoSuchAlgorithmException{
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
}
