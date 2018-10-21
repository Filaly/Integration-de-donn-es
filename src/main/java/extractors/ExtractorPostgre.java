package extractors;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import requests.Req1;
import requests.Req2;
import requests.Req3;

public class ExtractorPostgre {
	private static String jdbcUrl = "jdbc:postgresql://localhost:5433/IntegrationDB";
	private static String username = "Ahmed";
	private static String password = "admin";
  
    Connection conn = null;
    private static Statement stmt = null;
    ResultSet rs = null;
    private static Map<String, String> refer;
    private Req1 req1;
    private Req2 req2;
    private Req3 req3;
	private Map<String, String> res3;
    
    public ExtractorPostgre()
    {
    	refer = new Hashtable<String, String>();
    }
    public void getRequest1FromMediator(Req1 req1){
        this.req1 = req1;
    }
    public void getRequest2FromMediator(Req2 req2){
        this.req2 = req2;
    }
    public void getRequest3FromMediator(Req3 req3){
        this.req3 = req3;
    }

    private void TradRequest(){
        refer.put(req2.getProvenance(),"provenance");
        refer.put(req2.getEtudiant(),"etudiant");
        refer.put(req2.getPays(),"fr");
        refer.put(req3.getTableCours(),"cours");
        refer.put(req3.getType(), "type");
    }

    public int sendResult1ToMediator() throws SQLException {
        return 0;
    }
    public int sendResult2ToMediator() throws SQLException {
        return exec_request2();
    }
    public Map<String, String> sendResult3ToMediator() throws SQLException {
        return exec_request3();
    }
    private int exec_request2() throws SQLException {

        TradRequest();

        String sql = "SELECT COUNT(*) FROM "+refer.get(req2.getEtudiant())+" WHERE "+ refer.get(req2.getProvenance())+"='"+ refer.get(req2.getPays())+"'";
        int resultatReq2=0;       
        try {
        	 conn = DriverManager.getConnection(jdbcUrl, username, password);
             stmt = conn.createStatement();
             rs = stmt.executeQuery(sql);            
            if (rs.next()) {
               resultatReq2=Integer.parseInt(rs.getString(1));
            }

          } catch (SQLException e) {
            e.printStackTrace();
          } finally {
            try {
              if (stmt != null) {
                stmt.close();
              }
              if (rs != null) {
                rs.close();
              }
              if (conn != null) {
                conn.close();
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }

        return resultatReq2;

    }
    private Map<String, String> exec_request3() throws SQLException {

        TradRequest();

       String sql = "SELECT "+refer.get(req3.getType())+", count("+refer.get(req3.getType())+") FROM "+refer.get(req3.getTableCours())+" GROUP by "+refer.get(req3.getType());
        res3 = null;
       
        try {
        	 conn = DriverManager.getConnection(jdbcUrl, username, password);
             stmt = conn.createStatement();
             rs = stmt.executeQuery(sql);
            while(rs.next()) {
            	res3.put(rs.getString(1), rs.getString(2));
            }
           

          } catch (SQLException e) {
            e.printStackTrace();
          } finally {
            try {

              // Step 5 Close connection
              if (stmt != null) {
                stmt.close();
              }
              if (rs != null) {
                rs.close();
              }
              if (conn != null) {
                conn.close();
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }

        return res3;

    }
}
