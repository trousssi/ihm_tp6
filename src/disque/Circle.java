/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package disque;

import java.awt.Color;
import java.awt.Graphics;

public class Circle {
    private int centreX, centreY, rayon;
    private Color couleur;
    
    public Circle(int centreX, int centreY, int rayon, Color couleur) {
        this.centreX = centreX;
        this.centreY = centreY;
        this.rayon = rayon;
        this.couleur = couleur;
    }
    
    public void draw(Graphics g) {
        g.setColor(couleur);
        g.drawOval(centreX - rayon, centreY - rayon, 2 * rayon, 2 * rayon);
    }
}