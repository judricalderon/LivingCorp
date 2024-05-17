package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.ResidentDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.entity.Resident;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Stateless
public class AdmiUserService {
    @Inject
    private InterfaceDao<User,String> userDao;
    @Inject
    private InterfaceDao<Resident,Integer> residentDao;

    private ModelMapper modelMapper;

    public AdmiUserService() {
        modelMapper = new ModelMapper();
           }
    public boolean createUser(UserDto userDto) throws RepetedObjectException {

            userDao.create(modelMapper.map(userDto,User.class));
            if(userDto.isPropertyAdmin()){
                return true;

            }else{
                return false;
            }
    }

    public boolean createPropertyResident(ResidentDto residentDto) throws RepetedObjectException {

        if (residentDto != null) {

            residentDao.create(modelMapper.map(residentDto,Resident.class));
            return true;
        }else return false;
    }
}
