package entryPoint;

import java.io.FileReader;
import java.io.IOException;

import controller.MyController;

public class MowItNow
{
   public static String FILE_PATH = "src/main/ressources/moves.txt";

   public static void main(String[] args)
   {
      if (args.length != 0)
      {
         FILE_PATH = args[0];
      }
      try
      {
         FileReader fr = new FileReader(FILE_PATH);
         new MyController(fr);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}
