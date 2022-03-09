package BoxOffice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class BoxOffice {
	private String listing; //Nom du fichier
	private int lineCount;
	int nbFilms;
	private final static String REGEX = "[A-Z]{4,} :\\s*\\b*";

	public BoxOffice(String listing) {
		this.listing = listing;
		lineCount = 0;
		nbFilms = 0;
		try {
			Scanner scanner = new Scanner(new File(listing));
			while (scanner.hasNextLine()){
				lineCount++;
				String[] parsedLine = scanner.nextLine().split(REGEX);
				addFilm(parsedLine[1].stripTrailing(),parsedLine[2].stripTrailing(), Integer.parseInt(parsedLine[3].stripTrailing()),Integer.parseInt(parsedLine[5].stripTrailing()));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}



	public abstract void addFilm(String titre, String realisateur, int annee, int nbEntrees);

	public int getLineCount() {
		return lineCount;
	}
}
