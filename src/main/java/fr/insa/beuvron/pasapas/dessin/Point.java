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
public class Point extends FigureSimple {
    
    private double px;
    private double py;

    public Point(double px, double py) {
        super(Color.BLUE);
        this.px = px;
        this.py = py;
    }

    @Override
    public String toString() {
        return "(" + px + "," + py + " ; couleur : " + this.getCouleur() + ")";
    }
    
    @Override
    public double minX() {
        return this.px;
    }

    /**
     * @return the px
     */
    public double getPx() {
        return px;
    }

    /**
     * @param px the px to set
     */
    public void setPx(double px) {
        this.px = px;
    }

    /**
     * @return the py
     */
    public double getPy() {
        return py;
    }

    /**
     * @param py the py to set
     */
    public void setPy(double py) {
        this.py = py;
    }
    
    
    
}
