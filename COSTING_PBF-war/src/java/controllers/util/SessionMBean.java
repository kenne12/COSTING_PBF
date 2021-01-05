/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.util;

import entities.Annee;
import entities.Budget;
import entities.Compte;
import entities.Institution;
//import entities.Parametrage;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionMBean {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("login").toString();
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("utilisateurid");
        } else {
            return null;
        }
    }

    public static Compte getUser() {
        HttpSession session = getSession();
        if (session != null) {
            return (Compte) session.getAttribute("user");
        } else {
            return null;
        }
    }

    public static Budget getBudget() {
        HttpSession session = getSession();
        if (session != null) {
            return (Budget) session.getAttribute("budget");
        } else {
            return null;
        }
    }

    public static Annee getAnnee() {
        HttpSession session = getSession();
        if (session != null) {
            return (Annee) session.getAttribute("annee");
        } else {
            return null;
        }
    }

    public static Institution getInstitution() {
        HttpSession session = getSession();
        if (session != null) {
            return (Institution) session.getAttribute("institution");
        } else {
            return null;
        }
    }

    public static Institution getInstitutionSession() {
        HttpSession session = getSession();
        if (session != null) {
            return (Institution) session.getAttribute("i_session");
        } else {
            return null;
        }
    }

    /*public static Parametrage getParametrage() {
        HttpSession session = getSession();
        if (session != null) {
            return (Parametrage) session.getAttribute("parametrage");
        } else {
            return null;
        }
    }*/

    public static List<Long> getUserAccessId() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<Long>) session.getAttribute("user_access_id");
        } else {
            return null;
        }
    }

    public static List<String> getAll_Link() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<String>) session.getAttribute("system_all_menu");
        } else {
            return null;
        }
    }

    public static Compte getCompte() {
        HttpSession session = getSession();
        if (session != null) {
            return (Compte) session.getAttribute("user");
        } else {
            return null;
        }
    }
}
