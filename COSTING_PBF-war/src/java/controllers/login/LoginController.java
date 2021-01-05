/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.login;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Annee;
import entities.Budget;
import entities.Compte;
import entities.Institution;
import entities.Utilisateur;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sessions.AnneeFacadeLocal;
import sessions.BudgetFacadeLocal;
import sessions.CompteFacadeLocal;
import sessions.InstitutionFacadeLocal;
import sessions.UtilisateurFacadeLocal;
import utilitaire.ShaHash;
import entities.MenuB;
import entities.PrivilegeB;
import org.primefaces.context.RequestContext;
import sessions.MenuBFacadeLocal;
import sessions.PrivilegeBFacadeLocal;
import utils.Routine;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private MenuBFacadeLocal menuBFacadeLocal;

    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee = new Annee();
    private List<Annee> annees = new ArrayList<>();

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    private Utilisateur utilisateur = new Utilisateur();

    @EJB
    private PrivilegeBFacadeLocal privilegeFacadeLocal;

    @EJB
    private InstitutionFacadeLocal institutionFacadeLocal;
    private Institution institution = new Institution();
    private List<Institution> institutions = new ArrayList<>();

    @EJB
    private CompteFacadeLocal compteFacadeLocal;
    private Compte compte = new Compte();

    private String confirmPassword = "";

    private String filename = "logo.jpeg";
    private String filenameInstitution = "logo1.png";

    private boolean showSessionPanel = true;
    private Routine routine = new Routine();

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    @EJB
    private BudgetFacadeLocal budgetFacadeLocal;

    //@EJB
    //private ParametrageFacadeLocal parametrageFacadeLocal;
    private String language = "fr";

    public LoginController() {

    }

    public void login() {
        try {
            Compte cpte = new Compte();
            cpte.setLogin(compte.getLogin());
            cpte.setMdp(new ShaHash().hash(compte.getMdp()));

            Compte usr = compteFacadeLocal.connexion(cpte);

            if (usr != null) {

                compte = usr;

                utilisateur = usr.getIdUtilisateur();
                HttpSession session = SessionMBean.getSession();

                session.setAttribute("login", utilisateur.getNom());
                session.setAttribute("user", usr);

                List<Annee> annees = anneeFacadeLocal.findByEtat(true);
                if (!annees.isEmpty()) {
                    annee = annees.get(0);
                }

                List<Institution> list = this.getInstitutions();
                if (!list.isEmpty()) {
                    institution = list.get(0);
                }

                List<MenuB> menu_mapped = menuBFacadeLocal.findAll();
                List<String> menu_all_mapped = new ArrayList<>();

                for (MenuB m : menu_mapped) {
                    String[] menus = m.getRessource().split(";");
                    for (String temp : menus) {
                        if (!menu_all_mapped.contains(temp)) {
                            menu_all_mapped.add(temp);
                        }
                    }
                }

                List<PrivilegeB> privilegesTemp = privilegeFacadeLocal.findByUser(usr.getIdCompte());
                List<Long> accesses = new ArrayList<>();
                List<String> access = new ArrayList<>();

                for (PrivilegeB p : privilegesTemp) {
                    accesses.add(p.getIdmenuB().getIdmenuB().longValue());
                    String[] menus = p.getIdmenuB().getRessource().split(";");
                    for (String temp : menus) {
                        if (!access.contains(temp)) {
                            access.add(temp);
                        }
                    }
                }

                session.setAttribute("user_access_id", accesses);
                session.setAttribute("user_all_menu", access);
                session.setAttribute("system_all_menu", menu_all_mapped);

                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/index.html");

            } else {
                System.err.println("echec d'authentification");
                compte = new Compte();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login et mot de passe incorrets", "Please enter correct username and Password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            JsfUtil.addErrorMessage("Echec de l'opération");
            compte = new Compte();
        }
    }

    public void initSession() {
        try {
            HttpSession session = SessionMBean.getSession();

            institution = institutionFacadeLocal.find(this.institution.getIdinstitution());
            try {
                filename = institution.getPhoto();
                filenameInstitution = institution.getPhotoInstitutionMere();
            } catch (Exception e) {
            }
            session.setAttribute("institution", institution);

            annee = anneeFacadeLocal.find(annee.getIdannee());
            session.setAttribute("annee", annee);

            Budget b = null;
            if (annee != null) {
                b = budgetFacadeLocal.findByNom(annee.getNom());
            }
            session.setAttribute("budget", b);

            /*Parametrage parametrage = null;
             List<Parametrage> parametrages = parametrageFacadeLocal.findAll();
             if (!parametrages.isEmpty()) {
             parametrage = parametrages.get(0);
             }
             session.setAttribute("parametrage", parametrage);*/
            showSessionPanel = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCompte() {
        if ((!this.compte.getMdp().equals("")) || (!this.compte.getMdp().equals(null))) {
            if (this.compte.getMdp().equals(this.confirmPassword)) {
                this.compte.setMdp(new ShaHash().hash(this.confirmPassword));
                this.compteFacadeLocal.edit(this.compte);
                this.confirmPassword = "";
                RequestContext.getCurrentInstance().execute("PF('Modifier_compteDialog').hide()");
                this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
                RequestContext.getCurrentInstance().execute("PF('NotifyDialog').show()");
            } else {
                this.compte = this.compteFacadeLocal.find(this.compte.getIdCompte());
                this.confirmPassword = "";
                this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("mot_de_passe_non_identic"));
                RequestContext.getCurrentInstance().execute("PF('NotifyDialog').show()");
            }
        } else {
            this.compte = this.compteFacadeLocal.find(this.compte.getIdCompte());
            this.confirmPassword = "";
            this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("saisir_mot_de_passe"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog').show()");
        }
    }

    public void logout() {
        HttpSession session = SessionMBean.getSession();
        Compte usr = SessionMBean.getUser();
        session.invalidate();
        String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void watcher() {
        try {
            if (SessionMBean.getUser() == null) {
                String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String switchFr() {
        language = "fr";
        return null;
    }

    public String switchEn() {
        language = "en";
        return null;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        annees = anneeFacadeLocal.findAllRange();
        return annees;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public List<Institution> getInstitutions() {
        institutions = institutionFacadeLocal.findAllEtatIsActif();
        return institutions;
    }

    public boolean isShowSessionPanel() {
        return showSessionPanel;
    }

    public void setShowSessionPanel(boolean showSessionPanel) {
        this.showSessionPanel = showSessionPanel;
    }

    public String getFilename() {
        return filename;
    }

    public String getFilenameInstitution() {
        return filenameInstitution;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Routine getRoutine() {
        return routine;
    }

}
