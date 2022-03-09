package BoxOffice;

public class FilmChaine extends Film{
	private FilmChaine next;
	public FilmChaine(Film f, FilmChaine n) {
		super(f);
		next = n;
	}

	public FilmChaine(Film f){
		this(f, null);
	}

	public FilmChaine getNext() {
		return next;
	}

	public void setNext(FilmChaine next) {
		this.next = next;
	}
}
