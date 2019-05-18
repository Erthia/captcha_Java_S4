package fr.upem.capcha.images;

import fr.upem.capcha.images.ponts.Pont;

public class Main {
	public static void main(String[] args) {
		Category test = new Pont();

	    System.out.println(test.categoryUrl);
	    System.out.println(test.getCategory());   
	    System.out.println(test.getPhotos());   

	}
}

