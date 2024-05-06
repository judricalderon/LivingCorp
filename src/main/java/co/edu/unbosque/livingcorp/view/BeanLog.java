package co.edu.unbosque.livingcorp.view;


import co.edu.unbosque.livingcorp.exception.ExceptionDontExist;
import co.edu.unbosque.livingcorp.exception.ExceptionPasswordNotEncrypted;
import co.edu.unbosque.livingcorp.exception.ExceptionRepetedObject;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.presistence.UserDao;
import co.edu.unbosque.livingcorp.service.ServiceLog;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

@Named(value = "beanLog")
@SessionScoped
public class BeanLog implements Serializable {
    private static final long serialVersionUID = 1L;


    @Inject
    private ServiceLog serviceLog;

    private UserDto userDto;



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
        } catch (ExceptionPasswordNotEncrypted e) {
            //imprimir mensaje de error
            System.out.println(e.getMessage());
            //redireccionar a pagina de error
            return "error.xhtml";
        } catch (ExceptionRepetedObject e) {
            System.out.println(e.getMessage());
            return "error.xhtml";
        } catch (ExceptionDontExist e) {
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
}
