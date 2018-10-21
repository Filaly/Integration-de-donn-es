package requests;

public class Req3 {

	private String tableCours;
	private String type;

    public Req3(String tableCours,String type) {
    	this.type=type;
        this.tableCours=tableCours;
    }

	public String getTableCours() {
		return tableCours;
	}

	public void setTableCours(String tableCours) {
		this.tableCours = tableCours;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    
   
}
