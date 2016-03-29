/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Server;

import java.io.IOException;
import static pds_banque.Server.encodageJsonServer.encodage;

/**
 *
 * @author Florian
 */
public class TestInsertion {
 
    public static void main(String[] args) throws IOException {

        float salaire = (float) 4500;

        encodage(4, "", "Mickael", "Json", salaire, "18 Rue de Paris", "94225", "Charenton le Pont", "0125476936", "mickael.jackson@gmail.com",
                "1958-08-29", 1, 2, 0, 1);

    }
    
    
}
