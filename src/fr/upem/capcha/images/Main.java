package fr.upem.capcha.images;

import fr.upem.capcha.images.vehicules.voitures.Voitures;
import fr.upem.capcha.images.vehicules.voitures.rouges.Rouges;

public class Main {
	public static void main(String[] args) {
		// Test Voitures
	    System.out.println("------- VOITURES ------");
		Category testVoiture = new Voitures();

	    System.out.println("CategorieUrl : " + testVoiture.getCategoryUrl());
	    System.out.println("Categorie : " + testVoiture.getCategory());   
	    System.out.println("Photos : \n" + testVoiture.getPhotos());
	    
	    System.out.println("\n ------ VOITURES ROUGES ------");

		// Test Voitures rouge
		Category testVoitureRouge = new Rouges();

	    System.out.println("CategorieUrl : " + testVoitureRouge.getCategoryUrl());
	    System.out.println("Categorie : " + testVoitureRouge.getCategory());   
	    System.out.println("Photos : \n" + testVoitureRouge.getPhotos());
		System.out.println("Random photos : \n" + testVoitureRouge.getRandomPhotos(2));

	    
		// Test photos	  
	    System.out.println("\n ------ TEST ------");

		System.out.println("Categorie enfant : \n" + testVoitureRouge.getCategory());	  
		System.out.println("Categorie parent : \n" + testVoiture.getCategory());	    
		System.out.println("Voiture rouge appartient à Voiture ? : " + testVoitureRouge.isPhotoCorrect(testVoiture));	    
	}
}

