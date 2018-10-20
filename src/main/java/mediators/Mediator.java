package mediators;

import extractors.ExtractorExcel;
import requests.Req2;
import requests.Req3;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Mediator {

    private int res2Excel;
    private int res2XML;
    private int res2Relat;

    private ArrayList<Map<String, String>> res3Excel;
    private Map<String,String> res3Xml;
    private Map<String,String> res3Relat;



    private ExtractorExcel extractorExcel;

    public Mediator() {
        this.extractorExcel = new ExtractorExcel();
    }

    // Méthodes pour envoyer les requetes à l'extractor
    public void sendReq1() {

    }

    public void sendReq2(Req2 req2) {
        extractorExcel.getRequest2FromMediator(req2);
    }

    public void sendReq3(Req3 req3) {
        extractorExcel.getRequest3FromMediator(req3);
    }

    // Méthodes pour récupérer les résultats depuis l'extracteurr

    public void getRes1() {
    }

    private void getRes2() throws SQLException {
        this.res2Excel = extractorExcel.sendResult2ToMediator();
        //this.res2XML = extractorXML.send...
    }

    private void getRes3() throws SQLException {
        this.res3Excel = extractorExcel.sendResult3ToMediator();
    }

    //Méthodes pour agréger les résultats

    public int agregaRes2() throws SQLException {
        getRes2();
        return res2Excel ; //+ res2XML + res2 Excel
    }

    public ArrayList<Map<String, String>> agregaRes3() throws SQLException {
        getRes3();
        return res3Excel ; //+ res2XML + res2 Excel
    }
    
    public ExtractorExcel getExtractorExcel() {
        return extractorExcel;
    }

}
