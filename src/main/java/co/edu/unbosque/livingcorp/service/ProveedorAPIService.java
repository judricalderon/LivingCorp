package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.FailConectionException;
import co.edu.unbosque.livingcorp.model.dto.ServiceProviderDto;
import com.google.gson.Gson;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProveedorAPIService implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String URL = "http://localhost:8888/proveedor/api";
    private final Client client;
    private final Gson gson;

    public ProveedorAPIService(Client client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }
    public ServiceProviderDto createServiceProvider(ServiceProviderDto serviceProviderDto) throws FailConectionException {
        try {
            Response response = client.target(URL)
                    .path("/proveedor")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(serviceProviderDto, MediaType.APPLICATION_JSON));

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(ServiceProviderDto.class);
        } else {
            throw new FailConectionException("error en conexion" + response.getStatusInfo().getReasonPhrase());
        }
    }catch (FailConectionException e){
        throw new FailConectionException("error en conexion");
    }
    }
    public List<ServiceProviderDto> getServiceMaintenance() throws FailConectionException {
        List<ServiceProviderDto> serviceProviderDtos = getProviders();
        return serviceProviderDtos.stream()
                .filter(entity -> !entity.getServiceType().equals("Transporte"))
                .collect(Collectors.toList());
    }

    public ServiceProviderDto getServiceTrasport() throws FailConectionException {
        List<ServiceProviderDto> serviceProviderDtos = getProviders();
        return serviceProviderDtos.stream()
                .filter(entity -> entity.getServiceType().equals("Transporte"))
                .findFirst()
                .orElse(null);
    }

    public List<ServiceProviderDto> getProviders() throws FailConectionException {
        try {
            Response response = client.target(URL)
                    .path("/proveedor")
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                ServiceProviderDto[] providerDtos = gson.fromJson(response.readEntity(String.class), ServiceProviderDto[].class);
                return Arrays.asList(providerDtos);
            } else {
                throw new FailConectionException("error en conexion"+ response.getStatusInfo().getReasonPhrase());
            }
        }catch (FailConectionException e){
            throw new FailConectionException("error en conexion");
        }


    }
}
