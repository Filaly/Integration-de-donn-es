import mediators.Mediator;
import requests.Req1;
import requests.Req2;
import requests.Req3;
import requests.TypeCour;

import java.util.HashMap;

public class Principal {
    public static void main(String[] args) throws Exception {

        //définition des requtes
        Req1 req1 = new Req1("enseignant", "Heures");
        Req2 req2 = new Req2("etudiant","Provenance", "France");
        Req3 req3 = new Req3(TypeCour.CM);

        // instantiation du médiateur
        Mediator mediator = new Mediator();

        mediator.getExtractorExcel().connection();
        //envoi des requete
        mediator.sendReq1(req1);
        mediator.sendReq2(req2);


        //recupération des résultats agrégés
        HashMap<String, Integer> numHeures = mediator.agregaRes1();
        int numEtudiants= mediator.agregaRes2();

        //affichage du résultat
        System.out.println("Réponse la requete 1 : nombre d'heures "+ numHeures);
        System.out.println("Réponse la requete 2 : nombre d'etudiant "+ numEtudiants);

        mediator.getExtractorExcel().disconnection();

    }
}
