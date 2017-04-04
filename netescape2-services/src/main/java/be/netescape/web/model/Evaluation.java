/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.netescape.web.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos
 */
@Embeddable
public class Evaluation implements Serializable {

    @Size(max = 255)
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "note")
    private Integer note;

    public Evaluation() {
    }

    public Evaluation(String commentaire, Integer note) {
        this.commentaire = commentaire;
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.commentaire != null ? this.commentaire.hashCode() : 0);
        hash = 97 * hash + (this.note != null ? this.note.hashCode() : 0);
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
        final Evaluation other = (Evaluation) obj;
        if ((this.commentaire == null) ? (other.commentaire != null) : !this.commentaire.equals(other.commentaire)) {
            return false;
        }
        if (this.note != other.note && (this.note == null || !this.note.equals(other.note))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
