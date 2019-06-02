package fr.upem.capcha;

import java.io.File;
import java.util.Objects;

final public class UrlOperations{
    //transform absolutePath to relative path "fr/upem/..."
  static public String AbsoluteToRelative(String absPath){
    int cut = absPath.lastIndexOf("fr/upem/capcha/images/");
     if (cut==-1) throw new IllegalArgumentException("Arborescence problem");
    return absPath.substring(cut);
  }

  static public int countSeparators(String path, char separator){
    int result = -1;
    int cursor = 0;
    while(cursor != -1){
      cursor = path.indexOf(separator, cursor + 1);
      result ++;
    }
    return result;
  }

  static public String packageToPath(String namePackage) {
		Objects.requireNonNull(namePackage, "Le nom du package ne doit pas être nul");
		namePackage = namePackage.replace(".", File.separator); 
		namePackage = namePackage.substring(namePackage.lastIndexOf("images" + File.separator), namePackage.length()); // filtre jusqu'a /images
		namePackage = namePackage.replace("images" + File.separator, File.separator); // remplace images/ par /
		return namePackage;
  }

  static public String folderToClassPath(File folder){
    String str = folder.getName();
		String className = str.substring(0, 1).toUpperCase() + str.substring(1);
		String path = folder.getPath(); 
		path = path.replace(File.separator, ".");
		path = path.substring(path.lastIndexOf("fr."), path.length()); // filtre jusqu'a /images
		return path + "." + className;
  }
}