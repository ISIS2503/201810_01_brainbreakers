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
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author vn.gomez
 */
@Entity
public class UbicacionEntity extends BaseEntity implements Serializable {

    private String latitud;
    private String longitud;
    private int telefono;
    private String direccion;
    
    @PodamExclude
    @ManyToMany(mappedBy = "locations")
    private List<RegaloEntity> regalos;
    
    @PodamExclude
    @OneToOne
    private TareaEntity tarea;

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

    public List<RegaloEntity> getRegalos() {
        return regalos;
    }

    public void setRegalos(List<RegaloEntity> regalos) {
        this.regalos = regalos;
    }

    public TareaEntity getTarea() {
        return tarea;
    }

    public void setTarea(TareaEntity tarea) {
        this.tarea = tarea;
    }
    
}
