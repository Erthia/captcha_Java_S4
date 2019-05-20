package fr.upem.capcha;


public class Main {
	public static void main(String[] args) {
		Controller test = new Controller();
		Categories testCategories = new Categories();
		
		test.createSelectedImageList();
		
	    System.out.println("Categories : \n" + testCategories.getCategoryList());
	    
	    System.out.println("\nRight Category : \n" + test.getRightCategory().getCategory());

	    
	    System.out.println("\nImages selectionnées : \n" + test.getImagesList());
	}
}
