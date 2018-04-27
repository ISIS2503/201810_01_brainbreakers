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
import co.edu.uniandes.isis2503.nosqljpa.interfaces.IUserLogic;
import static co.edu.uniandes.isis2503.nosqljpa.model.dto.converter.UserConverter.CONVERTER;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.UserDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DivisionResidencialDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.ResidenciaDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.UserEntity;
import co.edu.uniandes.isis2503.nosqljpa.persistence.UserPersistence;
import co.edu.uniandes.isis2503.nosqljpa.persistence.DivisionResidencialPersistence;
import java.util.List;
import java.util.UUID;
/**
 *
 * @author e.reyesm
 */
public class UserLogic implements IUserLogic{
    
    private final UserPersistence persistence;
    private ResidenciaLogic logicR;

    public UserLogic() {
        this.persistence = new UserPersistence();
        logicR = new ResidenciaLogic();
    }

    @Override
    public UserDTO add(UserDTO dto, String residencia) throws Exception{
        ResidenciaDTO buscado = logicR.find(residencia);
        if(buscado == null)
        {
            throw new Exception("no existe esa recidencia");
        }
        buscado.agregarUsuario(dto.getCorreo());
        logicR.update(buscado);
        UserDTO result = CONVERTER.entityToDto(persistence.add(CONVERTER.dtoToEntity(dto)));
        return result;
    }

    @Override
    public UserDTO update(UserDTO dto) {
         UserDTO result = CONVERTER.entityToDto(persistence.update(CONVERTER.dtoToEntity(dto)));
        return result;
    }

    @Override
    public UserDTO find(String id) {
        return CONVERTER.entityToDto(persistence.find(id));
    }

    @Override
    public List<UserDTO> all() {
       return CONVERTER.listEntitiesToListDTOs(persistence.all());
    }

    @Override
    public Boolean delete(String id, String residencia) throws Exception {
        ResidenciaDTO buscado = logicR.find(residencia);
        if(buscado == null)
        {
            throw new Exception("no existe esa recidencia");
        }
        buscado.deleteUsuario(id);
        logicR.update(buscado);
        return persistence.delete(id);
        
    }
    
}
