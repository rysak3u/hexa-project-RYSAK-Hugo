package org.iut.mastermind.domain.proposition;

public class MotSecret {
    private final String motSecret;

    public MotSecret(String mot) {
        this.motSecret = mot;
    }

    // on retourne le résultat de la comparaison
    // du mot essayé avec le mot secret
    public Reponse compareProposition(String essai) {
        var response =  new Reponse(motSecret);
        response.compare(essai);
        return response;
    }
}
