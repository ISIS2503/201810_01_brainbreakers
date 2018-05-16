/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.entities;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author sp.joven
 */

@Entity 
public class TareaEntity extends BaseEntity implements Serializable {

    /**
     * Atributo privado aprobada.
     */
    private boolean aprobada;

    /**
     * Atributo privado dia.
     */
    @Temporal(TemporalType.DATE)
    private Date  dia;
  
    /**
     * Atributo privado nombre.
     */
    private String nombre;
    /**
     * Atributo privado de la imagen.
     */
    private String image;

     /**
     * Atributo privado ubicacion.
     */
    @PodamExclude
    @OneToOne
    private UbicacionEntity ubicacion;

    /**
     * Atributo privado opcionServicio.
     */
    @PodamExclude
    @ManyToOne
    private OpcionServicioEntity opcionServicio;
    

     /**
     * Obtiene el atributo aprobada
     *
     * @return atributo aprobada.
     */
    public boolean isAprobada() {
        return aprobada;
    }
    
    /**
     * Establece el valor del estado de la tarea.
     *
     * @param aprobada nuevo valor del estado de la tarea.
     */
    public void setAprobada(boolean aprobada) {
        this.aprobada = aprobada;
    }
    
    /**
     * Obtiene el atributo dia.
     *
     * @return atributo dia.
     */
    public Date getDia() {
        return dia;
    }
     /**
     * Establece el dia de la tarea.
     *
     * @param dia nuevo valor del dia de la tarea.
     */
    public void setDia(Date dia) {
        this.dia = dia;
    }

    /**
     * Obtiene el atributo nombre.
     *
     * @return atributo nombre.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el valor del nombre de la tarea.
     *
     * @param nombre nuevo valor del nombre de la tarea.
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     /**
     * Obtiene el atributo opcion servicio
     *
     * @return atributo opcion servicio.
     */
     public OpcionServicioEntity getOpcionServicio() {
        return opcionServicio;
    }
     
     /**
     * Establece el valor de la opcion servicio de la tarea.
     *
     * @param opcionServicio nuevo valor de la opcion servicio de la tarea.
     */

    public void setOpcionServicio(OpcionServicioEntity opcionServicio) {
        this.opcionServicio = opcionServicio;
    }
      /**
     * Obtiene el atributo ubicacion
     *
     * @return atributo ubicacion.
     */
    public UbicacionEntity getUbicacion() {
        return ubicacion;
    }
    
    /**
     * Establece el valor de la ubicacion  de la tarea.
     *
     * @param ubicacion nuevo valor de la ubicacion de la tarea.
     */

    public void setUbicacion(UbicacionEntity ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
    
}
