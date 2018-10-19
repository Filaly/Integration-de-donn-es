package extractors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ExtractorXml {
    String SQLrequete;

    public void recive_requete(String mediator_requete) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
        if (mediator_requete == "a") {
            lire_XML("modele/Univ_BD_3.xml");
        }

    }

    public void lire_XML(String path_fichier) throws FileNotFoundException, SAXException,
            IOException, ParserConfigurationException {
        Document document = null;
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;

        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(path_fichier);

        Element E1, E2, E_1, E_2;

        //liste des enseignant,
        NodeList enseignants = document.getElementsByTagName("Enseignant");


        System.out.println("enseignants.getLength()" + enseignants.getLength());

        NodeList enseig;
        NodeList Nb_heures;
        for (int index = 0; index < enseignants.getLength(); index++) {
            int sum = 0;

            // un Enseignant
            E1 = (Element) enseignants.item(index);
            enseig = E1.getElementsByTagName("Nom"); // Enseignant_name
            E_1 = (Element) enseig.item(0);
            System.out.println("Enseignant_name : " + E_1.getTextContent());

            //chercher dans chaque enseignant son NB_heures
            NodeList enseignements = E1.getElementsByTagName("Enseigne");
            System.out.println("enseignements.getLength()" + enseignements.getLength());
            for (int j = 0; j < enseignements.getLength(); j++) {

                E2 = (Element) enseignements.item(j);
                Nb_heures = E2.getElementsByTagName("Nb_heures"); // Nb_heures
                E_2 = (Element) Nb_heures.item(0);
                sum=sum + Integer.parseInt(E_2.getTextContent());
                System.out.println("Numbre d'heures : " + E_2.getTextContent());

            }
            System.out.println("La SUM total is ---------> " + sum);
        }

/* --------------------------------------- Request 2 ---------------------------------------------------*/
        Element E3, E_3;

        //liste des etudiants
        NodeList etudiants = document.getElementsByTagName("Etudiant");
        int sum2 = 0;
        NodeList L;
        for (int index = 0; index < etudiants.getLength(); index++) {
            // un etudiant
            E3 = (Element) etudiants.item(index);
            L = E3.getElementsByTagName("Provenance");
            E_3 = (Element) L.item(0); // un seul noeud NumEt
            String pays = E_3.getTextContent();
            System.out.println("un etudiant  " + E_3.getTextContent());
            // System.out.println("pays  "+pays);

            if (pays.equals("France")) {
                sum2 += 1;
            }

        }
        System.out.println("num etudiant total de france " + sum2);
/* --------------------------------------- Request 3 ---------------------------------------------------*/


    }
}



