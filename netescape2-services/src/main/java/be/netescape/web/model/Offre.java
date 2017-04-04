/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import be.netescape.web.enums.Langue;
import be.netescape.web.enums.Sexe;
import be.netescape.web.enums.Statut;
import be.netescape.web.enums.Unite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "offre")
@SequenceGenerator(name = "seq_offre", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offre.findAll", query = "SELECT o FROM Offre o"),
    @NamedQuery(name = "Offre.findAllByPersonne", query = "SELECT o FROM Offre o WHERE o.personne.nom LIKE :nom"),
    @NamedQuery(name = "Offre.findAllByClient", query = "SELECT o FROM Offre o WHERE o.client.denomination LIKE :denomination"),
    @NamedQuery(name = "Offre.findAllByClientProspect", query = "SELECT o FROM Offre o WHERE o.client.prospect = :prospect"),
    @NamedQuery(name = "Offre.findAllByClientVille", query = "SELECT o FROM Offre o WHERE o.client.adresse.ville LIKE :ville"),
    @NamedQuery(name = "Offre.findByAgemaximum", query = "SELECT o FROM Offre o WHERE o.agemaximum = :agemaximum"),
    @NamedQuery(name = "Offre.findByAgeminimum", query = "SELECT o FROM Offre o WHERE o.ageminimum = :ageminimum"),
    @NamedQuery(name = "Offre.findByBudget", query = "SELECT o FROM Offre o WHERE o.budget = :budget"),
    @NamedQuery(name = "Offre.findByCreation", query = "SELECT o FROM Offre o WHERE o.creation = :creation"),
    @NamedQuery(name = "Offre.findByDelais", query = "SELECT o FROM Offre o WHERE o.delais = :delais"),
    @NamedQuery(name = "Offre.findByObjectif", query = "SELECT o FROM Offre o WHERE o.objectif LIKE :objectif"),
    @NamedQuery(name = "Offre.findByParticipant", query = "SELECT o FROM Offre o WHERE o.participant = :participant"),
    @NamedQuery(name = "Offre.findByRealisation", query = "SELECT o FROM Offre o WHERE o.realisation = :realisation"),
    @NamedQuery(name = "Offre.findByReference", query = "SELECT o FROM Offre o WHERE o.reference = :reference"),
    @NamedQuery(name = "Offre.findByRemarque", query = "SELECT o FROM Offre o WHERE o.remarque LIKE :remarque"),
    @NamedQuery(name = "Offre.findByRendezvous", query = "SELECT o FROM Offre o WHERE o.rendezvous = :rendezvous"),
    @NamedQuery(name = "Offre.findByLangue", query = "SELECT o FROM Offre o WHERE o.langue = :langue"),
    @NamedQuery(name = "Offre.findBySexe", query = "SELECT o FROM Offre o WHERE o.sexe = :sexe"),
    @NamedQuery(name = "Offre.findByStatut", query = "SELECT o FROM Offre o WHERE o.statut = :statut"),
    @NamedQuery(name = "Offre.findByOffreid", query = "SELECT o FROM Offre o WHERE o.offreid = :offreid")})
public class Offre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_offre")
    @Basic(optional = false)
    @Column(name = "offreid")
    private Integer offreid;
    @Column(name = "agemaximum")
    @NotNull
    private Integer agemaximum;
    @Column(name = "ageminimum")
    @NotNull
    private Integer ageminimum;
    @Column(name = "budget")
    @NotNull
    private Integer budget;
    @Column(name = "creation")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date creation;
    @Column(name = "delais")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date delais;
    @Column(name = "objectif")
    @Size(max = 255)
    private String objectif;
    @Column(name = "participant")
    @NotNull
    private Integer participant;
    @Column(name = "realisation")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date realisation;
    @Column(name = "reference")
    private String reference;
    @Column(name = "remarque")
    @Size(max = 255)
    private String remarque;
    @Column(name = "rendezvous")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date rendezvous;
    @Transient
    private int montant;
    @Transient
    private boolean lock;
    /*
     * Associations.
     */
    @JoinColumn(name = "personneid", referencedColumnName = "ressourceid")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Personne personne;
    @JoinColumn(name = "clientid", referencedColumnName = "clientid")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Client client;
    @Embedded
    private Evaluation evaluationglobal;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offre", cascade = CascadeType.ALL)
    private List<ServiceCommander> servicecommanderList = new ArrayList<ServiceCommander>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offre", cascade = CascadeType.ALL)
    private List<ActiviteCommander> activitecommanderList = new ArrayList<ActiviteCommander>();
    ;
    /*
     * Enumérations.
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private Langue langue;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Sexe sexe;
    @Enumerated(EnumType.STRING)
    private Statut statut;

    public Offre() {
    }

    public Offre(Integer offreid) {
        this.offreid = offreid;
    }

    public int getMontant() {
        for (ActiviteCommander ac : activitecommanderList) {
            //le prix des activités.
            this.montant = this.montant + ac.getActivite().getPrix();
            for (ComposantCommander cc : ac.getComposantcommanderList()) {
                //le prix des eventuels options supplémentaires
                for (OptionSupplementaireCommander osc : cc.getOptioncommanderList()) {
                    if (osc.getOption().getUnite().equals(Unite.PERSONNE) || osc.getOption().getUnite().equals(Unite.PIECE)) {
                        this.montant = this.montant + (osc.getOption().getPrix() * osc.getNombre());
                    } else {
                        this.montant = this.montant + osc.getOption().getPrix();
                    }
                }
            }
        }
        for (ServiceCommander sc : servicecommanderList) {
            //le prix des services.
            if (sc.getService().getUnite().equals(Unite.PERSONNE) || sc.getService().getUnite().equals(Unite.PIECE)) {
                this.montant = this.montant + (sc.getService().getPrix() * this.getParticipant());
            } else {
                this.montant = this.montant + sc.getService().getPrix();
            }
        }
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Integer getAgemaximum() {
        return agemaximum;
    }

    public void setAgemaximum(Integer agemaximum) {
        this.agemaximum = agemaximum;
    }

    public Integer getAgeminimum() {
        return ageminimum;
    }

    public void setAgeminimum(Integer ageminimum) {
        this.ageminimum = ageminimum;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getDelais() {
        return delais;
    }

    public void setDelais(Date delais) {
        this.delais = delais;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public Integer getParticipant() {
        return participant;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public Date getRealisation() {
        return realisation;
    }

    public void setRealisation(Date realisation) {
        this.realisation = realisation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Date getRendezvous() {
        return rendezvous;
    }

    public void setRendezvous(Date rendezvous) {
        this.rendezvous = rendezvous;
    }

    public Integer getOffreid() {
        return offreid;
    }

    public void setOffreid(Integer offreid) {
        this.offreid = offreid;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
            servicecommander.setOffre(null);
        }
    }

    public Evaluation getEvaluationglobal() {
        return evaluationglobal;
    }

    public void setEvaluationglobal(Evaluation evaluationglobal) {
        this.evaluationglobal = evaluationglobal;
    }

    @XmlTransient
    public List<ActiviteCommander> getActivitecommanderList() {
        return activitecommanderList;
    }

    public void setActivitecommanderList(List<ActiviteCommander> activitecommanderList) {
        this.activitecommanderList = activitecommanderList;
    }

    public void addActiviteCommanderList(ActiviteCommander activitecommander) {
        if (activitecommander == null) {
            return;
        }
        if (activitecommanderList == null) {
            activitecommanderList = new ArrayList<ActiviteCommander>();
        }
        if (!activitecommanderList.contains(activitecommander)) {
            activitecommanderList.add(activitecommander);
            activitecommander.setOffre(this);
        }
    }

    public void removeActiviteCommanderList(ActiviteCommander activitecommander) {
        if (activitecommander == null || activitecommanderList == null) {
            return;
        }
        if (activitecommanderList.contains(activitecommander)) {
            activitecommanderList.remove(activitecommander);
            activitecommander.setOffre(null);
        }
    }

    public boolean isLock() {
        if (statut.equals(statut.REALISER)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.offreid != null ? this.offreid.hashCode() : 0);
        hash = 83 * hash + (this.agemaximum != null ? this.agemaximum.hashCode() : 0);
        hash = 83 * hash + (this.ageminimum != null ? this.ageminimum.hashCode() : 0);
        hash = 83 * hash + (this.budget != null ? this.budget.hashCode() : 0);
        hash = 83 * hash + (this.creation != null ? this.creation.hashCode() : 0);
        hash = 83 * hash + (this.delais != null ? this.delais.hashCode() : 0);
        hash = 83 * hash + (this.objectif != null ? this.objectif.hashCode() : 0);
        hash = 83 * hash + (this.participant != null ? this.participant.hashCode() : 0);
        hash = 83 * hash + (this.realisation != null ? this.realisation.hashCode() : 0);
        hash = 83 * hash + (this.reference != null ? this.reference.hashCode() : 0);
        hash = 83 * hash + (this.remarque != null ? this.remarque.hashCode() : 0);
        hash = 83 * hash + (this.rendezvous != null ? this.rendezvous.hashCode() : 0);
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
        final Offre other = (Offre) obj;
        if (this.offreid != other.offreid && (this.offreid == null || !this.offreid.equals(other.offreid))) {
            return false;
        }
        if (this.agemaximum != other.agemaximum && (this.agemaximum == null || !this.agemaximum.equals(other.agemaximum))) {
            return false;
        }
        if (this.ageminimum != other.ageminimum && (this.ageminimum == null || !this.ageminimum.equals(other.ageminimum))) {
            return false;
        }
        if (this.budget != other.budget && (this.budget == null || !this.budget.equals(other.budget))) {
            return false;
        }
        if (this.creation != other.creation && (this.creation == null || !this.creation.equals(other.creation))) {
            return false;
        }
        if (this.delais != other.delais && (this.delais == null || !this.delais.equals(other.delais))) {
            return false;
        }
        if ((this.objectif == null) ? (other.objectif != null) : !this.objectif.equals(other.objectif)) {
            return false;
        }
        if (this.participant != other.participant && (this.participant == null || !this.participant.equals(other.participant))) {
            return false;
        }
        if (this.realisation != other.realisation && (this.realisation == null || !this.realisation.equals(other.realisation))) {
            return false;
        }
        if ((this.reference == null) ? (other.reference != null) : !this.reference.equals(other.reference)) {
            return false;
        }
        if ((this.remarque == null) ? (other.remarque != null) : !this.remarque.equals(other.remarque)) {
            return false;
        }
        if (this.rendezvous != other.rendezvous && (this.rendezvous == null || !this.rendezvous.equals(other.rendezvous))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.tfe.netescape.Offre[ offreid=" + offreid + " ]";
    }
}
