package extractors;

import requests.Req2;

import java.net.URL;
import java.sql.*;
import java.util.Hashtable;
import java.util.Map;



public class ExtractorExcel {

    private Connection conn;
    private static Map<String, String> table; //<GAV,LAV>
    private String id_etudiant;

    private Req2 req2;

    public ExtractorExcel(Req2 req2)
    {
        this.req2=req2;
        table = new Hashtable<String, String>();
    }

    public void getRequest2FromMediator(Req2 req2){
        this.req2 = req2;
    }

    private void TradForReq2(){
        table.put(req2.getProvenance(),"Provenance");
        table.put(req2.getEtudiant(),"etudiant");

    }

    public int exec_request2() throws SQLException {

        TradForReq2();
        connection();

        //System.out.println(table.get(req2.getProvenance()));
        String sql = "SELECT * FROM \"2006\" WHERE "+table.get(req2.getProvenance())+" = \'"+req2.getPays()+"\' " +
                     "AND Statut = \'"+table.get(req2.getEtudiant())+"\' " +
                     "UNION SELECT * FROM \"2007\" WHERE "+table.get(req2.getProvenance())+" = \'"+req2.getPays()+"\' " +
                     "AND Statut = \'"+table.get(req2.getEtudiant())+"\' ";


        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        int iNumCols = resultSetMetaData.getColumnCount();
        int sumEtudiant = 0;
        Object colval;
        while (rs.next()) {
            sumEtudiant += 1;
            for (int i = 1; i <= iNumCols; i++) {
                colval = rs.getObject(i);

                System.out.print(colval + "  ");

            }
            System.out.println();
        }
        System.out.println(sumEtudiant);
        rs.close();
        stmt.close();

        return sumEtudiant;

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





    public void sendResultToMediator(){

    }

}