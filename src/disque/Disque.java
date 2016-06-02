/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package disque;

import java.util.*;
import javax.swing.*;

public class Disque extends JPanel {
    private GregorianCalendar calendar = new GregorianCalendar();
    private final DisqueHoraire disque; // enlever quen fenetre
    
    public Disque() {       
        disque = new DisqueHoraire();
        this.add(disque);
        setDate();
        
        // (1) Creer les boutons pour controler le disque horaire
        // (2) Creer l'interface pour ajouter les boutons et le disque
        // (3) S'abonner aux boutons pour controler la date : utiliser
        // les méthodes ci-dessous
    }
    
    private void moisSuivant() { calendar.add(Calendar.MONTH, 1); setDate(); }
    private void moisPrecedent() { calendar.add(Calendar.MONTH, -1); setDate(); }
    private void heureSuivante() { calendar.add(Calendar.HOUR_OF_DAY, 1); setDate(); }
    private void heurePrecedente() { calendar.add(Calendar.HOUR_OF_DAY, -1); setDate(); }
    private void jourSuivant() { calendar.add(Calendar.DAY_OF_MONTH, 1); setDate(); }
    private void jourPrecedent() { calendar.add(Calendar.DAY_OF_MONTH, -1); setDate(); }
    
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