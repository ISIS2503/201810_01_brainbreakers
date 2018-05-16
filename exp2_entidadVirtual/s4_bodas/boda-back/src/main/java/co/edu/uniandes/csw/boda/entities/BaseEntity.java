package co.edu.uniandes.csw.boda.entities;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Generic entity with ID and name fields to inherit from..
 *
 * This entity sets a standar of fields and functions all entities in a project
 * should have. For example, all entities should be compared by ID when not
 * null, otherwise use the object equals method.
 *
 * @author ISIS2603
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /**
     * Atributo privado id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo privado name
     */
    private String name;

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     */
    public void setName(String name) {
        this.name = name;
    }

}
