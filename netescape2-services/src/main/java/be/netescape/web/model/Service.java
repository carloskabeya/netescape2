/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

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
@Table(name = "service")
@SequenceGenerator(name = "seq_service", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findAllServiceCommander", query = "SELECT s.servicecommanderList FROM Service s"),
    @NamedQuery(name = "Service.findByDescription", query = "SELECT s FROM Service s WHERE s.description LIKE :description"),
    @NamedQuery(name = "Service.findByNom", query = "SELECT s FROM Service s WHERE s.nom LIKE :nom"),
    @NamedQuery(name = "Service.findByPrix", query = "SELECT s FROM Service s WHERE s.prix = :prix"),
    @NamedQuery(name = "Service.findByUnite", query = "SELECT s FROM Service s WHERE s.unite = :unite"),
    @NamedQuery(name = "Service.findByServiceid", query = "SELECT s FROM Service s WHERE s.serviceid = :serviceid")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_service")
    @Basic(optional = false)
    @Column(name = "serviceid")
    private Integer serviceid;
    @NotNull
    @Column(name = "nom")
    private String nom;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "prix")
    @NotNull
    private Integer prix;
    /*
     * Associations.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service", cascade = CascadeType.ALL)
    private List<ServiceCommander> servicecommanderList = new ArrayList<ServiceCommander>();
    /*
     * Enumérations.
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private Unite unite;

    public Service() {
    }

    public Service(Integer serviceid) {
        this.serviceid = serviceid;
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

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getServiceid() {
        return serviceid;
    }

    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    @XmlTransient
    public List<ServiceCommander> getServicecommanderList() {
        return servicecommanderList;
    }

    public void setServicecommanderList(List<ServiceCommander> servicecommanderList) {
        this.servicecommanderList = servicecommanderList;
    }

    public void addServiceCommanderList(ServiceCommander servicecommander) {
        if (servicecommander == null) {
            return;
        }
        if (servicecommanderList == null) {
            servicecommanderList = new ArrayList<ServiceCommander>();
        }
        if (!servicecommanderList.contains(servicecommander)) {
            servicecommanderList.add(servicecommander);
        }
    }

    public void removeServiceCommanderList(ServiceCommander servicecommander) {
        if (servicecommander == null || servicecommanderList == null) {
            return;
        }
        if (servicecommanderList.contains(servicecommander)) {
            servicecommanderList.remove(servicecommander);
            servicecommander.setService(null);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.serviceid != null ? this.serviceid.hashCode() : 0);
        hash = 59 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 59 * hash + (this.nom != null ? this.nom.hashCode() : 0);
        hash = 59 * hash + (this.prix != null ? this.prix.hashCode() : 0);
        hash = 59 * hash + (this.unite != null ? this.unite.hashCode() : 0);
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
        final Service other = (Service) obj;
        if (this.serviceid != other.serviceid && (this.serviceid == null || !this.serviceid.equals(other.serviceid))) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
            return false;
        }
        if (this.prix != other.prix && (this.prix == null || !this.prix.equals(other.prix))) {
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
