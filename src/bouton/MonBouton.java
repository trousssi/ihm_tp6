/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bouton;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author laurillau
 */
public class MonBouton extends JPanel {
    
    private boolean presse = false;    // etat du bouton
    
    private int diametre;              // diametre du bouton en pixels
    
    private Observateur observateur;   // objet abonné au bouton
    
    public MonBouton(Observateur observateur) {
        
        setBackground(Color.white);
        setDoubleBuffered(true);
        
        this.observateur = observateur;
        
      
        // S'abonner aux évènements clic souris (MouseListener)
        //
        // Si le curseur est à l'intérieur du bouton :
        // => (1) changer l'état du bouton (pressé ou relevé), 
        // => (2) redessiner le bouton, 
        // => (3) notifier l'objet abonné (sorte de Listener)
        
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 Dimension dimension = getSize();
                 Point centre = new Point(dimension.width/2,dimension.height/2);
                 Point click = e.getPoint();
                 int distanceAuCentre = (int) centre.distance(click);
                 if (distanceAuCentre< diametre/2) {
                    
                    presse = !presse;
                    repaint();
                    observateur.notifier();
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
        });
        
        
    }
    
    @Override
    public void paint(Graphics g) {
        Dimension dimension = this.getSize(); // Taille de la zone de dessin
        
        // Calcul des coordonnées et du diamètre du bouton.
       
       
        if (dimension.height<dimension.width) {
        diametre = (int) (dimension.height*0.9) ;
        } else {
            this.diametre = (int) (dimension.width*0.9);
        }
        
        Point centre = new Point(dimension.width/2,dimension.height/2);
        Point coin = new Point(centre.x-(diametre/2),centre.y-(diametre/2));
        
        
        // Le bouton est un cercle contenu dans un carré tel que :
        // * Les côtés (= diamètre) ont une longueur égale à 90% de la plus
        //   petite dimension de la zone de dessin 
        //
        // * Le cercle/carré est centré dans la zone de dessin
                
        // Dessiner le bouton par un cercle pour le contour
        // et un cercle plein pour le centre :
        // Contour noir, centre rempli en vert si enfoncé, gris sinon
        
      
        g.setColor(Color.black);
        g.drawOval(coin.x,coin.y ,this.diametre, this.diametre); //Contour noir
        if (this.presse == true) {
        g.setColor(Color.green);
        } else { g.setColor(Color.GRAY);}
        
        g.fillOval(coin.x,coin.y ,this.diametre, this.diametre);
        
    }
    
    public boolean isPressed() {
        return presse;
    }
}
