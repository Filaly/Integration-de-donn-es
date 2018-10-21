package requests;

public class Req3 {

    private String type_cours;
    private TypeCour[] cours;
    private String tableCours;
    private String type;

    public Req3(String type_cours, TypeCour[] cours, String tableCours, String type ){
        this.type_cours = type_cours;
        this.cours = cours;
        this.type=type;
        this.tableCours=tableCours;

    }

    public String getType_cours() {
        return type_cours;
    }

    public void setType_cours(String type_cours) {
        this.type_cours = type_cours;
    }

    public TypeCour[] getCours() {
        return cours;
    }

    public void setCours(TypeCour[] cours) {
        this.cours = cours;
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
