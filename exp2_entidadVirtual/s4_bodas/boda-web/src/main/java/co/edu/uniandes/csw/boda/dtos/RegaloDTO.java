/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.RegaloEntity;

/**
 *
 * @author mf.valllejo
 */
public class RegaloDTO {
    
    private Long id;
    private String name;
    private String imagen;
    private boolean comprado;

    /**
     * Constructor por defecto
     */
    public RegaloDTO(){
        //Constructor por defecto
    }
    
    public RegaloDTO(RegaloEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.imagen = entity.getImagen();
        this.comprado = entity.isComprado();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }
    
    public RegaloEntity toEntity(){
        RegaloEntity entity = new RegaloEntity();
        entity.setId(this.id);
        entity.setImagen(this.imagen);
        entity.setComprado(this.comprado);
        entity.setName(this.name);
        return entity;
    }
}
