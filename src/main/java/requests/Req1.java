package requests;

public class Req1 {

    private String statut;
    private int nbHeures;

    public Req1(String statut, int nbHeures) {
        this.statut = statut;
        this.nbHeures = nbHeures;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getNbHeures() {
        return nbHeures;
    }

    public void setNbHeures(int nbHeures) {
        this.nbHeures = nbHeures;
    }
}
