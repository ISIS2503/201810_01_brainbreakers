/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.TareaEntity;
import java.util.Date;


/**
 * TareaDTO Objeto de transferencia de datos de tareas. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor. 
 *
 * @author sp.joven
 */
public class TareaDTO {
   
    private Long id;
    private boolean aprobada;
    private Date  dia;
    private String nombre;
    private String image;
    
    /**
     * Constructor por defecto
     */
    public TareaDTO() {
        //Constructor por defecto
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param tarea: Es la entidad que se va a convertir a DTO
     */
    public TareaDTO(TareaEntity tarea) {
        
        this.id=tarea.getId();
        this.aprobada = tarea.isAprobada();
        this.dia = tarea.getDia();
        this.nombre = tarea.getNombre();
        this.image = tarea.getImage();
    }

    
  public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id =id;
    }

    public Boolean isAprobada() {
        return aprobada;
    }

    public void setAprobada(Boolean aprobada) {
        this.aprobada =aprobada;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
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

    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public TareaEntity toEntity() {
        TareaEntity entity = new TareaEntity();
        entity.setId(this.id);
        entity.setAprobada(this.aprobada);
        entity.setNombre(this.nombre);
        entity.setDia(this.dia);
        entity.setImage(this.image);
        return entity;
    }
    
}
