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
package co.edu.uniandes.isis2503.nosqljpa.logic;

import co.edu.uniandes.isis2503.nosqljpa.interfaces.IUnidadResidencialLogic;
import static co.edu.uniandes.isis2503.nosqljpa.model.dto.converter.UnidadResidencialConverter.CONVERTER;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.UnidadResidencialDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DivisionResidencialDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.ResidenciaDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.DivisionResidencialEntity;
import co.edu.uniandes.isis2503.nosqljpa.persistence.UnidadResidencialPersistence;
import co.edu.uniandes.isis2503.nosqljpa.persistence.DivisionResidencialPersistence;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author e.reyesm
 */
public class UnidadResidencialLogic implements IUnidadResidencialLogic {

    private final UnidadResidencialPersistence persistence;
    private DivisionResidencialLogic logicDR;
    private ResidenciaLogic logicR;

    public UnidadResidencialLogic() {
        this.persistence = new UnidadResidencialPersistence();
        this.logicDR = new DivisionResidencialLogic();
        this.logicR = new ResidenciaLogic();
    }

    @Override
    public UnidadResidencialDTO add(UnidadResidencialDTO dto) {
        UnidadResidencialDTO result = CONVERTER.entityToDto(persistence.add(CONVERTER.dtoToEntity(dto)));
        return result;
    }

    @Override
    public UnidadResidencialDTO addDivison(String nombreUnidad, String division) throws Exception {
        UnidadResidencialDTO buscada = find(nombreUnidad);
        if (buscada == null) {
            throw new Exception("No hay unidad residencial con ese nombre");
        }
        DivisionResidencialDTO agregar = new DivisionResidencialDTO(division, nombreUnidad);
        logicDR.add(agregar);
        buscada.agregarDivisionResidenciales(division);
        update(buscada);
        return buscada;
    }

    @Override
    public DivisionResidencialDTO addResidencia(String nombreUnidad, String divison, String residencia) throws Exception {
        UnidadResidencialDTO buscada = find(nombreUnidad);
        if (buscada == null) {
            throw new Exception("No hay unidad residencial con ese nombre");
        }
        String key = divison+"_"+nombreUnidad;
        System.out.println(key+"*********************************************************");
        DivisionResidencialDTO divisionBuscado = logicDR.find(key);
        if (divisionBuscado == null) {
            throw new Exception("No hay una division residencial con ese nombre");
        }
        System.out.println("PASO1111*********************************************************");
        key = residencia+"_"+divison+"_"+nombreUnidad;
        logicR.add(new ResidenciaDTO(key));
        divisionBuscado.agregarResidencia(residencia);
        System.out.println("PASO2222*********************************************************");
        logicDR.update(divisionBuscado);
        System.out.println("PASO3333*********************************************************");
        return divisionBuscado;
    }

    @Override
    public UnidadResidencialDTO update(UnidadResidencialDTO dto) {
        UnidadResidencialDTO result = CONVERTER.entityToDto(persistence.update(CONVERTER.dtoToEntity(dto)));
        return result;
    }

    @Override
    public UnidadResidencialDTO find(String id) {
        return CONVERTER.entityToDto(persistence.find(id));
    }

    @Override
    public List<UnidadResidencialDTO> all() {
        return CONVERTER.listEntitiesToListDTOs(persistence.all());
    }

    @Override
    public Boolean delete(String id) {
        return persistence.delete(id);
    }
}
