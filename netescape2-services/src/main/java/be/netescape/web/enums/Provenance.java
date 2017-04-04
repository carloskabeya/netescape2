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
public enum Provenance {

    OPT("OPT"),
    MAGAZINE("Magazine"),
    BAO("BAO"),
    CLIENT("Client"),
    HOTEL("Hôtel"),
    SALON("Salon"),
    INTERNET("Internet"),
    DHF("DHF"),
    AGENCE("Agence"),
    AUTRES("Autres");
    protected String value;

    Provenance(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static List getValues() {
        return Arrays.asList(Provenance.values());
    }

    @Override
    public String toString() {
        return this.value;
    }
}
