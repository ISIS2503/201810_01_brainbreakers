/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.CalificacionEntity;

/**
 *
 * @author nf.ortiz
 */
public class CalificacionDetailDTO extends CalificacionDTO {
    
    
    
    public CalificacionDetailDTO() {
        super();
    }
    
  
    public CalificacionDetailDTO(CalificacionEntity calificacion) {
        super(calificacion);
    } 
     @Override
    public CalificacionEntity toEntity() {
        CalificacionEntity m = super.toEntity();
       
        return m; //To change body of generated methods, choose Tools | Templates.
    }
    
}
