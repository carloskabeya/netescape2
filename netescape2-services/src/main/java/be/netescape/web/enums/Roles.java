/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.enums;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Carlos
 */
public enum Roles {

    ADMINISTRATEUR("Administrateur"),
    COMMERCIAL("Commerciale"),
    DIRECTION("Direction"),
    CLIENT("Client");
    protected String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static List getValues() {
        return Arrays.asList(Roles.values());
    }

    @Override
    public String toString() {
        return this.value;
    }
}
