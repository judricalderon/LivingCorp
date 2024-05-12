package co.edu.unbosque.livingcorp.view;


import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.PasswordNotEncryptedException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.service.LogService;
import jakarta.annotation.PostConstruct;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

@Named(value = "logBean")
@SessionScoped
public class LogBean implements Serializable {
    private static final long serialVersionUID = 1L;


    @Inject
    private LogService serviceLog;

    private UserDto userDto;
    private String mistake;



    @PostConstruct
    public void inti (){
        userDto = new UserDto();
    }
    //Admin:1234

    //atributo para loguear
    public String log() {


       //atributo auxiliar para saber si el proceso de logueo fue exitoso
        System.out.println(userDto.toString());
        try {
            //se ejecuta el metodo de logueo del servicio
            userDto = serviceLog.log(userDto);
            //si el log es exitoso se inicia session a true
            if(userDto !=null) {
                //si está bloqueado se envía a la pag de error
                if(userDto.getAttempt()>=3){
                    //se da el valor de bloqueado al error
                    mistake = "usuario Bloqueado por exceso de intentos";
                    //se envía a error
                    return "error.xhtml";
                }
                //se crea objeto session y se pone en activa
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                //se incluye el usuario para así utilizarlo en otros beans
                session.setAttribute("userLogIn", userDto);
                System.out.println(userDto.toString());
                //retorna a la pagina de admi o usuario
                return serviceLog.redireccionar(userDto);

            }
            //se reinicia el userDto para que no quede en null
            userDto = new UserDto();
            //retorna a la pagina de nuevo al la pagina del log
            return serviceLog.redireccionar(null);
        } catch (PasswordNotEncryptedException e) {
            //se da valor de que la clave no fue encriptada
            mistake = e.getMessage();
            //imprimir en consola mensaje de error
            System.out.println(e.getMessage());
            //redireccionar a pagina de error
            return "error.xhtml";
        } catch (RepetedObjectException e) {
            //valor a que el objeto que quiere persistir esta siendo repetido
            mistake = e.getMessage();
            System.out.println(e.getMessage());
            return "error.xhtml";
        } catch (DontExistException e) {
            //el objeto no existe
            mistake = e.getMessage();
            System.out.println(e.getMessage());
            return "error.xhtml";
        }
       }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public LogService getServiceLog() {
        return serviceLog;
    }

    public void setServiceLog(LogService serviceLog) {
        this.serviceLog = serviceLog;
    }

    public String getMistake() {
        return mistake;
    }

    public void setMistake(String mistake) {
        this.mistake = mistake;
    }
}
