package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.PropertyResourceDto;
import co.edu.unbosque.livingcorp.model.dto.ResourceDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.entity.Property;
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
    private InterfaceDao<Resource,String> resourceDao;

    private ModelMapper modelMapper;

    public AdmiPropertyResourceService() {
        modelMapper = new ModelMapper();
    }

    public boolean createPropertyResource(PropertyResourceDto propertyResourceDto) {
        return true;
    }


    public List<UserDto> ListUser(){
        return userDao.getAll()
                       .stream()
                       .map(entity -> modelMapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }

    public List<PropertyDto> ListProperty(){
        return propertyDao.getAll()
                .stream()
                .map(entity -> modelMapper.map(entity, PropertyDto.class))
                .collect(Collectors.toList());
    }

    public List<ResourceDto> ListResource(){
        return resourceDao.getAll()
                .stream()
                .map(entity -> modelMapper.map(entity, ResourceDto.class))
                .collect(Collectors.toList());
    }

}
