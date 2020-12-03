import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Catalogs {

  public static void main(String[] args) throws IOException {


    File myObj1 = new File("catalog");
    File myObj2 = new File("catalog_new");

//    makeDirectory(myObj1);
//    getList(myObj1);
//
//    renameDirectory(myObj1, myObj2);
//
//    copyFolder(myObj1, myObj2);
//    deleteDirectory(myObj1);
  }

  public static void makeDirectory(File myObj) {
    try {
      boolean bool = myObj.mkdir();
      if (bool) {
        System.out.println("Katalog stworzony pomyślnie.");
      }else{
        System.out.println("Wystąpił błąd poczas tworzenia katalogu.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void getList(File myObj) {
    String[]entries = myObj.list();
    for(String s: entries){
      File currentFile = new File(myObj.getPath(),s);
      System.out.println((currentFile.isDirectory() ? "Catalog " : "File ") + currentFile.getName());
    }
  }

  public static void renameDirectory(File oldDir, File newDir) {
    boolean result = oldDir.renameTo(newDir);
    if (result) {
      System.out.println("Zmieniono nazwę: " + newDir.getName());
    } else {
      System.out.println("Nie udało się zmienić nazwy");
    }
  }

  public static void deleteDirectory(File myObj) {
    try {
      String[]entries = myObj.list();
      for(String s: entries){
        File currentFile = new File(myObj.getPath(),s);
        if (currentFile.isDirectory()) {
          deleteDirectory(currentFile);
        }
        currentFile.delete();
      }
      myObj.delete();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void moveDirectory(File oldDir, File newDir) {

    Path myPathOld = Paths.get(oldDir.getAbsolutePath());
    Path myPathNew = Paths.get(newDir.getAbsolutePath());

    try {
      Files.move(myPathOld, myPathNew);
    } catch (FileAlreadyExistsException ex) {
      System.out.println("Plik docelowy już istnieje");
    } catch (IOException ex) {
      System.out.format("Błąd I/O: %s%n", ex);
    }
  }

  public static void copyDirectory(File oldDir, File newDir) {

    Path myPathOld = Paths.get(oldDir.getAbsolutePath());
    Path myPathNew = Paths.get(newDir.getAbsolutePath());

    try {
      Files.copy(myPathOld, myPathNew);
    } catch (FileAlreadyExistsException ex) {
      System.out.println("Plik docelowy już istnieje");
    } catch (IOException ex) {
      System.out.format("Błąd I/O: %s%n", ex);
    }
  }

  public static void copyFolder(File oldDir, File newDir) throws IOException {

    Path myPathOld = Paths.get(oldDir.getAbsolutePath());
    Path myPathNew = Paths.get(newDir.getAbsolutePath());

    try (Stream<Path> stream = Files.walk(myPathOld)) {
      stream.forEach(source -> copy(source, myPathNew.resolve(myPathOld.relativize(source))));
    }
  }

  private static void copy(Path source, Path dest) {
    try {
      Files.copy(source, dest, REPLACE_EXISTING);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public static void renameOrMoveFile(File myObjOld, File myObjNew) {
    boolean result = myObjOld.renameTo(myObjNew);
    if (result) {
      System.out.println("Zmieniono nazwę / lokalizację: " + myObjNew.getName());
    } else {
      System.out.println("Nie udało się zmienić nazwy");
    }
  }

}
