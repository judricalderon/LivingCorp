package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.PasswordNotEncryptedException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.persistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Stateless
public class LogService implements Serializable {
    private static final Logger logger = Logger.getLogger(LogService.class);
    private static final long serialVersionUID = 1L;
    @Inject
    private InterfaceDao<User, String> userDao;
    private ModelMapper mp;

    public LogService() {
        mp = new ModelMapper();
    }


    public UserDto log(UserDto userDto) throws PasswordNotEncryptedException, RepetedObjectException, DontExistException {

        if (userDao.find(userDto.getNameUser()) != null) {
            UserDto auxUserDto = mp.map(userDao.find(userDto.getNameUser()), UserDto.class);
            if (auxUserDto.getAttempt() <= 3) {
                String auxEncrypted = encryptedPassword(userDto.getPassword());
                if (auxUserDto.getPassword().equals(auxEncrypted) && auxUserDto.getNameUser().equals(userDto.getNameUser() )) {
                    auxUserDto.setAttempt(0);
                    auxUserDto.setLastLogin(LocalDateTime.now());
                    userDao.update(mp.map(auxUserDto, User.class));
                    auxUserDto.setPassword("");
                    logger.info("User is create");
                    return auxUserDto;

                } else {
                    auxUserDto.setAttempt(auxUserDto.getAttempt() + 1);
                    userDao.update(mp.map(auxUserDto, User.class));
                    logger.info("User is updated");
                }
            } else {

                auxUserDto.setBlocked(true);
                userDao.update(mp.map(auxUserDto, User.class));
                logger.info("User is blocked");
                return auxUserDto;
            }

        } else {
            logger.info("no existe el usuario");
            throw new DontExistException("no existe el usuario");
        }
        return null;
    }

    public String redireccionar(UserDto userDto) {
        if (userDto != null) {
            if (userDto.isPropertyAdmin()) {
                logger.info("User is admin");
                return "panelAdmiResource.xhtml";
            } else {
                logger.info("User is not admin");
                return "panelUser.xhtml";
            }
        } else {
            logger.info("User is null");
            return "log.xhtml";
        }
    }

    public String encryptedPassword(String password) throws PasswordNotEncryptedException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
           byte[] digest = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
               hexString.append(String.format("%02X", b));
            }
            return hexString.toString();
         } catch (NoSuchAlgorithmException e) {
            throw new PasswordNotEncryptedException("La contraseña no se ha cifrado");
        }


    }

}
