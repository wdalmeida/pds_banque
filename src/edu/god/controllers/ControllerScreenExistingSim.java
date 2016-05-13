/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.models.AccessDB;
import edu.god.views.ScreenExistingSim;
import edu.god.views.ScreenLoanSim;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author Warren
 */
public class ControllerScreenExistingSim implements ActionListener, MouseListener {
    
    private final ScreenExistingSim ses;
    private final JLabel title;
    private final JTable tblSim;
    private final JButton next;
    private final JButton cancel;
    private final AccessDB db;
    private String idCust;
    private int idCon;
    
    public ControllerScreenExistingSim(ScreenExistingSim ses, JLabel title, JTable Simtab, JButton next, JButton cancel,String idCu,int idCo) {
        this.ses = ses;
        this.title = title;
        this.tblSim = Simtab;
        this.next = next;
        this.cancel = cancel;
        this.db = AccessDB.getAccessDB();
        idCon=idCo;
        idCust=idCu;
        System.out.println("test4");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            ses.dispose();
            try {
                ScreenLoanSim newWindow = new ScreenLoanSim(idCon, tblSim.getModel().getValueAt(tblSim.getSelectedRow(), 0).toString());
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(ControllerScreenExistingSim.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
