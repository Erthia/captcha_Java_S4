package fr.upem.capcha.images;

import fr.upem.capcha.Categories;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Categories categories = new Categories();
		Category firstCat = categories.getRandomCat();
		// Test getRandomPhotos
	    System.out.println("------- PREMIERE CAT ------");

		System.out.println("Photos : \n" + firstCat.getPhotos());
		System.out.println("Random photos (6) : \n" + firstCat.getRandomPhotos(6));
	    
		System.out.println("\n ------ DEUXIEME CAT ------");
		Category secondCat;
		ArrayList<Category> categoriesSup = firstCat.getChildren();
		if(categoriesSup.isEmpty() == true){
			System.out.println("No more images");
			secondCat=null;
		}
		else{
			secondCat = Categories.getRandomCat(categoriesSup);
			System.out.println("Photos : \n" + secondCat.getPhotos());
			System.out.println("Random photos(5) : \n" + secondCat.getRandomPhotos(5));

			System.out.println("\n ------ TROISIEME CAT ------");
			Category thirdCat;
			categoriesSup = secondCat.getChildren();
			if(categoriesSup.isEmpty() == true){
				System.out.println("No more images");
				thirdCat=null;
			}
			else{
				thirdCat = Categories.getRandomCat(categoriesSup);
				System.out.println("Photos : \n" + thirdCat.getPhotos());
				System.out.println("Random photos(4) : \n" + thirdCat.getRandomPhotos(4));
			}
		}
	}
}

