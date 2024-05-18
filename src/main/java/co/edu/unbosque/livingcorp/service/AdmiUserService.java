package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.PasswordNotEncryptedException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.ResidentDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.entity.Resident;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stateless
public class AdmiUserService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private InterfaceDao<User,String> userDao;
    @Inject
    private InterfaceDao<Resident,Integer> residentDao;

    private ModelMapper modelMapper;

    public AdmiUserService() {
        modelMapper = new ModelMapper();
           }
    public boolean createUser(UserDto userDto) throws RepetedObjectException, PasswordNotEncryptedException {
            userDto.setPassword(encryptedPassword(userDto.getPassword()));
            userDao.create(modelMapper.map(userDto,User.class));
            if(userDto.isPropertyAdmin()){
                return true;
            }else{
                return false;
            }
    }

    public boolean createPropertyResident(ResidentDto residentDto) throws RepetedObjectException {

        if (residentDto != null) {

            residentDao.create(modelMapper.map(residentDto,Resident.class));
            return true;
        }else return false;
    }


    public String encryptedPassword(String password) throws PasswordNotEncryptedException {
        try {//inicializamos el metodo que utiliza el algoritmo de cifrado
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //le pasamos la contrasena para que la codifique y estoretorna una cadena de byte, asi que se mete en un arreglo
            byte[] digest = md.digest(password.getBytes());
            //se crea una cadea hexagecimal para poder la cadena de los bytes
            StringBuilder hexString = new StringBuilder();
            //un for iterando los bytes para incluirlos en el atributo que recibe gexagecimales
            for (byte b : digest) {
                //se convierte la cadena de bytes a hexagecimal gracias a ese formato (volver a ver video)
                hexString.append(String.format("%02X", b));
            }
            //pasamos la hexagecimal a string
            return hexString.toString();
            //por si se coloca un algoritmo que no se esa utilizando
        } catch (NoSuchAlgorithmException e) {
            throw new PasswordNotEncryptedException("La contraseña no se ha cifrado");
        }


    }
}
