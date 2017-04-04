/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "timing")
@SequenceGenerator(name = "seq_timing", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timing.findAll", query = "SELECT t FROM Timing t"),
    @NamedQuery(name = "Timing.findByDebut", query = "SELECT t FROM Timing t WHERE t.debut = :debut"),
    @NamedQuery(name = "Timing.findByFin", query = "SELECT t FROM Timing t WHERE t.fin = :fin"),
    @NamedQuery(name = "Timing.findByTimingid", query = "SELECT t FROM Timing t WHERE t.timingid = :timingid")})
public class Timing implements Serializable, Comparable<Timing> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_timing")
    @Basic(optional = false)
    @Column(name = "timingid")
    private Integer timingid;
    @Column(name = "debut")
    @Temporal(TemporalType.TIME)
    private Date debut;
    @Column(name = "fin")
    @Temporal(TemporalType.TIME)
    private Date fin;
    /*
     * Associations.
     */
    @JoinColumn(name = "composantid", referencedColumnName = "composantid")
    @ManyToOne
    private Composant composant;
    @JoinColumn(name = "activiteid", referencedColumnName = "activiteid")
    @ManyToOne
    private Activite activite;

    public Timing() {
    }

    public Timing(Date debut, Date fin, Composant composant, Activite activite) {
        this.debut = debut;
        this.fin = fin;
        this.composant = composant;
        this.activite = activite;
    }

    public Timing(Integer timingid) {
        this.timingid = timingid;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Integer getTimingid() {
        return timingid;
    }

    public void setTimingid(Integer timingid) {
        this.timingid = timingid;
    }

    public Composant getComposant() {
        return composant;
    }

    public void setComposant(Composant composant) {
        this.composant = composant;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.timingid != null ? this.timingid.hashCode() : 0);
        hash = 23 * hash + (this.debut != null ? this.debut.hashCode() : 0);
        hash = 23 * hash + (this.fin != null ? this.fin.hashCode() : 0);
        hash = 23 * hash + (this.composant != null ? this.composant.hashCode() : 0);
        hash = 23 * hash + (this.activite != null ? this.activite.hashCode() : 0);
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
        final Timing other = (Timing) obj;
        if (this.timingid != other.timingid && (this.timingid == null || !this.timingid.equals(other.timingid))) {
            return false;
        }
        if (this.debut != other.debut && (this.debut == null || !this.debut.equals(other.debut))) {
            return false;
        }
        if (this.fin != other.fin && (this.fin == null || !this.fin.equals(other.fin))) {
            return false;
        }
        if (this.composant != other.composant && (this.composant == null || !this.composant.equals(other.composant))) {
            return false;
        }
        if (this.activite != other.activite && (this.activite == null || !this.activite.equals(other.activite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.tfe.netescape.Timing[ timingid=" + timingid + " ]";
    }

    @Override
    public int compareTo(Timing t) {
        return this.debut.compareTo(t.debut);
    }
}
