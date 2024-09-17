/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.jeux;

/**
 *
 * @author fdebertranddeb01
 */
public enum Joueur {
    
    J1,J2;
    
    public Joueur adversaire() {
        if (this == J1) {
            return J2;
        } else {
            return J1;
        }
    }
    
}
