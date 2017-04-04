/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import be.netescape.web.enums.TypeOption;
import be.netescape.web.enums.Unite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "optionsupplementaire")
@SequenceGenerator(name = "seq_option", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OptionSupplementaire.findAll", query = "SELECT o FROM OptionSupplementaire o"),
    @NamedQuery(name = "OptionSupplementaire.findAllComposant", query = "SELECT o.composantList FROM OptionSupplementaire o"),
    @NamedQuery(name = "OptionSupplementaire.findAllOptionSupplementaireCommander", query = "SELECT o.optioncommanderList FROM OptionSupplementaire o"),
    @NamedQuery(name = "OptionSupplementaire.findByDetails", query = "SELECT o FROM OptionSupplementaire o WHERE o.details LIKE :details"),
    @NamedQuery(name = "OptionSupplementaire.findByNom", query = "SELECT o FROM OptionSupplementaire o WHERE o.nom LIKE :nom"),
    @NamedQuery(name = "OptionSupplementaire.findByPrix", query = "SELECT o FROM OptionSupplementaire o WHERE o.prix = :prix"),
    @NamedQuery(name = "OptionSupplementaire.findByTva", query = "SELECT o FROM OptionSupplementaire o WHERE o.tva = :tva"),
    @NamedQuery(name = "OptionSupplementaire.findByType", query = "SELECT o FROM OptionSupplementaire o WHERE o.type = :type"),
    @NamedQuery(name = "OptionSupplementaire.findByUnite", query = "SELECT o FROM OptionSupplementaire o WHERE o.unite = :unite"),
    @NamedQuery(name = "OptionSupplementaire.findByOptionid", query = "SELECT o FROM OptionSupplementaire o WHERE o.optionid = :optionid")})
public class OptionSupplementaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_option")
    @Basic(optional = false)
    @Column(name = "optionid")
    private Integer optionid;
    @Size(max = 255)
    @Column(name = "details")
    private String details;
    @Column(name = "nom")
    @NotNull
    private String nom;
    @Column(name = "prix")
    @NotNull(message = "Montant entre 1 et 10000€")
    private Integer prix;
    @Column(name = "tva")
    @NotEmpty
    private String tva;
    /*
     * Associations.
     */
    @ManyToMany(mappedBy = "optionList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Composant> composantList = new ArrayList<Composant>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "option", cascade = CascadeType.ALL)
    private List<OptionSupplementaireCommander> optioncommanderList = new ArrayList<OptionSupplementaireCommander>();
    /*
     * Enumérations.
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private TypeOption type;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Unite unite;

    public OptionSupplementaire() {
    }

    public OptionSupplementaire(Integer optionid) {
        this.optionid = optionid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public Integer getOptionid() {
        return optionid;
    }

    public void setOptionid(Integer optionid) {
        this.optionid = optionid;
    }

    public TypeOption getType() {
        return type;
    }

    public void setType(TypeOption type) {
        this.type = type;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    @XmlTransient
    public List<Composant> getComposantList() {
        return composantList;
    }

    public void setComposantList(List<Composant> composantList) {
        this.composantList = composantList;
    }

    public void addComposant(Composant composant) {
        if (composant == null) {
            return;
        }
        if (composantList == null) {
            composantList = new ArrayList<Composant>();
        }
        if (!(composantList.contains(composant))) {
            composantList.add(composant);
        }
    }

    public void removeComposant(Composant composant) {
        if (composant == null || composantList == null) {
            return;
        }
        if (composantList.contains(composant)) {
            composantList.remove(composant);
            composant.removeOptionList(this);
        }
    }

    @XmlTransient
    public List<OptionSupplementaireCommander> getOptioncommanderList() {
        return optioncommanderList;
    }

    public void setOptioncommanderList(List<OptionSupplementaireCommander> optioncommanderList) {
        this.optioncommanderList = optioncommanderList;
    }

    public void addOptionSupplementaireCommander(OptionSupplementaireCommander optionsupplementairecommander) {
        if (optionsupplementairecommander == null) {
            return;
        }
        if (optioncommanderList == null) {
            optioncommanderList = new ArrayList<OptionSupplementaireCommander>();
        }
        if (!(optioncommanderList.contains(optionsupplementairecommander))) {
            optioncommanderList.add(optionsupplementairecommander);
            optionsupplementairecommander.setOption(this);
        }

    }

    public void removeOptionSupplementaireCommander(OptionSupplementaireCommander optionsupplementairecommander) {
        if (optionsupplementairecommander == null || optioncommanderList == null) {
            return;
        }
        if (optioncommanderList.contains(optionsupplementairecommander)) {
            optioncommanderList.remove(optionsupplementairecommander);
            optionsupplementairecommander.setOption(null);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.optionid != null ? this.optionid.hashCode() : 0);
        hash = 79 * hash + (this.details != null ? this.details.hashCode() : 0);
        hash = 79 * hash + (this.nom != null ? this.nom.hashCode() : 0);
        hash = 79 * hash + (this.prix != null ? this.prix.hashCode() : 0);
        hash = 79 * hash + (this.tva != null ? this.tva.hashCode() : 0);
        hash = 79 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 79 * hash + (this.unite != null ? this.unite.hashCode() : 0);
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
        final OptionSupplementaire other = (OptionSupplementaire) obj;
        if (this.optionid != other.optionid && (this.optionid == null || !this.optionid.equals(other.optionid))) {
            return false;
        }
        if ((this.details == null) ? (other.details != null) : !this.details.equals(other.details)) {
            return false;
        }
        if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
            return false;
        }
        if (this.prix != other.prix && (this.prix == null || !this.prix.equals(other.prix))) {
            return false;
        }
        if ((this.tva == null) ? (other.tva != null) : !this.tva.equals(other.tva)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.unite != other.unite) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }
}
