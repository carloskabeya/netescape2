/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "composant")
@SequenceGenerator(name = "seq_composant", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Composant.findAll", query = "SELECT c FROM Composant c"),
    @NamedQuery(name = "Composant.findAllComposantCommander", query = "SELECT c.composantcommanderList FROM Composant c"),
    @NamedQuery(name = "Composant.findAllNombreRessource", query = "SELECT c.nombreressourceList FROM Composant c"),
    @NamedQuery(name = "Composant.findAllOptionSupplementaire", query = "SELECT c.optionList FROM Composant c"),
    @NamedQuery(name = "Composant.findAllTiming", query = "SELECT c.timingList FROM Composant c"),
    @NamedQuery(name = "Composant.findByDescription", query = "SELECT c FROM Composant c WHERE c.description LIKE :description"),
    @NamedQuery(name = "Composant.findByNom", query = "SELECT c FROM Composant c WHERE c.nom LIKE :nom"),
    @NamedQuery(name = "Composant.findByComposantid", query = "SELECT c FROM Composant c WHERE c.composantid = :composantid")})
public class Composant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_composant")
    @Basic(optional = false)
    @Column(name = "composantid")
    private Integer composantid;
    @Column(name = "description")
    @Size(max = 255)
    private String description;
    @Column(name = "nom")
    @NotNull
    private String nom;
    /*
     * Associations.
     */
    @JoinTable(name = "composantoptions", joinColumns = {
        @JoinColumn(name = "composantid", referencedColumnName = "composantid")}, inverseJoinColumns = {
        @JoinColumn(name = "optionid", referencedColumnName = "optionid")})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OptionSupplementaire> optionList = new ArrayList<OptionSupplementaire>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "composant", cascade = CascadeType.ALL)
    private List<ComposantCommander> composantcommanderList = new ArrayList<ComposantCommander>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "composant", cascade = CascadeType.ALL)
    private List<Timing> timingList = new ArrayList<Timing>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "composant", cascade = CascadeType.ALL)
    private List<NombreRessource> nombreressourceList = new ArrayList<NombreRessource>();

    public Composant() {
    }

    public Composant(Integer composantid) {
        this.composantid = composantid;
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

    public Integer getComposantid() {
        return composantid;
    }

    public void setComposantid(Integer composantid) {
        this.composantid = composantid;
    }

    @XmlTransient
    public List<OptionSupplementaire> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<OptionSupplementaire> optionList) {
        this.optionList = optionList;
    }

    public void addOptionList(OptionSupplementaire option) {
        if (option == null) {
            return;
        }
        if (optionList == null) {
            optionList = new ArrayList<OptionSupplementaire>();
        }
        if (!optionList.contains(option)) {
            optionList.add(option);
            option.addComposant(this);
        }
    }

    public void removeOptionList(OptionSupplementaire option) {
        if (option == null || optionList == null) {
            return;
        }
        if (optionList.contains(option)) {
            optionList.remove(option);
            option.removeComposant(this);
        }
    }

    @XmlTransient
    public List<ComposantCommander> getComposantcommanderList() {
        return composantcommanderList;
    }

    public void setComposantcommanderList(List<ComposantCommander> composantcommanderList) {
        this.composantcommanderList = composantcommanderList;
    }

    public void addComposantCommander(ComposantCommander composantcommander) {
        if (composantcommander == null) {
            return;
        }
        if (composantcommanderList == null) {
            composantcommanderList = new ArrayList<ComposantCommander>();
        }
        if (!composantcommanderList.contains(composantcommander)) {
            composantcommanderList.add(composantcommander);
            composantcommander.setComposant(this);
        }
    }

    public void removeComposantCommander(ComposantCommander composantcommander) {
        if (composantcommander == null || composantcommanderList == null) {
            return;
        }
        if (composantcommanderList.contains(composantcommander)) {
            composantcommanderList.remove(composantcommander);
            composantcommander.setComposant(null);
        }
    }

    @XmlTransient
    public List<Timing> getTimingList() {
        return timingList;
    }

    public void setTimingList(List<Timing> timingList) {
        this.timingList = timingList;
    }

    public void addTiming(Timing timing) {
        if (timing == null) {
            return;
        }
        if (timingList == null) {
            timingList = new ArrayList<Timing>();
        }
        if (!timingList.contains(timing)) {
            timingList.add(timing);
            timing.setComposant(this);
        }

    }

    public void removeTiming(Timing timing) {
        if (timing == null) {
            return;
        }
        if (timingList.contains(timing)) {
            timingList.remove(timing);
            timing.setComposant(null);
        }
    }

    @XmlTransient
    public List<NombreRessource> getNombreressourceList() {
        return nombreressourceList;
    }

    public void setNombreressourceList(List<NombreRessource> nombreressourceList) {
        this.nombreressourceList = nombreressourceList;
    }

    public void addNombreRessource(NombreRessource nombreRessource) {
        if (nombreRessource == null) {
            return;
        }
        if (!nombreressourceList.contains(nombreRessource)) {
            nombreressourceList.add(nombreRessource);
            nombreRessource.setComposant(this);
        }
    }

    public void removeNombreRessource(NombreRessource nombreressource) {
        if (nombreressource == null || nombreressourceList == null) {
            return;
        }
        if (nombreressourceList.contains(nombreressource)) {
            nombreressourceList.remove(nombreressource);
            nombreressource.setComposant(null);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.composantid != null ? this.composantid.hashCode() : 0);
        hash = 83 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 83 * hash + (this.nom != null ? this.nom.hashCode() : 0);
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
        final Composant other = (Composant) obj;
        if (this.composantid != other.composantid && (this.composantid == null || !this.composantid.equals(other.composantid))) {
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
