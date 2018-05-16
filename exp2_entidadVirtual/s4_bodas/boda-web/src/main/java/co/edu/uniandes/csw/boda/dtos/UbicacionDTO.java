/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.UbicacionEntity;

/**
 * CantanteDTO Objeto de transferencia de datos de cantantes. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 * @author vn.gomez
 */
public class UbicacionDTO {
    
    private Long id;
    private String name;
    private String latitud;
    private String longitud;
    private int telefono;
    private String direccion;
    
    
    
    /**
     * Constructor por defecto
     */
    public UbicacionDTO() {
        //Constructor por defecto
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param ubicacion: Es la entidad que se va a convertir a DTO
     */
    public UbicacionDTO(UbicacionEntity ubicacion) {
        this.id = ubicacion.getId();
        this.name = ubicacion.getName();
        this.latitud = ubicacion.getLatitud();
        this.longitud = ubicacion.getLongitud();
        this.telefono = ubicacion.getTelefono();
        this.direccion = ubicacion.getDireccion();
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

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public UbicacionEntity toEntity() {
        UbicacionEntity entity = new UbicacionEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setLatitud(this.latitud);
        entity.setLongitud(this.longitud);
        entity.setTelefono(this.telefono);
        entity.setDireccion(this.direccion);
        return entity;
    }
    
}
