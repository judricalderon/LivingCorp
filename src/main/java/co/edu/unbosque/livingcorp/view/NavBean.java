package co.edu.unbosque.livingcorp.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

@Named(value = "navBean")
@RequestScoped
public class NavBean implements Serializable {
    private static final long serialVersionUID = 1L;
    public String inicio(){
        return "index.xhtml";
    }
   /* public String signOut(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return "index.xhtml";
    }
    public String createProperty(){return "panelAdmiProperty.xhtml";}
    public String createResource (){return "panelAdmiResource.xhtml";}
    public String manegeResource(){return "panelAdmiPropertyResource.xhtml";}*/
}
