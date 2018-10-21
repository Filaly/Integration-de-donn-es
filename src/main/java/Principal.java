import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mediators.Mediator;
import requests.Req1;
import requests.Req2;
import requests.Req3;
import requests.TypeCour;

public class Principal {
    public static void main(String[] args) throws Exception {
    	

        //définition des requtes
        Req1 req1 = new Req1("",2);
        Req2 req2 = new Req2("etudiant","Provenance", "France");
        Req3 req3 = new Req3("Cours","Type");

        // instantiation du médiateur
        Mediator mediator = new Mediator();

       // mediator.getExtractorExcel().connection();
        //envoi des requete
        mediator.sendReq2(req2);
        mediator.sendReq3(req3);


        //recupération des résultats agrégés
        int numEtudiants= mediator.agregaRes2();
        Map<String, String> resultatReq3Postgres= new HashMap<String, String>();;
        
        resultatReq3Postgres=mediator.agregaRes3();
        

        //affichage du résultat
       // System.out.println("Réponse la requete 2 : nombre d'etudiant "+ numEtudiants);
        System.out.println("Requete 3");
        java.util.Iterator iter = (Iterator) resultatReq3Postgres.keySet().iterator();

        while(iter.hasNext()) {

            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + " --- " + entry.getValue());

        }
       // mediator.getExtractorExcel().disconnection();

    }
}
