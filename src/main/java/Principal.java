import extractors.ExtractorExcel;
import mediators.Mediator;
import requests.Req1;
import requests.Req2;
import requests.Req3;
import requests.TypeCour;

import java.util.Hashtable;
import java.util.Map;


public class Principal {
    public static void main(String[] args) throws Exception {

        TypeCour[] cours = {TypeCour.TP, TypeCour.TD, TypeCour.CM};

        //définition des requtes
        Req1 req1 = new Req1("",2);
        Req2 req2 = new Req2("etudiant","Provenance", "France");
        Req3 req3 = new Req3("Type", cours );


        // instantiation du médiateur
        Mediator mediator = new Mediator();

        mediator.getExtractorExcel().connection();
        System.out.println("------------");
        //envoi des requete
        mediator.sendReq2(req2);
        mediator.sendReq3(req3);


        //recupération des résultats agrégés
        int numEtudiants= mediator.agregaRes2();

        //affichage du résultat
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
