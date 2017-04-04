/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import be.netescape.web.enums.Provenance;
import be.netescape.web.enums.RaisonSociale;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "client")
@SequenceGenerator(name = "seq_client", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findAllByOffreStatut", query = "SELECT c FROM Client c, Offre o WHERE o.statut = :statut"),
    @NamedQuery(name = "Client.findAllByVille", query = "SELECT c FROM Client c WHERE c.adresse.ville LIKE :ville"),
    @NamedQuery(name = "Client.findByDenomination", query = "SELECT c FROM Client c WHERE c.denomination LIKE :denomination"),
    @NamedQuery(name = "Client.findByPrive", query = "SELECT c FROM Client c WHERE c.prive = :prive"),
    @NamedQuery(name = "Client.findByProspect", query = "SELECT c FROM Client c WHERE c.prospect = :prospect"),
    @NamedQuery(name = "Client.findByTva", query = "SELECT c FROM Client c WHERE c.tva = :tva"),
    @NamedQuery(name = "Client.findByProvenance", query = "SELECT c FROM Client c WHERE c.provenance = :provenance"),
    @NamedQuery(name = "Client.findByRaisonSociale", query = "SELECT c FROM Client c WHERE c.raisonSociale = :raisonsociale"),
    @NamedQuery(name = "Client.findByClientid", query = "SELECT c FROM Client c WHERE c.clientid = :clientid")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_client")
    @Basic(optional = false)
    @Column(name = "clientid")
    private Integer clientid;
    @Column(name = "denomination")
    @NotNull
    private String denomination;
    @Column(name = "prive")
    private Boolean prive;
    @Column(name = "prospect", columnDefinition = "boolean default 'true'")
    private Boolean prospect;
    @Column(name = "tva")
    @NotEmpty
    private String tva;
    /*
     * Associations.
     */
    @Embedded
    @Valid
    private Adresse adresse = new Adresse();
    @JoinColumn(name = "personneid", referencedColumnName = "ressourceid")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @Valid
    private Personne contact = new Personne();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private List<Offre> offreList = new ArrayList<Offre>();
    /*
     * Enumérations
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private RaisonSociale raisonSociale;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Provenance provenance;

    public Client() {
    }

    public Client(Integer clientid) {
        this.clientid = clientid;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Boolean getPrive() {
        return prive;
    }

    public void setPrive(Boolean prive) {
        this.prive = prive;
    }

    public Boolean getProspect() {
        return prospect;
    }

    public void setProspect(Boolean prospect) {
        this.prospect = prospect;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public RaisonSociale getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(RaisonSociale raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public Provenance getProvenance() {
        return provenance;
    }

    public void setProvenance(Provenance provenance) {
        this.provenance = provenance;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Personne getContact() {
        return contact;
    }

    public void setContact(Personne contact) {
        this.contact = contact;
    }

    @XmlTransient
    public List<Offre> getOffreList() {
        return offreList;
    }

    public void setOffreList(List<Offre> offreList) {
        this.offreList = offreList;
    }

    public void addOffreList(Offre offre) {
        if (offre == null) {
            return;
        }
        if (offreList == null) {
            offreList = new ArrayList<Offre>();
        }
        if (!offreList.contains(offre)) {
            offreList.add(offre);
            offre.setClient(this);
        }
    }

    public void removeOffreList(Offre offre) {
        if (offre == null || offreList == null) {
            return;
        }
        if (offreList.contains(offre)) {
            offreList.remove(offre);
            offre.setClient(null);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.clientid != null ? this.clientid.hashCode() : 0);
        hash = 41 * hash + (this.denomination != null ? this.denomination.hashCode() : 0);
        hash = 41 * hash + (this.prive != null ? this.prive.hashCode() : 0);
        hash = 41 * hash + (this.prospect != null ? this.prospect.hashCode() : 0);
        hash = 41 * hash + (this.tva != null ? this.tva.hashCode() : 0);
        hash = 41 * hash + (this.adresse != null ? this.adresse.hashCode() : 0);
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
        final Client other = (Client) obj;
        if (this.clientid != other.clientid && (this.clientid == null || !this.clientid.equals(other.clientid))) {
            return false;
        }
        if ((this.denomination == null) ? (other.denomination != null) : !this.denomination.equals(other.denomination)) {
            return false;
        }
        if (this.prive != other.prive && (this.prive == null || !this.prive.equals(other.prive))) {
            return false;
        }
        if (this.prospect != other.prospect && (this.prospect == null || !this.prospect.equals(other.prospect))) {
            return false;
        }
        if ((this.tva == null) ? (other.tva != null) : !this.tva.equals(other.tva)) {
            return false;
        }
        if (this.adresse != other.adresse && (this.adresse == null || !this.adresse.equals(other.adresse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.tfe.netescape.Client[ clientid=" + clientid + " ]";
    }
}
