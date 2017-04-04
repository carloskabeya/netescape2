/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "nombreressource")
@SequenceGenerator(name = "seq_nombreressource", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NombreRessource.findAll", query = "SELECT n FROM NombreRessource n"),
    @NamedQuery(name = "NombreRessource.findByQuantite", query = "SELECT n FROM NombreRessource n WHERE n.quantite = :quantite"),
    @NamedQuery(name = "NombreRessource.findByNombreressourceid", query = "SELECT n FROM NombreRessource n WHERE n.nombreressourceid = :nombreressourceid")})
public class NombreRessource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_nombreressource")
    @Basic(optional = false)
    @Column(name = "nombreressourceid")
    private Integer nombreressourceid;
    @Column(name = "quantite")
    private float quantite;
    /*
     * Associations.
     */
    @JoinColumn(name = "typeressourceid", referencedColumnName = "typeressourceid")
    @ManyToOne
    private TypeRessource typeressource;
    @JoinColumn(name = "composantid", referencedColumnName = "composantid")
    @ManyToOne
    private Composant composant;

    public NombreRessource() {
    }

    public NombreRessource(Integer nombreressourceid) {
        this.nombreressourceid = nombreressourceid;
    }

    public NombreRessource(float quantite, TypeRessource typeressource, Composant composant) {
        this.quantite = quantite;
        this.typeressource = typeressource;
        this.composant = composant;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public Integer getNombreressourceid() {
        return nombreressourceid;
    }

    public void setNombreressourceid(Integer nombreressourceid) {
        this.nombreressourceid = nombreressourceid;
    }

    public TypeRessource getTyperessource() {
        return typeressource;
    }

    public void setTyperessource(TypeRessource typeressource) {
        this.typeressource = typeressource;
    }

    public Composant getComposant() {
        return composant;
    }

    public void setComposant(Composant composant) {
        this.composant = composant;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.nombreressourceid != null ? this.nombreressourceid.hashCode() : 0);
        hash = 59 * hash + Float.floatToIntBits(this.quantite);
        hash = 59 * hash + (this.typeressource != null ? this.typeressource.hashCode() : 0);
        hash = 59 * hash + (this.composant != null ? this.composant.hashCode() : 0);
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
        final NombreRessource other = (NombreRessource) obj;
        if (this.nombreressourceid != other.nombreressourceid && (this.nombreressourceid == null || !this.nombreressourceid.equals(other.nombreressourceid))) {
            return false;
        }
        if (Float.floatToIntBits(this.quantite) != Float.floatToIntBits(other.quantite)) {
            return false;
        }
        if (this.typeressource != other.typeressource && (this.typeressource == null || !this.typeressource.equals(other.typeressource))) {
            return false;
        }
        if (this.composant != other.composant && (this.composant == null || !this.composant.equals(other.composant))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "app.tfe.netescape.NombreRessource[ nombreressourceid=" + nombreressourceid + " ]";
    }
}
