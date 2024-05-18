package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.*;
import co.edu.unbosque.livingcorp.model.entity.PropertyResource;
import co.edu.unbosque.livingcorp.model.entity.Resident;
import co.edu.unbosque.livingcorp.model.entity.Resource;
import co.edu.unbosque.livingcorp.model.entity.ResourceBooking;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private InterfaceDao<PropertyResource,Integer> propertyResourceDao;
    @Inject
    private InterfaceDao<Resident,Integer> residentDao;
    @Inject
    private InterfaceDao<ResourceBooking,Integer> resourceBookingDao;
    private ModelMapper modelMapper;

    public UserService() {
        modelMapper = new ModelMapper();
    }

    public boolean crearReserva(ResourceBookingDto resourceBookingDto) throws RepetedObjectException {
                if(resourceBookingDto != null){
                    resourceBookingDto.setPaymentComplete(true);
                    resourceBookingDao.create(modelMapper.map(resourceBookingDto,ResourceBooking.class));
                    return true;
                }else {
                    return false;
                }
    }

    public List<PropertyResourceDto> obtenerReservas(UserDto userDto) throws DontExistException {
        ResidentDto residentDto = residentDao.getAll().stream()
                .filter(entity -> userDto.getNameUser().equals(entity.getUserName().getNameUser()))
                .map(entity -> modelMapper.map(entity, ResidentDto.class))
                .findFirst()
                .orElseThrow(() -> new DontExistException("El residente no existe"));

        List<PropertyResourceDto> reservas = propertyResourceDao.getAll().stream()
                .filter(entity-> entity.getProId().getIdProperty()==residentDto.getIdProperty().getIdProperty())
                .map(entity ->modelMapper.map(entity,PropertyResourceDto.class))
                .collect(Collectors.toList());
         return reservas;

    }
    public double calcularPrecio (ResourceBookingDto resourceBookingDto){
        Duration duration = Duration.between(resourceBookingDto.getBookingStartDate(), resourceBookingDto.getBookingEndDate());


        double aux= (double)duration.toHours()*resourceBookingDto.getPropertyResourceId().getMinPrice();

            return aux;
    }
    public boolean calcularTiempoMin(ResourceBookingDto resourceBookingDto){
        Duration duration = Duration.between(resourceBookingDto.getBookingStartDate(), resourceBookingDto.getBookingEndDate());
        double aux= (double)duration.toHours();
        return aux > 3;
    }


}
