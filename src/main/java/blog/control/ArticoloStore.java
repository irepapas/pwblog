/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.control;

import blog.entity.Articolo;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Irene
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class ArticoloStore {
    
    @PersistenceContext
    private EntityManager em;
    
    
    public Articolo create(Articolo art){
        return em.merge(art);
    }
    
    public Optional<Articolo> find(Long id) {
        Articolo found = em.find(Articolo.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }
    
    public List<Articolo> findByUser(Long userId) {
        return em.createQuery("select e from Articolo e where e.email.id= :userId", Articolo.class)
                .setParameter("userId", userId)
                .getResultList();
    }

   
}
