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

import co.edu.uniandes.isis2503.nosqljpa.interfaces.IResidenciaLogic;
import static co.edu.uniandes.isis2503.nosqljpa.model.dto.converter.ResidenciaConverter.CONVERTER;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.ResidenciaDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DivisionResidencialDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.UnidadResidencialDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.ResidenciaEntity;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.UnidadResidencialEntity;
import co.edu.uniandes.isis2503.nosqljpa.persistence.ResidenciaPersistence;
import co.edu.uniandes.isis2503.nosqljpa.persistence.UnidadResidencialPersistence;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author e.reyesm
 */
public class ResidenciaLogic implements IResidenciaLogic {

    private final ResidenciaPersistence persistence;
    private DivisionResidencialLogic logicDR;
    private UnidadResidencialPersistence persistenceUR;

    public ResidenciaLogic() {
        this.persistence = new ResidenciaPersistence();
        logicDR = new DivisionResidencialLogic();
        persistenceUR = new UnidadResidencialPersistence();
    }

    @Override
    public ResidenciaDTO add(ResidenciaDTO dto) {
        ResidenciaDTO result = CONVERTER.entityToDto(persistence.add(CONVERTER.dtoToEntity(dto)));
        return result;
    }

    @Override
    public ResidenciaDTO addAlerta(String unidad, String divison, String residencia, String alerta) throws Exception {
        UnidadResidencialEntity buscado = persistenceUR.find(unidad);
        if (buscado == null) {
            throw new Exception("No existe una unidad residencial con ese nombre");
        }
        String key = divison + "_" + unidad;
        DivisionResidencialDTO divisionB = logicDR.find(key);
        if (divisionB == null) {
            throw new Exception("No existe una division residencial con ese nombre");
        }
        key = residencia + "_" + divison + "_" + unidad;
        ResidenciaDTO agregar = find(key);
        agregar.agregarAlerta(alerta);
        update(agregar);
        return agregar;
    }

    @Override
    public ResidenciaDTO addHorario(String unidad, String divison, String residencia, String horario) throws Exception {
        UnidadResidencialEntity buscado = persistenceUR.find(unidad);
        if (buscado == null) {
            throw new Exception("No existe una unidad residencial con ese nombre");
        }
        String key = divison + "_" + unidad;
        DivisionResidencialDTO divisionB = logicDR.find(key);
        if (divisionB == null) {
            throw new Exception("No existe una division residencial con ese nombre");
        }
        key = residencia + "_" + divison + "_" + unidad;
        ResidenciaDTO agregar = find(key);
        agregar.agregarHorario(horario);
        update(agregar);
        return agregar;
    }

    @Override
    public ResidenciaDTO update(ResidenciaDTO dto) {
        ResidenciaDTO result = CONVERTER.entityToDto(persistence.update(CONVERTER.dtoToEntity(dto)));
        return result;
    }

    @Override
    public ResidenciaDTO find(String id) {
        return CONVERTER.entityToDto(persistence.find(id));
    }

    @Override
    public List<ResidenciaDTO> all() {
        return CONVERTER.listEntitiesToListDTOs(persistence.all());
    }

    @Override
    public Boolean delete(String id) {
        return persistence.delete(id);
    }

}
