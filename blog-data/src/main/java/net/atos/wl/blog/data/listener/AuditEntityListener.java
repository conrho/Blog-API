package net.atos.wl.blog.data.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import net.atos.wl.blog.data.entity.AuditableEntity;

/**
 * Entity listener for auditing entity information.
 */
public class AuditEntityListener {

    /**
     * Audit method that would be invoked before persisting the entity to set
     * the created date.
     * 
     * @param entity
     *            Entity that will be audited.
     */
    @PrePersist
    public void prePersist(final AuditableEntity entity) {
        entity.setCreatedDate(new Date());
    }

    /**
     * Audit method that would be invoked before updating the entity to set the
     * modified date.
     * 
     * @param entity
     *            Entity that will be audited.
     */
    @PreUpdate
    public void preUpdate(final AuditableEntity entity) {
        entity.setUpdatedDate(new Date());
    }
}
