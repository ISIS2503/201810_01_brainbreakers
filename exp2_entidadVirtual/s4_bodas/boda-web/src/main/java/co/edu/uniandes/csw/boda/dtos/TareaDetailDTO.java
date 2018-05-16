/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.TareaEntity;

/**
 *
 * @author sp.joven
 */
public class TareaDetailDTO extends TareaDTO{
    
    
    private UbicacionDTO ubicacion;
    private OpcionServicioDTO opcion;
  
    /**
     * Constructor por defecto
     */
    public TareaDetailDTO(){
        //Constructor por defecto
    }
    
     /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
     public TareaDetailDTO(TareaEntity entity) {
        super(entity);
        if (entity.getUbicacion() != null) {
            this.ubicacion= new UbicacionDTO(entity.getUbicacion());
        } else {
            entity.setUbicacion(null);
        }
         if (entity.getOpcionServicio()!= null) {
            this.opcion= new OpcionServicioDTO(entity.getOpcionServicio());
        } else {
            entity.setOpcionServicio(null);
        }
    }

    public UbicacionDTO getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionDTO ubicacion) {
        this.ubicacion = ubicacion;
    }
    public OpcionServicioDTO getOpcionServicio() {
        return opcion;
    }

    public void setOpcionServicio(OpcionServicioDTO opcion) {
        this.opcion = opcion;
    }
    
     
}
