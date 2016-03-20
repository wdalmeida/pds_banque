package pds_banque.Controller;

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
    private JTextField Password;
    private Window fen;
    private AccessDB bdd;

    public ControllerScreenConnection(Window fen0, JTextField Identifiant0, JTextField password0) {
        this.Identifiant = Identifiant0;
        this.Password = password0;
        this.fen = fen0;
        bdd = AccessDB.getAccessDB();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int idConsultant;
        try {
            idConsultant = bdd.getConnexion(Identifiant.getText(), Password.getText());
            if (idConsultant == 1) {
                this.fen.dispose();
                fen.setVisible(false);
                ScreenHome fen2 = new ScreenHome(idConsultant);
                fen2.changeScreen(Identifiant.getText(),Password.getText());
            } else {
                System.out.println("Connection failed");
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerScreenConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
