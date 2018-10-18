package mediators;

import requests.Req1;
import requests.Req2;
import requests.Req3;

public interface IMediateur {

    public void sendReq1();
    public void sendReq2();
    public void sendReq3();
    public Req1 getRes1();
    public Req2 getRes2();
    public Req3 getRes3();
    public String agregaRes();
    public void show();

}
