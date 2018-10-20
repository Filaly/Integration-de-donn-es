package requests;

public class Req2 {

    private String etudiant;
    private String provenance;

    private String pays;

    public Req2(String etudiant, String provenance, String pays) {
        this.etudiant = etudiant;
        this.provenance = provenance;
        this.pays = pays;
    }


    public String getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(String etudiant) {
        this.etudiant = etudiant;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }


    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

}
