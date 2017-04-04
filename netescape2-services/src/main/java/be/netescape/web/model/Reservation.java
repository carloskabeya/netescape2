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
@Table(name = "reservation")
@SequenceGenerator(name = "seq_reservation", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByDebut", query = "SELECT r FROM Reservation r WHERE r.debut = :debut"),
    @NamedQuery(name = "Reservation.findByFin", query = "SELECT r FROM Reservation r WHERE r.fin = :fin"),
    @NamedQuery(name = "Reservation.findByReservationid", query = "SELECT r FROM Reservation r WHERE r.reservationid = :reservationid")})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_reservation")
    @Basic(optional = false)
    @Column(name = "reservationid")
    private Integer reservationid;
    @Column(name = "debut")
    @Temporal(TemporalType.DATE)
    private Date debut;
    @Column(name = "fin")
    @Temporal(TemporalType.DATE)
    private Date fin;
    /*
     * Associations.
     */
    @JoinColumn(name = "ressourceid", referencedColumnName = "ressourceid")
    @ManyToOne
    private Ressource ressource;
    @JoinColumn(name = "composantcommanderid", referencedColumnName = "composantcommanderid")
    @ManyToOne
    private ComposantCommander composantcommander;

    public Reservation(Date debut, Date fin, Ressource ressource, ComposantCommander composantcommander) {
        this.debut = debut;
        this.fin = fin;
        this.ressource = ressource;
        this.composantcommander = composantcommander;
    }

    public Reservation(Ressource ressource) {
        this.ressource = ressource;
    }

    public Reservation() {
    }

    public Reservation(Integer reservationid) {
        this.reservationid = reservationid;
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

    public Integer getReservationid() {
        return reservationid;
    }

    public void setReservationid(Integer reservationid) {
        this.reservationid = reservationid;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    public ComposantCommander getComposantcommander() {
        return composantcommander;
    }

    public void setComposantcommander(ComposantCommander composantcommander) {
        this.composantcommander = composantcommander;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.reservationid != null ? this.reservationid.hashCode() : 0);
        hash = 37 * hash + (this.debut != null ? this.debut.hashCode() : 0);
        hash = 37 * hash + (this.fin != null ? this.fin.hashCode() : 0);
        hash = 37 * hash + (this.ressource != null ? this.ressource.hashCode() : 0);
        hash = 37 * hash + (this.composantcommander != null ? this.composantcommander.hashCode() : 0);
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
        final Reservation other = (Reservation) obj;
        if (this.reservationid != other.reservationid && (this.reservationid == null || !this.reservationid.equals(other.reservationid))) {
            return false;
        }
        if (this.debut != other.debut && (this.debut == null || !this.debut.equals(other.debut))) {
            return false;
        }
        if (this.fin != other.fin && (this.fin == null || !this.fin.equals(other.fin))) {
            return false;
        }
        if (this.ressource != other.ressource && (this.ressource == null || !this.ressource.equals(other.ressource))) {
            return false;
        }
        if (this.composantcommander != other.composantcommander && (this.composantcommander == null || !this.composantcommander.equals(other.composantcommander))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.tfe.netescape.Reservation[ reservationid=" + reservationid + " ]";
    }
}
