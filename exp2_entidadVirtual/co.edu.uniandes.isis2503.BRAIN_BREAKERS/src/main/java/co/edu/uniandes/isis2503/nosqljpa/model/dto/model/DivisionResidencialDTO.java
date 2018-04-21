/*
 * The MIT License
 *
 * Copyright 2018 Universidad De Los Andes - Departamento de Ingeniería de Sistemas.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.isis2503.nosqljpa.model.dto.model;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author e.reyesm
 */
public class DivisionResidencialDTO {
    private String nombre;
    private List<String> residencias;
    private String unidadResidencial;
    
    public DivisionResidencialDTO ()
    {
        residencias = new ArrayList<>();
    }
    
    public DivisionResidencialDTO (String pNombre, String pUnidad)
    {
        this.nombre = pNombre;
        this.unidadResidencial = pUnidad;
        residencias = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getResidencias() {
        return residencias;
    }

    public void setResiencias(List<String> divisionesResidenciales) {
        this.residencias = divisionesResidenciales;
    }

    public String getUnidadResidencial() {
        return unidadResidencial;
    }

    public void setUnidadResidencial(String unidadResidencial) {
        this.unidadResidencial = unidadResidencial;
    }
    
     public void agregarResidencia(String residencia) {
        residencias.add(residencia);
    }

}
