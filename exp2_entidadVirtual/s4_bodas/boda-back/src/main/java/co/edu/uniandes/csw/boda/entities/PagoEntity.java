package co.edu.uniandes.csw.boda.entities;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ca.guerrero
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable{
    
    /**
     * Atributo privado montoTotal.
     */
    private Double montoTotal;
    
    /**
     * Atributo privado fecha.
     */
    @Temporal(TemporalType.DATE)
    private Date fecha;
       
    /**
     * Atributo privado tarjetaCredito.
     */
    @PodamExclude
    @ManyToOne
    private TarjetaCreditoEntity tarjetaCredito;
    

     /**
     * Atributo privado opcionServicio.
     */
    @PodamExclude
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="opcionServicio_id")
    private OpcionServicioEntity opcionServicio;
    
    /**
    * Atributo privado correoPareja.
    */
    private String correoPareja;
    
    /**
    * Atributo privado numeroTarjeta.
    */
    private Long numeroTarjeta;
    
     /**
     * Obtiene el atributo montoTotal.
     *
     * @return atributo montoTotal.
     */
      public Double getMontoTotal() {
        return montoTotal;
    }
  
    /**
     * Establece el valor del atributo montoTotal.
     *
     * @param montoTotal nuevo valor del atributo
     */
    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    /**
     * Obtiene el atributo fecha.
     *
     * @return atributo fecha.
     */
    public Date getFecha() {
        return fecha;
    }
    
    /**
     * Establece el valor del atributo fecha.
     *
     * @param fecha nuevo valor del atributo
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Obtiene el atributo tarjetaCredito.
     *
     * @return atributo tarjetaCredito.
     */
    public TarjetaCreditoEntity getTarjetaCredito() {
        return tarjetaCredito;
    }
    
    /**
     * Establece el valor del atributo tarjetaCredito.
     *
     * @param tarjetaCredito nuevo valor del atributo
     */
    public void setTarjetaCredito(TarjetaCreditoEntity tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    /**
     * Obtiene el atributo opcionServicio.
     *
     * @return atributo opcionServicio.
     */
    public OpcionServicioEntity getOpcionServicio() {
        return opcionServicio;
    }
     /**
     * Establece el valor del atributo opcionServicio.
     *
     * @param opcionServicio nuevo valor del atributo
     */
    public void setOpcionServicio(OpcionServicioEntity opcionServicio) {
        this.opcionServicio = opcionServicio;
    }
    
    /**
     * Obtiene el atributo opcionServicio.
     *
     * @return atributo correoPareja.
     */
    public String getCorreoPareja() {
        return correoPareja;
    }

    /**
     * Establece el valor del atributo correoPareja.
     *
     * @param correoPareja nuevo valor del atributo
     */
    public void setCorreoPareja(String correoPareja) {
        this.correoPareja = correoPareja;
    }

    /**
     * Obtiene el atributo numeroTarjeta.
     *
     * @return atributo numeroTarjeta.
     */
    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

     /**
     * Establece el valor del atributo numeroTarjeta.
     *
     * @param numeroTarjeta nuevo valor del atributo
     */
    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
}