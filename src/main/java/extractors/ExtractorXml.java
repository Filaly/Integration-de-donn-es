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
import java.util.Hashtable;
import java.util.Map;

public class ExtractorXml {


    private static Map<String, String> table; //<GAV,LAV>


    public ExtractorXml()
    {
        table = new Hashtable<String, String>();
    }

    //Use Hash map to save nbHeures for each teacher
    Map<String , Integer> hmap = new HashMap<String ,Integer>();

    private Document document = null;
    private DocumentBuilderFactory factory = null;
    private DocumentBuilder builder = null;

    private Req1 req1;
    private Req2 req2;

    public void getRequest1FromMediator(Req1 req1) {
        this.req1 = req1;
    }

    public void getRequest2FromMediator(Req2 req2){
        this.req2 = req2;
    }
    public Map<String, Integer> sendResult1ToMediator() throws SQLException, IOException, SAXException, ParserConfigurationException {
        return exec_xml_request1();
    }

    public int sendResult2ToMediator() throws SQLException, IOException, SAXException, ParserConfigurationException {
        return exec_xml_request2();
    }


    private Map<String, Integer> exec_xml_request1() throws SQLException, ParserConfigurationException, SAXException, IOException {

        table.put(req1.getEnseignant(),"Enseignant");
        table.put(req1.getNbHeures(),"Nb_heures");

        NodeList enseignants = document.getElementsByTagName(table.get(req1.getEnseignant()));
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


            //chercher dans chaque enseignant son NB_heures
            NodeList enseignements = E1.getElementsByTagName("Enseigne");

            for (int j = 0; j < enseignements.getLength(); j++) {

                E2 = (Element) enseignements.item(j);
                Nb_heures = E2.getElementsByTagName(table.get(req1.getNbHeures())); // Nb_heures
                E_2 = (Element) Nb_heures.item(0);
                sum = sum + Integer.parseInt(E_2.getTextContent());

            }
            hmap.put(E_1.getTextContent(),sum);

        }
        return hmap;
    }

    private int exec_xml_request2() throws SQLException, ParserConfigurationException, SAXException, IOException {

        table.put(req2.getProvenance(),"Provenance");
        table.put(req2.getEtudiant(),"Etudiant");

        Element E3, E_3;
        int sum2 = 0;
        NodeList L;
        //liste des etudiants
        NodeList etudiants = document.getElementsByTagName(table.get(req2.getEtudiant()));

        for (int index = 0; index < etudiants.getLength(); index++) {
            // un etudiant
            E3 = (Element) etudiants.item(index);
            L = E3.getElementsByTagName(table.get(req2.getProvenance()));
            E_3 = (Element) L.item(0); // un seul noeud NumEt
            String pays = E_3.getTextContent();

            if (pays.equals("France")) {
                sum2 += 1;
            }
        }
        return sum2;

    }


    // read XML File :
    public void lire_XML(String path_fichier) throws SAXException,
            IOException, ParserConfigurationException {

        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(path_fichier);
    }

}



