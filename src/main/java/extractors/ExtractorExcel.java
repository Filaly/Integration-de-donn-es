package extractors;

import java.net.URL;
import java.sql.*;



public class ExtractorExcel {

    private Connection conn;

    public ExtractorExcel()
    {
        super();
    }

    public void connection() {
        try {
            Class.forName("com.hxtt.sql.excel.ExcelDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement du driver.");
            return;
        }

        try {
            URL url = getClass().getResource("../Source1.xls");
            this.conn = DriverManager.getConnection("jdbc:excel:///" + url.getPath());
            System.out.println("Connection au Source1.xls réussie");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("Erreur de connection à la base de données.");
        }
    }

    public void disconnection()
    {
        try
        {
            this.conn.close();
            System.out.println("Déconnexion de la Source1.xls réussie");
        }
        catch (SQLException ex)
        {
            System.err.println("Erreur de déconnexion à la base de données.");
        }
    }

}