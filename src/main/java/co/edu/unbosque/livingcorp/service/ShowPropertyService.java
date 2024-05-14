package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.dto.VisitorDto;
import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.entity.Visitor;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ShowPropertyService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private InterfaceDao<Property,Integer> propertyDao;
    @Inject
    private InterfaceDao<Visitor,Integer> visitorDao;
    @Inject
    private InterfaceDao<User,String> userDao;
    private ModelMapper modelMapper;



    public ShowPropertyService() {
        modelMapper = new ModelMapper();
    }

    public List<PropertyDto> listPropertyObject(){
        return propertyDao.getAll().stream()
                .filter(entity-> entity.isRent() || entity.isSale())
                .map(entity ->modelMapper.map(entity,PropertyDto.class))
                .collect(Collectors.toList());
    }



    public boolean createAppointmentVisitor(VisitorDto visitorDto) throws RepetedObjectException {
        if(visitorDto != null){
            visitorDao.create(modelMapper.map(visitorDto,Visitor.class));
            return true;
        }else {
            return true;
        }

    }
    public List<String> listNameUser(){

        return userDao.getAll()
                .stream()
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .filter(UserDto::isPropertyAdmin)
                .map(UserDto::getNameUser)
                .collect(Collectors.toList());
    }


}
