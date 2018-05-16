/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mf.valllejo
 */
@Entity
public class RegaloEntity extends BaseEntity implements Serializable {
    
    /**
     * Atributo privado imagen.
     */    
    private String imagen;
    
    /**
     * Atributo privado comprado.
     */
    private boolean comprado;
    
        /**
     * Atributo privado boda.
     */
    @PodamExclude
    @ManyToOne
    private BodaEntity boda;
    
    /**
     * Coleccion privada de invitados.
     */
    @PodamExclude
    @ManyToMany
    private List<UbicacionEntity> locations;
    
    /**
     * Atributo privado invitado.
     */    
    @PodamExclude
    @OneToOne
    private InvitadoEntity invitado;
    
    /**
     * Obtiene el atributo invitado.
     *
     * @return atributo invitado.
     */
    public InvitadoEntity getInvitado() {
        return invitado;
    }

    public void setInvitado(InvitadoEntity invitado) {
        this.invitado = invitado;
    }
    
    /**
     * Obtiene la colección de locations.
     *
     * @return colección ubicaciones.
     */
    public List<UbicacionEntity> getLocations() {
        return locations;
    }
    
    /**
     * Establece el valor de la colección de locations.
     *
     * @param locations nuevo valor de la colección.
     */
    public void setLocations(List<UbicacionEntity> locations) {
        this.locations = locations;
    }

    /**
     * Obtiene el atributo boda.
     *
     * @return atributo boda.
     */
    public BodaEntity getBoda() {
        return boda;
    }
    
    /**
     * Establece el valor del atributo boda.
     *
     * @param boda nuevo valor del atributo
     */
    public void setBoda(BodaEntity boda) {
        this.boda = boda;
    }
    
    /**
     * Obtiene el atributo imagen.
     *
     * @return atributo imagen.
     */
    public String getImagen() {
        return imagen;
    }
    
    /**
     * Establece el valor del atributo imagen.
     *
     * @param imagen nuevo valor del atributo
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    /**
     * Obtiene el atributo comprado.
     *
     * @return atributo comprado.
     */
    public boolean isComprado() {
        return comprado;
    }
    
    /**
     * Establece el valor del atributo comprado.
     *
     * @param comprado nuevo valor del atributo
     */
    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }
    
}
