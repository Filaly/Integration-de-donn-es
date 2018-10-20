package requests;

public class Req1 {

    private String enseignant;
    private String nbHeures;

    public Req1(String enseignant, String nbHeures) {
        this.enseignant = enseignant;
        this.nbHeures = nbHeures;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }

    public String getNbHeures() {
        return nbHeures;
    }

    public void setNbHeures(String nbHeures) {
        this.nbHeures = nbHeures;
    }
}
