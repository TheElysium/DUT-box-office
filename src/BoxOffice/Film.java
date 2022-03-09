package BoxOffice;

public class Film {
	private String titre;
	private String realisateur;
	private int annee;
	private int nbEntrees;

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getNbEntrees() {
		return nbEntrees;
	}

	public void setNbEntrees(int nbEntrees) {
		this.nbEntrees = nbEntrees;
	}

	public Film(String titre, String realisateur, int annee, int nbEntrees) {
		this.titre = titre;
		this.realisateur = realisateur;
		this.annee = annee;
		this.nbEntrees = nbEntrees;
	}

	public Film(Film f){
		this.titre = f.titre;
		this.realisateur = f.realisateur;
		this.annee = f.annee;
		this.nbEntrees = f.nbEntrees;
	}

	protected Film clone(){
		return new Film(this);
	}

	@Override
	public String toString() {
		return "("+annee+") "+titre+"   Real.: " +realisateur+"   Entrees: "+nbEntrees;
	}
}
