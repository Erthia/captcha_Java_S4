package fr.upem.capcha;


public class Main {
	public static void main(String[] args) {
		Controller test = new Controller();
		Categories testCategories = new Categories();
		
		test.createSelectedImageList();
		
	    System.out.println(testCategories.getCategoryList());
	    System.out.println(test.getImagesList());
	}
}
