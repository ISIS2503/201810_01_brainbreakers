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
package co.edu.uniandes.isis2503.nosqljpa.model.dto.converter;

import co.edu.uniandes.isis2503.nosqljpa.interfaces.IDivisionResidencialConverter;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DivisionResidencialDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.DivisionResidencialEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author e.reyesm
 */
public class DivisionResidencialConverter implements IDivisionResidencialConverter {

    public static IDivisionResidencialConverter CONVERTER = new DivisionResidencialConverter();

    public DivisionResidencialConverter() {

    }

    @Override
    public DivisionResidencialDTO entityToDto(DivisionResidencialEntity entity) {
        System.out.println(entity.getNombre());
        System.out.println(entity.getResidencias());
        System.out.println(entity.getUnidadResidencial());

        DivisionResidencialDTO dto = new DivisionResidencialDTO();      
        dto.setNombre(entity.getNombre());
        dto.setResiencias(entity.getResidencias());
        dto.setUnidadResidencial(entity.getUnidadResidencial());
        return dto;
    }

    @Override
    public DivisionResidencialEntity dtoToEntity(DivisionResidencialDTO dto) {
        DivisionResidencialEntity entity = new DivisionResidencialEntity(dto.getNombre(), dto.getUnidadResidencial(),dto.getLatitud(), dto.getLongitud());
        entity.setResidencias(dto.getResidencias());
        return entity;
    }

    @Override
    public List<DivisionResidencialDTO> listEntitiesToListDTOs(List<DivisionResidencialEntity> entities) {
        ArrayList<DivisionResidencialDTO> dtos = new ArrayList<>();
        for (DivisionResidencialEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }

    @Override
    public List<DivisionResidencialEntity> listDTOsToListEntities(List<DivisionResidencialDTO> dtos) {
        ArrayList<DivisionResidencialEntity> entities = new ArrayList<>();
        for (DivisionResidencialDTO dto : dtos) {
            entities.add(dtoToEntity(dto));
        }
        return entities;
    }

}