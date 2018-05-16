
package co.edu.uniandes.csw.boda.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author sp.joven
 */
@Entity
public class OpcionServicioEntity extends BaseEntity implements Serializable {

    /**
     * Atributo privado descripcion.
     */
    private String descripcion;

    /**
     * Atributo privado costo.
     */
    private Long costo;

    /**
     * Atributo privado diasDisponibles.
     */
    private String diasDisponibles;

    /**
     * Atributo privado image.
     */
    private String image;
    
     /**
     * Atributo privado pago.
     */
    @PodamExclude
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pago_id")
    private PagoEntity pago;
    
    /**
    * Atributo privado bodas.
    */
    @PodamExclude
    @ManyToMany(mappedBy = "opcionServicios")
    private List<BodaEntity> bodas;
    
    /**
     * Coleccion privada de calificacion.
     */
    @PodamExclude
    @OneToMany(mappedBy = "opcionServicio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalificacionEntity> calificacion;

    /**
     * Atributo privado proveedor.
     */
    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;

    /**
     * Coleccion privada de tareas.
     */
    @PodamExclude
    @OneToMany(mappedBy = "opcionServicio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TareaEntity> tareas;

    /**
     * Obtiene el atributo descripcion.
     *
     * @return atributo bodas.
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Establece el valor del atributo descripcion.
     *
     * @param descripcion nuevo valor del atributo
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el atributo costo.
     *
     * @return atributo bodas.
     */
    public Long getCosto() {
        return costo;
    }
    
    /**
     * Establece el valor del atributo costo.
     *
     * @param costo nuevo valor del atributo
     */
    public void setCosto(Long costo) {
        this.costo = costo;
    }

    /**
     * Obtiene el atributo diasDisponibles.
     *
     * @return atributo bodas.
     */
    public String getDiasDisponibles() {
        return diasDisponibles;
    }
    
    /**
     * Establece el valor del atributo diasDisponibles.
     *
     * @param diasDisponibles nuevo valor del atributo
     */
    public void setDiasDisponibles(String diasDisponibles) {
        this.diasDisponibles = diasDisponibles;
    }

    /**
     * Obtiene la colección de calificacion.
     *
     * @return colección tareas.
     */
    public List<CalificacionEntity> getCalificacion() {
        return calificacion;
    }

    /**
     * Establece el valor de la colección de tareas.
     *
     * @param calificacion nuevo valor de la colección.
     */
    public void setCalificacion(List<CalificacionEntity> calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Obtiene la colección de tareas.
     *
     * @return colección tareas.
     */
    public List<TareaEntity> getTareas() {
        return tareas;
    }

    /**
     * Establece el valor de la colección de tareas.
     *
     * @param tareas nuevo valor de la colección.
     */
    public void setTareas(List<TareaEntity> tareas) {
        this.tareas = tareas;
    }

    /**
     * Obtiene el atributo proveedor.
     *
     * @return atributo proveedor.
     */
    public ProveedorEntity getProveedor() {
        return proveedor;
    }
    
    /**
     * Establece el valor del atributo proveedor.
     *
     * @param proveedor nuevo valor del atributo
     */
    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Obtiene el atributo image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Establece el valor del atributo image.
     *
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

     /**
     * Obtiene el atributo pago.
     *
     * @return el pago
     */
    public PagoEntity getPago() {
        return pago;
    }

     /**
     * Establece el valor del atributo pago.
     *
     * @param pago el pago a cambiar
     */
    public void setPago(PagoEntity pago) {
        this.pago = pago;
    }

    /**
     * Obtiene el atributo bodas.
     *
     * @return la bodas asociada
     */
    public List<BodaEntity> getBodas() {
        return bodas;
    }

     /**
     * Establece el valor del atributo bodas.
     *
     * @param boda la bodas a cambiar
     */
    public void setBodas(List<BodaEntity> boda) {
        this.bodas = boda;
    }
    
    
}
