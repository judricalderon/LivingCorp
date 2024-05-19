package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.*;
import co.edu.unbosque.livingcorp.model.entity.*;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserService implements Serializable {
    private static final Logger logger = Logger.getLogger(UserService.class);
    private static final long serialVersionUID = 1L;
    @Inject
    private InterfaceDao<PropertyResource,Integer> propertyResourceDao;
    @Inject
    private InterfaceDao<Resident,Integer> residentDao;
    @Inject
    private InterfaceDao<ResourceBooking,Integer> resourceBookingDao;
    private ModelMapper modelMapper;
    private Notification notification;

    public UserService() {
        modelMapper = new ModelMapper();
        notification = new Notification();
    }

    public boolean crearReserva(ResourceBookingDto resourceBookingDto,UserDto userDto) throws RepetedObjectException {
        System.out.println(resourceBookingDto+"crearReserva");
                if(resourceBookingDto != null){
                    resourceBookingDto.setPaymentComplete(true);
                    resourceBookingDao.create(modelMapper.map(resourceBookingDto,ResourceBooking.class));
                    logger.info("Creada reserva completada");
                    notification.MsnResident(resourceBookingDto,userDto);
                    return true;
                }else {
                    logger.info("no se crea reserva");
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
        logger.info("Create List PropertyResoruce");
         return reservas;

    }
    public double calcularPrecio (ResourceBookingDto resourceBookingDto){
        Duration duration = Duration.between(resourceBookingDto.getBookingStartDate(), resourceBookingDto.getBookingEndDate());
        logger.info(duration);
    double aux= (double)duration.toHours()*resourceBookingDto.getPropertyResourceId().getMinPrice();
        logger.info(aux+"calcularPrecio");
            return aux;
    }
    public boolean calcularTiempoMin(ResourceBookingDto resourceBookingDto){
        Duration duration = Duration.between(resourceBookingDto.getBookingStartDate(), resourceBookingDto.getBookingEndDate());
        double aux= (double)duration.toHours();
        logger.info(aux+"calcularTiempoMin");
        return aux > 3;
    }


}
