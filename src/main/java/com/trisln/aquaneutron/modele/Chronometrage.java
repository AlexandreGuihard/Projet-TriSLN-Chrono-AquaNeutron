package com.trisln.aquaneutron.modele;

import com.trisln.aquaneutron.modele.Exceptions.ChronoNotStartedException;

/**
 * La classe Chronometrage permet de mesurer le temps écoulé entre le démarrage
 * et l'arrêt d'un chronomètre.
 */
public class Chronometrage {
    private double tempsDeparts;
    private double duree;
    private boolean enPause;
    private double tempsPause;
    private boolean estParti;

    /**
     * Constructeur de la classe Chronometrage.
     * Initialise le chronomètre avec un temps de départ à 0.
     */
    public Chronometrage() {
        this.tempsDeparts = 0;
        this.duree = 0;
        this.tempsPause = 0;
        this.enPause = false;
        this.estParti =false;
    }

    public boolean getEstParti(){
        return this.estParti;
    }

    /**
     * Démarre le chronomètre en enregistrant le temps actuel en millisecondes.
     */
    public void demarrer() {
        if (this.enPause) {
            double tempsReprise = System.currentTimeMillis();
            this.tempsDeparts += (tempsReprise - tempsPause);
            this.enPause = false;
            System.out.println("C'est reparti");
        } else {
            if(this.estParti){
                System.out.println("le chronomètre est déjà lancé");
            } else{
                this.tempsDeparts = System.currentTimeMillis();
                this.estParti = true;
                System.out.println("Go");
            }
        }
    }

    /**
     * Stoppe le chronomètre et calcule la durée écoulée en secondes.
     * 
     * @throws ChronoNotStartedException si le chronomètre n'a pas été démarré avant d'être stoppé.
     */
    public void stopper() throws ChronoNotStartedException {
        if (!enPause) {
            this.tempsPause = System.currentTimeMillis();
            this.enPause = true;
            System.out.println("Pause");
        } else {
            System.out.println("C'est déjà en pause");
            throw new ChronoNotStartedException();
        }
    }

    /**
     * Vérifie si le chronomètre a démarrer
     * 
     * @return true si le chronomètre est en pause et false sinon.
      * @throws ChronoNotStartedException 
      */
    public boolean estDemarrer(){
        return !this.enPause;
    }

    /**
     * Retourne la durée écoulée entre le démarrage et l'arrêt du chronomètre.
     * 
     * @return la durée en secondes.
      * @throws ChronoNotStartedException 
      */
    public double getDuree() throws ChronoNotStartedException {
        if (this.estParti) {
            if (enPause) {
                this.duree = (tempsPause - this.tempsDeparts) / 1000;
            } else {
                this.duree = (System.currentTimeMillis() - this.tempsDeparts) / 1000;
            }
        } else {
            throw new ChronoNotStartedException();
        }
        return this.duree;
    }


}