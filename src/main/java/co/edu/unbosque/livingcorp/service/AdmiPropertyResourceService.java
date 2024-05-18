package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.PropertyResourceDto;
import co.edu.unbosque.livingcorp.model.dto.ResourceDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.entity.PropertyResource;
import co.edu.unbosque.livingcorp.model.entity.Resource;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AdmiPropertyResourceService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private InterfaceDao<User,String> userDao;
    @Inject
    private InterfaceDao<Property,Integer> propertyDao;
    @Inject
    private InterfaceDao<Resource,Integer> resourceDao;
    @Inject
    private InterfaceDao<PropertyResource,Integer> propertyResourceDao;

    private ModelMapper modelMapper;

    public AdmiPropertyResourceService() {
        modelMapper = new ModelMapper();
    }

    public boolean createPropertyResource(PropertyResourceDto propertyResourceDto) throws RepetedObjectException {
        if (propertyResourceDto != null){
            propertyResourceDto.setProId(findPropertyByName(propertyResourceDto.getProId()));
            propertyResourceDto.setResId(findResourceByName(propertyResourceDto.getResId()));
            propertyResourceDao.create(modelMapper.map(propertyResourceDto,PropertyResource.class));
            return true;
        }
        return false;
    }



    public List<String> listEmilUser(){

        return userDao.getAll()
                       .stream()
                       .map(entity -> modelMapper.map(entity, UserDto.class))
                .filter(UserDto::isPropertyAdmin)
                .map(UserDto::getEmailUser)
                .collect(Collectors.toList());
    }

    public PropertyDto findPropertyByName(PropertyDto propertyDto ){
        List<PropertyDto> propertiesDto = propertyDao.getAll()
                .stream()
                .map(entity -> modelMapper.map(entity, PropertyDto.class))
                .collect(Collectors.toList());
        return propertiesDto.stream()
                .filter(entity -> propertyDto.getNameProperty().equals(entity.getNameProperty()))
                .findFirst().orElseGet(PropertyDto::new);
    }

    public List<String> listNameProperty(){

        return propertyDao.getAll()
                .stream()
                .map(entity -> modelMapper.map(entity, PropertyDto.class))
                .map(PropertyDto::getNameProperty)
                .collect(Collectors.toList());
    }   
    
    
    
    public ResourceDto findResourceByName(ResourceDto resourceDto){
        List<ResourceDto> resourcesDto = resourceDao.getAll()
                .stream()
                .map(entity -> modelMapper.map(entity, ResourceDto.class))
                .collect(Collectors.toList());
        return resourcesDto.stream()
                .filter(entity -> resourceDto.getType().equals(entity.getType()))
                .findFirst().orElseGet(ResourceDto::new);
    }

    public List<String> listTypeResource(){

        return resourceDao.getAll()
                .stream()
                .map(entity -> modelMapper.map(entity, ResourceDto.class))
                .map(ResourceDto::getType)
                .collect(Collectors.toList());
    }

}
