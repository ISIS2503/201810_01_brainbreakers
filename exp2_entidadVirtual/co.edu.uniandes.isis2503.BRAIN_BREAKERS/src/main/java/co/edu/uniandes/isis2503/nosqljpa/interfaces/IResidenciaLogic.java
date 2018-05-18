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
package co.edu.uniandes.isis2503.nosqljpa.interfaces;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.AlertaDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.ResidenciaDTO;
import java.util.List;

/**
 *
 * @author e.reyesm
 */
public interface IResidenciaLogic {
    public ResidenciaDTO add(ResidenciaDTO dto);
    public ResidenciaDTO addAlerta(String unidad,String divison, String residencia,String alerta)throws Exception;
    public ResidenciaDTO addHorario(String unidad,String divison, String residencia,String horario)throws Exception;
    public ResidenciaDTO update(ResidenciaDTO dto);
    public ResidenciaDTO find(String id);
    public ResidenciaDTO updateHorario(String pHorario, String nombreResidencia, String pHorarioAntiguo);
    public List<ResidenciaDTO> all();
    public Boolean delete(String id);
    public List<AlertaDTO> getAllAlertas();
    public List<AlertaDTO> getAllAlertasByUnidadAndAlerta(String nombreUnidad, String tipo);
    //public List<String> getAllHorarios(String id);
    public List<AlertaDTO> getAllAlertasByUnidad(String nombreUnidad);
    public void validarUsuario(String user, String nombreResidencia) throws Exception;
    public ResidenciaDTO deleteHorario(String nombreResidencia, String pHorario);
}
