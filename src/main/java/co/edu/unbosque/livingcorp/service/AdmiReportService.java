package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.ResidentDto;
import co.edu.unbosque.livingcorp.model.dto.ResourceBookingDto;
import co.edu.unbosque.livingcorp.model.entity.Resident;
import co.edu.unbosque.livingcorp.model.entity.ResourceBooking;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AdmiReportService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private InterfaceDao<Resident,Integer> residentDtoDao;
    @Inject
    private InterfaceDao<ResourceBooking,Integer> resourceBookingDao;
    private ModelMapper modelMapper;

    public AdmiReportService() {
        modelMapper = new ModelMapper();
    }
    public List<ResourceBookingDto> listResourceBookings() {
        return resourceBookingDao.getAll().stream()
                .map(entity -> modelMapper.map(entity, ResourceBookingDto.class))
                .collect(Collectors.toList());
    }
    public List<ResidentDto> listResident() {
        return residentDtoDao.getAll().stream()
                .filter(entity -> !entity.getUserName().isPropertyAdmin())
                .map(entity -> modelMapper.map(entity, ResidentDto.class))
                .collect(Collectors.toList());
    }
}
