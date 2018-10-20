package extractors;

import requests.Req2;
import requests.Req3;
import requests.TypeCour;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;



public class ExtractorExcel {

    private Connection conn;
    private static Map<String, String> table; //<Med,Source>

    private Req2 req2;
    private Req3 req3;

    public ExtractorExcel()
    {
        table = new Hashtable<String, String>();
    }

    public void getRequest2FromMediator(Req2 req2){
        this.req2 = req2;
    }

    public void getRequest3FromMediator(Req3 req3) {
        this.req3 = req3;
    }


    public int sendResult2ToMediator() throws SQLException {
        return exec_request2();
    }
    public ArrayList<Map<String, String>> sendResult3ToMediator() throws SQLException {
        return exec_request3();
    }

    private int exec_request2() throws SQLException {
        table.put(req2.getProvenance(),"Provenance");
        table.put(req2.getEtudiant(),"etudiant");

        String sql = "SELECT * FROM \"2006\" WHERE "+table.get(req2.getProvenance())+" = \'"+req2.getPays()+"\' " +
                     "AND Statut = \'"+table.get(req2.getEtudiant())+"\' " +
                     "UNION SELECT * FROM \"2007\" WHERE "+table.get(req2.getProvenance())+" = \'"+req2.getPays()+"\' " +
                     "AND Statut = \'"+table.get(req2.getEtudiant())+"\' ";


        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);


        int sumEtudiant = 0;

        while (rs.next()) {
            sumEtudiant += 1;
        }

        rs.close();
        stmt.close();

        return sumEtudiant;

    }

    private ArrayList<Map<String, String>> exec_request3() throws SQLException {

        Map <String, String> nbHeurs2006 = new Hashtable<String, String>();
        Map <String, String> nbHeurs2007 = new Hashtable<String, String>();

        ArrayList<Map<String, String>> listOfMaps = new ArrayList<Map<String, String>>();

        table.put(req3.getType_cours(), "Type_Cours");

        String sql;
        for (TypeCour t: req3.getCours()) {
            sql = "SELECT COUNT(*) FROM \"2006\" WHERE \"Type_Cours\" = \'"+t.toString()+"\' ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int iNumCols = resultSetMetaData.getColumnCount();

            Object colval;
            while (rs.next()) {
                for (int i = 1; i <= iNumCols; i++) {
                    colval = rs.getObject(i);
                    nbHeurs2006.put(t.toString(),colval.toString());
                }
            }

            rs.close();
            stmt.close();

        }

        for (TypeCour t: req3.getCours()) {
            sql = "SELECT COUNT(*) FROM \"2007\" WHERE \"Type_Cours\" = \'"+t.toString()+"\' ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int iNumCols = resultSetMetaData.getColumnCount();

            Object colval;
            while (rs.next()) {
                for (int i = 1; i <= iNumCols; i++) {
                    colval = rs.getObject(i);
                    nbHeurs2007.put(t.toString(),colval.toString());
                }
            }

            rs.close();
            stmt.close();

        }
        listOfMaps.add(nbHeurs2006);
        listOfMaps.add(nbHeurs2007);

        return listOfMaps;
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