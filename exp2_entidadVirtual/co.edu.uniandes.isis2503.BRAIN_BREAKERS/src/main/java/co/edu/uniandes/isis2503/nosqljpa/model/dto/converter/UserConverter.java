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
import co.edu.uniandes.isis2503.nosqljpa.interfaces.IUserCoverter;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.UserDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author e.reyesm
 */
public class UserConverter implements IUserCoverter{
     public static IUserCoverter CONVERTER = new UserConverter();

    public UserConverter() {

    }

    @Override
    public UserDTO entityToDto(UserEntity entity) {
        UserDTO dto = new UserDTO(entity.getCorreo(),entity.getContraseña());
        return dto;
    }

    @Override
    public UserEntity dtoToEntity(UserDTO dto) {
        UserEntity entity = new UserEntity(dto.getCorreo(),dto.getPassword());
        return entity;
    }

    @Override
    public List<UserDTO> listEntitiesToListDTOs(List<UserEntity> entities) {
        ArrayList<UserDTO> dtos = new ArrayList<>();
        for (UserEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }

    @Override
    public List<UserEntity> listDTOsToListEntities(List<UserDTO> dtos) {
        ArrayList<UserEntity> entities = new ArrayList<>();
        for (UserDTO dto : dtos) {
            entities.add(dtoToEntity(dto));
        }
        return entities;
    }

}
