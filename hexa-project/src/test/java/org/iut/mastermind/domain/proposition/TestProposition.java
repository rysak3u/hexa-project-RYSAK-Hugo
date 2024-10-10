package org.iut.mastermind.domain.proposition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.iut.mastermind.domain.proposition.Lettre.*;

@DisplayName("Test sur la proposition:")
public class TestProposition {

    @Test
    @DisplayName("une lettre est incorrecte")
    public void casLettreIncorrecte() {
        var mot = "S";
        var reponse = new Reponse(mot);
        reponse.compare("Z");
        assertResultat(reponse, INCORRECTE);
    }

    @Test
    @DisplayName("une lettre est placée")
    public void casLettrePlacee() {
        var mot = "S";
        var reponse = new Reponse(mot);
        reponse.compare("S");
        assertResultat(reponse, PLACEE);
    }

    @Test
    @DisplayName("une lettre est incorrecte, une non placée")
    public void casDeuxiemeLettreMalPlacee() {
        var mot = "SO";
        var reponse = new Reponse(mot);
        reponse.compare("ZS");
        assertResultat(reponse,  INCORRECTE, NON_PLACEE);
    }

    @Test
    @DisplayName("une lettre est incorrecte, non placée, placée")
    public void casCombinaisons() {
        var mot = "SOL";
        var reponse = new Reponse(mot);
        reponse.compare("ZSL");
        assertResultat(reponse,  INCORRECTE, NON_PLACEE, PLACEE);
    }

    @Test
    @DisplayName("toutes les lettres sont placées")
    void casToutesLettresPlacees() {
        var mot = "SOLID";
        var reponse = new Reponse(mot);
        reponse.compare("SOLID");
        assertThat(reponse.lettresToutesPlacees()).isTrue();
    }

    @Test
    @DisplayName("la proposition n'est pas correcte")
    void casLettresIncorrectes() {
        var mot = "SOLID";
        var reponse = new Reponse(mot);
        reponse.compare("SOL*D");
        assertThat(reponse.lettresToutesPlacees()).isFalse();
    }

    @Test
    @DisplayName("vérifie la taille du résultat")
    void casAccesLettres() {
        var mot = "SOLID";
        var reponse = new Reponse(mot);
        reponse.compare("SOL*D");
        assertThat(reponse.lettresResultat()).hasSize(5);
    }


    private void assertResultat(Reponse reponse, Lettre... resultatAttendu) {
        for (int position = 0; position < resultatAttendu.length; position++) {
            Lettre attendue = resultatAttendu[position];
            assertThat(reponse.lettre(position)).isEqualTo(attendue);
        }
    }
}
