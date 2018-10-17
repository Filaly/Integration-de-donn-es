package main.java;

import java.sql.SQLException;
import main.java.extractors.ExtractorXml;

public class Principal {
    public static void main(String[] args) throws Exception {
    	System.out.println("hello from MAIN");
    	ExtractorXml example = new ExtractorXml();
    	example.lire_XML("modele/Univ_BD_3.xml");
    	
    }
}
