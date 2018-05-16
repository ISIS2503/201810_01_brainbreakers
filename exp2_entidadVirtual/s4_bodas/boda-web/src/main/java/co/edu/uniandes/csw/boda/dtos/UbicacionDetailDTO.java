/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.UbicacionEntity;

/**
 *
 * @author vn.gomez
 */
public class UbicacionDetailDTO extends UbicacionDTO {
    
    /**
     * Constructor por defecto
     */
    public UbicacionDetailDTO(){
        //Constructor por defecto
    }
    
     /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
     public UbicacionDetailDTO( UbicacionEntity entity) {
        super(entity);
    }
   
    
}
