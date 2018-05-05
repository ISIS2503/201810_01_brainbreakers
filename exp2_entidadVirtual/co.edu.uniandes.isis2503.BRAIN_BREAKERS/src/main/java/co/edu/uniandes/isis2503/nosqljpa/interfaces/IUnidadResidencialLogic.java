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
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.UnidadResidencialDTO;
import java.util.List;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DivisionResidencialDTO;

/**
 *
 * @author e.reyesm
 */
public interface IUnidadResidencialLogic {
    public UnidadResidencialDTO add(UnidadResidencialDTO dto);
    public UnidadResidencialDTO addDivison(String nombreUnidad, String division) throws Exception;
    public DivisionResidencialDTO addResidencia(String nombreUnidad, String divison, String residencia, String barrio) throws Exception;
    public UnidadResidencialDTO update(UnidadResidencialDTO dto);
    public UnidadResidencialDTO find(String id);
    public List<UnidadResidencialDTO> all();
    public Boolean delete(String id);
}
