package mediators;

import extractors.ExtractorExcel;
import requests.Req1;
import requests.Req2;
import requests.Req3;

import java.sql.SQLException;

public class Mediator implements IMediateur {

    private int res2Excel;
    private int res2XML;
    private int res2Relat;

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

    public void sendReq3() {

    }

    // Méthodes pour récupérer les résultats depuis l'extracteurr

    public void getRes1() {
    }

    public void getRes2() throws SQLException {
        this.res2Excel = extractorExcel.sendResult2ToMediator();
        //this.res2XML = extractorXML.send...
    }

    public void getRes3() {
    }

    //Méthodes pour agréger les résultats

    public int agregaRes2() throws SQLException {
        getRes2();
        return res2Excel ; //+ res2XML + res2 Excel
    }

    public void show() {
    }

}
