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
public enum Unite {

    JOUR("Jour"),
    DEMIJOUR("1/2 jour"),
    PIECE("Pièce"),
    PAIRE("paire"),
    PERSONNE("Personne");
    protected String value;

    Unite(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    public static List getValues() {
        return Arrays.asList(Unite.values());
    }
    @Override
    public String toString() {
        return this.value;
    }
}
