package extractors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import requests.Req1;
import requests.Req2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ExtractorXml {

    private static Map<String, String> table1; //<GAV,LAV>
    private static Map<String, String> table2; //<GAV,LAV>

    //Use Hash map to save nbHeures for each teacher
    HashMap<String , Integer> hmap = new HashMap<String ,Integer>();

    Document document = null;
    DocumentBuilderFactory factory = null;
    DocumentBuilder builder = null;

    private Req1 req1;
    private Req2 req2;

    public void getRequest1FromMediator(Req1 req1) {
        this.req1 = req1;
    }

    public void getRequest2FromMediator(Req2 req2){
        this.req2 = req2;
    }
    public HashMap<String, Integer> sendResult1ToMediator() throws SQLException, IOException, SAXException, ParserConfigurationException {
        return xml_request1();
    }

    public int sendResult2ToMediator() throws SQLException, IOException, SAXException, ParserConfigurationException {
        return xml_request2();
    }


    private HashMap<String, Integer> xml_request1() throws SQLException, ParserConfigurationException, SAXException, IOException {
        TradRequest1();
        try {
            lire_XML("C:\\Users\\Hania\\OneDrive\\Beu\\projectMediateur\\Integration-de-donn-es\\src\\main\\resources\\Univ_BD_3.xml");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        NodeList enseignants = document.getElementsByTagName("Enseignant");
        System.out.println("Request 1 answer from XML is : ");
        Element E1, E2, E_1, E_2;
        NodeList enseig;
        NodeList Nb_heures;
        int sum = 0;
        for (int index = 0; index < enseignants.getLength(); index++) {
            sum = 0;

            // un Enseignant
            E1 = (Element) enseignants.item(index);
            enseig = E1.getElementsByTagName("Nom"); // Enseignant_name
            E_1 = (Element) enseig.item(0);
            System.out.println("Enseignant_name : " + E_1.getTextContent());

            //chercher dans chaque enseignant son NB_heures
            NodeList enseignements = E1.getElementsByTagName("Enseigne");
            // System.out.println("enseignements.getLength()" + enseignements.getLength());
            for (int j = 0; j < enseignements.getLength(); j++) {

                E2 = (Element) enseignements.item(j);
                Nb_heures = E2.getElementsByTagName("Nb_heures"); // Nb_heures
                E_2 = (Element) Nb_heures.item(0);
                sum = sum + Integer.parseInt(E_2.getTextContent());
                System.out.println("Numbre d'heures : " + E_2.getTextContent());

            }
            hmap.put(E_1.getTextContent(),sum);
            System.out.println("La SUM total is ---------> " + sum);
        }
        return hmap;
    }

    private int xml_request2() throws SQLException, ParserConfigurationException, SAXException, IOException {
        TradRequest2();
        try {
            lire_XML("C:\\Users\\Hania\\OneDrive\\Beu\\projectMediateur\\Integration-de-donn-es\\src\\main\\resources\\Univ_BD_3.xml");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Element E3, E_3;
        int sum2 = 0;
        NodeList L;
        //liste des etudiants
        NodeList etudiants = document.getElementsByTagName("Etudiant");
        System.out.println("the Provenance Xml file : ");
        for (int index = 0; index < etudiants.getLength(); index++) {
            // un etudiant
            E3 = (Element) etudiants.item(index);
            L = E3.getElementsByTagName("Provenance");
            E_3 = (Element) L.item(0); // un seul noeud NumEt
            String pays = E_3.getTextContent();
            System.out.println(E_3.getTextContent());
            // System.out.println("pays  "+pays);
            if (pays.equals("France")) {
                sum2 += 1;
            }
        }
        return sum2;

    }

    /*public int sendResult1ToMediator() throws SQLException, IOException, SAXException, ParserConfigurationException {
        return xml_request1();
    }
    public int sendResu2t1ToMediator() throws SQLException, IOException, SAXException, ParserConfigurationException {
        return xml_request2();
    }*/

    //Translate the mediator Request
    private void TradRequest1(){
        try {
            table1.put(req1.getEnseignant(),"enseignant");
            table1.put(req1.getNbHeures(),"Heures");
        }catch(NullPointerException e)
        {
            System.out.print("");
        }
    }
    private void TradRequest2(){
        try {
            table2.put(req2.getProvenance(),"Provenance");
            table2.put(req2.getEtudiant(),"etudiant");
        }catch(NullPointerException e)
        {
            System.out.print("");
        }
    }

    // read XML File :
    public void lire_XML(String path_fichier) throws SAXException,
            IOException, ParserConfigurationException {

        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(path_fichier);
    }

}



