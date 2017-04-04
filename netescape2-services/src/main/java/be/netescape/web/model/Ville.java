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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "ville")
@SequenceGenerator(name = "seq_ville", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ville.findAll", query = "SELECT v FROM Ville v"),
    @NamedQuery(name = "Ville.findByCode", query = "SELECT v FROM Ville v WHERE v.code = :code"),
    @NamedQuery(name = "Ville.findByNom", query = "SELECT v FROM Ville v WHERE v.nom = :nom"),
    @NamedQuery(name = "Ville.findByVilleid", query = "SELECT v FROM Ville v WHERE v.villeid = :villeid")})
public class Ville implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_ville")
    @Basic(optional = false)
    @Column(name = "villeid")
    private Integer villeid;
    @Column(name = "code")
    @NotEmpty
    @Pattern(regexp = "\\d{5}", message = "Nombre requis ex:4000")
    private String code;
    @Column(name = "nom")
    @NotNull
    private String nom;

    public Ville() {
    }

    public Ville(Integer villeid) {
        this.villeid = villeid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getVilleid() {
        return villeid;
    }

    public void setVilleid(Integer villeid) {
        this.villeid = villeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (villeid != null ? villeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ville)) {
            return false;
        }
        Ville other = (Ville) object;
        if ((this.villeid == null && other.villeid != null) || (this.villeid != null && !this.villeid.equals(other.villeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.tfe.netescape.Ville[ villeid=" + villeid + " ]";
    }
}
