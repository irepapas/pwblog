/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.control;

import blog.entity.Commento;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
public class CommentiStore {

    @Inject
    ArticoloStore articoloStore;
    @PersistenceContext
    EntityManager em;
    @Inject
    @ConfigProperty(name = "maxResult", defaultValue = "10")
    int maxResult;
    @Inject
    UserStore userStore;

    public Commento create(Commento c) {
        return em.merge(c);
    }

    public Optional<Commento> find(Long id) {
        Commento found = em.find(Commento.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

    public void nascosto(Long id) {
        Commento found = em.find(Commento.class, id);
        found.setVisibile(false);
        em.merge(found);
    }

    private TypedQuery<Commento> cercaQuery(boolean visibile, Long userId, Long articoloId, Long commentoId, Long commentRefId, LocalDateTime inizio, LocalDateTime fine) {
        return em.createQuery("select e from Commento e where e.visibile= :visibile and e.userId= :userId and e.articoloId= :articoloId"
                + " and e.id= :commentoId and e.commentRefId= :commentRefId and e.createdOn >= begin and e.createdOn <= end order by e.id ", Commento.class)
                .setParameter("visible", visibile)
                .setParameter("userId", userId == null ? "%" : userId)
                .setParameter("articoloId", articoloId == null ? "%" : articoloId)
                .setParameter("id", commentoId == null ? "%" : commentoId)
                .setParameter("commentRefId", commentRefId == null ? "%" : commentRefId)
                .setParameter("from", inizio == null ? "%" : inizio)
                .setParameter("to", fine == null ? "%" : fine);
    }

    public List<Commento> cercaArticolo(Long articoloId) {
        return cercaQuery(true, null, articoloId, null, null, null, null).getResultList();
    }

    public List<Commento> cercaPeriodo(LocalDateTime start, LocalDateTime stop) {
        return cercaQuery(true, null, null, null, null, start, stop).getResultList();
    }

    public List<Commento> cercaRefComm(Long commentRefId) {
        return cercaQuery(true, null, null, null, commentRefId, null, null).getResultList();
    }

    public List<Commento> cercaUser(Long userId) {
        return cercaQuery(true, userId, null, null, null, null, null).getResultList();
    }

    public List<Commento> cercaUserAndArticolo(Long userId, Long articoloId) {
        return cercaQuery(true, userId, articoloId, null, null, null, null).getResultList();
    }

}
