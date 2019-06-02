package fr.upem.capcha;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import fr.upem.capcha.images.Category;
import fr.upem.capcha.images.Images;
import fr.upem.capcha.images.Picture;
import java.util.Iterator;

/**
 * Class to store and access the different Category's classes (one instance by class);
 * 
 * @author Hamadache Hédi
 * @author Corradi Emilie
 */
public class Categories {
	private ArrayList<Category> categoryList; // only the categories which level is 0

	public Categories() {
		String path = Images.class.getResource("Images.class").getPath();
		File folder = new File(path);
		path = folder.getParent();
		this.categoryList = createCategoryList(new File(path), null);
	}

	private ArrayList<Category> createCategoryList(File currentFolder, Category currentCat) {
		ArrayList<Category> childrenCat = new ArrayList<>();
		ArrayList<Picture> pictures = new ArrayList<>();
		File[] subFiles = currentFolder.listFiles();
		if (subFiles == null) {
			throw new IllegalArgumentException("There is a problem concerning the current folder, or an I/O error occurs");
		}

		for (int i = 0; i < subFiles.length; i++) {
			if (subFiles[i].isDirectory()) {
				Category instance = Category.instanceFromFolder(subFiles[i]);
				if(instance == null) throw new IllegalArgumentException("The name of the folder does not match a name of a class");
				instance.setChildren(createCategoryList(subFiles[i], instance));
				// une fois la récurrence déroulée,
				// on parcourt la liste de pictures des enfants de l'enfant pour construire celle de l'enfant
				Iterator<Category> it = instance.getChildren().iterator();
				while(it.hasNext()){
					ArrayList<Picture> currentPictures = it.next().getList();
					if(currentPictures != null)
						instance.addPictures(currentPictures);
				}
				childrenCat.add(instance);
			}
			else if(subFiles[i].getName().endsWith(".jpg") || subFiles[i].getName().endsWith(".JPG") || subFiles[i].getName().endsWith(".jpeg") || subFiles[i].getName().endsWith(".png")){
				String relUrl = UrlOperations.AbsoluteToRelative(subFiles[i].getAbsolutePath());
				pictures.add(new Picture(relUrl, currentFolder.getName()));
				//comment récupérer les images enfants ???
			}
		}
		if(currentCat != null && pictures != null) currentCat.addPictures(pictures);
		return childrenCat;
	}


	/**
	 * @return the list of the 0-level Categories.
	 */
	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	/**
	 * @return a random Category among the 0-level Categories.
	 */
	public final Category getRandomCat() {
		return getRandomCat(this.categoryList);
	}

	/**
	 * @param catList a list of Category
	 * @return a random Category among the given list
	 */
	static public final Category getRandomCat(List<Category> catList) {
		long seed = System.currentTimeMillis();
		Random RGenerator = new Random(seed);
		return catList.get(RGenerator.nextInt(catList.size()));
	}
}
