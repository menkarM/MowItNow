package model;

import java.io.IOException;
import java.util.regex.Pattern;

public class Tondeuse
{

   private int    id_tondeuse;
   private int    x;
   private int    y;
   private String orientation;

   public Tondeuse(int number, int x, int y, String orientation)
   {
      this.id_tondeuse = number;
      this.x = x;
      this.y = y;
      this.orientation = orientation;
   }

   public int getId_tondeuse()
   {
      return id_tondeuse;
   }

   public void setId_tondeuse(int id_tondeuse)
   {
      this.id_tondeuse = id_tondeuse;
   }

   public int getX()
   {
      return x;
   }

   public void setX(int x)
   {
      this.x = x;
   }

   public int getY()
   {
      return y;
   }

   public void setY(int y)
   {
      this.y = y;
   }

   public String getOrientation()
   {
      return orientation;
   }

   public void setOrientation(String orientation)
   {
      this.orientation = orientation;
   }

   public String toString()
   {
      return x + " " + y + " " + orientation;
   }

   public void move_tondeuse(int x, int y, String orientation)
   {
      this.x = x;
      this.y = y;
      this.orientation = orientation;
   }

   /**
    * Vérifie le format des moves de la tondeuse, et les executes si ils sont
    * valides. Si le move est faux on ne fait rien
    * 
    * @param moves la chaine correspondant à tous les moves de la tondeuse
    * @param pelouse la pelouse
    */
   public void checkMoves(String moves, Pelouse pelouse) throws IOException
   {
      if (Pattern.matches("[A,G,D]*", moves))
      {
         for (int i = 0; i < moves.length(); i++)
         {
            String currentMove = moves.substring(i, i + 1);
            if ("N".equals(orientation))
            {
               if ("A".equals(currentMove)) y = (y < pelouse.getGrid_taille_y()) ? y + 1 : y;
               else if ("G".equals(currentMove)) orientation = "W";
               else if ("D".equals(currentMove)) orientation = "E";
            }
            else if ("E".equals(orientation))
            {
               if ("A".equals(currentMove)) x = (x < pelouse.getGrid_taille_x()) ? x + 1 : x;
               else if ("G".equals(currentMove)) orientation = "N";
               else if ("D".equals(currentMove)) orientation = "S";
            }
            else if ("W".equals(orientation))
            {
               if ("A".equals(currentMove)) x = (x > 0) ? x - 1 : x;
               else if ("G".equals(currentMove)) orientation = "S";
               else if ("D".equals(currentMove)) orientation = "N";
            }
            else if ("S".equals(orientation))
            {
               if ("A".equals(currentMove)) y = (y > 0) ? y - 1 : y;
               else if ("G".equals(currentMove)) orientation = "E";
               else if ("D".equals(currentMove)) orientation = "W";
            }
         }
      }
      else
      {
         throw new IOException("Problème format des moves");
      }
   }
}
