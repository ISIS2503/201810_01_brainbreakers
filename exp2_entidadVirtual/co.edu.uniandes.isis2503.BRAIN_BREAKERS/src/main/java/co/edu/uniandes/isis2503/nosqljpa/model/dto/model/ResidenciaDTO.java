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
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author e.reyesm
 */
public class ResidenciaDTO {
   
    private String nombre;
    
    private String barrio;

    private List<String> alertas;
  
    private List<String> horarios;
    
    private List<String> ususarios;
    
    private int numeroClaves;

    public ResidenciaDTO() {
        alertas = new ArrayList<>();
        horarios = new ArrayList<>();
        ususarios = new ArrayList<>();
        numeroClaves = 0;
    }

    public ResidenciaDTO(String pNombre, String pBarrio) {
        nombre =pNombre;
        barrio = pBarrio;
        alertas = new ArrayList<>();
        horarios = new ArrayList<>();
        ususarios = new ArrayList<>();
        numeroClaves = 0;
    }
        public List<String> getUsusarios() {
        return ususarios;
    }

    public void setUsusarios(List<String> ususarios) {
        this.ususarios = ususarios;
    }
    public void agregarUsuario(String pUsuario)
    {
        ususarios.add(pUsuario);
    }
     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
    
    

    public List<String> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<String> alertas) {
        this.alertas = alertas;
    }
    
    public void agregarAlerta(String alerta)
    {
        alertas.add(alerta);
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }
    
    public void agregarHorario(String horario)
    {
        horarios.add(horario);
    }

    public int getNumeroClaves() {
        return numeroClaves;
    }

    public void setNumeroClaves(int numeroClaves) {
        this.numeroClaves = numeroClaves;
    }
    
    public void deleteUsuario(String usuario)
    {
        ususarios.remove(usuario);
    }

}
