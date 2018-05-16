/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author aj.ortiz10
 */
@Entity
public class ProveedorEntity extends BaseEntity implements Serializable {

    /**
     * Atributo privado especialidad.
     */
    private String especialidad;

    /**
     * Atributo privado direccion de imagen
     */
    private String imagen;

    /**
     * Coleccion privada de servicios.
     */
    @PodamExclude
    @ManyToMany(mappedBy = "proveedores")
    private List<ServicioEntity> servicios;

    /**
     * Coleccion privada de opcionesServicio.
     */
    @PodamExclude
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpcionServicioEntity> opcionesServicio;

    /**
     * Obtiene el atributo especialidad.
     *
     * @return atributo especialidad.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece el valor del atributo especialidad.
     *
     * @param especialidad nuevo valor del atributo
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Retorna la direccion de la imagen del proveedor
     *
     * @return atributo imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Actualiza la dirección de la imagen del proveedor
     *
     * @param imagen
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene la colección de servicios.
     *
     * @return colección servicios.
     */
    public List<ServicioEntity> getServicios() {
        return servicios;
    }

    /**
     * Establece el valor de la colección de servicios.
     *
     * @param servicios nuevo valor de la colección.
     */
    public void setServicios(List<ServicioEntity> servicios) {
        this.servicios = servicios;
    }

    /**
     * Obtiene la colección de opcionesServicio.
     *
     * @return colección opcionesServicio.
     */
    public List<OpcionServicioEntity> getOpcionesServicio() {
        return opcionesServicio;
    }

    /**
     * Establece el valor de la colección de opcionesServicio.
     *
     * @param servicio nuevo valor de la colección.
     */
    public void setOpcionesServicio(List<OpcionServicioEntity> servicio) {
        this.opcionesServicio = servicio;
    }
}
