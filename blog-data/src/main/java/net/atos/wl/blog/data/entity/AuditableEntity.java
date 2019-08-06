package net.atos.wl.blog.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.atos.wl.blog.data.listener.AuditEntityListener;

/**
 * Abstract class to provide entity with properties used for auditing.
 */
@MappedSuperclass
@EntityListeners(value = {AuditEntityListener.class})
public abstract class AuditableEntity extends PersistableEntity {

    /**
     * Generated serial version Id.
     */
    private static final long serialVersionUID = -3846660351249034784L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    /**
     * Getter for createdDate.
     *
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Setter for createdDate.
     *
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Getter for updatedDate.
     *
     * @return the updatedDate
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Setter for updatedDate.
     *
     * @param updatedDate
     *            the updatedDate to set
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

}
