package co.edu.unbosque.livingcorp.view;


import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.PasswordNotEncryptedException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.service.LogService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.Serializable;

@Named(value = "logBean")
@SessionScoped
public class LogBean implements Serializable {
    private static final Logger logger = Logger.getLogger(LogBean.class);
    private static final long serialVersionUID = 1L;
    @Inject
    private LogService serviceLog;
    private UserDto userDto;

    @PostConstruct
    public void inti (){
        userDto = new UserDto();
    }
    //Admin:1234
    public String log() {
        try {
            userDto = serviceLog.log(userDto);
            if(userDto !=null) {
                if(userDto.getAttempt()>=3){
                    logger.info(userDto.toString());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Blocked user"));
                    return "log.xhtml";
                }
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("userLogIn", userDto);
                logger.info("Session in");
                return serviceLog.redireccionar(userDto);

            }
            userDto = new UserDto();
            logger.info("User not logged in");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Error Log"));
            return serviceLog.redireccionar(null);
        } catch (PasswordNotEncryptedException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
           logger.error(e.getMessage());
            return "log.xhtml";
        } catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            logger.error(e.getMessage());
            return "log.xhtml";
        } catch (DontExistException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            logger.error(e.getMessage());
            return "log.xhtml";
        }
       }
       public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        logger.info("Session is logged out");
        return "index.xhtml";
       }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
