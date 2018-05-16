/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.ParejaEntity;

/**
 *CityDTO Objeto de transferencia de datos de Parejas. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el cliente y el servidor.
 * @author nf.ortiz
 */
public class ParejaDTO {

    
    //////////////////////////////
    /////Atributos////////////////
    //////////////////////////////
    private String correoElec;
    
    private String nombreInd1;
    
    private String nombreInd2;
    
    private int telefono;
    
    private String direccion;
    
    private boolean pago;
    
    private String contrasenia;
    
    private String nombreAbreviado;
    

    
    /**
     * Constructor por defecto
     */
    public ParejaDTO() {
        //Constructor por defecto
    }
    
    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en  la entidad que viene de argumento.
     * @param pareja: Es la entidad que se va a convertir a DTO 
     */
    public ParejaDTO(ParejaEntity pareja) {
        this.contrasenia = pareja.getContrasenia();
        this.correoElec = pareja.getCorreoElec();
        this.direccion = pareja.getDireccion();
        this.nombreAbreviado = pareja.getNombreAbreviado();
        this.nombreInd1 = pareja.getNombreInd1();
        this.nombreInd2 = pareja.getNombreInd2();
        this.pago = pareja.isPago();
        this.telefono = pareja.getTelefono();
    
    }


    public String getCorreoElec() {
        return correoElec;
    }

    public void setCorreoElec(String correoElec) {
        this.correoElec = correoElec;
    }

    public String getNombreInd1() {
        return nombreInd1;
    }

    public void setNombreInd1(String nombreInd1) {
        this.nombreInd1 = nombreInd1;
    }

    public String getNombreInd2() {
        return nombreInd2;
    }

    public void setNombreInd2(String nombreInd2) {
        this.nombreInd2 = nombreInd2;
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

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombreAbreviado() {
        return nombreAbreviado;
    }

    public void setNombreAbreviado(String nombreAbreviado) {
        this.nombreAbreviado = nombreAbreviado;
    }
    
    public ParejaEntity toEntity(){
        ParejaEntity entity = new ParejaEntity();
        entity.setCorreoElec(this.correoElec);
        entity.setContrasenia(this.contrasenia);
        entity.setDireccion(this.direccion);
        entity.setNombreAbreviado(this.nombreAbreviado);
        entity.setNombreInd1(this.nombreInd1);
        entity.setNombreInd2(this.nombreInd2);
        entity.setPago(this.pago);
        entity.setTelefono(this.telefono);
        return entity;
    }
}
