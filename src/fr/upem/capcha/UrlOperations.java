package fr.upem.capcha;

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

}