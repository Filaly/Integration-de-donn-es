package mediators;

import extractors.ExtractorExcel;
import extractors.ExtractorXml;
import org.xml.sax.SAXException;
import extractors.ExtractorPostgre;
import requests.Req1;
import requests.Req2;
import requests.Req3;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Mediator {

    private Map<String, Integer> res1Xml;

    private int res2Excel;
    private int res2XML;
    private int res2Relat;
    private ExtractorExcel extractorExcel;
    private ExtractorPostgre extractorPostgre;
    private Map<String, String> res3Rel;

    private ArrayList<Map<String, String>> res3Excel;
    private Map<String,String> res3Xml;
    private Map<String,String> res3Relat;

    private ExtractorXml extractorXml;


    public Mediator() {
        this.extractorExcel = new ExtractorExcel();
        this.extractorXml = new ExtractorXml();
        this.extractorPostgre = new ExtractorPostgre();
    }

    // Méthodes pour envoyer les requetes à l'extractor
    public void sendReq1(Req1 req1) {
        extractorXml.getRequest1FromMediator(req1);
    }

    public void sendReq2(Req2 req2) {
        extractorXml.getRequest2FromMediator(req2);
        extractorExcel.getRequest2FromMediator(req2);
        extractorPostgre.getRequest2FromMediator(req2);
    }

    public void sendReq3(Req3 req3) {
        extractorExcel.getRequest3FromMediator(req3);
        extractorPostgre.getRequest3FromMediator(req3);
    }

    // Méthodes pour récupérer les résultats depuis l'extracteurr

    public void getRes1() throws SQLException, ParserConfigurationException, SAXException, IOException {
        this.res1Xml = extractorXml.sendResult1ToMediator();
    }

    public void getRes2() throws SQLException, ParserConfigurationException, SAXException, IOException {
        this.res2Excel = extractorExcel.sendResult2ToMediator();
        this.res2Relat = extractorPostgre.sendResult2ToMediator();
        this.res2XML = extractorXml.sendResult2ToMediator();
    }

    private void getRes3() throws SQLException {
        this.res3Excel = extractorExcel.sendResult3ToMediator();
        this.res3Rel = extractorPostgre.sendResult3ToMediator();
    }

    //Méthodes pour agréger les résultats
    public Map<String, Integer> agregaRes1() throws SQLException, IOException, SAXException, ParserConfigurationException {
        getRes1();
        return res1Xml ;
    }

    public int agregaRes2() throws SQLException, IOException, SAXException, ParserConfigurationException {
        getRes2();
        return res2Excel+res2XML ; //+ res2XML + res2 Excel
    }

    public ArrayList<Map<String, String>> agregaRes3() throws SQLException {
        getRes3();
        return res3Excel ; //+ res2XML + res2 Excel
    }

    public Map<String, String> agregaRes3PG() throws SQLException {
        getRes3();
        return res3Rel ; // resultat de req3 sous forme de MAP
    }


    public ExtractorExcel getExtractorExcel() {
        return extractorExcel;
    }

    public ExtractorXml getExtractorXml() {

        return extractorXml;
    }


    public ExtractorPostgre getExtractorPostgre() {
        return extractorPostgre;
    }
}
