/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "personne")
@DiscriminatorValue(value = "PERSONNE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p"),
    @NamedQuery(name = "Personne.findAllReservation", query = "SELECT p.reservationList FROM Personne p"),
    @NamedQuery(name = "Personne.findAllOffre", query = "SELECT p.offreList FROM Personne p"),
    @NamedQuery(name = "Personne.findAllTypeProfil", query = "SELECT p.typeprofilList FROM Personne p"),
    @NamedQuery(name = "Personne.findByEmail", query = "SELECT p FROM Personne p WHERE p.email LIKE :email"),
    @NamedQuery(name = "Personne.findByGsm", query = "SELECT p FROM Personne p WHERE p.gsm LIKE :gsm"),
    @NamedQuery(name = "Personne.findByIdentifiant", query = "SELECT p FROM Personne p WHERE p.identifiant LIKE :identifiant"),
    @NamedQuery(name = "Personne.findByNom", query = "SELECT p FROM Personne p WHERE p.nom LIKE :nom"),
    @NamedQuery(name = "Personne.findByMotdePasse", query = "SELECT p FROM Personne p WHERE p.motdepasse LIKE :motdepasse"),
    @NamedQuery(name = "Personne.findByPrenom", query = "SELECT p FROM Personne p WHERE p.prenom LIKE :prenom"),
    @NamedQuery(name = "Personne.findByTelephone", query = "SELECT p FROM Personne p WHERE p.telephone LIKE :telephone")})
public class Personne extends Ressource {

    @Column(name = "nom")
    @NotNull
    private String nom;
    @Column(name = "prenom")
    @NotNull
    private String prenom;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "fax")
    private String fax;
    @Column(name = "email")
    @Email
    @NotEmpty
    private String email;
    @Column(name = "gsm")
    @NotEmpty
    private String gsm;
    @NotEmpty
    @Column(name = "identifiant")
    private String identifiant;
    @Column(name = "image")
    @Lob
    private byte[] image;
    @NotEmpty
    @Column(name = "motdepasse")
    private String motdepasse;
    @Column(name = "salt")
    private byte[] salt;

    /*
     * Associations.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personnetypeprofils", joinColumns = {
        @JoinColumn(name = "ressourceid", nullable = false, updatable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "typeressourceid", nullable = false, updatable = false)})
    private List<TypeProfil> typeprofilList = new ArrayList<TypeProfil>();
    @JoinColumn(name = "clientid", referencedColumnName = "clientid")
    @OneToOne(mappedBy = "contact", fetch = FetchType.LAZY)
    private Client client;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personne")
    private List<Offre> offreList = new ArrayList<Offre>();

    public Personne() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<TypeProfil> getTypeprofilList() {
        return typeprofilList;
    }

    public void setTypeprofilList(List<TypeProfil> typeprofilList) {
        this.typeprofilList = typeprofilList;
    }

    public void addTypeProfil(TypeProfil aThis) {
        if (aThis == null) {
            return;
        }
        if (this.typeprofilList == null) {
            this.typeprofilList = new ArrayList<TypeProfil>();
        }
        this.typeprofilList.add(aThis);
        if (!aThis.getPersonneList().contains(this)) {
            aThis.getPersonneList().add(this);
        }
    }

    public void removeTypeProfil(TypeProfil aThis) {
        if (aThis == null) {
            return;
        }
        if (this.typeprofilList == null) {
            return;
        }
        if (this.typeprofilList.contains(aThis)) {
            this.typeprofilList.remove(aThis);
        }
        if (aThis.getPersonneList().contains(this)) {
            aThis.getPersonneList().remove(this);
        }
    }

    public void removeAllTypeProfil() {
        this.typeprofilList.clear();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
            offre.setPersonne(this);
        }
    }

    public void removeOffreList(Offre offre) {
        if (offre == null || offreList == null) {
            return;
        }
        if (offreList.contains(offre)) {
            offreList.remove(offre);
            offre.setPersonne(null);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.nom != null ? this.nom.hashCode() : 0);
        hash = 97 * hash + (this.prenom != null ? this.prenom.hashCode() : 0);
        hash = 97 * hash + (this.telephone != null ? this.telephone.hashCode() : 0);
        hash = 97 * hash + (this.fax != null ? this.fax.hashCode() : 0);
        hash = 97 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 97 * hash + (this.gsm != null ? this.gsm.hashCode() : 0);
        hash = 97 * hash + (this.identifiant != null ? this.identifiant.hashCode() : 0);
        hash = 97 * hash + Arrays.hashCode(this.image);
        hash = 97 * hash + (this.motdepasse != null ? this.motdepasse.hashCode() : 0);
        hash = 97 * hash + Arrays.hashCode(this.salt);
        hash = 97 * hash + (this.typeprofilList != null ? this.typeprofilList.hashCode() : 0);
        hash = 97 * hash + (this.client != null ? this.client.hashCode() : 0);
        hash = 97 * hash + (this.offreList != null ? this.offreList.hashCode() : 0);
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
        final Personne other = (Personne) obj;
        if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
            return false;
        }
        if ((this.prenom == null) ? (other.prenom != null) : !this.prenom.equals(other.prenom)) {
            return false;
        }
        if ((this.telephone == null) ? (other.telephone != null) : !this.telephone.equals(other.telephone)) {
            return false;
        }
        if ((this.fax == null) ? (other.fax != null) : !this.fax.equals(other.fax)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.gsm == null) ? (other.gsm != null) : !this.gsm.equals(other.gsm)) {
            return false;
        }
        if ((this.identifiant == null) ? (other.identifiant != null) : !this.identifiant.equals(other.identifiant)) {
            return false;
        }
        if (!Arrays.equals(this.image, other.image)) {
            return false;
        }
        if ((this.motdepasse == null) ? (other.motdepasse != null) : !this.motdepasse.equals(other.motdepasse)) {
            return false;
        }
        if (!Arrays.equals(this.salt, other.salt)) {
            return false;
        }
        if (this.typeprofilList != other.typeprofilList && (this.typeprofilList == null || !this.typeprofilList.equals(other.typeprofilList))) {
            return false;
        }
        if (this.client != other.client && (this.client == null || !this.client.equals(other.client))) {
            return false;
        }
        if (this.offreList != other.offreList && (this.offreList == null || !this.offreList.equals(other.offreList))) {
            return false;
        }
        return true;
    }
}
