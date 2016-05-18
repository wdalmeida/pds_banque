/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.models.AccessDB;
import edu.god.views.ScreenExistingSim;
import edu.god.views.ScreenHome;
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
import javax.swing.JTable;

/**
 *
 * @author Warren
 */
public class ControllerScreenExistingSim implements ActionListener, MouseListener {

    private final ScreenExistingSim ses;
    private final JTable tblSim;
    private final JButton btnModified;
    private final JButton btnCancel;
    private final JButton btnCompare;
    private final JButton btnCreate;
    private final AccessDB db;
    private final String idCust;
    private final int idCon;

    public ControllerScreenExistingSim(ScreenExistingSim ses, JTable Simtab, JButton next, JButton cancel, JButton compare, JButton create, String idCu, int idCo) {
        this.ses = ses;
        this.tblSim = Simtab;
        this.btnModified = next;
        this.btnCancel = cancel;
        this.db = AccessDB.getAccessDB();
        idCon = idCo;
        idCust = idCu;
        btnCompare = compare;
        btnCreate = create;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnModified) {
            ses.dispose();
            try {
                ScreenLoanSim newWindow = new ScreenLoanSim(idCon, tblSim.getModel().getValueAt(tblSim.getSelectedRow(), 0).toString(), true);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(ControllerScreenExistingSim.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnCancel) {
            ses.dispose();
            ScreenHome newWindow = new ScreenHome(idCon);

        } else if (e.getSource() == btnCreate) {
            ses.dispose();
            try {
                ScreenLoanSim newWindow = new ScreenLoanSim(idCon, tblSim.getModel().getValueAt(tblSim.getSelectedRow(), 0).toString(), false);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(ControllerScreenExistingSim.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (e.getSource() == btnCompare) {
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            ses.dispose();
            try {
                ScreenLoanSim newWindow = new ScreenLoanSim(idCon, tblSim.getModel().getValueAt(tblSim.getSelectedRow(), 0).toString(), true);
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
