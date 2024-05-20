package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.FailConectionException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.ServiceProviderDto;
import co.edu.unbosque.livingcorp.model.dto.ServiceRFQDto;
import co.edu.unbosque.livingcorp.model.dto.ServiceRequestDto;
import co.edu.unbosque.livingcorp.model.entity.ServiceRFQ;
import co.edu.unbosque.livingcorp.model.entity.ServiceRequest;
import co.edu.unbosque.livingcorp.model.persistence.InterfaceDao;
import com.google.gson.Gson;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProveedorAPIService implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ProveedorAPIService.class);
    private static final String URL = "http://localhost:8888/proveedor/api";
    private final Client client;
    private final Gson gson;
    private ModelMapper modelMapper;
    @Inject
    private InterfaceDao<ServiceRFQ,Integer> serviceRFQDao;
    @Inject
    private InterfaceDao<ServiceRequest,Integer> serviceRequestDao;
    private NotificationService notificationService;

    public ProveedorAPIService() {
        this.client = ClientBuilder.newClient();
        this.gson = new Gson();
        modelMapper = new ModelMapper();
    }


    public boolean createServiceRFQ(ServiceRFQDto serviceRFQDto) throws RepetedObjectException {
        if(serviceRFQDto != null) {
            serviceRFQDao.create(modelMapper.map(serviceRFQDto,ServiceRFQ.class));
            notificationService.notificationService(serviceRFQDto);
            logger.info("Service RFQ created");
            return true;
        }else {
            logger.info("Service RFQ not created");
            return false;
        }
    }

    public boolean createServiceRequest(ServiceRequestDto serviceRequestDto) throws RepetedObjectException {
        if(serviceRequestDto != null) {
            serviceRequestDao.create(modelMapper.map(serviceRequestDto,ServiceRequest.class));
            notificationService.notificationRequest(serviceRequestDto);
            logger.info("Service Request created");
        return true;
        }else {
            logger.info("Service Request not created");
            return false;
        }
    }


    public List<String> getServiceMaintenance() throws FailConectionException {
        List<ServiceProviderDto> serviceProviderDtos = getProviders();
        logger.info("List Service maintenance");
        return serviceProviderDtos.stream()
                .filter(entity -> !entity.getServiceType().equals("Transporte"))
                .map(ServiceProviderDto::getServiceType)
                .collect(Collectors.toList());
    }

    public ServiceProviderDto getServiceTransport() throws FailConectionException, DontExistException {
        List<ServiceProviderDto> serviceProviderDtos = getProviders();
        logger.info("List Service Transport");
        return serviceProviderDtos.stream()
                .filter(entity -> entity.getServiceType().equals("Transporte"))
                .findFirst()
                .orElseGet(ServiceProviderDto::new);
    }

    public List<ServiceProviderDto> getProviders() throws FailConectionException {
        try {Response response = client.target(URL)
                    .path("/proveedor")
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                ServiceProviderDto[] providerDtos = gson.fromJson(response.readEntity(String.class), ServiceProviderDto[].class);
                logger.info("List of Service Providers");
                return Arrays.asList(providerDtos);
            } else {
                logger.error(response.getStatus());
                throw new FailConectionException("error en conexion"+ response.getStatusInfo().getReasonPhrase());
            }
        }catch (FailConectionException e){
            logger.error("Not Conexion");
            throw new FailConectionException("error en conexion");
        }


    }
}
