/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.privilege;

import entities.Compte;
import entities.MenuB;
import entities.PrivilegeB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import sessions.CompteFacadeLocal;
import sessions.MenuBFacadeLocal;
import sessions.PrivilegeBFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractPrivilege {

    @EJB
    protected CompteFacadeLocal compteFacadeLocal;
    protected Compte compte = new Compte();
    protected List<Compte> comptes = new ArrayList<>();

    @EJB
    protected PrivilegeBFacadeLocal privilegeFacadeLocal;
    protected List<PrivilegeB> privileges = new ArrayList<>();

    @EJB
    protected MenuBFacadeLocal menuFacadeLocal;
    protected List<MenuB> menus = new ArrayList<>();
    protected DualListModel<MenuB> dualMenu = new DualListModel<>();

    protected String fileName;
    
    protected Routine routine = new Routine();

    protected boolean detail = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public List<Compte> getComptes() {
        comptes = compteFacadeLocal.findByEtat("Actif");
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public boolean isDetail() {
        return detail;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public boolean isImprimer() {
        return imprimer;
    }

    public List<MenuB> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuB> menus) {
        this.menus = menus;
    }

    public DualListModel<MenuB> getDualMenu() {
        return dualMenu;
    }

    public void setDualMenu(DualListModel<MenuB> dualMenu) {
        this.dualMenu = dualMenu;
    }

    public List<PrivilegeB> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<PrivilegeB> privileges) {
        this.privileges = privileges;
    }

    public Routine getRoutine() {
        return routine;
    }
    
    

}
