/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Carlos
 */
@Embeddable
public class Adresse implements Serializable {

    @Column(name = "numero")
    @Pattern(regexp = "\\d{1,3}", message = "Nombre requis entre 1 et 999")
    private String numero;
    @Column(name = "rue")
    @NotNull
    private String rue;
    /*
     * Associations.
     */
    @ManyToOne
    @Valid
    private Ville ville = new Ville();

    public Adresse() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return numero + " " + rue + " " + ville.getCode() + " " + ville.getNom();
    }
}
