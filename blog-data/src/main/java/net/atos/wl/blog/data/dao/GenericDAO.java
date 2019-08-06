package net.atos.wl.blog.data.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface with CRUD operations common to all DAO's
 * 
 * @param <T>
 *            JPA entity.
 */
public interface GenericDAO<T extends Serializable> {

    /**
     * Method create a new record in DB.
     * 
     * @param entity
     *            T to be created.
     */
    void create(final T entity);

    /**
     * Method to fetch details for the given Id.
     * 
     * @param id
     *            Integer.
     * @return T entity.
     */
    T read(final Integer id);

    /**
     * Method to update details.
     * 
     * @param entity
     *            T to be updated.
     * @return updated entity.
     */
    T update(final T entity);

    /**
     * Method to delete the record from DB.
     * 
     * @param entity
     *            entity to be deleted.
     */
    void delete(final T entity);

    /**
     * Method to delete entity based on the Id.
     * 
     * @param entityId
     *            int id of the entity to be deleted.
     */
    void deleteById(final int entityId);

    /**
     * Method to find all entities.
     * 
     * @return List of entities.
     */
    List<T> findAll();
}
