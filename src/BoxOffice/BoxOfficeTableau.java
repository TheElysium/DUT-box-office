package BoxOffice;

public class BoxOfficeTableau extends BoxOffice {
	public final static int TAILLE_INITIALE = 100;
	private Film[] elements;

	public BoxOfficeTableau(String listing) {
		super(listing);
		triTop(elements, nbFilms);
	}

	@Override
	public void addFilm(String titre, String realisateur, int annee, int nbEntrees) {
		if(elements == null) init();

		if(nbFilms +1 > elements.length){
			doublerTableau();
		}

		for (int i = 0; i < nbFilms; i++) {
			if(titre.equals(elements[i].getTitre()) && realisateur.equals(elements[i].getRealisateur())){
				elements[i].setNbEntrees(elements[i].getNbEntrees()+nbEntrees);
				return;
			}
		}

		elements[nbFilms++] = new Film(titre, realisateur, annee, nbEntrees);
	}

	private void init() {
		elements = new Film[TAILLE_INITIALE];
	}

	private void doublerTableau(){
		Film[] res = new Film[elements.length * 2];
		for (int i = 0; i < elements.length; i++) {
			res[i] = elements[i].clone();
		}
		elements = res;
	}


	private static void triTop(Film[] elements, int curseur){
		for (int i = 0; i < curseur; i++) {
			int maxI = max(i, curseur, elements);
			swap(i, maxI, elements);
			//System.out.printf("Max ajouté: %s\n", top[i][0]);
		}
	}

	private static void swap(int i, int maxI, Film[] tab) {
		Film tmp = tab[i];
		tab[i] = tab[maxI];
		tab[maxI] = tmp;
	}

	private static int max(int i, int curseur, Film[] tab) {
		Film max = tab[i];
		int maxI = i;
		for (int j = i+1; j < curseur; j++) {
			//System.out.printf("%s: %d > %s: %d ? %b\n", tab[j][0], (int) tab[j][1], max[0], (int) max[1] , (int) tab[j][1] > (int) max[1]);
			if(tab[j].getNbEntrees() > max.getNbEntrees()){
				max = tab[j];
				maxI = j;
			}
		}
		return maxI;
	}


	public static void main(String[] args) {
		if (args.length<1) System.err.println("nom de fichier manquant");
		else{
			//String listing = "medium_2010-2015.box";
			String listing = args[0];

			BoxOfficeTableau l = new BoxOfficeTableau(listing);
			Affichage.afficher(l, listing);
			Film f = l.elements[0];
			int i = 0;
			while (f != null && i < 3){
				Affichage.affichageTop(f);
				f = l.elements[++i];
			}
		}
	}

}
