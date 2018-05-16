/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.CalificacionEntity;

/**
 *CityDTO Objeto de transferencia de datos de Calificaciones. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el cliente y el servidor.
 * @author nf.ortiz
 *
 * @author nf.ortiz
 */
public class CalificacionDTO {
    private Long id;
    
    private String comentario;
    
    private double calificacionNum;

    public CalificacionDTO() {
       //Constructor por defecto
    }
    
    public CalificacionDTO(CalificacionEntity calificacion ) {
        this.id = calificacion.getId();
        this.comentario = calificacion.getComentario();
        this.calificacionNum =calificacion.getCalificacionNum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getCalificacionNum() {
        return calificacionNum;
    }

    public void setCalificacionNum(double calificacionNum) {
        this.calificacionNum = calificacionNum;
    }
    
    public CalificacionEntity toEntity(){
        CalificacionEntity entity = new CalificacionEntity();
        entity.setId(this.id);
        entity.setComentario(this.comentario);
        entity.setCalificacionNum(this.calificacionNum);
        return entity;
    }
}
