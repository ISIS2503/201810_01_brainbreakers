/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.TarjetaCreditoEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TarjetaCreditoDTO Objeto de transferencia de datos de Tarjetas de Credito. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el cliente y el servidor.
 * @author ca.guerrero
 */
public class TarjetaCreditoDTO {
    private Long numero;
    private int numDeSeg;
    @Temporal(TemporalType.DATE)
    private Date fechaVen;
    private String nombreBanco;
     /**
     * Constructor por defecto
     */
    public TarjetaCreditoDTO() {
        //Constructor por defecto
    }
    
    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param tarjeta: Es la entidad que se va a convertir a DTO 
     */
    public TarjetaCreditoDTO(TarjetaCreditoEntity tarjeta) {
        this.numero = tarjeta.getNumero();
        this.numDeSeg = tarjeta.getNumDeSeg();
        this.fechaVen = tarjeta.getFechaVen();
        this.nombreBanco = tarjeta.getName();
    }

    /**
     * @return el numero de la tarjeta de credito
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * @param numero el numero a cambiar
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    /**
     * @return el numero de seguridad de la tarjeta de credito
     */
    public int getNumDeSeg() {
        return numDeSeg;
    }

    /**
     * @param numDeSeg el numero de seguridad a cambiar
     */
    public void setNumDeSeg(int numDeSeg) {
        this.numDeSeg = numDeSeg;
    }

    /**
     * @return la fecha de vencimiento de la tarjeta de credito
     */
    public Date getFechaVen() {
        return fechaVen;
    }

    /**
     * @param fechaVen la fecha de vencimiento a cambiar
     */
    public void setFechaVen(Date fechaVen) {
        this.fechaVen = fechaVen;
    }

    /**
     * @return el nombre del banco de la tarjeta de credito
     */
    public String getNombreBanco() {
        return nombreBanco;
    }

    /**
     * @param nombreBanco el nombre del banco a cambiar
     */
    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }
    
     /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public TarjetaCreditoEntity toEntity() {
        TarjetaCreditoEntity entity = new TarjetaCreditoEntity();
        entity.setNumero(this.numero);
        entity.setNumDeSeg(this.numDeSeg);
        entity.setFechaVen(this.fechaVen);
        entity.setName(this.nombreBanco);
        return entity;
    }
}
