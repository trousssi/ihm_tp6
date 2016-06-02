/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package disque;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
*
* @author laurillau
*/
public class DisqueHoraire extends JPanel {
    
    private final String [] MOIS =  { "Janvier", "Février", "Mars", "Avril", 
                                      "Mai", "Juin", "Juillet", "Août", 
                                      "Septembre", "Octobre", "Novembre", 
                                      "Décembre" };
    
    private final int [] NB_JOURS = { 31, 28, 31, 30, 31, 30,
                                      31, 31, 30, 31, 30, 31 };

    private static final int RAYON_JOUR    = 50; 
    private static final int RAYON_MOIS    = RAYON_JOUR + 100;
    private static final int RAYON_HEURES  = RAYON_MOIS + 100;
    private static final int RAYON_CONTOUR = RAYON_HEURES + 80;
    
    private static final int CENTRE_X      = RAYON_CONTOUR + 20;
    private static final int CENTRE_Y      = RAYON_CONTOUR + 20;
    
    private boolean bissextile = false;
    private int mois = 4, jour = 4, heure = 4, minutes = 4;     
    
    private Circle cJour;
    private Circle cMois;
    private Circle cHeures;
    private Circle cContour;
    
    public DisqueHoraire() {
        setBackground(Color.white);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(2 * CENTRE_X, 2 * CENTRE_Y));
        
        // Créer 4 objets Circle chargés de dessiner les cercles concentriques
        cJour = new Circle(CENTRE_X, CENTRE_Y, RAYON_JOUR, Color.BLACK);
        cMois = new Circle(CENTRE_X, CENTRE_Y, RAYON_MOIS, Color.BLACK);
        cHeures = new Circle(CENTRE_X, CENTRE_Y, RAYON_HEURES, Color.BLACK);
        cContour = new Circle(CENTRE_X, CENTRE_Y, RAYON_CONTOUR, Color.BLACK);
        // A FAIRE
    }
    
    public void paint(Graphics g) {
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                         RenderingHints.VALUE_ANTIALIAS_ON);

        // Dessiner le curseur
        afficherCurseur(g);
        
        // Dessiner les cercles concentriques        
        // A FAIRE
        this.cJour.draw(g);
        this.cMois.draw(g);
        this.cHeures.draw(g);
        this.cContour.draw(g);
        
        // Dessiner les sections pour les mois            
        afficherMois(g);            

        // Dessiner les sections pour les jours
        afficherJours(g);
                
        // Dessiner les sections pour les heures
        afficherHeures(g);
    }
    
    private void afficherMois(Graphics g) {
        TextLabel label;
        int x1, y1, x2, y2;
        int m = mois;
        
        for(int i = 0; i < 12; i++) {
            // Calcul de l'angle en degré d'abord pour simplifier (mais non nécessaire)
            //
            // La méthode toRadians permet de convertir la valeur en degré 
            // vers une valeur en radian, ce qui est nécessaire pour appliquer 
            // les fonctions cosinus et sinus
            double angle = Math.toRadians((i * 360.0) / 12);
            
            // Dessiner les sections
            g.setColor(Color.BLACK);
            
            x1 = (int) (this.CENTRE_X + (this.RAYON_MOIS * Math.cos(angle)));
            y1 = (int) (this.CENTRE_Y + (this.RAYON_MOIS * Math.sin(angle)));
            x2 = (int) (this.CENTRE_X + (this.RAYON_HEURES * Math.cos(angle)));
            y2 = (int) (this.CENTRE_Y + (this.RAYON_HEURES * Math.sin(angle)));
            g.drawLine(x1, y1, x2, y2);
            // A FAIRE

            // Afficher les mois
            g.setColor(i == 0 ? Color.RED : Color.BLACK);
            
            // A FAIRE
            String mois = MOIS[m];
            m =  (m+1) % 12;
            label = new TextLabel(mois,x1,y1,angle);
            label.draw(g, 0, 0);
        }            
    }
    
    private void afficherJours(Graphics g) {    
        // A FAIRE
     
         TextLabel label;
        int x1, y1, x2, y2;
        int m = mois;
        int j = jour;
        int nbj = NB_JOURS[m];
        if (this.bissextile && m == 1) {
            nbj = nbj+1;
        }
        for(int i = 0; i < nbj; i++) {
            // Calcul de l'angle en degré d'abord pour simplifier (mais non nécessaire)
            //
            // La méthode toRadians permet de convertir la valeur en degré 
            // vers une valeur en radian, ce qui est nécessaire pour appliquer 
            // les fonctions cosinus et sinus
            double angle = Math.toRadians((i * 360.0) / nbj);
            
            // Dessiner les sections
            g.setColor(Color.BLACK);
            
            x1 = (int) (this.CENTRE_X + (this.RAYON_JOUR * Math.cos(angle)));
            y1 = (int) (this.CENTRE_Y + (this.RAYON_JOUR * Math.sin(angle)));
            x2 = (int) (this.CENTRE_X + (this.RAYON_MOIS * Math.cos(angle)));
            y2 = (int) (this.CENTRE_Y + (this.RAYON_MOIS * Math.sin(angle)));
            g.drawLine(x1, y1, x2, y2);
            // A FAIRE

            // Afficher les mois
            g.setColor(i == 0 ? Color.RED : Color.BLACK);
            
            // A FAIRE
            
            String jour = "               " + j; 
            j = ((j+1) % nbj ) ;
            if (j ==0) {
                j = nbj;
            }
            label = new TextLabel(jour,x1,y1,angle);
            label.draw(g, 0, 0);
        }            
    }
    
    private void afficherHeures(Graphics g) {        
        // A FAIRE
           TextLabel label;
        int x1, y1, x2, y2;
        int h = heure;
        int m = minutes;
        
        if (m<15) {
            m = 0;
        }  else if (m>= 15 && m<45) {
            m = 3;
        } else {
            m =0;
            h = (h+1) % 24;
        }
        
        for(int i = 0; i < 48; i++) {
            // Calcul de l'angle en degré d'abord pour simplifier (mais non nécessaire)
            //
            // La méthode toRadians permet de convertir la valeur en degré 
            // vers une valeur en radian, ce qui est nécessaire pour appliquer 
            // les fonctions cosinus et sinus
            double angle = Math.toRadians((i * 360.0) / 48);
            
            // Dessiner les sections
            g.setColor(Color.BLACK);
            
            x1 = (int) (this.CENTRE_X + (this.RAYON_HEURES * Math.cos(angle)));
            y1 = (int) (this.CENTRE_Y + (this.RAYON_HEURES * Math.sin(angle)));
            x2 = (int) (this.CENTRE_X + (this.RAYON_CONTOUR * Math.cos(angle)));
            y2 = (int) (this.CENTRE_Y + (this.RAYON_CONTOUR * Math.sin(angle)));
            g.drawLine(x1, y1, x2, y2);
            // A FAIRE

            // Afficher les heures
            g.setColor(i == 0 ? Color.RED : Color.BLACK);
            
            // A FAIRE
            String heure_minute = h + "h" + m + "0" ;
            if (m == 3) {
                m =0;
                h = (h+1) % 24;
            } else {
                m =3;
            }
            
            
            label = new TextLabel(heure_minute,x1,y1,angle);
            label.draw(g, 0, 0);
        }            
    
    }
    
    private void afficherCurseur(Graphics g) {
        
        // Section pour les heures
        g.setColor(new Color(192,192,192,127));
        g.fillArc(20, 20, 
                  2 * RAYON_CONTOUR, 
                  2 * RAYON_CONTOUR, 
                  (int) (-360/48.0 - 3.75), (3 * 360) / 48);
        
        g.setColor(Color.WHITE);
        g.fillOval(20 + (RAYON_CONTOUR - RAYON_HEURES) , 
                   20 + (RAYON_CONTOUR - RAYON_HEURES),
                   2 * RAYON_HEURES,
                   2 * RAYON_HEURES);
        
        // Section pour le mois
        g.setColor(new Color(192,192,192,127));
        g.fillArc(20 + (RAYON_CONTOUR - RAYON_HEURES), 
                  20 + (RAYON_CONTOUR - RAYON_HEURES), 
                  2 * RAYON_HEURES, 
                  2 * RAYON_HEURES, -15, 360/12);
        
        g.setColor(Color.WHITE);
        g.fillOval(20 + (RAYON_CONTOUR - RAYON_MOIS) , 
                   20 + (RAYON_CONTOUR - RAYON_MOIS),
                   2 * RAYON_MOIS,
                   2 * RAYON_MOIS);

        // Section pour le jour
        int nbJours = NB_JOURS[mois] + ((mois == 1) && bissextile ? 1 : 0);
        g.setColor(new Color(192,192,192,127));
        g.fillArc(20 + (RAYON_CONTOUR - RAYON_MOIS), 
                  20 + (RAYON_CONTOUR - RAYON_MOIS), 
                  2 * RAYON_MOIS, 
                  2 * RAYON_MOIS, -180/nbJours, 360/nbJours);
        
        g.setColor(Color.WHITE);
        g.fillOval(20 + (RAYON_CONTOUR - RAYON_JOUR) , 
                   20 + (RAYON_CONTOUR - RAYON_JOUR),
                   2 * RAYON_JOUR,
                   2 * RAYON_JOUR);            
    }
    
    public void setDate(int mois, int jour, int heure, int minutes, boolean bis) {
        this.mois = mois;
        this.jour = jour;
        this.heure = heure;
        this.minutes = minutes;
        this.bissextile = bis;        
    }
}
