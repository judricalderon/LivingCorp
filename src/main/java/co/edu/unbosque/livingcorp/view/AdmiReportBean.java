package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.ResidentDto;
import co.edu.unbosque.livingcorp.model.dto.ResourceBookingDto;
import co.edu.unbosque.livingcorp.service.AdmiReportService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.List;

@Named(value = "admiReportBean")
@RequestScoped
public class AdmiReportBean implements Serializable {
    private static final Logger logger = Logger.getLogger(AdmiReportBean.class);
    private static final long serialVersionUID = 1L;
    @Inject
    private AdmiReportService admiReportService;
    private List<ResourceBookingDto> resourceBookings;
    private List<ResidentDto> residentDtos;

    @PostConstruct
    public void init() {
        logger.info("AdmiReportBean init");
        resourceBookings = admiReportService.listResourceBookings();
        residentDtos = admiReportService.listResident();
    }

    public List<ResourceBookingDto> getResourceBookings() {
        return resourceBookings;
    }

    public void setResourceBookings(List<ResourceBookingDto> resourceBookings) {
        this.resourceBookings = resourceBookings;
    }

    public List<ResidentDto> getResidentDtos() {
        return residentDtos;
    }

    public void setResidentDtos(List<ResidentDto> residentDtos) {
        this.residentDtos = residentDtos;
    }
}
