package com.trisln.aquaneutron.modele.exceptions;
/**
 * Classe pour avertir que le chronomètre n'a pas démarré
 */
public class ChronoNotStartedException extends Exception {
    public ChronoNotStartedException(){
        super("Le chronometre n'a pas démarré");
    }
}