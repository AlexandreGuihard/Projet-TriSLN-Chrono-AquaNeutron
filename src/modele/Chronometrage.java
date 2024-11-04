package src.modele;

import src.modele.Exceptions.ChronoNotStartedException;

/**
 * La classe Chronometrage permet de mesurer le temps écoulé entre le démarrage
 * et l'arrêt d'un chronomètre.
 */
public class Chronometrage {
    private double tempsDeparts;
    private double duree;

    /**
     * Constructeur de la classe Chronometrage.
     * Initialise le chronomètre avec un temps de départ à 0.
     */
    public Chronometrage() {
        this.tempsDeparts = 0;
        this.duree = 0;
    }

    /**
     * Démarre le chronomètre en enregistrant le temps actuel en millisecondes.
     */
    public void demarrer() {
        this.tempsDeparts = System.currentTimeMillis();
    }

    /**
     * Stoppe le chronomètre et calcule la durée écoulée en secondes.
     * 
     * @throws ChronoNotStartedException si le chronomètre n'a pas été démarré avant
     *                                   d'être stoppé.
     */
    public void stopper() throws ChronoNotStartedException {
        if (tempsDeparts != 0) {
            this.duree = (System.currentTimeMillis() - this.tempsDeparts) / 1000;
        } else {
            throw new ChronoNotStartedException();
        }
    }

    /**
     * Retourne la durée écoulée entre le démarrage et l'arrêt du chronomètre.
     * 
     * @return la durée en secondes.
     */
    public double getDuree() {
        return this.duree;
    }
}
