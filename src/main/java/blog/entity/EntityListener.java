/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Irene
 */
public class EntityListener {
    
    @PrePersist
    public void onPrePersist(AbstractEntity e){
        e.setCreatedOn(LocalDateTime.now());
    }
    
    @PreUpdate
    public void onPreUpdate(AbstractEntity e){
        e.setModifiedOn(LocalDateTime.now());
    }
    
}
