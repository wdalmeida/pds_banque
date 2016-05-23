/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.views.ScreenCompareSimulation;
import edu.god.views.ScreenExistingSim;
import edu.god.views.ScreenHome;
import edu.god.views.ScreenLoanSim;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
public class ControllerScreenExistingSim implements ActionListener, MouseListener, FocusListener {

    private final ScreenExistingSim ses;
    private final JTable tblSim;
    private final JButton btnModified;
    private final JButton btnCancel;
    private final JButton btnCreate;
    private final JButton btnCompareSimulation;
    private final String idCust;
    private final int idCon;

    /**
     *Default constructor
     * 
     * @param ses ScreenExistingSim
     * @param simTab JTable
     * @param next JButton
     * @param cancel JButton
     * @param compare JButton
     * @param create JButton
     * @param idCu String
     * @param idCo int
     */
    public ControllerScreenExistingSim(ScreenExistingSim ses, JTable simTab, JButton next, JButton cancel, JButton compare, JButton create, String idCu, int idCo) {
        this.ses = ses;
        this.tblSim = simTab;
        this.btnModified = next;
        this.btnCancel = cancel;
        this.btnCompareSimulation = compare;
        idCon = idCo;
        idCust = idCu;
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
                ScreenLoanSim newWindow = new ScreenLoanSim(idCon, idCust, false);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(ControllerScreenExistingSim.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnCompareSimulation) {
            try {
                ScreenCompareSimulation scs = new ScreenCompareSimulation(Integer.parseInt(idCust)); // create new JFrame ScreenCompareSimulation
            } catch (SQLException ex) {
                Logger.getLogger(ControllerScreenExistingSim.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        ses.getRootPane().setDefaultButton((JButton) e.getSource());
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
}
