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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "servicecommander")
@SequenceGenerator(name = "seq_service_commander", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceCommander.findAll", query = "SELECT s FROM ServiceCommander s"),
    @NamedQuery(name = "ServiceCommander.findByCompris", query = "SELECT s FROM ServiceCommander s WHERE s.compris = :compris"),
    @NamedQuery(name = "ServiceCommander.findByServicecommanderid", query = "SELECT s FROM ServiceCommander s WHERE s.servicecommanderid = :servicecommanderid")})
public class ServiceCommander implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_service_commander")
    @Basic(optional = false)
    @Column(name = "servicecommanderid")
    private Integer servicecommanderid;
    @Column(name = "compris")
    private Boolean compris;
    @Column(name = "nombre")
    private Integer nombre;
    /*
     * Associations
     */
    @JoinColumn(name = "serviceid", referencedColumnName = "serviceid")
    @ManyToOne
    private Service service;
    @JoinColumn(name = "offreid", referencedColumnName = "offreid")
    @ManyToOne
    private Offre offre;

    public ServiceCommander() {
    }

    public ServiceCommander(Boolean compris, Service service, Offre offre) {
        this.compris = compris;
        this.service = service;
        this.offre = offre;
    }

    public ServiceCommander(Integer servicecommanderid) {
        this.servicecommanderid = servicecommanderid;
    }

    public Boolean getCompris() {
        return compris;
    }

    public void setCompris(Boolean compris) {
        this.compris = compris;
    }

    public Integer getServicecommanderid() {
        return servicecommanderid;
    }

    public void setServicecommanderid(Integer servicecommanderid) {
        this.servicecommanderid = servicecommanderid;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.servicecommanderid != null ? this.servicecommanderid.hashCode() : 0);
        hash = 59 * hash + (this.compris != null ? this.compris.hashCode() : 0);
        hash = 59 * hash + (this.service != null ? this.service.hashCode() : 0);
        hash = 59 * hash + (this.offre != null ? this.offre.hashCode() : 0);
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
        final ServiceCommander other = (ServiceCommander) obj;
        if (this.servicecommanderid != other.servicecommanderid && (this.servicecommanderid == null || !this.servicecommanderid.equals(other.servicecommanderid))) {
            return false;
        }
        if (this.compris != other.compris && (this.compris == null || !this.compris.equals(other.compris))) {
            return false;
        }
        if (this.service != other.service && (this.service == null || !this.service.equals(other.service))) {
            return false;
        }
        if (this.offre != other.offre && (this.offre == null || !this.offre.equals(other.offre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.tfe.netescape.ServiceCommander[ servicecommanderid=" + servicecommanderid + " ]";
    }
}
