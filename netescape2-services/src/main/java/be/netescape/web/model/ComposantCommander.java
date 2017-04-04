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
@Table(name = "composantcommander")
@SequenceGenerator(name = "seq_composant_commander", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComposantCommander.findAll", query = "SELECT c FROM ComposantCommander c"),
    @NamedQuery(name = "ComposantCommander.findByComposantcommanderid", query = "SELECT c FROM ComposantCommander c WHERE c.composantcommanderid = :composantcommanderid")})
public class ComposantCommander implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_composant_commander")
    @Basic(optional = false)
    @Column(name = "composantcommanderid")
    private Integer composantcommanderid;
    
    public ComposantCommander(Composant composant, ActiviteCommander activitecommander,
            List<Reservation> reservationList, List<OptionSupplementaireCommander> optioncommanderList) {
        this.composant = composant;
        this.activitecommander = activitecommander;
        this.reservationList = reservationList;
        this.optioncommanderList = optioncommanderList;
    }
    /*
     * Associations.
     */
    @JoinColumn(name = "composantid", referencedColumnName = "composantid")
    @ManyToOne
    private Composant composant;
    @JoinColumn(name = "activitecommanderid", referencedColumnName = "activitecommanderid")
    @ManyToOne
    private ActiviteCommander activitecommander;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "composantcommander", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<Reservation>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "composantcommander", cascade = CascadeType.ALL)
    private List<OptionSupplementaireCommander> optioncommanderList = new ArrayList<OptionSupplementaireCommander>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "typeressourcemanquantes", joinColumns = {
        @JoinColumn(name = "composantcommanderid", nullable = false, updatable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "typeressourceid", nullable = false, updatable = false)})
    private List<TypeRessource> typeressourcemanquanteList = new ArrayList<TypeRessource>();
    
    public ComposantCommander() {
    }
    
    public ComposantCommander(Integer composantcommanderid) {
        this.composantcommanderid = composantcommanderid;
    }
    
    public Integer getComposantcommanderid() {
        return composantcommanderid;
    }
    
    public void setComposantcommanderid(Integer composantcommanderid) {
        this.composantcommanderid = composantcommanderid;
    }
    
    public Composant getComposant() {
        return composant;
    }
    
    public void setComposant(Composant composant) {
        this.composant = composant;
    }
    
    public List<TypeRessource> getTyperessourcemanquanteList() {
        return typeressourcemanquanteList;
    }
    
    public void setTyperessourcemanquanteList(List<TypeRessource> typeressourcemanquanteList) {
        this.typeressourcemanquanteList = typeressourcemanquanteList;
    }
    
    public void addTyperessourcemanquanteList(TypeRessource typeressource) {
        if (typeressource == null) {
            return;
        }
        //permet doublon pour faire le décompte de type ressource manquante.
        typeressourcemanquanteList.add(typeressource);
    }
    
    public void removeTyperessourcemanquanteList(TypeRessource typeressouce) {
        throw new UnsupportedOperationException("non implémentée");
    }
    
    public ActiviteCommander getActivitecommander() {
        return activitecommander;
    }
    
    public void setActivitecommander(ActiviteCommander activitecommander) {
        this.activitecommander = activitecommander;
    }
    
    @XmlTransient
    public List<OptionSupplementaireCommander> getOptioncommanderList() {
        return optioncommanderList;
    }
    
    public void setOptioncommanderList(List<OptionSupplementaireCommander> optioncommanderList) {
        this.optioncommanderList = optioncommanderList;
    }
    
    public void addOptionCommanderList(OptionSupplementaireCommander optionsupplementairecommander) {
        if (optionsupplementairecommander == null) {
            return;
        }
        if (optioncommanderList == null) {
            optioncommanderList = new ArrayList<OptionSupplementaireCommander>();
        }
        if (!optioncommanderList.contains(optionsupplementairecommander)) {
            optioncommanderList.add(optionsupplementairecommander);
            optionsupplementairecommander.setComposantcommander(this);
        }
    }
    
    public void removeOptionCommanderList(OptionSupplementaireCommander optionsupplementairecommander) {
        if (optionsupplementairecommander == null) {
            return;
        }
        if (optioncommanderList.contains(optionsupplementairecommander)) {
            optioncommanderList.remove(optionsupplementairecommander);
            optionsupplementairecommander.setComposantcommander(null);
        }
    }
    
    @XmlTransient
    public List<Reservation> getReservationList() {
        return reservationList;
    }
    
    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
    
    public void addReservationList(Reservation reservation) {
        if (reservation == null) {
            return;
        }
        if (!reservationList.contains(reservation)) {
            reservationList.add(reservation);
            reservation.setComposantcommander(this);
        }
    }
    
    public void removeReservationList(Reservation reservation) {
        if (reservation == null) {
            return;
        }
        if (reservationList.contains(reservation)) {
            reservationList.remove(reservation);
            reservation.setComposantcommander(null);
            //libère aussi la ressource.
            reservation.setRessource(null);
        }
    }
    
    public void removeAllReservationList() {
        for (Reservation r : reservationList) {
            r.setComposantcommander(null);
            r.setRessource(null);
        }
        reservationList.clear();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (composantcommanderid != null ? composantcommanderid.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComposantCommander)) {
            return false;
        }
        ComposantCommander other = (ComposantCommander) object;
        if ((this.composantcommanderid == null && other.composantcommanderid != null) || (this.composantcommanderid != null && !this.composantcommanderid.equals(other.composantcommanderid))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "app.tfe.netescape.ComposantCommander[ composantcommanderid=" + composantcommanderid + " ]";
    }
}
