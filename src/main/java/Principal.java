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
        Req3 req3 = new Req3(TypeCour.CM);

        // instantiation du médiateur
        Mediator mediator = new Mediator();

        //envoi des requete
        mediator.sendReq2(req2);


        //recupération des résultats agrégés
        int numEtudiants= mediator.agregaRes2();

        //affichage du résultat
        System.out.println("Réponse la requete 1 : nombre d'etudiant "+ numEtudiants);

    }
}
