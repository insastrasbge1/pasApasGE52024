/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.dessin;

/**
 *
 * @author fdebertranddeb01
 */
public class Test {
    
    public static void main(String[] args) {
        Point p = new Point(2,3);
        Segment s = new Segment(new Point(0,0),p );
        System.out.println("s = " + s);
        System.out.println("min point = " + p.minX());
    }
    
}
