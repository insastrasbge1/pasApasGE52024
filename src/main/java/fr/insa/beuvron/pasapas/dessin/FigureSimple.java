/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.dessin;

import java.awt.Color;

/**
 *
 * @author fdebertranddeb01
 */
public abstract class FigureSimple extends Figure{
    
    private Color couleur;

    public FigureSimple(Color couleur) {
        this.couleur = couleur;
    }
    
    public FigureSimple() {
        this(Color.BLACK);
    }

    /**
     * @return the couleur
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
}
