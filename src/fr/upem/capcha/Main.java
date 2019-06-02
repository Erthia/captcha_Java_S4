package fr.upem.capcha;
import java.util.ArrayList;
import fr.upem.capcha.images.Picture;

// test class
public class Main {

	public static void main(String[] args) throws Exception {
		Categories categories = new Categories();
		System.out.println("--- CATEGORIES ---\n" + categories.getCategoryList());

		Controller controller = new Controller(categories);
		controller.setRightCategory();
		System.out.println("\n--- RIGHT CATEGORY ---\n" + controller.getRightCategory());
		System.out.println("\nTheir images : \n" + controller.getRightCategory().getList());

		ArrayList<Picture> toDisplay = controller.createSelectedImageList();
		System.out.println("\n--- TO DISPLAY ---\n" + toDisplay);
		
		controller.setRightCategory();
		System.out.println("\n--- RIGHT CATEGORY ---\n" + controller.getRightCategory());
		System.out.println("\nTheir images : \n" + controller.getRightCategory().getList());

		toDisplay = controller.createSelectedImageList();
		System.out.println("\n--- TO DISPLAY ---\n" + toDisplay);
	}
}
