package requests;

public class Req3 {

    private String type_cours;
    private TypeCour[] cours;

    public Req3(String type_cours, TypeCour[] cours ){
        this.type_cours = type_cours;
        this.cours = cours;

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
}
