package main.java;

// import java.sql.SQLException;

 import main.java.extractors.ExtractorXml;

public class Principal {
    public static void main(String[] args) throws Exception {
        System.out.println("hello from MAIN");
        ExtractorXml example = new ExtractorXml();
        example.lire_XML("C:\\Users\\Hania\\OneDrive\\Beu\\TD-mediateur\\Integration-de-donn-es\\modele\\Univ_BD_3.xml");

    }
}
