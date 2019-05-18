package fr.upem.capcha.images;

import fr.upem.capcha.images.vehicules.voitures.Voiture;
import fr.upem.capcha.images.vehicules.voitures.rouges.Rouge;

public class Main {
	public static void main(String[] args) {
		
	    System.out.println("VOITURES : ");
		Category testVoiture = new Voiture();

	    System.out.println(testVoiture.categoryUrl);
	    System.out.println(testVoiture.getCategory());   
	    System.out.println(testVoiture.getPhotos());
	    
	    System.out.println("");
	    
	    System.out.println("VOITURES ROUGES : ");

		Category testVoitureRouge = new Rouge();

	    System.out.println(testVoitureRouge.categoryUrl);
	    System.out.println(testVoitureRouge.getCategory());   
	    System.out.println(testVoitureRouge.getPhotos()); 
	}
}

