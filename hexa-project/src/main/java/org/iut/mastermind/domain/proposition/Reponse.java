package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();


    public Reponse(String mot) {
        this.motSecret = mot;
    }

    // on récupère la lettre à la position dans le résultat
    public Lettre lettre(int position) {

        return position<resultat.size()? resultat.get(position) : null;
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        resultat.clear();
        IntStream.range(0,essai.length())
                .forEach(i -> resultat.add(evaluationCaractere(essai.charAt(i),i)));
    }

    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        return resultat.stream().allMatch(lettre -> lettre.equals(Lettre.PLACEE));
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    // renvoie le statut du caractère
    private Lettre evaluationCaractere(char carCourant, int position) {
        if(estPlace(carCourant,position)){
            return Lettre.PLACEE;
        }
        if(estPresent(carCourant)){
            return Lettre.NON_PLACEE;
        }
        return Lettre.INCORRECTE;
    }

    // le caractère est présent dans le mot secret
    private boolean estPresent(char carCourant) {
        return motSecret.contains(carCourant+"");
    }

    // le caractère est placé dans le mot secret
    private boolean estPlace(char carCourant, int position) {
        return motSecret.charAt(position) == carCourant;
    }
}
