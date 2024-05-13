package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Stateless
public class AdmiPropertiesService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private InterfaceDao<Property,Integer> propertyDao;
    @Inject
    private InterfaceDao<User,String> userDao;
    private ModelMapper modelMapper;

    public AdmiPropertiesService() {
        modelMapper = new ModelMapper();
    }

    public boolean createProperties(PropertyDto propertiesDto) throws RepetedObjectException {
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
                propertyDao.create(modelMapper.map(propertiesDto, Property.class));
                return true;
            }
        }else {
            propertyDao.create(modelMapper.map(propertiesDto, Property.class));
            return true;
        }

    }
    public List<UserDto> listarAdmi(){
        return userDao.getAll().stream()
                .filter(User::isPropertyAdmin)
                .map(entity -> modelMapper.map(entity,UserDto.class))
                .collect(Collectors.toList());
    }

}
