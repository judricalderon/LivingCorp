package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.PasswordNotEncryptedException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.presistence.InterfaceDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Stateless //lo reconoce como componente para posteriormente inyecta
//se deja para poder pasarlo entre servidores o persistir, esto por session
public class LogService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private InterfaceDao<User, String> userDao;
    private ModelMapper mp;

    public LogService() {
        mp = new ModelMapper();
    }

//loguear usuario y admi
public UserDto log(UserDto userDto) throws PasswordNotEncryptedException, RepetedObjectException, DontExistException {
    //busco el usuario en bd


    //verifico que no sea null el usuario
    if (userDao.find(userDto.getNameUser()) != null) {
        //busco el usuario en bd
        UserDto auxUserDto = mp.map(userDao.find(userDto.getNameUser()), UserDto.class);
        //comparo que sus intentos no sean mayores a 3
        if (auxUserDto.getAttempt()<=3){
            //encripto la contraseña del usuario de la vista
            String auxEncrypted = encryptedPassword(userDto.getPassword());
            //valido que sean las contraseñas y nombres iguales
            if (auxUserDto.getPassword().equals(auxEncrypted) && auxUserDto.getNameUser().equals(userDto.getNameUser())) {
                //reinicio los intentos a 0
                auxUserDto.setAttempt(0);
                //fecha de ingreso
                auxUserDto.setLastLogin(LocalDateTime.now());
                // persisto la modificacion
                userDao.update(mp.map(auxUserDto, User.class));
                //modifico para que la contraseña sea vacia y no enviarla a la vista
                 auxUserDto.setPassword("");
                //se retorna un usuario
                return auxUserDto;

            } else {
                //se le suma un intento
                auxUserDto.setAttempt(auxUserDto.getAttempt()+1);


                // persisto la modificacion
                userDao.update(mp.map(auxUserDto, User.class));
            }
        } else{
            //se setea para bloquear el usurio
            auxUserDto.setBlocked(true);
            //se persiste el usuario ya bloqueado
            userDao.update(mp.map(auxUserDto, User.class));
            //se retorna el usuario para indicar que está bloqueado
            return auxUserDto;
        }

    } else {
        throw new DontExistException("no existe el usuario");
    }
    //returna un null en caso que el proceso no sea satisfactorio
    return null;
}

//redireccionar a la pagina de usuario o admi
    public String redireccionar(UserDto userDto) {
        //verifica si es null
        if(userDto != null){
        //verifico si el usuario es administrador
        if(userDto.isPropertyAdmin()){
            //redirecciono a la pag .xhtml del panel administrador
            return "panelAdmiResource.xhtml";
        }else {
            //retorno el panel del usuario
            return "panelUser.xhtml";
        }}else{
            //retorno la pagina del log
            return "log.xhtml";
        }

    }

// metodo para cifrar contrasena
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
