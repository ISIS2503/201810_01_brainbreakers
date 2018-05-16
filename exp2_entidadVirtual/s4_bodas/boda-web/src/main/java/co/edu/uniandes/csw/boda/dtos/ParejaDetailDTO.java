/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.boda.dtos;

import co.edu.uniandes.csw.boda.entities.BodaEntity;
import co.edu.uniandes.csw.boda.entities.ParejaEntity;
import co.edu.uniandes.csw.boda.entities.TarjetaCreditoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nf.ortiz
 */
public class ParejaDetailDTO  extends ParejaDTO {
    
    public List<BodaDTO> bodas;
    
    public List<TarjetaCreditoDetailDTO> tarjetas;

    public ParejaDetailDTO() {
        //Constructor por defecto
    }

    public ParejaDetailDTO(ParejaEntity pareja) {
        super(pareja);
        if(pareja.getBodas()!=null){
            this.bodas = new ArrayList<>();
            for(BodaEntity bodita: pareja.getBodas()){
                bodas.add(new BodaDetailDTO(bodita));
            }
        }
        if(pareja.getTarjetasCredito()!=null){
            this.tarjetas = new ArrayList<>();
            for(TarjetaCreditoEntity tarjetita: pareja.getTarjetasCredito())
            {
                tarjetas.add(new TarjetaCreditoDetailDTO(tarjetita));
            }
        }
        
    }

    public List<BodaDTO> getBodas() {
        return bodas;
    }

    public void setBodas(List<BodaDTO> boda) {
        this.bodas = boda;
    }
    
     /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public ParejaEntity toEntity() {
       ParejaEntity parejaE = super.toEntity();
       if(this.bodas!=null){
           List<BodaEntity> bod = new ArrayList<>();
           for(BodaDTO bodito: this.bodas){
               bod.add(bodito.toEntity());
           }
           parejaE.setBodas(bod);
       }
       if(this.tarjetas != null){
        List<TarjetaCreditoEntity> tar = new ArrayList<>();
           for(TarjetaCreditoDTO tarjetito: this.tarjetas){
               tar.add(tarjetito.toEntity());
           }
           parejaE.setTarjetasCredito(tar);
       }
        return parejaE;
    }
}
