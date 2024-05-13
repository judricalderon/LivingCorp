package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AdmiPropertiesService  {


    @Inject
    private InterfaceDao<Property,Integer> propertyDao;
    @Inject
    private InterfaceDao<User,String> userDao;
    private ModelMapper modelMapper;

    public AdmiPropertiesService() {
        modelMapper = new ModelMapper();
    }

    public boolean createProperties(PropertyDto propertiesDto) throws RepetedObjectException, DontExistException {
        List<PropertyDto> listaBd = propertyDao.getAll()
                                                .stream()
                                                .map(entity ->modelMapper.map(entity,PropertyDto.class))
                                                .collect(Collectors.toList());
        if(!listaBd.isEmpty()) {
            boolean nombreIgual = listaBd.stream()
                    .anyMatch(entity -> entity.getNameProperty().equals(propertiesDto.getNameProperty()) && entity.getCity().equals(propertiesDto.getCity()));
            if (nombreIgual){
                return false;
            }else {
                propertiesDto.setPropertyAdmin(modelMapper.map(userDao.find(propertiesDto.getPropertyAdmin().getNameUser()), UserDto.class));
                propertyDao.create(modelMapper.map(propertiesDto, Property.class));
                return true;
            }
        }else {
            propertiesDto.setPropertyAdmin(modelMapper.map(userDao.find(propertiesDto.getPropertyAdmin().getNameUser()), UserDto.class));
            propertyDao.create(modelMapper.map(propertiesDto, Property.class));
            return true;
        }

    }
    public List<String> listarAdmi(){
        List<String> administradores = userDao.getAll().stream()
                .filter(User::isPropertyAdmin)
                .map(entity -> modelMapper.map(entity,UserDto.class))
                .map(UserDto::getNameUser)
                .collect(Collectors.toList());
        return administradores;
    }

}
