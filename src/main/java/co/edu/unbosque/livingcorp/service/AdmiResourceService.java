package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.ResourceDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.entity.Notification;
import co.edu.unbosque.livingcorp.model.entity.Resource;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AdmiResourceService {
    private static final Logger logger = Logger.getLogger(AdmiResourceService.class);

    @Inject
    private InterfaceDao<Resource, Integer> resourceDao;
    @Inject
    private InterfaceDao<User,String> userDao;
    private ModelMapper modelMapper;


    public AdmiResourceService() {
        modelMapper = new ModelMapper();

    }

    public boolean create(ResourceDto resourceDto,UserDto userDto) throws RepetedObjectException {
        logger.info("Creating resource: " + resourceDto);
        if (resourceDto != null) {
            resourceDao.create(modelMapper.map(resourceDto, Resource.class));
            logger.info("Resource created: " + resourceDto);
            return true;
        } else {
            logger.info("Resource not created");
            return false;
        }
    }
    public List<String> listEmailResident(){
        return userDao.getAll().stream()
                .map(entity ->modelMapper.map(entity, UserDto.class))
                .filter(entity ->!entity.isPropertyAdmin())
                .map(UserDto::getEmailUser)
                .collect(Collectors.toList());
    }

}
