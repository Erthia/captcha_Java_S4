package fr.upem.capcha;

import fr.upem.capcha.images.Category;
import fr.upem.capcha.images.ponts.Ponts;

public class Main {

	public static void main(String[] args) throws Exception {
		Category Ponts = new Ponts();
		Controller test = new Controller();
		Categories testCategories = new Categories();
		
		test.createSelectedImageList();
		
	    System.out.println("Categories : \n" + testCategories.getCategoryList());
	    
	    System.out.println("\nRight Category : \n" + test.getRightCategory().getCategory());

	    
	    System.out.println("\nImages selectionnées : \n" + test.getImagesList());
	    
	    Controller.verify(Ponts.getList());
	}
}
