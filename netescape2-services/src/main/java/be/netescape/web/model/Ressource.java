/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Table(name = "ressource")
@SequenceGenerator(name = "seq_ressource", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ressource.findAll", query = "SELECT r FROM Ressource r"),
    @NamedQuery(name = "Ressource.findAllReservation", query = "SELECT r.reservationList FROM Ressource r"),
    @NamedQuery(name = "Ressource.findByDisponible", query = "SELECT r FROM Ressource r WHERE r.disponible = :disponible"),
    @NamedQuery(name = "Ressource.findByRessourceid", query = "SELECT r FROM Ressource r WHERE r.ressourceid = :ressourceid")})
public abstract class Ressource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_ressource")
    @Basic(optional = false)
    @Column(name = "ressourceid")
    private Integer ressourceid;
    @Column(name = "disponible")
    private Boolean disponible;

    /*
     * Associations.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ressource")
    private List<Reservation> reservationList = new ArrayList<Reservation>();

    public Ressource() {
    }

    public Ressource(Integer ressourceid) {
        this.ressourceid = ressourceid;
    }

    public Integer getRessourceid() {
        return ressourceid;
    }

    public void setRessourceid(Integer ressourceid) {
        this.ressourceid = ressourceid;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    @XmlTransient
    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public void addReservationList(Reservation reservation) {
    }

    public void removeReservationList(Reservation reservation) {
        if (reservation == null || reservationList == null) {
            return;
        }
        if (reservationList.contains(reservation)) {
            reservationList.remove(reservation);
            reservation.setRessource(null);
        }
    }

    public boolean isDisponible(Date val1, Date val2) {
        if (val1 == null || val2 == null) {
            return true;
        }
        if (val1.after(val2)) {
            throw new IllegalArgumentException("arg 1 doit être > arg 2.");
        }
        for (Reservation r : reservationList) {
            if (r.getDebut().before(val1) && r.getFin().before(val1)
                    || r.getDebut().after(val2) && r.getFin().after(val2)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.ressourceid != null ? this.ressourceid.hashCode() : 0);
        hash = 89 * hash + (this.disponible != null ? this.disponible.hashCode() : 0);
        hash = 89 * hash + (this.reservationList != null ? this.reservationList.hashCode() : 0);
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
        final Ressource other = (Ressource) obj;
        if (this.ressourceid != other.ressourceid && (this.ressourceid == null || !this.ressourceid.equals(other.ressourceid))) {
            return false;
        }
        if (this.disponible != other.disponible && (this.disponible == null || !this.disponible.equals(other.disponible))) {
            return false;
        }
        if (this.reservationList != other.reservationList && (this.reservationList == null || !this.reservationList.equals(other.reservationList))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.tfe.netescape.Ressource[ ressourceid=" + ressourceid + " ]";
    }
}
