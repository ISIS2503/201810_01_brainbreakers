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
public class InvitadoDetailDTO extends InvitadoDTO {

    public RegaloDTO regalo;

    /**
     * Constructor por defecto
     */
    public InvitadoDetailDTO() {
        //Constructor por defecto
    }

    public InvitadoDetailDTO(InvitadoEntity entity) {
        super(entity);
        if (entity.getRegalo() != null) {
            this.regalo = new RegaloDTO(entity.getRegalo());
        }
    }
    
    @Override
    public InvitadoEntity toEntity() {
        InvitadoEntity invitadoE = super.toEntity();
        if (this.regalo != null) {

            invitadoE.setRegalo(this.regalo.toEntity());
        }
        return invitadoE;
    }

    public RegaloDTO getRegalo() {
        return regalo;
    }

    public void setRegalo(RegaloDTO regalo) {
        this.regalo = regalo;
    }


}
