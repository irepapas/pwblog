/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Irene
 */
@Entity
@Table(name = "commento")
public class Commento extends AbstractEntity implements Serializable {
    
    @Id
    @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", initialValue = 100, allocationSize = 1)
    @GeneratedValue(generator = "comment_sequence")
    private Long id; 
    
    public enum Valutazione {
        PESSIMO, SUFFICIENTE, MEDIO, BUONO, OTTIMO
    }
    
    @Column(nullable = false)
    private String textComment;
    @Column(nullable = false)
    private Valutazione valutazione;
    @ManyToOne(optional = false)
    private Long articoloId;
    @ManyToOne(optional = false)
    private Long userId;
    @ManyToOne
    private Commento commentRefId;
    private boolean visibile = true;

    public Commento() {
    }

    public Commento(String textComment, Valutazione valutazione) {
        this.textComment = textComment;
        this.valutazione = valutazione;
    }
    
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public Valutazione getValutazione() {
        return valutazione;
    }

    public void setValutazione(Valutazione valutazione) {
        this.valutazione = valutazione;
    }

    public Long getArticoloId() {
        return articoloId;
    }

    public void setArticoloId(Long articoloId) {
        this.articoloId = articoloId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Commento getCommentRefId() {
        return commentRefId;
    }

    public void setCommentRefId(Commento commentRefId) {
        this.commentRefId = commentRefId;
    }

    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commento other = (Commento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

}
