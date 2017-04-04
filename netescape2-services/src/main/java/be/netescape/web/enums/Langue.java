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
public enum Langue {

    FRANCAIS("Français"),
    NEERLANDAIS("Néerlandais"),
    ANGLAIS("Anglais"),
    ALLEMAND("Allemand");
    protected String value;

    Langue(String value) {
        this.value = value;
    }


    public static List getValues() {
        return Arrays.asList(Langue.values());
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
