/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package disque;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Disque extends JPanel {
    private GregorianCalendar calendar = new GregorianCalendar();
    private DisqueHoraire disque; 
    private JPanel panelBouton;
    private JButton jourS;
    private JButton jourP;
    private JButton moisS;
    private JButton moisP;
    private JButton heureS;
    private JButton heureP;
    
    
    
    public Disque() {
       
       
        disque = new DisqueHoraire();
        this.setLayout(new BorderLayout());
        
        this.add(disque,BorderLayout.CENTER);
        setDate();
        
        this.add(this.panelBouton = new JPanel(),BorderLayout.SOUTH);
        // (1) Creer les boutons pour controler le disque horaire
        // (2) Creer l'interface pour ajouter les boutons et le disque
        // (3) S'abonner aux boutons pour controler la date : utiliser
        // les méthodes ci-dessous
        this.jourS = new JButton("jour suivant");
        this.panelBouton.add(this.jourS);
        
        this.jourS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               jourSuivant();
               resize();
               disque.repaint();
            }
        });
        
        this.jourP = new JButton("jour précedent");
        this.panelBouton.add(this.jourP);
        
        this.jourP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               jourPrecedent();
               resize();
               disque.repaint();
            }
        });
        
        this.moisS = new JButton("mois suivant");
        this.panelBouton.add(this.moisS);
        
        this.moisS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               moisSuivant();
               resize();
               disque.repaint();
            }
        });
        
        this.moisP = new JButton("mois précedent");
        this.panelBouton.add(this.moisP);
        
        this.moisP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               moisPrecedent();
               resize();
               disque.repaint();
            }
        });
        this.heureS = new JButton("heure suivante");
        this.panelBouton.add(this.heureS);
        
        this.heureS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               heureSuivante();
               resize();
               disque.repaint();
               
            }
        });
        
        this.heureP = new JButton("heure précedente");
        this.panelBouton.add(this.heureP);
        
        this.heureP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               heurePrecedente();
               resize();
               disque.repaint();
            }
        });
        
    }
    
    private void moisSuivant() { calendar.add(Calendar.MONTH, 1); setDate(); }
    private void moisPrecedent() { calendar.add(Calendar.MONTH, -1); setDate(); }
    private void heureSuivante() { calendar.add(Calendar.HOUR_OF_DAY, 1); setDate(); }
    private void heurePrecedente() { calendar.add(Calendar.HOUR_OF_DAY, -1); setDate(); }
    private void jourSuivant() { calendar.add(Calendar.DAY_OF_MONTH, 1); setDate(); }
    private void jourPrecedent() { calendar.add(Calendar.DAY_OF_MONTH, -1); setDate(); }
    
    private void resize() {
        Dimension dim = this.getSize();
        this.setSize(dim.width+1,dim.height+1);
        this.setSize(dim);
        
    }
    
    private void setDate() {
        boolean bissextile;
        int annee, mois, jour, heure, minutes;
        
        annee = calendar.get(Calendar.YEAR);
        mois = calendar.get(Calendar.MONTH);
        jour = calendar.get(Calendar.DAY_OF_MONTH);
        heure = calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE);
        bissextile = calendar.isLeapYear(annee);

        disque.setDate(mois, jour, heure, minutes, bissextile);
    }
    
    public static void main(String [] args) {        
       JFrame frame = new JFrame();
       frame.setTitle("Disque horaire");
       frame.setSize(900, 800);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(new Disque());
       frame.setVisible(true);
    }
}