/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.control;

import blog.entity.Tag;
import java.util.List;
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
public class TagSotre {
    
    @PersistenceContext
    private EntityManager em;

    public Tag create(Tag tag) {
       return em.merge(tag);
    }
    public List<Tag> search(String tagName) {
        return em.createQuery("select e from Tag e where e.tagName=: tagName", Tag.class)
                .getResultList();
    }
}
