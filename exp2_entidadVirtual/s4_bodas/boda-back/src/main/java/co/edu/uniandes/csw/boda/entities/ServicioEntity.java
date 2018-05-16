/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author aj.ortiz10
 */
@Entity
public class ServicioEntity extends BaseEntity implements Serializable {

    /**
     * Atributo privado descripcion.
     */
    private String descripcion;

    /**
     * Coleccion privada de proveedores.
     */
    @PodamExclude
    @ManyToMany
    private List<ProveedorEntity> proveedores;

    /**
     * Atributo privado image.
     */
    private String image;

    /**
     * Obtiene el atributo descripcion.
     *
     * @return atributo descripcion.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece el valor del atributo descripcion.
     *
     * @param descripcion nuevo valor del atributo
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la colección de proveedores.
     *
     * @return colección proveedores.
     */
    public List<ProveedorEntity> getProveedores() {
        return proveedores;
    }

    /**
     * Establece el valor de la colección de proveedores.
     *
     * @param proveedores nuevo valor de la colección.
     */
    public void setProveedores(List<ProveedorEntity> proveedores) {
        this.proveedores = proveedores;
    }

    /**
     * Obtiene el atributo image.
     *
     * @return atributo image.
     */
    public String getImage() {
        return image;
    }

    /**
     * Establece el valor del atributo image.
     *
     * @param image nuevo valor del atributo
     */
    public void setImage(String image) {
        this.image = image;
    }
}
