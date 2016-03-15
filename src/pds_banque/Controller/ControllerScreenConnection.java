package pds_banque.Controller;

import pds_banque.Model.AccesBDD;
import java.awt.event.*;
import javax.swing.*;
import pds_banque.View.ScreenHome;
import pds_banque.View.Fenetre;

public class ControllerScreenConnection implements ActionListener {

    private JTextField Identifiant;
    private JTextField MotDePasse;
    private Fenetre fen;
    private AccesBDD bdd;

    public ControllerScreenConnection(Fenetre fen0, JTextField Identifiant0, JTextField MotDePasse0) {
        this.Identifiant = Identifiant0;
        this.MotDePasse = MotDePasse0;
        this.fen = fen0;
        bdd = AccesBDD.getAccesBDD();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AccesBDD bdd = AccesBDD.getAccesBDD();
        int validation = bdd.getConnexion(Identifiant.getText(), MotDePasse.getText());
        if (validation == 1) {
            this.fen.dispose();
            fen.setVisible(false);
            ScreenHome fen2 = new ScreenHome();
            fen2.changementEcran(Identifiant.getText());
        } else {
            System.out.println("Connexion échoue");
        }
    }
}
