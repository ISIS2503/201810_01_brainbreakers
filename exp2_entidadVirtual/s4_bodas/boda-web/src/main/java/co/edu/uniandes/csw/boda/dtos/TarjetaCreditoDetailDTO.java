/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.PagoEntity;
import co.edu.uniandes.csw.boda.entities.ParejaEntity;
import co.edu.uniandes.csw.boda.entities.TarjetaCreditoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ca.guerrero
 */
public class TarjetaCreditoDetailDTO extends TarjetaCreditoDTO {
    
    private List<PagoDTO> pagos;
    private ParejaDTO pareja;
    
    /**
     * Constructor por defecto
     */
    public TarjetaCreditoDetailDTO() {
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public TarjetaCreditoDetailDTO(TarjetaCreditoEntity entity) {
        super(entity);
            if (entity.getPagos() != null) {
            this.pagos = new ArrayList<>();
            for (PagoEntity entityPago : entity.getPagos()) {
                pagos.add(new PagoDTO(entityPago));
            }
        }
            if(entity.getPareja() != null) {
            this.pareja = new ParejaDTO(entity.getPareja());
       }
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public TarjetaCreditoEntity toEntity() {
        TarjetaCreditoEntity resp =super.toEntity();
        if (this.pagos != null) {
            List<PagoEntity> pag = new ArrayList<>();
            for (PagoDTO dtoPago : this.pagos) {
                pag.add(dtoPago.toEntity());
            }
            resp.setPagos(pag);
        }
        return resp;
    }

    public List<PagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }

    public ParejaDTO getPareja() {
        return pareja;
    }

    public void setPareja(ParejaDTO pareja) {
        this.pareja = pareja;
    }
}
