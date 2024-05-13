package co.edu.unbosque.livingcorp.service;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.ResourceDto;
import co.edu.unbosque.livingcorp.model.entity.Resource;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

@Stateless
public class AdmiResourceService  {


    @Inject
    private InterfaceDao<Resource,Integer> resourceDao;
    private ModelMapper modelMapper;

    public AdmiResourceService() {
        modelMapper = new ModelMapper();
    }
    public boolean create(ResourceDto resourceDto) throws RepetedObjectException {

        if (resourceDto!= null){
            //creo el recuro
            resourceDao.create(modelMapper.map(resourceDto,Resource.class));
            return true;
        }else {
            return false;
        }
    }

}
