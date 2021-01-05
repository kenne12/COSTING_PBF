/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;

//import controllers.SocieteController;
import controllers.util.SessionMBean;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.joda.time.DateTime;
//import sessions.OperationsFacadeLocal;

/**
 *
 * @author kenne gervais
 */
public class Utilitaires {

    private static final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    public static final String path = servletContext.getRealPath("");
    public static final String repertoire_document = "/photos/";
    public static final String userAvatar = "avatar1.png";

    public static String formaterStringMoney(Long valeur) {
        String chaine = Long.toString(valeur);
        if (chaine == null) {
            return "0";
        }
        int taille = chaine.length(), j = taille;
        String result = "";
        int i = 0;
        while (i < taille) {
            result += chaine.charAt(i);
            i++;
            j--;
            if (j > 0 && j % 3 == 0) {
                result += ' ';
            }
        }

        return result;
    }

    public static String formaterStringMoney(Integer valeur) {
        String chaine = Integer.toString(valeur);
        if (chaine == null) {
            return "0";
        }
        int taille = chaine.length(), j = taille;
        String result = "";
        int i = 0;
        while (i < taille) {
            result += chaine.charAt(i);
            i++;
            j--;
            if (j > 0 && j % 3 == 0) {
                result += ' ';
            }
        }

        return result;
    }

    public static String formaterStringMoney(String valeur) {
        String chaine = valeur;
        if (chaine == null) {
            return null;
        }
        int taille = chaine.length(), j = taille;
        String result = "";
        int i = 0;
        while (i < taille) {
            result += chaine.charAt(i);
            i++;
            j--;
            if (j > 0 && j % 3 == 0) {
                result += ' ';
            }
        }
        return result;
    }

    public static String formaterStringMoney(Double val) {
        String result = "";
        try {
            String pEntiere = partieEntiere(val);
            String pDec = partieDecimale(val);
            String chaine = pEntiere;
            int taille = chaine.length(), j = taille;

            int i = 0;
            while (i < taille) {
                result += chaine.charAt(i);
                i++;
                j--;
                if (j > 0 && j % 3 == 0) {
                    result += ' ';
                }
            }
            if (pDec != null) {
                result = result + "." + pDec;
            }

        } catch (Exception e) {
            result = "0";
            e.printStackTrace();
        }
        return result;

    }

    private static String partieDecimale(Double nombre) {
        return partieDecimale(nombre.toString());
    }

    private static String partieDecimale(String nombre) {
        String result = "";
        int taille = nombre.length();
        boolean copie = false;
        for (int i = 0; i < taille; i++) {
            if (copie) {
                result += nombre.charAt(i);
            } else if (nombre.charAt(i) == '.') {
                copie = true;
            }
        }
        if (result.equals("0")) {
            return null;
        }
        return result;
    }

    private static String partieEntiere(Double nombre) {
        Integer tmp = nombre.intValue();
        return tmp.toString();
    }

    public static String formatPrenomMaj(String prenom) {
        if (prenom.isEmpty()) {
            return " ";
        }
        char prenLettre = '0';
        String leReste;
        String lettre1;

        prenLettre = prenom.charAt(0);//recuperation de la premiere lettre

        leReste = prenom.substring(1, prenom.length());//recuperation du reste du nom sauf la premiere lettre

        lettre1 = String.valueOf(prenLettre);

        leReste = leReste.toLowerCase();//le reste ne minuscules

        return lettre1.toUpperCase() + leReste;
    }

    public static double arrondiNDecimales(double x, int n) {
        double pow = Math.pow(10, n);
        return (Math.floor(x * pow)) / pow;
    }

    public static Date addDayToDate(Date date, int nbre) {
        DateTime date_time = new DateTime("" + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
        date_time.plusDays(nbre);
        return date_time.toDate();
    }

    /*public static Operations saveOperation(String action, Compte compte, OperationsFacadeLocal operationFacadeLocal) {
     Operations operation = new Operations();
     try {
     operation.setIdOperations(operationFacadeLocal.nextId());
     operation.setLibelle(action);
     operation.setDateOpration(new Date());
     operation.setHeureOpration(new Date());
     operation.setIdCompte(compte);
     operationFacadeLocal.create(operation);
     return operation;
     } catch (Exception e) {
     e.printStackTrace();
     } finally {
     return operation;
     }
     }*/
    public static String getExtension(String nomFichier) {
        int taille = nomFichier.length();
        String extension = "";
        for (int i = 0; i < taille; i++) {
            if (nomFichier.charAt(i) == '.') {
                extension = "";
            } else {
                extension = extension + nomFichier.charAt(i);
            }
        }
        return extension;
    }

    public static boolean estFichierImage(String nom) {
        String extension = getExtension(nom);
        if ((extension == null) || (extension.equals(""))) {
            return false;
        }
        String ext = extension.toUpperCase();
        if (ext.equals("PDF")) {
            return true;
        }
        if (ext.equals("JPG")) {
            return true;
        }
        if (ext.equals("JPEG")) {
            return true;
        }
        if (ext.equals("GIF")) {
            return true;
        }
        if (ext.equals("PNG")) {
            return true;
        }
        if (ext.equals("BMP")) {
            return true;
        }
        return false;
    }

    public static boolean isAccess(Long idmenu) {
        if (SessionMBean.getUserAccessId().isEmpty()) {
            return false;
        } else {
            if (SessionMBean.getUserAccessId().contains(1L)) {
                return true;
            } else {
                if (SessionMBean.getUserAccessId().contains(idmenu)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

}
