/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import pds_banque.Model.AccessDB;
import pds_banque.View.ScreenDeleteCust;
import pds_banque.View.ScreenHome;

/**
 *
 * @author florent
 */
public class ControllerScreenDeleteCust implements ActionListener {
    private AccessDB bdd;
    private ScreenDeleteCust screendeletecust;
    private JComboBox allCustomer;
    private int idConsultant;
    private JButton btnSubmit;
    private JButton btnBack;
    
    public ControllerScreenDeleteCust(ScreenDeleteCust sdc0, int idConsultant0, JButton back, JButton submit) {
        this.btnSubmit = submit;
        this.btnBack = back;
        this.screendeletecust = sdc0;
        this.idConsultant = idConsultant0;
    }
    public ControllerScreenDeleteCust(ScreenDeleteCust sdc0,JComboBox allCustomer0,int idC0, JButton btnSubmit0, JButton btnBack0) {
        this.btnSubmit = btnSubmit0;
        this.btnBack = btnBack0;
        this.screendeletecust = sdc0;
        this.idConsultant = idC0;
        this.bdd = AccessDB.getAccessDB();
        this.allCustomer = allCustomer0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int res;
        if (e.getSource() == btnSubmit) {
            try {
                int idCustomer=Integer.parseInt(allCustomer.getSelectedItem().toString());
                res = bdd.DeleteCustomer(idCustomer);
                if (res == 1) {
                    this.screendeletecust.dispose();
                    screendeletecust.setVisible(false);
                    ScreenHome fen2 = new ScreenHome(idConsultant);
                } else {
                    System.out.println("insertio ko");
                }
                /*  }else {
                 // here mesagge for wrong format of birthday or salary or phonenumber or Postal code
                 }*/
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ControllerScreenCreateCust.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnBack) {
            this.screendeletecust.dispose();
            screendeletecust.setVisible(false);
            ScreenHome screenHome = new ScreenHome(idConsultant);
        }
    }
}
