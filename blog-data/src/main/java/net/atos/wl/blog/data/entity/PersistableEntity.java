package net.atos.wl.blog.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import net.atos.wl.blog.common.dto.State;

/**
 * Abstract class with common properties for entities.
 */
@MappedSuperclass
public abstract class PersistableEntity implements Serializable {

    /**
     * Generated serial version Id.
     */
    private static final long serialVersionUID = 3545323257834288031L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Long version = 0L;

    @Enumerated(EnumType.STRING)
    @Column(name = "record_state", nullable = false)
    private State recordState = State.ACTIVE;

    /**
     * Getter for id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for id.
     *
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Setter for version.
     *
     * @param version
     *            the version to set
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Getter for recordState.
     *
     * @return the recordState
     */
    public State getRecordState() {
        return recordState;
    }

    /**
     * Setter for recordState.
     *
     * @param recordState
     *            the recordState to set
     */
    public void setRecordState(State recordState) {
        this.recordState = recordState;
    }

}
