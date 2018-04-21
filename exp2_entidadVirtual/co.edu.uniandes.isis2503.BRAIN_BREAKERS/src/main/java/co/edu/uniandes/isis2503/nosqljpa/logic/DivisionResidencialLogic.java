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

import co.edu.uniandes.isis2503.nosqljpa.interfaces.IDivisionResidencialLogic;
import static co.edu.uniandes.isis2503.nosqljpa.model.dto.converter.DivisionResidencialConverter.CONVERTER;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DivisionResidencialDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.DivisionResidencialEntity;
import co.edu.uniandes.isis2503.nosqljpa.persistence.DivisionResidencialPersistence;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author e.reyesm
 */
public class DivisionResidencialLogic implements IDivisionResidencialLogic {

    private final DivisionResidencialPersistence persistence;

    public DivisionResidencialLogic() {
        this.persistence = new DivisionResidencialPersistence();
    }

    @Override
    public DivisionResidencialDTO add(DivisionResidencialDTO dto) {
        DivisionResidencialDTO result = CONVERTER.entityToDto(persistence.add(CONVERTER.dtoToEntity(dto)));
        return result;
    }

    @Override
    public DivisionResidencialDTO addResidencia(String divison, String recidencia) {
        DivisionResidencialDTO buscada = find(divison);
        buscada.agregarResidencia(recidencia);
        update(buscada);
        return buscada;
    }

    @Override
    public DivisionResidencialDTO update(DivisionResidencialDTO dto) {
        DivisionResidencialDTO result = CONVERTER.entityToDto(persistence.update(CONVERTER.dtoToEntity(dto)));
        return result;
    }

    @Override
    public DivisionResidencialDTO find(String id) {
        return CONVERTER.entityToDto(persistence.find(id));
    }

    @Override
    public List<DivisionResidencialDTO> all() {
        return CONVERTER.listEntitiesToListDTOs(persistence.all());
    }

    @Override
    public Boolean delete(String id) {
        return persistence.delete(id);
    }

}
