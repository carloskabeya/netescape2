/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Table(name = "typeressource")
@SequenceGenerator(name = "seq_typeressource", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeRessource.findAll", query = "SELECT t FROM TypeRessource t"),
    @NamedQuery(name = "TypeRessource.findByDescription", query = "SELECT t FROM TypeRessource t WHERE t.description LIKE :description"),
    @NamedQuery(name = "TypeRessource.findByNom", query = "SELECT t FROM TypeRessource t WHERE t.nom = :nom"),
    @NamedQuery(name = "TypeRessource.findByTyperessourceid", query = "SELECT t FROM TypeRessource t WHERE t.typeressourceid = :typeressourceid")})
public abstract class TypeRessource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_typeressource")
    @Basic(optional = false)
    @Column(name = "typeressourceid")
    private Integer typeressourceid;
    @Column(name = "nom")
    @NotNull
    private String nom;
    @Column(name = "description")
    @Size(max = 255)
    private String description;
    /*
     * Associations
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeressource")
    private List<NombreRessource> nombreressourceList = new ArrayList<NombreRessource>();

    public TypeRessource() {
    }

    public TypeRessource(String description, String nom, List<Ressource> ressourceList, List<NombreRessource> nombreressourceList) {
        this.description = description;
        this.nom = nom;
        this.nombreressourceList = nombreressourceList;
    }

    public TypeRessource(Integer typeressourceid) {
        this.typeressourceid = typeressourceid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getTyperessourceid() {
        return typeressourceid;
    }

    public void setTyperessourceid(Integer typeressourceid) {
        this.typeressourceid = typeressourceid;
    }

    @XmlTransient
    public List<NombreRessource> getNombreressourceList() {
        return nombreressourceList;
    }

    public void setNombreressourceList(List<NombreRessource> nombreressourceList) {
        this.nombreressourceList = nombreressourceList;
    }

//    public List<ComposantCommander> getComposantmanquantList() {
//        return composantmanquantList;
//    }
//
//    public void setComposantmanquantList(List<ComposantCommander> composantmanquantList) {
//        this.composantmanquantList = composantmanquantList;
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.typeressourceid != null ? this.typeressourceid.hashCode() : 0);
        hash = 97 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 97 * hash + (this.nom != null ? this.nom.hashCode() : 0);
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
        final TypeRessource other = (TypeRessource) obj;
        if (this.typeressourceid != other.typeressourceid && (this.typeressourceid == null || !this.typeressourceid.equals(other.typeressourceid))) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }
}
