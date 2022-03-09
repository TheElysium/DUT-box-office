package BoxOffice;

public class BoxOfficeHash extends BoxOffice{
	private final int TAILLE_INITIALE = 100000;
	private ListeChaineeFilms[] elements;
	private Object[][] listeFilms;

	public BoxOfficeHash(String listing) {
		super(listing);
	}

	@Override
	public void addFilm(String titre, String realisateur, int annee, int nbEntrees) {
		if(elements == null){
			elements = new ListeChaineeFilms[TAILLE_INITIALE];
			listeFilms = new Object[TAILLE_INITIALE][2];
		}
		if(nbFilms == 0){
			insert(titre,realisateur, annee,nbEntrees);
			return;
		}
		FilmChaineHash isInHashTable = search(titre, realisateur);
		if(isInHashTable != null){
			isInHashTable.setNbEntrees(isInHashTable.getNbEntrees()+nbEntrees);
			return;
		}
		insert(titre,realisateur, annee,nbEntrees);
	}


	private void insert(String titre, String realisateur, int annee, int nbEntrees){
		listeFilms[nbFilms][0] = titre;
		listeFilms[nbFilms][1] = realisateur;
		nbFilms++;
		FilmChaineHash toAdd = new FilmChaineHash(new Film(titre,realisateur,annee,nbEntrees));
		int hashcode = getHashCode(titre, realisateur);
		int index = convertToIndex(hashcode);
		if(elements[index] == null){
			elements[index] = new ListeChaineeFilms(toAdd);
			return;
		}
		ListeChaineeFilms l = elements[index];
		l.insert(new FilmChaineHash(new Film(titre, realisateur,annee,nbEntrees)));
	}
	private int convertToIndex(int hashcode) {
		return hashcode%TAILLE_INITIALE;
	}

	private int getHashCode(String titre, String realisateur) {
		int res = 0;
		for (int i = 0; i < titre.length() && i < 10; i++) {
			char c = titre.charAt(i);
			res += c * 3.1415;
		}
		for (int i = 0; i < realisateur.length() && i < 10; i++) {
			char c = realisateur.charAt(i);
			res += c * 42;
		}
		return res;
	}

	public FilmChaineHash search(String titre, String realisateur){
		int hashcode = getHashCode(titre,realisateur);
		int index = convertToIndex(hashcode);
		if(elements[index] == null) return null;
		ListeChaineeFilms l = elements[index];
		FilmChaineHash f = l.first;
		while (f != null) {
			if(f.getTitre().equals(titre) && f.getRealisateur().equals(realisateur)){
				return f;
			}
			f = f.getNext();
		}
		return null;
	}
	
	public Film[] top3() {
		Film[] top = {search((String) listeFilms[0][0], (String) listeFilms[0][1]),
				search((String) listeFilms[1][0], (String) listeFilms[1][1]),
				search((String) listeFilms[2][0], (String) listeFilms[2][1]),
		};

		for (int i = 3; i < nbFilms; i++) {
			FilmChaineHash f = search((String) listeFilms[i][0], (String) listeFilms[i][1]);
			if(f.getNbEntrees() > top[0].getNbEntrees()){
				top[2] = top[1];
				top[1] = top[0];
				top[0] = f;
			}
			else if(f.getNbEntrees() > top[1].getNbEntrees()){
				top[2] = top[1];
				top[1] = f;
			}
			else if(f.getNbEntrees() > top[2].getNbEntrees()){
				top[2] = f;
			}
		}
		return top;
	}

	public static void main(String[] args) {
		if (args.length<1) System.err.println("nom de fichier manquant");
		else {
			String listing = args[0];

			BoxOfficeHash l = new BoxOfficeHash(listing);
			Affichage.afficher(l, listing);
			Film[] top = l.top3();
			for (Film film : top) {
				Affichage.affichageTop(film);
			}
		}
	}
}
