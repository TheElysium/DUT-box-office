package BoxOffice;

public class BoxOfficeArbre extends BoxOffice{
	private FilmArbre root;
	private FilmArbre toUpdate;
	public BoxOfficeArbre(String listing) {
		super(listing);
	}

	@Override
	public void addFilm(String titre, String realisateur, int annee, int nbEntrees) {
		if(root == null) {
			root = new FilmArbre(new Film(titre, realisateur, annee, nbEntrees));
//			System.out.println(root);
			nbFilms++;
			return;
		}

		toUpdate = null;

		root = modifyTree(root, titre,nbEntrees, realisateur);
		if(toUpdate == null) {
			FilmArbre toAdd = new FilmArbre(new Film(titre, realisateur, annee, nbEntrees));
//			System.out.println(toAdd);
			insert(root, toAdd);
			nbFilms++;

		}else{
			insert(root, toUpdate);
		}
	}

	//Technique utilisée: https://www.youtube.com/watch?v=a-53QSxovUA&list=LL&index=1&t=640s
	//Modifier le nombre d'entrées d'un film (supprimer puis insérer la nouvelle valeur)
	private FilmArbre modifyTree(FilmArbre root, String titre, int nbEntrees, String realisateur) {
		if(root == null){
			return root;
		}
		if(root.getTitre().equals(titre) && root.getRealisateur().equals(realisateur)){
			// Le film à supprimer est atteint
			FilmArbre toAdd = new FilmArbre(root);
			toAdd.setNbEntrees(toAdd.getNbEntrees() + nbEntrees);
			toUpdate = toAdd;

			//Si il n'y a pas d'enfant à gauche
			if (root.gauche == null) {
				FilmArbre tmp = root.droite;
				return tmp;
			}

			//Si il n'y a pas d'enfant à droite
			else if (root.droite == null) {
				FilmArbre tmp = root.gauche;
				return tmp;
			}

			// Si il y a deux enfants
			else {
				FilmArbre curr=root.droite;
				FilmArbre gauche=root.gauche;
				FilmArbre droite =root.droite;
				FilmArbre succ_parent=null;

				root = null;
				while(curr.gauche!=null)
				{
					succ_parent=curr;
					curr=curr.gauche;
				}

				if(succ_parent!=null)
				{
					succ_parent.gauche=curr.droite;
					curr.droite=droite;
				}
				curr.gauche=gauche;
				return curr;
			}
		}
		//Parcours récursif comme pour l'affichage pre-in-postfixe
		root.gauche = modifyTree(root.gauche, titre, nbEntrees, realisateur);
		root.droite = modifyTree(root.droite, titre, nbEntrees,realisateur);
		return root;
	}

	private FilmArbre insert(FilmArbre root, FilmArbre toAdd) {
		if (root==null){
			root = toAdd;
		}
		else if(root.getCle() > toAdd.getCle()){
			root.gauche = insert(root.gauche, toAdd);
		}
		else if(root.getCle() <= toAdd.getCle()){
			root.droite = insert(root.droite, toAdd);
		}
		return root;
	}

	private void top3(FilmArbre root){
		//Cas spéciaux où l'arbre à moins de 3 noeuds
		if(root == null) return;
		FilmArbre parent = root;
		if(root.droite == null){
			Affichage.affichageTop(root);
			return;
		}
		FilmArbre node = root.droite;
		if(node.droite == null){
			Affichage.affichageTop(root);
			Affichage.affichageTop(node);
			return;
		}
		FilmArbre nodeChild = node.droite;

		//On va tout à droite de notre arbre pour avoir le max
		while(nodeChild.droite != null){
			parent = node;
			node = nodeChild;
			nodeChild = nodeChild.droite;
		}
		Affichage.affichageTop(nodeChild);
		Affichage.affichageTop(node);

		if(node.gauche != null){
			Affichage.affichageTop(node.gauche);
		}else{
			Affichage.affichageTop(parent);
		}
	}

	public static void afficherArbre(FilmArbre root){
		if(root == null) return;
		afficherArbre(root.gauche);
		System.out.println(root.getTitre() + " : " + root.getCle());
		afficherArbre(root.droite);
	}


	public static void main(String[] args) {
		if (args.length<1) System.err.println("nom de fichier manquant");
		else {
			String listing = args[0];

			BoxOfficeArbre l = new BoxOfficeArbre(listing);
			Affichage.afficher(l, listing);
			l.top3(l.root);
		}
	}
}
