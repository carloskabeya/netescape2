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
@Table(name = "typemateriel")
@DiscriminatorValue(value = "TYPEMATERIEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeMateriel.findAll", query = "SELECT t FROM TypeMateriel t"),
    @NamedQuery(name = "TypeMateriel.findAllMateriel", query = "SELECT t.materielList FROM TypeMateriel t"),
    @NamedQuery(name = "TypeMateriel.findAllNombreRessource", query = "SELECT t.nombreressourceList FROM TypeMateriel t"),
    @NamedQuery(name = "TypeMateriel.findByTyperessourceid", query = "SELECT t FROM TypeMateriel t WHERE t.typeressourceid = :typeressourceid")})
public class TypeMateriel extends TypeRessource {

    /*
     * Associations.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typemateriel", cascade = CascadeType.ALL)
    private List<Materiel> materielList = new ArrayList<Materiel>();

    public TypeMateriel() {
    }

    public TypeMateriel(List<Materiel> materielList, String description,
            String nom, List<Ressource> ressourceList, List<NombreRessource> nombreressourceList) {
        super(description, nom, ressourceList, nombreressourceList);
        this.materielList = materielList;
    }

    @XmlTransient
    public List<Materiel> getMaterielList() {
        return materielList;
    }

    public void setMaterielList(List<Materiel> materielList) {
        this.materielList = materielList;
    }

    public void addMaterielList(Materiel materiel) {
        if (materiel == null) {
            return;
        }
        if (materielList == null) {
            materielList = new ArrayList<Materiel>();
        }
        if (!materielList.contains(materiel)) {
            materielList.add(materiel);
            materiel.setTypemateriel(this);
        }
    }

    public void removeMaterielList(Materiel materiel) {
        if (materiel == null || materielList == null) {
            return;
        }
        if (materielList.contains(materiel)) {
            materielList.remove(materiel);
            materiel.setTypemateriel(null);
        }
    }
}
