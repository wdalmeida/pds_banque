package edu.god.server;

import static edu.god.serialisation.JsonDecoding.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Arrays;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Florian
 */
public class MultiServer {

    public static void launchServer(int port) throws IOException, FileNotFoundException, ParseException, SQLException, ClassNotFoundException {
        ServerSocket hostingSocket = new ServerSocket(port);
        String dataFromClient;

        while (true) {
            Socket connectionSocket = hostingSocket.accept();
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outputToClient = new DataOutputStream(connectionSocket.getOutputStream());
            dataFromClient = inputFromClient.readLine();
            System.out.println(dataFromClient);

            String userPrefix = dataFromClient.split("\\####")[0]; // prefixe, inidiquant le type de requete

            String numberPrefix = dataFromClient.split("####")[1]; // prefixe, inidiquant le type de requete

            String encodedRequest = dataFromClient.split("\\####")[2]; //la requete en question


            /*switch (requestType) {
             case "i":
             //System.out.println("Le serveur a recu une requete d'update" + '\n');
             outputToClient.writeBytes("Le serveur a recu une requete d'insert" + '\n');

             break;
             case "u":
             System.out.println("Le serveur a recu une requete d'update" + '\n');
             outputToClient.writeBytes("Le serveur a recu une requete d'update" + '\n');
             break;
             case "s":
             System.out.println("Le serveur a reçu une requete de select");
             outputToClient.writeBytes("Le serveur a recu une requete de select" + '\n');
             break;

             case "d":
             System.out.println("Le serveur a reçu une requete de delete");
             outputToClient.writeBytes("Le serveur a recu une requete de delete" + '\n');
             break;

             default:
             System.out.println("Le serveur a reçu une requete non valide");
             outputToClient.writeBytes("Le serveur a recu une requete non valide" + '\n');
             break;
             }
             */
            if (userPrefix.startsWith("P")) {
                //Florent's  requests
                switch (numberPrefix){
                    case "1":                        
                        String[] resQuery1 = AccessDB_server.getTypeLoanCustomer(decodingSimCustType(encodedRequest));
                        System.out.println(Arrays.toString(resQuery1));
                        outputToClient.writeBytes(Arrays.toString(resQuery1) + '\n');
                        break;
                    case "2":
                        JSONObject resQuery2 = AccessDB_server.getSimulationsLoanOfCustomer(decodingSimsLoan(encodedRequest));
                        System.out.println(resQuery2.toString());
                        outputToClient.writeBytes(resQuery2.toString() + '\n');
                        break;
                    case "3" :
                        String resQuery3 = AccessDB_server.getSalaryOfCustomer(decodingSalaryCustomer(encodedRequest));
                        System.out.println(resQuery3);
                        outputToClient.writeBytes(resQuery3 + '\n');
                        break;
                }
            } 
            else if (userPrefix.startsWith("D")) {
                //Warren's requestsc
                switch (numberPrefix) {
                    case "1":
                        JSONObject resQuery1 = AccessDB_server.getCustomer(decodingSearchCustomer(encodedRequest));
                        System.out.println(resQuery1.toString());
                        outputToClient.writeBytes(resQuery1.toString() + '\n');
                        break;
                    case "2":
                        int resQuery2 = AccessDB_server.getUserConnexion(decodeLoginConsultant(encodedRequest));
                        System.out.println(String.valueOf(resQuery2));
                        outputToClient.writeBytes(String.valueOf(resQuery2) + '\n');
                        break;
                    case "3":
                        JSONObject resQuery3 = AccessDB_server.getDateTypeSims(decodingInfoSimCust(encodedRequest));
                        System.out.println(String.valueOf(resQuery3));
                        outputToClient.writeBytes(String.valueOf(resQuery3) + '\n');
                        break;
                    case "4":
                        String resQuery4 = AccessDB_server.getIDCustomer(decodingCustomerId(encodedRequest));
                        System.out.println(resQuery4);
                        outputToClient.writeBytes(resQuery4 + '\n');
                        break;
                    case "5":
                        String[] resQuery5 = AccessDB_server.getLastFirstNameCustomer(decodingLastFirstCustomer(encodedRequest));
                        System.out.println(Arrays.toString(resQuery5));
                        outputToClient.writeBytes(Arrays.toString(resQuery5) + '\n');
                        break;
                    case "6":
                        String[] resQuery6 = AccessDB_server.getLoanType();
                        System.out.println(Arrays.toString(resQuery6));
                        outputToClient.writeBytes(Arrays.toString(resQuery6) + '\n');
                        break;
                    case "7":
                        String[] resQuery7 = AccessDB_server.getSimByID(decodingSimId(encodedRequest));
                        System.out.println(Arrays.toString(resQuery7));
                        outputToClient.writeBytes(Arrays.toString(resQuery7) + '\n');
                        break;
                    case "8":
                        int resQuery8 = AccessDB_server.updateLoanSim(decodingUpLoanSim(encodedRequest));
                        System.out.println(String.valueOf(resQuery8));
                        outputToClient.writeBytes(String.valueOf(resQuery8) + '\n');
                        break;
                    case "9":
                        int resQuery9 = AccessDB_server.insertLoanSim(decodingInLoanSim(encodedRequest));
                        System.out.println(String.valueOf(resQuery9));
                        outputToClient.writeBytes(String.valueOf(resQuery9) + '\n');
                        break;
                    case "10":
                        String resQuery10 = AccessDB_server.getIdLoanType(decodingIdLoanType(encodedRequest));
                        System.out.println(String.valueOf(resQuery10));
                        outputToClient.writeBytes(String.valueOf(resQuery10) + '\n');
                        break;
                    case "11":
                        String resQuery11 = AccessDB_server.getIdCustInSim(decodingIdCustInSim(encodedRequest));
                        System.out.println(resQuery11);
                        outputToClient.writeBytes(resQuery11 + '\n');
                        break;
                    case "12":
                        int[] resQuery12 = AccessDB_server.getParambyID(decodingParamById(encodedRequest));
                        System.out.println(String.valueOf(Arrays.toString(resQuery12)));
                        outputToClient.writeBytes(String.valueOf(Arrays.toString(resQuery12)) + '\n');
                        break;
                    case "13":
                        float[] resQuery13 = AccessDB_server.getRatePc(decodingRatePc(encodedRequest));
                        System.out.println(String.valueOf(Arrays.toString(resQuery13)));
                        outputToClient.writeBytes(String.valueOf(Arrays.toString(resQuery13)) + '\n');
                        break;
                    case "14":
                        float[] resQuery14 = AccessDB_server.getRateAg(decodingRateAg(encodedRequest));
                        System.out.println(String.valueOf(Arrays.toString(resQuery14)));
                        outputToClient.writeBytes(String.valueOf(Arrays.toString(resQuery14)) + '\n');
                        break;
                    case "15":
                        String resQuery15 = AccessDB_server.getIdAgency(decodingIdAgency(encodedRequest));
                        System.out.println(resQuery15);
                        outputToClient.writeBytes(resQuery15 + '\n');
                        break;
                    case "16":
                        String resQuery16 = AccessDB_server.getIdPc(decodingIdPc(encodedRequest));
                        System.out.println(resQuery16);
                        outputToClient.writeBytes(resQuery16 + '\n');
                        break;
                    default:
                        break;
                }
            } //////////////////////////////////////////
            else if (userPrefix.startsWith("M")) {
                //Coline's requests
            } //////////////////////////////////////////
            else if (userPrefix.startsWith("L")) {
                //Florian's requests
                //the request is a Json Object, it needs to be decoded
                if (numberPrefix.startsWith("1")) {
                    AccessDB_server.sendUpdateRequest(decodeCustomer(encodedRequest));
                }
            } //////////////////////////////////////////
            else if (userPrefix.startsWith("S")) {
                //Nabil's requests

            }

            Object obj = dataFromClient;
            //decodageCustomer(obj);
            // String result = AccessDB_server.sendQueryRequest(decodeLoginConsultant(dataFromClient));

            //   outputToClient.writeBytes(result + '\n');
        }

    }

    public static void main(String argv[]) throws Exception {

        launchServer(3000);

    }

    private static String decodeInfoSim(String encodedRequest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
