package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.entity.Property;
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
    private ModelMapper modelMapper;

    public ShowPropertyService() {
        modelMapper = new ModelMapper();
    }

    public List<PropertyDto> listProperty(){
        return propertyDao.getAll().stream()
                .filter(entity-> entity.isRent() || entity.isSale())
                .map(entity ->modelMapper.map(entity,PropertyDto.class))
                .collect(Collectors.toList());
    }

}
