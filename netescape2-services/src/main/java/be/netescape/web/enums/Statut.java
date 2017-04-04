/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.enums;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author carloskabeya
 */
public enum Statut {

    CREER("Attente"),
    ACCEPTER("Acceptée"),
    VALIDER("Validée"),
    ENVOYER("Envoyée"),
    REALISER("Réalisée"),
    ANNULER("Annulée"),
    EXPIRER("Expirée");
    protected String value;

    Statut(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static List getValues() {
        return Arrays.asList(Statut.values());
    }

    @Override
    public String toString() {
        return this.value;
    }
}
