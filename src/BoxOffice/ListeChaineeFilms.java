package BoxOffice;

public class ListeChaineeFilms {
	FilmChaineHash first;

	public ListeChaineeFilms(FilmChaineHash first){
		this.first = first;
	}

	public void insert(FilmChaineHash toAdd){
		FilmChaineHash elem = first;
		if(first == null){
			first = toAdd;
			return;
		}
		toAdd.setNext(first);
		first = toAdd;
	}

}
