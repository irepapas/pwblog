/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import blog.dto.ArticoloCreate;
import blog.dto.ArticoloUpdate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Irene
 */
@Entity
@Table(name = "articolo")
public class Articolo extends AbstractEntity implements Serializable{
    
    @Id
    @SequenceGenerator(name = "articolo_sequence", sequenceName = "articolo_sequence", initialValue = 10, allocationSize = 1)
    @GeneratedValue(generator = "articolo_sequence")
    protected Long id;
    
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private String articolo;
   

    public Articolo() {
    }
    
    public Articolo(ArticoloCreate a){
        this.titolo = a.titolo;
        this.articolo = a.articolo;
        
    }

    public String getArticolo() {
        return articolo;
    }

    public void setArticolo(String articolo) {
        this.articolo = articolo;
    }
    
    public void setArticolo (ArticoloUpdate a){
         this.articolo = a.articolo == null ? this.articolo : a.articolo;
    }

    public String getTitlo() {
        return titolo;
    }

    public void setTitlo(String titolo) {
        this.titolo = titolo;
    }
    
    public void setTitolo (ArticoloUpdate a){
        this.titolo = a.titolo == null ? this.titolo : a.titolo;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Articolo other = (Articolo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
