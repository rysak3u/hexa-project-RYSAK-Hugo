package org.iut.mastermind.domain.partie;

public record Joueur(String nom) {


    public String getNom() {
        return nom;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if(getClass() != o.getClass()){
            return false;
        }
        Joueur j = (Joueur) o;
        return this.nom.equals(j.nom());
    }

}
