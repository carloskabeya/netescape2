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
public enum RaisonSociale {

    ASBL("A.S.B.L"),
    SCS("S.C.S"),
    SNC("S.N.C"),
    SPRL("S.P.R.L"),
    ASSOCIATION("Association"),
    EI("Entreprise Individuelle"),
    GEIE("G.E.I.E"),
    GIE("G.I.E"),
    SA("S.A"),
    SCA("S.C.A"),
    SCRI("S.C.R.I"),
    SCRL("S.C.R.L");
    protected String value;

    RaisonSociale(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    public static List getValues() {
        return Arrays.asList(RaisonSociale.values());
    }

    @Override
    public String toString() {
        return this.value;
    }
}
