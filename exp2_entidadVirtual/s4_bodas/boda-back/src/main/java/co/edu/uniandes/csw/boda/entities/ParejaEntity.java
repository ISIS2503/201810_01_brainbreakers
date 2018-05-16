/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author nf.ortiz
 */
@Entity
public class ParejaEntity implements Serializable {

    /**
     * Atributo privado correoElec.
     */
    @Id
    private String correoElec;

    /**
     * Atributo privado nombreInd1.
     */
    private String nombreInd1;

    /**
     * Atributo privado nombreInd2.
     */
    private String nombreInd2;

    /**
     * Atributo privado telefono.
     */
    private int telefono;

    /**
     * Atributo privado direccion.
     */
    private String direccion;

    /**
     * Atributo privado pago.
     */
    private boolean pago;

    /**
     * Atributo privado contrasenia.
     */
    private String contrasenia;

    /**
     * Atributo privado nombreAbrevieado.
     */
    private String nombreAbreviado;
    
    /**
     * Atributo privado bodas.
     */
    @PodamExclude
    @OneToMany(mappedBy = "pareja",fetch = FetchType.LAZY)
    private List<BodaEntity> bodas;
    
    /**
     * Coleccion privada de tarjetasCredito.
     */
    @PodamExclude
    @OneToMany(mappedBy = "pareja", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TarjetaCreditoEntity> tarjetasCredito;
    
    /**
     * Obtiene el atributo bodas.
     *
     * @return atributo bodas.
     */
    public List<BodaEntity> getBodas() {
        return bodas;
    }
    
    /**
     * Establece el valor de la coleccion  bodas.
     *
     * @param bodas nuevo valor del atributo
     */
    public void setBodas(List<BodaEntity> bodas) {
        this.bodas = bodas;
    }
    
    /**
     * Obtiene la colección de tarjetasCredito.
     *
     * @return colección tarjetasCredito.
     */
    public List<TarjetaCreditoEntity> getTarjetasCredito() {
        return tarjetasCredito;
    }
    
    /**
     * Establece el valor de la colección de tarjetasCredito.
     *
     * @param tarjetasCredito nuevo valor de la colección.
     */
    public void setTarjetasCredito(List<TarjetaCreditoEntity> tarjetasCredito) {
        this.tarjetasCredito = tarjetasCredito;
    }
    
    /**
     * Obtiene el atributo correoElec.
     *
     * @return atributo correoElec.
     */
    public String getCorreoElec() {
        return correoElec;
    }
    
    /**
     * Establece el valor del atributo correoElec.
     *
     * @param correoElec nuevo valor del atributo
     */
    public void setCorreoElec(String correoElec) {
        this.correoElec = correoElec;
    }
    
    /**
     * Obtiene el atributo nombreInd1.
     *
     * @return atributo nombreInd1.
     */
    public String getNombreInd1() {
        return nombreInd1;
    }
    
    /**
     * Establece el valor del atributo nombreInd1.
     *
     * @param nombreInd1 nuevo valor del atributo
     */
    public void setNombreInd1(String nombreInd1) {
        this.nombreInd1 = nombreInd1;
    }
    
    /**
     * Obtiene el atributo nombreInd2.
     *
     * @return atributo nombreInd2.
     */
    public String getNombreInd2() {
        return nombreInd2;
    }
    
    /**
     * Establece el valor del atributo nombreInd2.
     *
     * @param nombreInd2 nuevo valor del atributo
     */
    public void setNombreInd2(String nombreInd2) {
        this.nombreInd2 = nombreInd2;
    }
    
    /**
     * Obtiene el atributo telefono.
     *
     * @return atributo telefono.
     */
    public int getTelefono() {
        return telefono;
    }
    
    /**
     * Establece el valor del atributo telefono.
     *
     * @param telefono nuevo valor del atributo
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    /**
     * Obtiene el atributo direccion.
     *
     * @return atributo direccion.
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Establece el valor del atributo direccion.
     *
     * @param direccion nuevo valor del atributo
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Obtiene el atributo pago.
     *
     * @return atributo pago.
     */
    public boolean isPago() {
        return pago;
    }
    
    /**
     * Establece el valor del atributo pago.
     *
     * @param pago nuevo valor del atributo
     */
    public void setPago(boolean pago) {
        this.pago = pago;
    }
    
    /**
     * Obtiene el atributo contrasenia.
     *
     * @return atributo contrasenia.
     */
    public String getContrasenia() {
        return contrasenia;
    }
    
    /**
     * Establece el valor del atributo contrasenia.
     *
     * @param contrasenia nuevo valor del atributo
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    /**
     * Obtiene el atributo nombreAbreviado.
     *
     * @return atributo nombreAbreviado.
     */
    public String getNombreAbreviado() {
        return nombreAbreviado;
    }
    
    /**
     * Establece el valor del atributo nombreAbreviado.
     *
     * @param nombreAbreviado nuevo valor del atributo
     */
    public void setNombreAbreviado(String nombreAbreviado) {
        this.nombreAbreviado = nombreAbreviado;
    }
}
