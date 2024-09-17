/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fr.insa.beuvron.pasapas.jeux;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author fdebertranddeb01
 */
public interface Jeux<C extends Coup,S extends Situation> {
    
    public S situationInitiale();
    
    public List<C> coupsPossibles(S situationActuelle);
    
    public S jouer(S situationActuelle,C coup,Joueur j);
    
    public Statut statut(S situation);
    
    default public List<C> jouePartie(boolean J1Humain,boolean J2Humain) {
        Joueur curJ = Joueur.J1;
        S curS = this.situationInitiale();
        List<C> res = new ArrayList<>();
        while (this.statut(curS) == Statut.EnCours) {
            List<C> coups = this.coupsPossibles(curS);
            C coupChoisi = coups.get((int) (Math.random()*coups.size()));
            res.add(coupChoisi);
            curS = this.jouer(curS, coupChoisi,curJ);
            curJ = curJ.adversaire();
        }
        return res;
        
    }
    
}
