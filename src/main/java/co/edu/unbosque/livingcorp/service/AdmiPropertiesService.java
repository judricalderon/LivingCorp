package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.entity.Property;
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
    private ModelMapper modelMapper;

    public AdmiPropertiesService() {
        modelMapper = new ModelMapper();
    }

    private void createProperties(PropertyDto propertiesDto) {
        List<PropertyDto> listaBd = propertyDao.getAll()
                                                .stream()
                                                .map(entity ->modelMapper.map(entity,PropertyDto.class))
                                                .collect(Collectors.toList());
        if(!listaBd.isEmpty()) {
            boolean nombreIgual = listaBd.stream()
                    .anyMatch(entity -> entity.getNameProperty().equals(propertiesDto.getNameProperty()) && entity.getCity().equals(propertiesDto.getCity()));
            if (nombreIgual){

            }else {

            }
        }

    }

}
