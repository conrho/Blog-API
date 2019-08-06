package net.atos.wl.blog.business.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Dozer object mapper for mapping DTO to entity and vice versa.
 */
@Component
public class ObjectMapper {

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    /**
     * Method to create instance of destination class and map the value of
     * source object to destination.
     * 
     * @param source
     *            Object.
     * @param destinationClass
     *            Class<T>.
     * @return destination class instance with populated value.
     */
    public <T> T map(final Object source, final Class<T> destinationClass) {
        return this.dozerBeanMapper.map(source, destinationClass);
    }

    /**
     * Method to map information from source object to destination object.
     * 
     * @param source
     *            Object.
     * @param destination
     *            Object.
     */
    public void map(final Object source, final Object destination) {
        this.dozerBeanMapper.map(source, destination);
    }
}
