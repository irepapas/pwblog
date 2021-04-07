/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.control;

import blog.dto.ArticoloCreate;
import blog.dto.UserUpdate;
import blog.entity.Articolo;
import blog.entity.User;
import blog.security.SecurityEncoding;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author Irene
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class UserStore {
    
    @PersistenceContext
    private EntityManager em;
    @Inject
    @ConfigProperty(name = "maxResult", defaultValue = "10")
    int maxResult;
    @Inject
    ArticoloStore articoloStore;
    @Inject
    CommentiStore commentiStore;
    
    public Optional<User> find(Long id) {
        User found = em.find(User.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }
    public Optional<User> findByEmailAndPwd(String email, String pwd) {
        try {
            User found = em.createQuery("select e from User e where e.email=: email and e.pwd=: pwd", User.class)
                    .setParameter("email", email)
                    .setParameter("pwd", SecurityEncoding.shaHash(pwd))
                    .getSingleResult();
            return Optional.of(found);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
   
    private TypedQuery<User> searchQuery(boolean bloccato, String lName) {
        return em.createQuery("select e from User e where e.bloccato= :bloccato and e.lName like :lName order by e.email ", User.class)
                .setParameter("bloccato", bloccato)
                .setParameter("lName", lName == null ? "%" : "%" + lName + "%");
    }
    
    public List<User> searchAll() {
        return searchQuery(false,null).getResultList();
    }
    
    public List<User> search(int start, int maxResult, String lName) {

        return searchQuery(false, lName)
                .setFirstResult(start)
                .setMaxResults(maxResult == 0 ? this.maxResult : maxResult)
                .getResultList();
    }
    
     public User create(User u) {
        u.setPwd(SecurityEncoding.shaHash(u.getPwd()));
        User saved = em.merge(u);
        return saved;
    }
     
     public User update(User user, UserUpdate u) {
        user.setfName(u);
        user.setlName(u);
        user.setPwd(u);
        return em.merge(user);
    }
     
     public void bloccato(Long id) {
        User found = em.find(User.class, id);
        found.setBloccato(true);
        em.merge(found);
        
    }
     public void elimina(Long id) {
        User found = em.find(User.class, id);
        found.setEliminato(true);
        em.merge(found);
        
    }
}
