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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
 @Entity
@Table(name = "activitecommander")
@SequenceGenerator(name = "seq_activite_commander", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActiviteCommander.findAll", query = "SELECT a FROM ActiviteCommander a"),
    @NamedQuery(name = "ActiviteCommander.findByActivitecommanderid", query = "SELECT a FROM ActiviteCommander a WHERE a.activitecommanderid = :activitecommanderid")})
public class ActiviteCommander implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_activite_commander")
    @Basic(optional = false)
    @Column(name = "activitecommanderid")
    private Integer activitecommanderid;
    @Column(name = "remise")
    private Long remise;
    /*
     * Associations/
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "activitecommander", cascade = CascadeType.ALL)
    private List<ComposantCommander> composantcommanderList = new ArrayList<ComposantCommander>();
    @JoinColumn(name = "offreid", referencedColumnName = "offreid")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Offre offre;
    @JoinColumn(name = "activiteid", referencedColumnName = "activiteid")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Activite activite;
    @Embedded
    private Evaluation evaluationactivite = new Evaluation();

    public ActiviteCommander() {
    }

    public ActiviteCommander(Activite activite, Offre offre) {
        this.activite = activite;
        this.offre = offre;
    }

    public ActiviteCommander(Long remise, List<ComposantCommander> composantcommanderList, Offre offre, Activite activite) {
        this.remise = remise;
        this.composantcommanderList = composantcommanderList;
        this.offre = offre;
        this.activite = activite;
    }

    public ActiviteCommander(Integer activitecommanderid) {
        this.activitecommanderid = activitecommanderid;
    }

    public Integer getActivitecommanderid() {
        return activitecommanderid;
    }

    public void setActivitecommanderid(Integer activitecommanderid) {
        this.activitecommanderid = activitecommanderid;
    }

    public Long getRemise() {
        return remise;
    }

    public void setRemise(Long remise) {
        this.remise = remise;
    }

    public Evaluation getEvaluationactivite() {
        return evaluationactivite;
    }

    public void setEvaluationactivite(Evaluation evaluationactivite) {
        this.evaluationactivite = evaluationactivite;
    }

    @XmlTransient
    public List<ComposantCommander> getComposantcommanderList() {
        return composantcommanderList;
    }

    public void setComposantcommanderList(List<ComposantCommander> composantcommanderList) {
        this.composantcommanderList = composantcommanderList;
    }

    public void addComposantCommanderList(ComposantCommander composantcommander) {
        if (composantcommander == null) {
            return;
        }
        if (!composantcommanderList.contains(composantcommander)) {
            composantcommanderList.add(composantcommander);
            composantcommander.setActivitecommander(this);
        }
    }

    public void removeComposantCommanderList(ComposantCommander composantcommander) {
        if (composantcommander == null || composantcommanderList == null) {
            return;
        }
        if (composantcommanderList.contains(composantcommander)) {
            composantcommanderList.remove(composantcommander);
            composantcommander.setActivitecommander(null);
        }
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.activitecommanderid != null ? this.activitecommanderid.hashCode() : 0);
        hash = 97 * hash + (this.composantcommanderList != null ? this.composantcommanderList.hashCode() : 0);
        hash = 97 * hash + (this.offre != null ? this.offre.hashCode() : 0);
        hash = 97 * hash + (this.activite != null ? this.activite.hashCode() : 0);
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
        final ActiviteCommander other = (ActiviteCommander) obj;
        if (this.activitecommanderid != other.activitecommanderid && (this.activitecommanderid == null || !this.activitecommanderid.equals(other.activitecommanderid))) {
            return false;
        }
        if (this.composantcommanderList != other.composantcommanderList && (this.composantcommanderList == null || !this.composantcommanderList.equals(other.composantcommanderList))) {
            return false;
        }
        if (this.offre != other.offre && (this.offre == null || !this.offre.equals(other.offre))) {
            return false;
        }
        if (this.activite != other.activite && (this.activite == null || !this.activite.equals(other.activite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.tfe.netescape.ActiviteCommander[ activitecommanderid=" + activitecommanderid + " ]";
    }
}
