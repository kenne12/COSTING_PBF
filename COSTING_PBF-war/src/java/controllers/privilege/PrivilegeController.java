/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.privilege;

import controllers.util.JsfUtil;
import entities.Compte;
import entities.MenuB;
import entities.PrivilegeB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Gervais
 */
@ManagedBean
@ViewScoped
public class PrivilegeController extends AbstractPrivilege implements Serializable {

    @PostConstruct
    private void initAcces() {
        compte = new Compte();
    }

    public void prepareCreate() {
        compte = new Compte();
        dualMenu.getSource().clear();
        dualMenu.getTarget().clear();
        RequestContext.getCurrentInstance().execute("PF('AccessCreateDialog').show()");
    }

    public void viewAccess(Compte compte) {
        this.privileges.clear();
        try {
            this.compte = compte;
            privileges = privilegeFacadeLocal.findByUser(compte.getIdCompte());
            RequestContext.getCurrentInstance().execute("PF('AccessDetailDialog').show()");
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Echec");
        }
    }

    public void handleUserChange() {
        dualMenu.getSource().clear();
        dualMenu.getTarget().clear();
        try {
            if (compte.getIdCompte() != null) {

                List<PrivilegeB> privilegeTemp = privilegeFacadeLocal.findByUser(compte.getIdCompte());
                if (privilegeTemp.isEmpty()) {
                    dualMenu.setSource(menuFacadeLocal.findAll());
                } else {

                    List<MenuB> menusTarget = new ArrayList<>();

                    for (PrivilegeB p : privilegeTemp) {
                        menusTarget.add(p.getIdmenuB());
                    }

                    dualMenu.getTarget().addAll(menusTarget);
                    dualMenu.getSource().addAll(menuFacadeLocal.findAll());
                    dualMenu.getSource().removeAll(menusTarget);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (compte.getIdCompte() != null) {
                compte = compteFacadeLocal.find(compte.getIdCompte());

                for (MenuB m : dualMenu.getSource()) {
                    PrivilegeB p = privilegeFacadeLocal.findByUser(compte.getIdCompte(), m.getIdmenuB());
                    if (p != null) {
                        privilegeFacadeLocal.remove(p);
                    }
                }

                Boolean flag = false;
                for (MenuB m : dualMenu.getTarget()) {
                    if (m.getIdmenuB() == 1) {
                        flag = true;
                        PrivilegeB p = privilegeFacadeLocal.findByUser(compte.getIdCompte(), 1);
                        if (p == null) {
                            p = new PrivilegeB();
                            p.setIdprivilegeB(privilegeFacadeLocal.nextVal());
                            p.setIdmenuB(m);
                            p.setIdCompte(compte);
                            privilegeFacadeLocal.create(p);
                            break;
                        }
                        break;
                    }
                }

                if (flag == false) {
                    for (MenuB m : dualMenu.getTarget()) {
                        PrivilegeB p = privilegeFacadeLocal.findByUser(compte.getIdCompte(), m.getIdmenuB());
                        if (p == null) {
                            p = new PrivilegeB();
                            p.setIdprivilegeB(privilegeFacadeLocal.nextVal());
                            p.setIdmenuB(m);
                            p.setIdCompte(compte);
                            privilegeFacadeLocal.create(p);
                        }
                    }
                }
                RequestContext.getCurrentInstance().execute("PF('AccessCreateDialog').hide()");
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucun utilisateur selectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Echec");
        }
    }

}
