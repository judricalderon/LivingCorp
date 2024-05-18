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



        try {

            userDto = serviceLog.log(userDto);

            if(userDto !=null) {
                if(userDto.getAttempt()>=3){
                    mistake = "usuario Bloqueado por exceso de intentos";
                    return "error.xhtml";
                }
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

                session.setAttribute("userLogIn", userDto);

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
       public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";
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
