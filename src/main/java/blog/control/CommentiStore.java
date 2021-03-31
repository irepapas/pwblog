/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.control;

import blog.entity.Commento;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Irene
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class CommentiStore {
    
    @Inject
    ArticoloStore articoloStore;
    @PersistenceContext
    EntityManager em;
    
     public List<Commento> searchByArticolo(Long articoloId){
        return em.createQuery("select e from Commento e where e.articolo.id= :articoloId  order by e.createdOn", Commento.class)
                .setParameter("accountId", articoloId)
                .getResultStream()
                .collect(Collectors.toList());
    }
     public Commento create(Commento c){
         return em.merge(c);
     }
     public Optional<Commento> find(Long id) {
        Commento found = em.find(Commento.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }
    
}
