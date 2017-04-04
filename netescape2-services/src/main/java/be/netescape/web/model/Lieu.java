/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "lieu")
@DiscriminatorValue(value = "LIEU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lieu.findAll", query = "SELECT l FROM Lieu l"),
    @NamedQuery(name = "Lieu.findAllReservation", query = "SELECT l.reservationList FROM Lieu l"),
    @NamedQuery(name = "Lieu.findByMaximum", query = "SELECT l FROM Lieu l WHERE l.maximum = :maximum"),
    @NamedQuery(name = "Lieu.findByRessourceid", query = "SELECT l FROM Lieu l WHERE l.ressourceid = :ressourceid")})
public class Lieu extends Ressource {

    @Column(name = "nom")
    @NotNull
    private String nom;
    @Column(name = "description")
    @Size(max = 255)
    private String description;
    @Column(name = "maximum")
    @NotNull(message = "Nombre entier requis")
    private Integer maximum;
    /*
     * Associations
     */
    @ManyToOne
    @JoinColumn(name = "typelieuid", referencedColumnName = "typeressourceid")
    @NotNull
    private TypeLieu typelieu;
    @Embedded
    @Valid
    private Adresse adresse = new Adresse();

    public Lieu() {
    }

    public Lieu(String nom, String description, Integer maximum, TypeLieu typelieu) {
        this.nom = nom;
        this.description = description;
        this.maximum = maximum;
        this.typelieu = typelieu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public TypeLieu getTypelieu() {
        return typelieu;
    }

    public void setTypelieu(TypeLieu typelieu) {
        this.typelieu = typelieu;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.nom != null ? this.nom.hashCode() : 0);
        hash = 89 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 89 * hash + (this.maximum != null ? this.maximum.hashCode() : 0);
        hash = 89 * hash + (this.typelieu != null ? this.typelieu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lieu other = (Lieu) obj;
        if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.maximum != other.maximum && (this.maximum == null || !this.maximum.equals(other.maximum))) {
            return false;
        }
        if (this.typelieu != other.typelieu && (this.typelieu == null || !this.typelieu.equals(other.typelieu))) {
            return false;
        }
        return true;
    }
}
