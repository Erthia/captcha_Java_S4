package fr.upem.capcha;

// test class
public class Main {

	public static void main(String[] args) throws Exception {
		Categories categories = new Categories();
		Controller controller = new Controller(categories);
		
		System.out.println("Categories : \n" + categories.getCategoryList());
		
		controller.setRightCategory();

		System.out.println("\nRight category : \n" + controller.getRightCategory());
		
		System.out.println("\nTheir images : \n" + controller.getRightCategory().getList());
	
	}
}
