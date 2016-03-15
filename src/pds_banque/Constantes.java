/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque;

/**
 *
 * @author florent
 */
public interface Constantes{

    /* date fixe */
    public static final String DATE = "2014-09-26"; // a remplacer par la date du jour

    /* url de la base de donnees interne */
    public static final String URL_INT = "jdbc:mysql://localhost/pds_banque"; // 

    /* utilisateur de la base de donnees interne */
    public static final String USER_INT = "root"; // a remplacer par l'utilisateur de la base de donnees interne

    /* mot de passe de l'utilisateur de la base de donnees interne */
    public static final String PASSWD_INT = ""; // a remplacer par le mot de pas de l'utilisateur de la base de donnees interne

    /* largeur de la fenetre */
    public static final int LF = 500;

    /* hauteur de la fenetre */
    public static final int HF = 500;

     /* valeur des options dans la classe Affichage_Message */
    public static final int ERREUR = 0;
    public static final int INFORMATION = 1;
    public static final int ATTENTION = 2;
    public static final int QUESTION = 3;
    public static final int NORMAL = -1;
    public static final int OUI_NON_OPTION = 0;

}
