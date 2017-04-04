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
@Table(name = "optionsupplementairecommander")
@SequenceGenerator(name = "seq_option_commander", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OptionSupplementaireCommander.findAll", query = "SELECT o FROM OptionSupplementaireCommander o"),
    @NamedQuery(name = "OptionSupplementaireCommander.findByOptioncommanderid", query = "SELECT o FROM OptionSupplementaireCommander o WHERE o.optioncommanderid = :optioncommanderid")})
public class OptionSupplementaireCommander implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_option_commander")
    @Basic(optional = false)
    @Column(name = "optioncommanderid")
    private Integer optioncommanderid;
    @Column(name = "nombre")
    private Integer nombre;
    @Column(name = "remise")
    private Long remise;
    /**
     * Associations
     */
    @JoinColumn(name = "optionid", referencedColumnName = "optionid")
    @ManyToOne
    private OptionSupplementaire option;
    @JoinColumn(name = "composantcommanderid", referencedColumnName = "composantcommanderid")
    @ManyToOne
    private ComposantCommander composantcommander;

    public OptionSupplementaireCommander() {
    }

    public OptionSupplementaireCommander(Integer nombre, OptionSupplementaire option, ComposantCommander composantcommander) {
        this.nombre = nombre;
        this.option = option;
        this.composantcommander = composantcommander;
    }

    public OptionSupplementaireCommander(Integer optioncommanderid) {
        this.optioncommanderid = optioncommanderid;
    }

    public Integer getOptioncommanderid() {
        return optioncommanderid;
    }

    public void setOptioncommanderid(Integer optioncommanderid) {
        this.optioncommanderid = optioncommanderid;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public Long getRemise() {
        return remise;
    }

    public void setRemise(Long remise) {
        this.remise = remise;
    }

    public OptionSupplementaire getOption() {
        return option;
    }

    public void setOption(OptionSupplementaire option) {
        this.option = option;
    }

    public ComposantCommander getComposantcommander() {
        return composantcommander;
    }

    public void setComposantcommander(ComposantCommander composantcommander) {
        this.composantcommander = composantcommander;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 73 * hash + (this.option != null ? this.option.hashCode() : 0);
        hash = 73 * hash + (this.composantcommander != null ? this.composantcommander.hashCode() : 0);
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
        final OptionSupplementaireCommander other = (OptionSupplementaireCommander) obj;
        if (this.nombre != other.nombre && (this.nombre == null || !this.nombre.equals(other.nombre))) {
            return false;
        }
        if (this.option != other.option && (this.option == null || !this.option.equals(other.option))) {
            return false;
        }
        if (this.composantcommander != other.composantcommander && (this.composantcommander == null || !this.composantcommander.equals(other.composantcommander))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.tfe.netescape.OptionCommander[ optioncommanderid=" + optioncommanderid + " ]";
    }
}
