/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.ProveedorEntity;

/**
 *
 * @author aj.ortiz10
 */
public class ProveedorDTO {
    
    
    private long id;
    private String name;
    private String especialidad;
    private String imagen;
    
    /**
     * Clase constructor
     */
    public ProveedorDTO(){
        //Constructor por defecto
    }
    
    /**
     * Contructor de un ProveedorDTO
     * @param proveedor 
     */
    public ProveedorDTO(ProveedorEntity proveedor) {
        this.id = proveedor.getId();
        this.name = proveedor.getName();
        this.especialidad = proveedor.getEspecialidad();
        this.imagen = proveedor.getImagen();
    }
    
    /**
     * Retorna el id del proveedor.
     * @return parametro id
     */
    public long getId() {
        return id;
    }
    
    /**
     * Actualizar el id del proveedorDTO con el id dado por parámetro.
     * @param id 
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retornar el name del proveedorDTO.
     * @return atributo name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Actualizar el name del proveedorDTO con el name dado por parámetro.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Retornar la especialidad del proveedor.
     * @return atributo especialidad
     */
    public String getEspecialidad(){
        return especialidad;
    }
    
    /**
     * Actualiza la especialidad del proveedor DTO con la especialidad dada por parámetro.
     * @param especialidad 
     */
    public void setEspecialidad(String especialidad){
        this.especialidad=especialidad;
    }
    
    /**
     * Retorna la dirección de la imagen del proveedor DTO.
     * @return atributo imagen
     */
    public String getImagen()    {
        return imagen;
    }
    
    /**
     * Actualiza el valor de la imagen del proveedorDTO.
     * @param imagen 
     */
    public void setImagen(String imagen){
        this.imagen = imagen;
    }
    
     /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public ProveedorEntity toEntity() {
        ProveedorEntity entity = new ProveedorEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setEspecialidad(this.especialidad);
        entity.setImagen(this.imagen);
        return entity;
    }
}
