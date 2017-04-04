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
public enum Sexe {

    MASCULIN("Maculin"),
    FEMININ("Féminin"),
    MIXTE("Mixte");
    protected String value;

    Sexe(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static List getValues() {
        return Arrays.asList(Sexe.values());
    }

    @Override
    public String toString() {
        return this.value;

    }
}
