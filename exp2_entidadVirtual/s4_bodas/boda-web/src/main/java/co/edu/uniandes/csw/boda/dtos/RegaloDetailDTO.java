/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.RegaloEntity;
import co.edu.uniandes.csw.boda.entities.UbicacionEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mf.valllejo
 */
public class RegaloDetailDTO extends RegaloDTO{

    public InvitadoDTO invitado;
    
    private List<UbicacionDTO> locations;
    
    public BodaDTO boda;

    /**
     * Constructor por defecto
     */
    public RegaloDetailDTO() {
        super();
    }

    public RegaloDetailDTO(RegaloEntity entity) {
        super(entity);
        if (entity.getInvitado() != null) {
            this.invitado = new InvitadoDTO(entity.getInvitado());
        }
        if (entity.getLocations() != null) {
            locations = new ArrayList<>();
            for (UbicacionEntity entityUbicacion : entity.getLocations()) {
                locations.add(new UbicacionDTO(entityUbicacion));
            }
        }
        if(entity.getBoda() !=null){
            this.boda = new BodaDTO(entity.getBoda());
        }
    }

    @Override
    public RegaloEntity toEntity() {
        RegaloEntity regaloE = super.toEntity();
        if (this.invitado != null) {

            regaloE.setInvitado(invitado.toEntity());
        }
        if (locations != null) {
            List<UbicacionEntity> locationsEntity = new ArrayList<>();
            for (UbicacionDTO dtoAuthor : locations) {
                locationsEntity.add(dtoAuthor.toEntity());
            }
            regaloE.setLocations(locationsEntity);
        }
        if(this.boda !=null){
            regaloE.setBoda(boda.toEntity());
        }
        return regaloE;
    }
    
    public List<UbicacionDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<UbicacionDTO> locations) {
        this.locations = locations;
    }
    
    public InvitadoDTO getInvitado() {
        return invitado;
    }

    public void setInvitado(InvitadoDTO invitado) {
        this.invitado = invitado;
    }
}