
import extractors.ExtractorExcel;
import mediators.Mediator;
import requests.Req1;
import requests.Req2;
import requests.Req3;
import requests.TypeCour;
import extractors.ExtractorXml;
import java.sql.SQLException;
public class Principal {
    public static void main(String[] args) throws Exception {

        System.out.println("hello from MAIN");
        ExtractorXml example = new ExtractorXml();
        example.lire_XML("C:\\Users\\Hania\\OneDrive\\Beu\\TD-mediateur\\Integration-de-donn-es\\modele\\Univ_BD_3.xml");


        /*Req1 req1 = new Req1("",2);
        Req2 req2 = new Req2("etudiant","Provenance", "France");
        Req3 req3 = new Req3(TypeCour.CM);
        Mediator m = new Mediator(req1,req2,req3);

        ExtractorExcel ex = new ExtractorExcel(req2);

        int nubreEtud=ex.exec_request2();

        System.out.println("nombre d'etudiant"+ nubreEtud)*/;

    }
}
