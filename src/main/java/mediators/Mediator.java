package mediators;

import extractors.ExtractorExcel;
import requests.Req1;
import requests.Req2;
import requests.Req3;

public class Mediator implements IMediateur {

    private Req1 req1;
    private Req2 req2;
    private Req3 req3;
    private ExtractorExcel extractorExcel;

    public Mediator(Req1 req1, Req2 req2, Req3 req3) {
        this.req1 = req1;
        this.req2 = req2;
        this.req3 = req3;
    }

    public void sendReq1() {

    }

    public void sendReq2() {
        extractorExcel.getRequest2FromMediator(req2);
    }

    public void sendReq3() {

    }

    public Req1 getRes1() {
        return null;
    }

    public Req2 getRes2() {
        return null;
    }

    public Req3 getRes3() {
        return null;
    }

    public String agregaRes() {
        return null;
    }

    public void show() {

    }
}
