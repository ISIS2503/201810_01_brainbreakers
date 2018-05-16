/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.BodaEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CantanteDTO Objeto de transferencia de datos de cantantes. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 * @author vn.gomez
 */
public class BodaDTO {
    
    private Long id;
    private String name;
    private String lugar;   
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String tema;
    private String religion;
    private String tipoBoda;
    private String image;

    
    /**
     * Constructor por defecto
     */
    public BodaDTO() {
        //Constructor por defecto
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param boda: Es la entidad que se va a convertir a DTO
     */
    public BodaDTO(BodaEntity boda) {
        this.id = boda.getId();
        this.name = boda.getName();
        this.lugar = boda.getLugar();
        this.fecha = boda.getFecha();
        this.tema = boda.getTema();
        this.religion = boda.getReligion();
        this.tipoBoda = boda.getTipoBoda();
        this.image = boda.getImage();
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
    
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getTipoBoda() {
        return tipoBoda;
    }

    public void setTipoBoda(String tipoBoda) {
        this.tipoBoda = tipoBoda;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public BodaEntity toEntity() {
        BodaEntity entity = new BodaEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setLugar(this.lugar);
        entity.setFecha(this.fecha);
        entity.setTema(this.tema);
        entity.setReligion(this.religion);
        entity.setTipoBoda(this.tipoBoda);
        entity.setImage(this.image);        
        return entity;
    }
    
}
