/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author vn.gomez
 */
@Entity
public class BodaEntity extends BaseEntity implements Serializable {

    /**
     * Atributo privado lugar.
     */
    private String lugar;

    /**
     * Atributo privado fecha.
     */
    @Temporal(TemporalType.DATE)
    private Date fecha;

    /**
     * Atributo privado tema.
     */
    private String tema;

    /**
     * Atributo privado religion.
     */
    private String religion;

    /**
     * Atributo privado tipo.
     */
    private String tipoBoda;

    /**
     * Atributo privado image.
     */
    private String image;

    /**
     * Atributo privado pareja.
     */
    @PodamExclude
    @ManyToOne
    private ParejaEntity pareja;
    
    /**
     * Atributo privado OpcionServicio.
     */
    @PodamExclude
    @ManyToMany
    private List<OpcionServicioEntity> opcionServicios;

    /**
     * Coleccion privada de invitados.
     */
    @PodamExclude
    @OneToMany(mappedBy = "boda", cascade = CascadeType.ALL)
    private List<InvitadoEntity> invitados = new ArrayList();

    /**
     * Coleccion privada de regalos.
     */
    @PodamExclude
    @OneToMany(mappedBy = "boda", cascade = CascadeType.ALL)
    private List<RegaloEntity> regalos = new ArrayList();

    /**
     * Obtiene la colección de invitados.
     *
     * @return colección invitados.
     */
    public List<InvitadoEntity> getInvitados() {
        return invitados;
    }

    /**
     * Establece el valor de la colección de invitados.
     *
     * @param invitados nuevo valor de la colección.
     */
    public void setInvitados(List<InvitadoEntity> invitados) {
        this.invitados = invitados;
    }

    /**
     * Obtiene la colección de regalos.
     *
     * @return colección tareas.
     */
    public List<RegaloEntity> getRegalos() {
        return regalos;
    }

    /**
     * Establece el valor de la colección de regalos.
     *
     * @param regalos nuevo valor de la colección.
     */
    public void setRegalos(List<RegaloEntity> regalos) {
        this.regalos = regalos;
    }

    /**
     * Obtiene el atributo pareja.
     *
     * @return atributo pareja.
     */
    public ParejaEntity getPareja() {
        return pareja;
    }

    /**
     * Establece el valor del atributo pareja.
     *
     * @param pareja nuevo valor del atributo
     */
    public void setPareja(ParejaEntity pareja) {
        this.pareja = pareja;
    }

    /**
     * Obtiene el atributo lugar.
     *
     * @return atributo lugar.
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Establece el valor del atributo lugar.
     *
     * @param lugar nuevo valor del atributo
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
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
     * Obtiene el atributo tema.
     *
     * @return atributo tema.
     */
    public String getTema() {
        return tema;
    }

    /**
     * Establece el valor del atributo tiema.
     *
     * @param tema nuevo valor del atributo
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * Obtiene el atributo religion.
     *
     * @return atributo religion.
     */
    public String getReligion() {
        return religion;
    }

    /**
     * Establece el valor del atributo religion.
     *
     * @param religion nuevo valor del atributo
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * Obtiene el atributo tipoBoda.
     *
     * @return atributo tipoBoda.
     */
    public String getTipoBoda() {
        return tipoBoda;
    }

    /**
     * Establece el valor del atributo tipoBoda.
     *
     * @param tipoBoda nuevo valor del atributo
     */
    public void setTipoBoda(String tipoBoda) {
        this.tipoBoda = tipoBoda;
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
     * Obtiene el atributo opcionServicio.
     *
     * @return la opcionServicio
     */
    public List<OpcionServicioEntity> getOpcionServicios() {
        return opcionServicios;
    }

    /**
     * Establece el valor del atributo opcionServicio.
     *
     * @param opcionServicio las opcioneServicio a cambiar
     */
    public void setOpcionServicios(List<OpcionServicioEntity> opcionServicio) {
        this.opcionServicios = opcionServicio;
    }
    
}
