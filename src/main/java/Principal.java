import extractors.ExtractorExcel;
import mediators.Mediator;
import requests.Req1;
import requests.Req2;
import requests.Req3;
import requests.TypeCour;

import java.util.Hashtable;
import java.util.Map;
import java.util.HashMap;


public class Principal {
    public static void main(String[] args) throws Exception {

        TypeCour[] cours = {TypeCour.TP, TypeCour.TD, TypeCour.CM};

        //définition des requtes
        Req1 req1 = new Req1("Enseignant","Heures");
        Req2 req2 = new Req2("Etudiant","Provenance", "France");
        Req3 req3 = new Req3("Type", cours );

        // instantiation du médiateur
        Mediator mediator = new Mediator();
        mediator.getExtractorExcel().connection();
        mediator.getExtractorXml().lire_XML("/Users/khatir/IdeaProjects/Integration_de_donnees/Integration-de-donn-es/src/main/resources/Univ_BD_3.xml");
        System.out.println("------------");

        //envoi des requete
        mediator.sendReq1(req1);
        mediator.sendReq2(req2);
        mediator.sendReq3(req3);

        //recupération des résultats agrégés
        Map<String, Integer> numHeures = mediator.agregaRes1();

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

        //deconnexion
        System.out.println("------------");
        mediator.getExtractorExcel().disconnection();
    }
}
