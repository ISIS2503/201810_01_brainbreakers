/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.ProveedorEntity;
import co.edu.uniandes.csw.boda.entities.ServicioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aj.ortiz10
 */
public class ServicioDetailDTO extends ServicioDTO {
    
    private List<ProveedorDTO> proveedores;

     /**
     * Constructor por defecto
     */
    public ServicioDetailDTO() {
        //Constructor por defecto
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ServicioDetailDTO(ServicioEntity entity) {
        super(entity);
        
        if (entity.getProveedores()!= null) {
            proveedores = new ArrayList<>();
            for (ProveedorEntity entityProveedor : entity.getProveedores()) {
                proveedores.add(new ProveedorDTO(entityProveedor));
            }
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public ServicioEntity toEntity() {
        ServicioEntity ServicioE = super.toEntity();
            if (proveedores != null) {
            List<ProveedorEntity> proveedoresEntity = new ArrayList<>();
            for (ProveedorDTO dtoRegalo : proveedores) {
                proveedoresEntity.add(dtoRegalo.toEntity());
            }
            ServicioE.setProveedores(proveedoresEntity);
        }
        return ServicioE;
    }

    public List<ProveedorDTO> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<ProveedorDTO> proveedores) {
        this.proveedores = proveedores;
    }
    
   
}
