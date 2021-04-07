/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.control;

import blog.dto.ArticoloUpdate;
import blog.entity.Articolo;
import blog.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author Irene
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class ArticoloStore {
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    @ConfigProperty(name = "maxResult", defaultValue = "10")
    int maxResult;
    
    @Inject
    CommentiStore commentiStore;
    
    @Inject
    TagSotre tagStore;
    
    
    public Articolo create(Articolo art,ArrayList<String> tags){
        Articolo create = em.merge(art);
        Tag tag = new Tag();
        for(String z : tags){
            tag.setTagName(z);
            tag.setArticoloId(art.getId());
            tagStore.create(tag);
        }
        return create;
    }
    
    public Articolo update(Articolo art, ArticoloUpdate u){
        art.setTitolo(u);
        art.setArticolo(u);
        return em.merge(art);
    }
    
    public Optional<Articolo> find(Long id) {
        Articolo found = em.find(Articolo.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }
    
    public List<Articolo> findByTitle(String titolo) {
        return em.createQuery("select e from Articolo e where e.titolo = : titolo", Articolo.class)
                .setParameter("titolo", titolo == null ? "%" : "%" + titolo + "%")
                .getResultList();
    }
    
   
   
}
