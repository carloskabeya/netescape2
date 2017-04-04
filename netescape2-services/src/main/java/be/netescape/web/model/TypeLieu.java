/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "typelieu")
@DiscriminatorValue(value = "TYPELIEU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeLieu.findAll", query = "SELECT t FROM TypeLieu t"),
    @NamedQuery(name = "TypeLieu.findAllLieu", query = "SELECT t.lieuList FROM TypeLieu t"),
    @NamedQuery(name = "TypeLieu.findAllNombreRessource", query = "SELECT t.nombreressourceList FROM TypeLieu t"),
    @NamedQuery(name = "TypeLieu.findByTyperessourceid", query = "SELECT t FROM TypeLieu t WHERE t.typeressourceid = :typeressourceid")})
public class TypeLieu extends TypeRessource {
    /*
     * Associations.
     */

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typelieu", cascade = CascadeType.ALL)
    private List<Lieu> lieuList = new ArrayList<Lieu>();

    public TypeLieu() {
        super();
    }

    public TypeLieu(List<Lieu> lieuList, String description, String nom,
            List<Ressource> ressourceList, List<NombreRessource> nombreressourceList) {
        super(description, nom, ressourceList, nombreressourceList);
        this.lieuList = lieuList;
    }

    @XmlTransient
    public List<Lieu> getLieuList() {
        return lieuList;
    }

    public void setLieuList(List<Lieu> lieuList) {
        this.lieuList = lieuList;
    }

    public void addLieuList(Lieu lieu) {
        if (lieu == null) {
            return;
        }
        if (lieuList == null) {
            lieuList = new ArrayList<Lieu>();
        }
        if (!lieuList.contains(lieu)) {
            lieuList.add(lieu);
            lieu.setTypelieu(this);
        }
    }

    public void removeLieuList(Lieu lieu) {
        if (lieu == null || lieuList == null) {
            return;
        }
        if (lieuList.contains(lieu)) {
            lieuList.remove(lieu);
            lieu.setTypelieu(null);
        }
    }
}
