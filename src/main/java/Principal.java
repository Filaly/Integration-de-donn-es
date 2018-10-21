import mediators.Mediator;
import requests.Req1;
import requests.Req2;
import requests.Req3;
import requests.TypeCour;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;


public class Principal {
    public static void main(String[] args) throws Exception {

        TypeCour[] cours = {TypeCour.TP, TypeCour.TD, TypeCour.CM};

        //définition des requtes
        Req1 req1 = new Req1("Enseignant","Heures");
        Req2 req2 = new Req2("Etudiant","Provenance", "France");
        Req3 req3 = new Req3("Type", cours,"Cours","Type" );
        Req3 req3PG = new Req3("Type",cours,"Cours","Type");


        // instantiation du médiateur
        Mediator mediator = new Mediator();
        mediator.getExtractorExcel().connection();
        mediator.getExtractorXml().lire_XML("/Users/khatir/IdeaProjects/Integration_de_donnees/Integration-de-donn-es/src/main/resources/Univ_BD_3.xml");
        System.out.println("------------");

        //envoi des requete
        mediator.sendReq1(req1);
        mediator.sendReq2(req2);
        mediator.sendReq3(req3);
        mediator.sendReq3(req3PG);

        //recupération des résultats agrégés
        Map<String, Integer> numHeures = mediator.agregaRes1();
        Map<String, String> resultatReq3Postgres= new HashMap<String, String>();;

        resultatReq3Postgres=mediator.agregaRes3PG();

        //affichage du résultat
        System.out.println("------------");
        System.out.println("Réponse de la requete 1 : ");
        System.out.println("Nombre d'heures de chaque enseignant "+ numHeures);

        System.out.println("------------");
        System.out.println("Réponse de la requete 2 : ");
        System.out.println("Nombre d'étudiant provenant de France : "+mediator.agregaRes2());

        System.out.println("------------");
        System.out.println("Réponse de la requete 3 : ");
        System.out.println("Nombre de cours par Type pour l'année 2006 : "+mediator.agregaRes3().get(0));
        System.out.println("Nombre de cours par Type pour l'année 2007 : "+mediator.agregaRes3().get(1));

        for (Object o : resultatReq3Postgres.keySet()) {

            Map.Entry entry = (Map.Entry) o;
            System.out.println(entry.getKey() + " --- " + entry.getValue());

        }
        //deconnexion
        System.out.println("------------");
        mediator.getExtractorExcel().disconnection();
    }
}
