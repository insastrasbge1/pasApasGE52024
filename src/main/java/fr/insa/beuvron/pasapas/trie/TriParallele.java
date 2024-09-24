/*
 Copyright 2000-2014 Francois de Bertrand de Beuvron

 This file is part of CoursBeuvron.

 CoursBeuvron is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 CoursBeuvron is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with CoursBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.beuvron.pasapas.trie;

import fr.insa.beuvron.pasapas.Utils;
import java.awt.Toolkit;

/**
 *
 * @author francois
 */
public class TriParallele extends Thread{

    /** todoDoc. */
    public static int SIZE = 100000000;

    /** todoDoc. */
    public static int BMAX = 5000;
    
    private int[] tab;
    private int min;
    private int max;

    public TriParallele(int[] tab, int min, int max) {
        this.tab = tab;
        this.min = min;
        this.max = max;
    }
    
    

    /**
     *
     * @param tab
     */
    public static void tri(int[] tab) {
        TriParallele tout = new TriParallele(tab, 0, tab.length - 1);
        tout.run();
// ou         
//        tout.start();
//        Utils.joinSansInterrupt(tout);
    }

    /**
     *
     * @param tab
     * @param min
     * @param max
     */
    public void run() {
//        System.out.println("sorting " + Arrays.toString(tab) + " between " + min + " and " + max);
//        if (max - min < Math.max(2,tab.length / Runtime.getRuntime().availableProcessors())) {
        if (max - min < 10000000) {
            TriSequentiel.triBorne(tab, min, max);
        } else {
            int milieu = (max + min) / 2;
            TriParallele debut = new TriParallele(tab, min, milieu);
            TriParallele fin = new TriParallele(tab, milieu+1, max);
            debut.start();
            fin.start();
            Utils.joinSansInterrupt(debut);
            Utils.joinSansInterrupt(fin);
            fusion(tab, min, max);
        }
//        System.out.println("sorted " + Arrays.toString(tab) + " between " + min + " and " + max);
    }

    private static void fusion(int[] tab, int min, int max) {
        int[] fu = new int[max - min + 1];
        int milieu = (max + min) / 2;
        int cur1 = min;
        int cur2 = milieu + 1;
        for (int i = 0; i < fu.length; i++) {
            if (cur1 > milieu) {
                // plus rien dans la première partie
                fu[i] = tab[cur2];
                cur2++;
            } else if (cur2 > max) {
                // plus rien dans la seconde partie
                fu[i] = tab[cur1];
                cur1++;
            } else if (tab[cur1] <= tab[cur2]) {
                fu[i] = tab[cur1];
                cur1++;
            } else {
                fu[i] = tab[cur2];
                cur2++;
            }
        }
        for (int i = 0; i < fu.length; i++) {
            tab[min + i] = fu[i];
        }
    }

    /**
     *
     * @param tab
     * @return
     */
    public static boolean testTrie(int[] tab) {
        boolean res = true;
        int i = 0;
        while (res && i < tab.length - 1) {
            res = tab[i] <= tab[i + 1];
            i++;
        }
        return res;
    }

    /**
     *
     * @param size
     * @param borneMax
     * @return
     */
    public static int[] tabAlea(int size, int borneMax) {
        int[] res = new int[size];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * borneMax);
        }
        return res;
    }

    /**
     *
     * @param size
     * @param bmax
     */
    public static void test(int size,int bmax) {
        int[] t = TriParallele.tabAlea(size,bmax);
        System.out.println("trie tableau taille : " + size
                + " (0 <= e < " + bmax + ")");
        long deb = System.currentTimeMillis();
        tri(t);
        long duree = System.currentTimeMillis() - deb;
        System.out.println("test : " + TriParallele.testTrie(t));
        System.out.println("in " + duree + " ms");

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        test(1000000000,TriParallele.BMAX);
    }

}
