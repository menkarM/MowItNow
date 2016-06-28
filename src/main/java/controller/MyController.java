package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import model.Pelouse;
import model.Tondeuse;

public class MyController
{
   private Pelouse            pelouse;
   public ArrayList<Tondeuse> list_tondeuses;

   public MyController(FileReader file_reader)
   {
      list_tondeuses = new ArrayList<Tondeuse>();
      control(file_reader);
   }

   /**
    * Contrôle les éléments du fichier d'entrée
    * 
    * @param fr Le fichier d'entrée
    */
   public void control(FileReader fr)
   {
      try
      {
         BufferedReader buff_taille = new BufferedReader(fr);
         // Parsing taille de la pelouse
         if (verifTaillePelouse(buff_taille.readLine()))
         {
            buff_taille.mark(1000);
            // On vérifie le nombre de lignes du fichier
            String line;
            int nbrLignes = 0;
            while ((line = buff_taille.readLine()) != null)
            {
               nbrLignes++;
            }
            buff_taille.reset();
            // On parse si chaque tondeuse possède bien une liste de moves
            if (nbrLignes % 2 == 0)
            {
               String currentLigne;
               // On parse les moves que si il y'a bien le compte
               int countTondeuse = 001;
               while ((currentLigne = buff_taille.readLine()) != null)
               {
                  Tondeuse tondeuse = initTondeuse(currentLigne, countTondeuse);
                  countTondeuse++;
                  currentLigne = buff_taille.readLine();
                  if (tondeuse != null)
                  {
                     // On vérifie les déplacements
                     tondeuse.checkMoves(currentLigne, pelouse);
                     System.out.println(tondeuse);
                  }
               }
            }
            else
            {
               throw new IOException("Nombre de lignes incorrects");
            }
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

   }

   /**
    * Vérifie que le format de la pelouse est bien respecté
    * 
    * @param taille_pelouse la string représentant la taille de la pelouse
    * @return true ou false si le format est respecté
    */
   public boolean verifTaillePelouse(String taille_pelouse) throws IOException
   {
      // On vérifie le format de la pelouse
      if (taille_pelouse != null && Pattern.matches("[0-9]*\\s[0-9]*", taille_pelouse))
      {
         int index = taille_pelouse.indexOf(" ");
         String str_x = taille_pelouse.substring(0, index);
         String str_y = taille_pelouse.substring(index + 1, taille_pelouse.length());
         pelouse = new Pelouse(Integer.parseInt(str_x), Integer.parseInt(str_y));
         return true;
      }
      else
      {
         throw new IOException("Format taille pelouse incorrect ==> " + taille_pelouse + " ?");
      }
   }

   /**
    * Vérifie le format d'origine de la tondeuse, initialise une nouvelle
    * tondeuse et l'ajoute à la liste des tondeuses
    * 
    * @param data la chaine correspondant à la position d'origine de la tondeuse
    * @param number la ligne dans le fichier et donc l'id correspondant à la
    *           tondeuse
    * @return l'instance de la nouvelle tondeuse crée
    */
   public Tondeuse initTondeuse(String data, int number) throws IOException
   {
      // On vérifie le format de la position d'origine de la tondeuse
      if (Pattern.matches("[0-9]*\\s[0-9]*\\s[N,E,W,S]", data))
      {
         String[] t = data.split("\\s");
         int x = Integer.parseInt(t[0]);
         int y = Integer.parseInt(t[1]);
         String orientation = t[2];
         // On vérifie que la position d'origine est bien située sur la pelouse
         if (x <= pelouse.getGrid_taille_x() && y <= pelouse.getGrid_taille_y())
         {
            Tondeuse tondeuse = new Tondeuse(number, x, y, orientation);
            this.list_tondeuses.add(tondeuse);
            return tondeuse;
         }
         else
         {
            throw new IOException("Les coordonnées " + x + " " + y + " de la tondeuse n°" + number + " sont en dehors de la pelouse");
         }
      }
      else
      {
         throw new IOException("Position d'origine de la tondeuse n° " + number + " incorrecte :" + data);
      }
   }

}
