package BoxOffice;

public class BoxOfficeChaine extends BoxOffice {
	private FilmChaine first;

	public BoxOfficeChaine(String listing){
		super(listing);
		tri();
	}

	@Override
	public void addFilm(String titre, String realisateur, int annee, int nbEntrees) {
		if(first == null){
			first = new FilmChaine(new Film(titre,realisateur, annee, nbEntrees), null);
			nbFilms++;
			return;
		}
		FilmChaine elem = first;
		while (elem.getNext() != null){
			if(titre.equals(elem.getTitre()) && realisateur.equals(elem.getRealisateur())){
				elem.setNbEntrees(elem.getNbEntrees() + nbEntrees);
				return;
			}
			elem = elem.getNext();
			//System.out.println(elem.getNext());
		}
		elem.setNext(new FilmChaine(new Film(titre, realisateur, annee, nbEntrees), null));
		//System.out.println("FIlm ajouté");
		nbFilms++;
	}

	private void tri(){
		FilmChaine elem = first;
		while (elem.getNext() != null){
			//System.out.println("tri");
			FilmChaine max = max(elem);
			swap(elem, max);
			elem = elem.getNext();
		}
	}

	private void swap(FilmChaine current, FilmChaine max) {
		Film tmp = new Film(current);
		//System.out.println("swap");

		current.setNbEntrees(max.getNbEntrees());
		current.setAnnee(max.getAnnee());
		current.setRealisateur(max.getRealisateur());
		current.setTitre(max.getTitre());

		max.setNbEntrees(tmp.getNbEntrees());
		max.setAnnee(tmp.getAnnee());
		max.setRealisateur(tmp.getRealisateur());
		max.setTitre(tmp.getTitre());

	}

	private FilmChaine max(FilmChaine elem) {
		FilmChaine maxFilm = new FilmChaine(elem);
		elem = elem.getNext();
		while (elem.getNext() != null) {
			if(elem.getNbEntrees()>maxFilm.getNbEntrees()){
				maxFilm = elem;
			}
			elem = elem.getNext();
		}
		return maxFilm;
	}

	public static void main(String[] args) {
		if (args.length<1) System.err.println("nom de fichier manquant");
		else{
			//String listing = "medium_2010-2015.box";
			String listing = args[0];
			BoxOfficeChaine l = new BoxOfficeChaine(listing);
			Affichage.afficher(l, listing);
			FilmChaine elem = l.first;
			for (int i = 0; l.nbFilms > 3 ? i < 3 : i < l.nbFilms; i++) {
				Affichage.affichageTop(elem);
				elem = elem.getNext();
			}
		}
	}
}
