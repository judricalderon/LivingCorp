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
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stateless
public class AdmiUserService implements Serializable {
    private static final Logger logger = Logger.getLogger(AdmiUserService.class);
    private static final long serialVersionUID = 1L;
    @Inject
    private InterfaceDao<User,String> userDao;
    @Inject
    private InterfaceDao<Resident,Integer> residentDao;

    private ModelMapper modelMapper;

    public AdmiUserService() {
        logger.info("AdmiUserService");
        modelMapper = new ModelMapper();
           }
    public boolean createUser(UserDto userDto) throws RepetedObjectException, PasswordNotEncryptedException {
            userDto.setPassword(encryptedPassword(userDto.getPassword()));
            userDao.create(modelMapper.map(userDto,User.class));
            if(userDto.isPropertyAdmin()){
                logger.info("Creating property admin");
                return true;
            }else{
                logger.info("Creating property user");
                return false;
            }
    }

    public boolean createPropertyResident(ResidentDto residentDto) throws RepetedObjectException {
    logger.info("Creating property resident");
        if (residentDto != null) {

            residentDao.create(modelMapper.map(residentDto,Resident.class));
            return true;
        }else return false;
    }


    public String encryptedPassword(String password) throws PasswordNotEncryptedException {
        try {
            logger.info("Encrypting password");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02X", b));
            }
            logger.info("Encrypted password: " + hexString);
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
            throw new PasswordNotEncryptedException("La contraseña no se ha cifrado");
        }


    }
}
