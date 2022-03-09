package BoxOffice;

public class FilmChaineHash extends Film{
	private FilmChaineHash next;
	public FilmChaineHash(Film f, FilmChaineHash next) {
		super(f);
		this.next = next;
	}

	public FilmChaineHash(Film f){this(f, null);}

	public FilmChaineHash getNext() {
		return next;
	}

	public void setNext(FilmChaineHash next) {
		this.next = next;
	}
}
