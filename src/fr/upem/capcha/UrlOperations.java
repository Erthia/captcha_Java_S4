package fr.upem.capcha;

import java.io.File;
import java.util.Objects;

/**
 * Static methods provider, to manipulate Strings : path, url, package names,...
 * 
 * @author Corradi Emilie
 * @author Hamadache Hédi
 */
final public class UrlOperations{
  /**
   * Transforms absolute path to relative path fr/upem/...
   * 
   * @param absPath the absolute path to transform
   * @return the relative path fr/upem/...
   */
  static public String AbsoluteToRelative(String absPath){
    int cut = absPath.lastIndexOf("fr/upem/capcha/images/");
     if (cut==-1) throw new IllegalArgumentException("Arborescence problem");
    return absPath.substring(cut);
  }

  /**
   * Transforms a name package into a relative path
   * 
   * @param namePackage Name of the package
   * @return relative path of the package (relative to the 'images' folder)
   */
  static public String packageToPath(String namePackage) {
		Objects.requireNonNull(namePackage, "Le nom du package ne doit pas être nul");
		namePackage = namePackage.replace(".", File.separator); 
		namePackage = namePackage.substring(namePackage.lastIndexOf("images" + File.separator), namePackage.length()); // filtre jusqu'a /images
		namePackage = namePackage.replace("images" + File.separator, File.separator); // remplace images/ par /
		return namePackage;
  }

  /**
   * Tranforms a folder into a package name.
   * 
   * @param folder assumed to be a folder
   * @return the package name of the folder
   */
  static public String folderToPackageName(File folder){
    String str = folder.getName();
		String className = str.substring(0, 1).toUpperCase() + str.substring(1);
		String path = folder.getPath(); 
		path = path.replace(File.separator, ".");
		path = path.substring(path.lastIndexOf("fr."), path.length()); // filtre jusqu'a /images
		return path + "." + className;
  }
}