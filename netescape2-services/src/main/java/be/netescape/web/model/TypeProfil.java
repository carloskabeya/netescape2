/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "typeprofil")
@DiscriminatorValue(value = "TYPEPROFIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeProfil.findAll", query = "SELECT t FROM TypeProfil t"),
    @NamedQuery(name = "TypeProfil.findByNom", query = "SELECT t FROM TypeRessource t WHERE t.nom = :nom"),
    @NamedQuery(name = "TypeProfil.findAllPersonne", query = "SELECT t.personneList FROM TypeProfil t"),
    @NamedQuery(name = "TypeProfil.findAllNombreRessource", query = "SELECT t.nombreressourceList FROM TypeProfil t"),
    @NamedQuery(name = "TypeProfil.findByTyperessourceid", query = "SELECT t FROM TypeProfil t WHERE t.typeressourceid = :typeressourceid")})
public class TypeProfil extends TypeRessource {
    /*
     * Associations.
     */

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "typeprofilList")
    private List<Personne> personneList = new ArrayList<Personne>();

    public TypeProfil() {
    }

    public TypeProfil(List<Personne> personneList, String description,
            String nom, List<Ressource> ressourceList, List<NombreRessource> nombreressourceList) {
        super(description, nom, ressourceList, nombreressourceList);
        this.personneList = personneList;
    }

    @XmlTransient
    public List<Personne> getPersonneList() {
        return personneList;
    }

    public void setPersonneList(List<Personne> personneList) {
        this.personneList = personneList;
    }

    public void addPersonneList(Personne personne) {
        if (personne == null) {
            return;
        }
        if (personneList == null) {
            personneList = new ArrayList<Personne>();
        }
        if (!personneList.contains(personne)) {
            personneList.add(personne);
            personne.addTypeProfil(this);
        }
    }

    public void removePersonneList(Personne personne) {
        if (personne == null || personneList == null) {
            return;
        }
        if (personneList.contains(personne)) {
            personneList.remove(personne);
            personne.removeTypeProfil(this);
        }
    }
}
