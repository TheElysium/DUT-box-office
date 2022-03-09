package BoxOffice;

public class FilmArbre extends Film{
	 FilmArbre gauche;
	 FilmArbre droite;

	public FilmArbre(Film f, FilmArbre g, FilmArbre d){
		super(f);
		gauche = g;
		droite = d;
	}

	public FilmArbre(Film f) {
		super(f);
	}

	public int getCle(){
		return getNbEntrees();
	}

	public void setCle(int i){
		setNbEntrees(getNbEntrees()+i);
	}

}
