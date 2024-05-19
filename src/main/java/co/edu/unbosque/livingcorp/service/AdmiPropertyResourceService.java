package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.*;
import co.edu.unbosque.livingcorp.model.entity.*;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AdmiPropertyResourceService implements Serializable {
    private static final Logger logger = Logger.getLogger(AdmiPropertyResourceService.class);

    private static final long serialVersionUID = 1L;

    @Inject
    private InterfaceDao<User,String> userDao;
    @Inject
    private InterfaceDao<Property,Integer> propertyDao;
    @Inject
    private InterfaceDao<Resource,Integer> resourceDao;
    @Inject
    private InterfaceDao<PropertyResource,Integer> propertyResourceDao;
    @Inject
    private InterfaceDao<Resident,Integer> residentDao;
    private NotificationService notificationService;
    private ModelMapper modelMapper;

    public AdmiPropertyResourceService() {
        modelMapper = new ModelMapper();
        notificationService = new NotificationService();
    }

    public boolean createPropertyResource(PropertyResourceDto propertyResourceDto) throws RepetedObjectException {
        if (propertyResourceDto != null){
            propertyResourceDto.setProId(findPropertyByName(propertyResourceDto.getProId()));
            propertyResourceDto.setResId(findResourceByName(propertyResourceDto.getResId()));
            propertyResourceDao.create(modelMapper.map(propertyResourceDto,PropertyResource.class));
            logger.info("Se crea la reserva a la propiedad: " + propertyResourceDto.getProId().getNameProperty());
            notificationService.notificationResource(emailResident(propertyResourceDto),propertyResourceDto);
            return true;
        }
        logger.info("No se crea la reserva a la propiedad: " + propertyResourceDto.getProId().getNameProperty());
        return false;
    }



    public List<String> listEmilUser(){
        logger.info("retorna lista de emals de los administradores");
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
        logger.info("retorna la primer propiedad con el mismo nombre");
        return propertiesDto.stream()
                .filter(entity -> propertyDto.getNameProperty().equals(entity.getNameProperty()))
                .findFirst().orElseGet(PropertyDto::new);
    }

    public List<String> listNameProperty(){
        logger.info("Retorna lista de nombre de las propiedades");
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
        logger.info("Retorna la primer reserva con el mismo tipo");
        return resourcesDto.stream()
                .filter(entity -> resourceDto.getType().equals(entity.getType()))
                .findFirst().orElseGet(ResourceDto::new);
    }

    public List<String> listTypeResource(){
        logger.info("retona el nombre de los recursos por tipo");
        return resourceDao.getAll()
                .stream()
                .map(entity -> modelMapper.map(entity, ResourceDto.class))
                .map(ResourceDto::getType)
                .collect(Collectors.toList());
    }
    public List<String> emailResident(PropertyResourceDto propertyResourceDto){
        return residentDao.getAll().stream()
                .map(entity -> modelMapper.map(entity, ResidentDto.class))
                .filter(entity -> entity.getIdProperty().getIdProperty()==propertyResourceDto.getProId().getIdProperty())
                .map(entity ->entity.getUserName().getEmailUser())
                .collect(Collectors.toList());
    }

}
