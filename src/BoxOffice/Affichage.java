package BoxOffice;

public class Affichage {
	public static void affichageTop(Film f){
		System.out.printf("(%d) %s   entrées : %d\n",f.getAnnee(),f.getTitre(),f.getNbEntrees());
	}

	public static void afficher(BoxOffice bo, String file){
		System.out.println("Fichier : " + file);
		System.out.println("Nombre de lignes : " + bo.getLineCount());
		System.out.println("Nombre de films : " + bo.nbFilms);
		System.out.println("----------");
		System.out.println("Films comptabilisant le plus grand nombre d'entrées :");
	}
}
