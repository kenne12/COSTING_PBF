/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.GroupeUtilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface GroupeUtilisateurFacadeLocal {

    void create(GroupeUtilisateur groupeUtilisateur);

    void edit(GroupeUtilisateur groupeUtilisateur);

    void remove(GroupeUtilisateur groupeUtilisateur);

    GroupeUtilisateur find(Object id);

    List<GroupeUtilisateur> findAll();

    List<GroupeUtilisateur> findRange(int[] range);

    int count();
    
}
