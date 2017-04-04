/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "materiel")
@DiscriminatorValue(value = "MATERIEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materiel.findAll", query = "SELECT m FROM Materiel m"),
    @NamedQuery(name = "Materiel.findAllReservation", query = "SELECT m.reservationList FROM Materiel m"),
    @NamedQuery(name = "Materiel.findByNom", query = "SELECT m FROM Materiel m WHERE m.nom LIKE :nom"),
    @NamedQuery(name = "Materiel.findByNumero", query = "SELECT m FROM Materiel m WHERE m.numero = :numero"),
    @NamedQuery(name = "Materiel.findByRessourceid", query = "SELECT m FROM Materiel m WHERE m.ressourceid = :ressourceid")})
public class Materiel extends Ressource {

    @Column(name = "numero")
    private String numero;
    @Column(name = "nom")
    @NotNull
    private String nom;
    @Column(name = "description")
    @Size(max = 255)
    private String description;
    @Transient
    @NotNull(message = "Nombre requis")
    private Integer quantite;
    /*
     * Association
     */
    @ManyToOne
    @JoinColumn(name = "typematerielid", referencedColumnName = "typeressourceid")
    @NotNull(message = "Champ obligatoire.")
    private TypeMateriel typemateriel;

    public Materiel() {
    }

    public Materiel(String numero, String nom, String description,
            TypeMateriel typemateriel, List<Reservation> reservationList, boolean disponible) {
        this.numero = numero;
        this.nom = nom;
        this.description = description;
        this.typemateriel = typemateriel;
        this.setDisponible(disponible);
        this.setReservationList(reservationList);
    }

    public Materiel(Materiel materiel) {
        this.description = materiel.description;
        this.nom = materiel.nom;
        this.typemateriel = materiel.typemateriel;
    }

    public String getNumero() {
        return numero;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public TypeMateriel getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(TypeMateriel typemateriel) {
        this.typemateriel = typemateriel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.numero != null ? this.numero.hashCode() : 0);
        hash = 17 * hash + (this.nom != null ? this.nom.hashCode() : 0);
        hash = 17 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 17 * hash + (this.typemateriel != null ? this.typemateriel.hashCode() : 0);
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
        final Materiel other = (Materiel) obj;
        if (this.numero != other.numero && (this.numero == null || !this.numero.equals(other.numero))) {
            return false;
        }
        if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.typemateriel != other.typemateriel && (this.typemateriel == null || !this.typemateriel.equals(other.typemateriel))) {
            return false;
        }
        return true;
    }
}
