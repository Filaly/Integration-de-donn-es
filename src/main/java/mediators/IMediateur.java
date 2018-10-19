package mediators;

import requests.Req1;
import requests.Req2;
import requests.Req3;

import java.sql.SQLException;

public interface IMediateur {

    public void sendReq1();

    public void sendReq3();
    public void getRes1();
    public void getRes2() throws SQLException;
    public void getRes3();
    public int agregaRes2() throws SQLException;
    public void show();

}
