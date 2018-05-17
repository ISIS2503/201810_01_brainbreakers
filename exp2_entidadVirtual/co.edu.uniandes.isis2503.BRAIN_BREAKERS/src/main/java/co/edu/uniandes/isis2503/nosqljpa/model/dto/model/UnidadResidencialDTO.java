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
public class UnidadResidencialDTO {

    private String nombre;
    private List<String> divisionesResidenciales;
    
    private long latitud;
    private long longitud;
    
    private List<String> administradores;
    private List<String> seguridad;

    public UnidadResidencialDTO() {
        divisionesResidenciales = new ArrayList<>();
        administradores = new ArrayList<>();
        seguridad = new ArrayList<>();
    }

    public UnidadResidencialDTO(String pNombre, long pLatitud, long pLongitud) {
        this.nombre = pNombre;
        divisionesResidenciales = new ArrayList<>();
        latitud = pLatitud;
        longitud = pLongitud;

    }
    public void setSeguridad(List<String> pSeguridad)
    {
       seguridad = pSeguridad;
    }
    public List<String> getSeguridad()
    {
        return seguridad;
    }
    public String buscarSeguridad(String pSeguridad)
    {
        String segur = "";
        for (int i = 0; i < this.seguridad.size() && segur.equals(""); i++) {
            String actual = seguridad.get(i);
            if(actual.equals(pSeguridad))
            {
                segur = actual;
            }
        }
        
        return segur;
    }
    public void agregarSeguridad (String pSeguridad)
    {
        seguridad.add(pSeguridad);
    }
    public void borrarSeguridad (String pSeguridad)
    {
        seguridad.remove(pSeguridad);
    }
    
    public void setAdministradores(List<String> pAdministradores)
    {
       administradores = pAdministradores;
    }
    public List<String> getAdministradores()
    {
        return administradores;
    }
    public String buscarAdministrador(String pAdmin)
    {
        String admin = "";
        for (int i = 0; i < administradores.size() && admin.equals(""); i++) {
            String actual = administradores.get(i);
            if(actual.equals(pAdmin))
            {
                admin = actual;
            }
        }
        
        return admin;
    }
    public void agregarAdministrador (String pAdmin)
    {
        administradores.add(pAdmin);
    }
    public void borrarAdministrador (String pAdmin)
    {
        administradores.remove(pAdmin);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getDivisionesResidenciales() {
        return divisionesResidenciales;
    }

    public void agregarDivisionResidenciales(String divisionResidencial) {
        divisionesResidenciales.add(divisionResidencial);
    }

    public void setDivisionesResidenciales(List<String> pDivisionesResidenciales) {
        divisionesResidenciales = pDivisionesResidenciales;
    }

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    
}
