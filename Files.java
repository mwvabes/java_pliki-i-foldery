import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Files {

  public static void main(String[] args) throws IOException {

    File myObj1 = new File("filename.txt");
    File myObj2 = new File("filename_new.txt");
//    createFile(myObj1);

    FileWriter myWriter = new FileWriter("filename.txt");
//    writeToFile(myWriter);

//    readFromFile(myObj1);
//    deleteFile(myObj1);
//    renameOrMoveFile(myObj1, myObj2);

    FileWriter myWriter_new = new FileWriter("filename_new1.txt");
//    copyFile(myObj1, myWriter_new);

  }

  public static void createFile(File myObj) {
    try {
      if (myObj.createNewFile()) {
        System.out.println("Utworzono plik: " + myObj.getName());
      } else {
        System.out.println("Plik o tej nazwie już istnieje.");
      }
    } catch (IOException e) {
      System.out.println("Wystąpił błąd.");
      e.printStackTrace();
    }
  }

  public static void writeToFile(FileWriter myWriter) {
    try {
      myWriter.write("Testowy zapis do pliku");
      myWriter.close();
      System.out.println("Zapisano pomyślnie.");
    } catch (IOException e) {
      System.out.println("Wystąpił błąd.");
      e.printStackTrace();
    }
  }

  public static void readFromFile(File myObj) {
    try {
      if (myObj.exists()) {
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          System.out.println(data);
        }
        myReader.close();
      } else {
        System.out.printf("Plik nie istnieje.");
      }
    } catch (FileNotFoundException e) {
      System.out.println("Wystąpił błąd.");
      e.printStackTrace();
    }
  }

  public static void deleteFile(File myObj) {
    if (myObj.delete()) {
      System.out.println("Pomyślnie usunięto plik: " + myObj.getName());
    } else {
      System.out.println("Nie udało się usunąć pliku.");
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

  public static void copyFile(File myObj, FileWriter myWriter) {
    try {
      if (myObj.exists()) {
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          myWriter.write(data + "\n");
        }
        myWriter.close();
        System.out.println("Zapisano pomyślnie.");
        myReader.close();
      } else {
        System.out.printf("Plik nie istnieje.");
      }
    } catch (FileNotFoundException e) {
      System.out.println("Wystąpił błąd.");
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
