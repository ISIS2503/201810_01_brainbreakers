/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author nf.ortiz
 */
@Entity
public class CalificacionEntity implements Serializable{
    
    /**
     * Atributo privado id.
     */    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Atributo privado comentario.
     */      
    private String comentario;
    
    /**
     * Atributo privado calificaion.
     */  
    private double calificacionNum;
    
    /**
     * Atributo privado opcionServicio.
     */  
    @PodamExclude
    @ManyToOne
    private OpcionServicioEntity opcionServicio;
    /**
     * Obtiene el atributo opcionServicio.
     * @return atributo opcionServicio.
     */
    public OpcionServicioEntity getOpcionServicio() {
        return opcionServicio;
    }
    
    /**
     * Establece el valor del atributo opcionServicio.
     * @param opcionServicio nuevo valor del atributo
     */
    public void setOpcionServicio(OpcionServicioEntity opcionServicio) {
        this.opcionServicio = opcionServicio;
    }
    
    /**
     * Obtiene el atributo id.
     * @return atributo id.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Establece el valor del atributo id.
     * @param id nuevo valor del atributo
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Obtiene el atributo comentario.
     * @return atributo comentario.
     */
    public String getComentario() {
        return comentario;
    }
    
    /**
     * Establece el valor del atributo comentario.
     * @param comentario nuevo valor del atributo
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    /**
     * Obtiene el atributo calificacion.
     * @return atributo calificacion.
     */
    public double getCalificacionNum() {
        return calificacionNum;
    }
    
    /**
     * Establece el valor del atributo calificacion.
     * @param calificacionNum nuevo valor del atributo
     */
    public void setCalificacionNum(double calificacionNum) {
        this.calificacionNum = calificacionNum;
    }
      
    
}
