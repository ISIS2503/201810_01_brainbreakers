/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mf.valllejo
 */
@Entity
public class InvitadoEntity extends BaseEntity implements Serializable {

    /**
     * Atributo privado documento.
     */
    private Long documento;

    /**
     * Atributo privado correo.
     */
    private String correo;

    /**
     * Atributo privado asistencia.
     */
    private String asistencia;

    /**
     * Atributo privado categoria.
     */
    private String categoria;

    /**
     * Atributo privado boda.
     */
    @PodamExclude
    @ManyToOne
    private BodaEntity boda;

    /**
     * Atributo privado regalo.
     */
    @PodamExclude
    @OneToOne
    private RegaloEntity regalo;

    /**
     * Obtiene el atributo regalo.
     *
     * @return atributo regalo.
     */
    public RegaloEntity getRegalo() {
        return regalo;
    }

    /**
     * Establece el valor del atributo regalo.
     *
     * @param regalo nuevo valor del atributo
     */
    public void setRegalo(RegaloEntity regalo) {
        this.regalo = regalo;
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
     * Obtiene el atributo documento.
     *
     * @return atributo documento.
     */
    public Long getDocumento() {
        return documento;
    }

    /**
     * Establece el valor del atributo docummento.
     *
     * @param documento nuevo valor del atributo
     */
    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el atributo correo.
     *
     * @return atributo correo.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el valor del atributo correo.
     *
     * @param correo nuevo valor del atributo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el atributo asistencia.
     *
     * @return atributo asistencia.
     */
    public String getAsistencia() {
        return asistencia;
    }

    /**
     * Establece el valor del atributo asistencia.
     *
     * @param asistencia nuevo valor del atributo
     */
    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    /**
     * Obtiene el atributo categoria.
     *
     * @return atributo pareja.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece el valor del atributo categoria.
     *
     * @param categoria nuevo valor del atributo
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
