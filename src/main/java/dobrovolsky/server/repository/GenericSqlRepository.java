package dobrovolsky.server.repository;

import dobrovolsky.server.domain.EntityObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class GenericSqlRepository<T extends EntityObject, PK> implements Repository<T, PK> {

    @PersistenceContext
    protected EntityManager entityManager;
    protected final Class<T> entityClass;

    protected GenericSqlRepository(Class<T> eClass) {
        this.entityClass = eClass;
    }

    @Override
    public void save(T entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public T findById(PK id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager
                .createQuery("select u from " + entityClass.getSimpleName() + " as u", entityClass)
                .getResultList();
    }
}