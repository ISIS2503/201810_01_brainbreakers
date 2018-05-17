/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.isis2503.nosqljpa.model.dto.model;

/**
 *
 * @author e.reyesm
 */
public class ValidacionDTO {
    
    private boolean validar;
    
    public ValidacionDTO (boolean pValido)
    {
        validar = pValido;
    }

    public boolean isValidar() {
        return validar;
    }

    public void setValidar(boolean validar) {
        this.validar = validar;
    }
}
