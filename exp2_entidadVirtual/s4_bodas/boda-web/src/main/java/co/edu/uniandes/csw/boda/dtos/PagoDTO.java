/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;
import co.edu.uniandes.csw.boda.entities.PagoEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PagoDTO Objeto de transferencia de datos de Pagos. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el cliente y el servidor.
 * @author ca.guerrero
 */
public class PagoDTO {
    private Long id;
    private String nombrePago;
    private Double montoTotal;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String correoPareja;
    private Long numeroTarjeta;
    /**
     * Constructor por defecto
     */
    public PagoDTO(){
        //Constructor por defecto
    }
    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param pago: Es la entidad que se va a convertir a DTO 
     */
    public PagoDTO(PagoEntity pago) {
        this.id = pago.getId();
        this.montoTotal = pago.getMontoTotal();
        this.fecha = pago.getFecha();
        this.nombrePago = pago.getName();
        this.correoPareja = pago.getCorreoPareja();
        this.numeroTarjeta = pago.getTarjetaCredito().getNumero();
    }

     /**
     * @return el id
     */
    public Long getId() {
        return id;
    }

    /**
    * @param id el id a cambiar
    */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return el montoTotal
     */
    public Double getMontoTotal() {
        return montoTotal;
    }

    /**
    * @param montoTotal el montoTotal a cambiar
    */
    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    /**
     * @return la fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
    * @param fecha la fecha a cambiar
    */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /**
     * @return nombre del pago
     */
    public String getNombrePago() {
        return nombrePago;
    }

    /**
    * @param nombrePago el nuevo nombre del pago 
    */
    public void setNombrePago(String nombrePago) {
        this.nombrePago = nombrePago;
    }

    /**
     * @return pareja que hara del pago
     */
    public String getCorreoPareja() {
        return correoPareja;
    }

    /**
    * @param correoPareja la nueva pareja que hara pago
    */
    public void setCorreoPareja(String correoPareja) {
        this.correoPareja = correoPareja;
    }

    public Long getTarjeta() {
        return numeroTarjeta;
    }

    public void setTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
    
     /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public PagoEntity toEntity() {
        PagoEntity entity = new PagoEntity();
        entity.setId(this.id);
        entity.setMontoTotal(this.montoTotal);
        entity.setFecha(this.fecha);
        entity.setName(this.nombrePago);
        entity.setCorreoPareja(this.correoPareja);
        entity.setNumeroTarjeta(numeroTarjeta);
        return entity;
    }
}
