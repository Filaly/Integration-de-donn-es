package mediators;

import extractors.ExtractorExcel;
import extractors.ExtractorXml;
import org.xml.sax.SAXException;
import requests.Req1;
import requests.Req2;
import requests.Req3;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Mediator {

    private HashMap<String, Integer> res1Xml;
    private int res2Excel;
    private int res2Xml;
    private int res2Relat;

    public ExtractorExcel getExtractorExcel() {

        return extractorExcel;
    }
    public ExtractorXml getExtractorXml() {

        return extractorXml;
    }

    private ExtractorExcel extractorExcel;
    private ExtractorXml extractorXml;


    public Mediator() {

        this.extractorExcel = new ExtractorExcel();
        this.extractorXml = new ExtractorXml();
    }

    // Méthodes pour envoyer les requetes à l'extractor


    public void sendReq1(Req1 req1) {
        extractorXml.getRequest1FromMediator(req1);
    }

    public void sendReq2(Req2 req2) {
        extractorXml.getRequest2FromMediator(req2);
        extractorExcel.getRequest2FromMediator(req2);
    }



    public void sendReq2() {

    }

    public void sendReq3() {

    }

    // Méthodes pour récupérer les résultats depuis l'extracteurr

    /*public void getRes1() throws SQLException, ParserConfigurationException, SAXException, IOException {
        this.res2XML = extractorXml.sendResult1ToMediator();
    }*/
    public void getRes1() throws SQLException, ParserConfigurationException, SAXException, IOException {
        this.res1Xml = extractorXml.sendResult1ToMediator();
    }

    public void getRes2() throws SQLException, ParserConfigurationException, SAXException, IOException {
        this.res2Excel = extractorExcel.sendResult2ToMediator();
        this.res2Xml = extractorXml.sendResult2ToMediator();
    }

    public void getRes3() {
    }

    //Méthodes pour agréger les résultats
    public HashMap<String, Integer> agregaRes1() throws SQLException, IOException, SAXException, ParserConfigurationException {
        getRes1();
        return res1Xml ;
    }

    public int agregaRes2() throws SQLException, IOException, SAXException, ParserConfigurationException {
        getRes2();
        System.out.println("result of excel resourse is : " + res2Excel);
        System.out.println("result of Xml resourse is : " + res2Xml);
        return res2Excel+res2Xml ; //+ res2XML + res2 Excel
    }

    public void show() {
    }

}
