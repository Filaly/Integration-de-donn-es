package mediators;

import extractors.ExtractorExcel;
import extractors.ExtractorPostgre;
import requests.Req1;
import requests.Req2;
import requests.Req3;

import java.sql.SQLException;
import java.util.Map;

public class Mediator implements IMediateur {

    private int res2Excel;
    private int res2XML;
    private int res2Relat;
    private ExtractorExcel extractorExcel;
    private ExtractorPostgre extractorPostgre;
    private Map<String, String> res3Rel;

    public ExtractorPostgre getExtractorPostgre() {
        return extractorPostgre;
    }
    public ExtractorExcel getExtractorExcel() {
        return extractorExcel;
    }

    

    public Mediator() {
        this.extractorExcel = new ExtractorExcel();
        this.extractorPostgre = new ExtractorPostgre();
    }

    // Méthodes pour envoyer les requetes à l'extractor
    public void sendReq1() {

    }

    public void sendReq2(Req2 req2) {
       // extractorExcel.getRequest2FromMediator(req2);
        extractorPostgre.getRequest2FromMediator(req2);
    }

    public void sendReq3(Req3 req3) {
    	extractorPostgre.getRequest3FromMediator(req3);

    }

    // Méthodes pour récupérer les résultats depuis l'extracteurr

    public void getRes1() {
    }

    public void getRes2() throws SQLException {
        //this.res2Excel = extractorExcel.sendResult2ToMediator();
        this.res2Relat = extractorPostgre.sendResult2ToMediator();
        //this.res2XML = extractorXML.send...
    }

    public void getRes3() {
    	try {
			this.res3Rel = extractorPostgre.sendResult3ToMediator();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //Méthodes pour agréger les résultats

    public int agregaRes2() throws SQLException {
        getRes2();
        return res2Excel+res2Relat ; //+ res2XML + res2 Excel + res2 Rel
    }
    public Map<String, String> agregaRes3() throws SQLException {
        getRes3();
        return res3Rel ; // resultat de req3 sous forme de MAP
    }

    public void show() {
    }
	@Override
	public void sendReq3() {
		// TODO Auto-generated method stub
		
	}

}
