/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "activite")
@SequenceGenerator(name = "seq_activite", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activite.findAll", query = "SELECT a FROM Activite a"),
    @NamedQuery(name = "Activite.findByDescription", query = "SELECT a FROM Activite a WHERE a.description LIKE :description"),
    @NamedQuery(name = "Activite.findByNom", query = "SELECT a FROM Activite a WHERE a.nom LIKE :nom"),
    @NamedQuery(name = "Activite.findByPrix", query = "SELECT a FROM Activite a WHERE a.prix = :prix"),
    @NamedQuery(name = "Activite.findByRangePrix", query = "SELECT a FROM Activite a WHERE a.prix BETWEEN :val1 AND :val2"),
    @NamedQuery(name = "Activite.findByStandard", query = "SELECT a FROM Activite a WHERE a.standard = :standard"),
    @NamedQuery(name = "Activite.findByTheme", query = "SELECT a FROM Activite a WHERE a.theme LIKE :theme"),
    @NamedQuery(name = "Activite.findMaxCommande", query = "SELECT COUNT (a.activitecommanderList) FROM Activite a"),
    @NamedQuery(name = "Activite.findByActiviteid", query = "SELECT a FROM Activite a WHERE a.activiteid LIKE :activiteid")})
public class Activite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_activite")
    @Basic(optional = false)
    @Column(name = "activiteid")
    private Integer activiteid;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "illustration")
    private byte[] illustration;
    @NotNull
    @Column(name = "nom")
    private String nom;
    @Column(name = "prix")
    @NotNull(message = "Montant entre 1 et 10000€")
    private Integer prix;
    @Column(name = "standard")
    private Boolean standard;
    @Size(max = 255)
    @Column(name = "theme")
    private String theme;
    @Column(name = "note")
    private float note;
    /*
     * Associations
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "activite", cascade = CascadeType.ALL)
    private List<Timing> timingList = new ArrayList<Timing>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "activite", cascade = CascadeType.ALL)
    private List<ActiviteCommander> activitecommanderList = new ArrayList<ActiviteCommander>();

    public Activite() {
    }

    public Activite(Integer activiteid) {
        this.activiteid = activiteid;
    }

    /**
     * Moyenne des notes obtenues par activité commandée.
     *
     * @return
     */
    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getIllustration() {
        return illustration;
    }

    public void setIllustration(byte[] illustration) {
        this.illustration = illustration;
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

    public Boolean getStandard() {
        return standard;
    }

    public void setStandard(Boolean standard) {
        this.standard = standard;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getActiviteid() {
        return activiteid;
    }

    public void setActiviteid(Integer activiteid) {
        this.activiteid = activiteid;
    }

    @XmlTransient
    public List<Timing> getTimingList() {
        return timingList;
    }

    public void setTimingList(List<Timing> timingList) {
        this.timingList = timingList;
    }

    public void addTimingList(Timing timing) {
        if (timing == null) {
            return;
        }
        if (timingList == null) {
            timingList = new ArrayList<Timing>();
        }
        if (!timingList.contains(timing)) {
            timingList.add(timing);
            //TODO utiliser cascade à la place.
            timing.getComposant().getTimingList().add(timing);
        }
    }

    public void removeTimingList(Timing timing) {
        if (timingList == null || timing == null) {
            return;
        }
        if (timingList.contains(timing)) {
            timingList.remove(timing);
            timing.setActivite(null);
        }
    }

    public void addActiviteCommanderList(ActiviteCommander activitecommander) {
        if (activitecommander == null) {
            return;
        }
        if (activitecommanderList == null) {
            activitecommanderList = new ArrayList<ActiviteCommander>();
        }
        if (!activitecommanderList.contains(activitecommander)) {
            activitecommanderList.add(activitecommander);
            activitecommander.setActivite(this);
        }
    }

    public void removeActiviteCommanderList(ActiviteCommander activitecommander) {
        if (activitecommander == null || activitecommanderList == null) {
            return;
        }
        if (activitecommanderList.contains(activitecommander)) {
            activitecommanderList.remove(activitecommander);
            activitecommander.setActivite(null);
        }
    }

    @XmlTransient
    public List<ActiviteCommander> getActivitecommanderList() {
        return activitecommanderList;
    }

    public void setActivitecommanderList(List<ActiviteCommander> activitecommanderList) {
        this.activitecommanderList = activitecommanderList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + (this.activiteid != null ? this.activiteid.hashCode() : 0);
        hash = 13 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 13 * hash + Arrays.hashCode(this.illustration);
        hash = 13 * hash + (this.nom != null ? this.nom.hashCode() : 0);
        hash = 13 * hash + (this.prix != null ? this.prix.hashCode() : 0);
        hash = 13 * hash + (this.standard != null ? this.standard.hashCode() : 0);
        hash = 13 * hash + (this.theme != null ? this.theme.hashCode() : 0);
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
        final Activite other = (Activite) obj;
        if (this.activiteid != other.activiteid && (this.activiteid == null || !this.activiteid.equals(other.activiteid))) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (!Arrays.equals(this.illustration, other.illustration)) {
            return false;
        }
        if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
            return false;
        }
        if (this.prix != other.prix && (this.prix == null || !this.prix.equals(other.prix))) {
            return false;
        }
        if (this.standard != other.standard && (this.standard == null || !this.standard.equals(other.standard))) {
            return false;
        }
        if ((this.theme == null) ? (other.theme != null) : !this.theme.equals(other.theme)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }
}
