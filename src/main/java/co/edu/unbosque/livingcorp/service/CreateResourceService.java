package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.ExceptionRepetedObject;
import co.edu.unbosque.livingcorp.model.dto.ResourceDto;
import co.edu.unbosque.livingcorp.model.entity.Resource;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

@Stateless
public class CreateResourceService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private InterfaceDao<Resource,Integer> resourceDao;
    private ModelMapper modelMapper;

    public CreateResourceService() {
        modelMapper = new ModelMapper();
    }
    public boolean create(ResourceDto resourceDto) throws ExceptionRepetedObject {

        if (resourceDto!= null){
            //creo el recuro
            resourceDao.create(modelMapper.map(resourceDto,Resource.class));
            return true;
        }else {
            return false;
        }
    }

}
