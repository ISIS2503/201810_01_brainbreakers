/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.InvitadoEntity;

/**
 *
 * @author mf.valllejo
 */
public class InvitadoDTO {
    
    private Long id;
    private String name;
    private Long documento;
    private String correo;
    private String asistencia;
    private String categoria; 
   
    /**
     * Constructor por defecto
     */
    public InvitadoDTO(){
        //Constructor por defecto
    }
    
    public InvitadoDTO(InvitadoEntity invitado){
        this.id = invitado.getId();
        this.asistencia = invitado.getAsistencia();
        this.categoria = invitado.getCategoria();
        this.correo = invitado.getCorreo();
        this.documento = invitado.getDocumento();
        this.name = invitado.getName();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
   
    public InvitadoEntity toEntity(){
        InvitadoEntity entity = new InvitadoEntity();
        entity.setId(this.id);
        entity.setAsistencia(this.asistencia);
        entity.setCategoria(this.categoria);
        entity.setCorreo(this.correo);
        entity.setDocumento(this.documento);
        entity.setName(this.name);
        return entity;
    }
}
