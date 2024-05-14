package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.VisitorDto;
import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.entity.Visitor;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ShowPropertyService {
    @Inject
    private InterfaceDao<Property,Integer> propertyDao;
    @Inject
    private InterfaceDao<Visitor,Integer> visitorDao;
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


}
