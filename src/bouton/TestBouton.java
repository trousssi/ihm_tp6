/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bouton;

import javax.swing.JFrame;

/**
 *
 * @author laurillau
 */
public class TestBouton implements Observateur {
    
    private JFrame frame;
    private MonBouton bouton;
    
    public void notifier() {
        // Traiter les notification lorsqu'il y a interaction avec le bouton :
        // Si le bouton est enfoncé : afficher "Le bouton est enfoncé"
        // Si le bouton est relevé  : afficher "Le bouton est relevé"
        if (bouton.isPressed()) {
            System.out.println("Le bouton est enfoncé");
        }
        else {
             System.out.println("Le bouton est relevé");
        }
        
    }

    public TestBouton() {
       bouton = new MonBouton(this);
       
       frame = new JFrame();
       frame.setTitle("Mon bouton");
       frame.setSize(200, 200);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(bouton);
       frame.setVisible(true);        
    }
        
    public static void main(String [] args) {        
        new TestBouton();
    }            
}
