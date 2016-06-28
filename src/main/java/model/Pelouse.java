package model;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Pelouse extends JPanel
{
   private int                 grid_taille_x;
   private int                 grid_taille_y;
   private ArrayList<Tondeuse> list_tondeuses;

   public Pelouse(int x, int y)
   {
      grid_taille_x = x;
      grid_taille_y = y;
      list_tondeuses = new ArrayList<Tondeuse>();
   }

   public int getGrid_taille_x()
   {
      return grid_taille_x;
   }

   public void setGrid_taille_x(int grid_taille_x)
   {
      this.grid_taille_x = grid_taille_x;
   }

   public int getGrid_taille_y()
   {
      return grid_taille_y;
   }

   public void setGrid_taille_y(int grid_taille_y)
   {
      this.grid_taille_y = grid_taille_y;
   }

   public ArrayList<Tondeuse> getList_tondeuses()
   {
      return list_tondeuses;
   }

   public void setList_tondeuses(ArrayList<Tondeuse> list_tondeuses)
   {
      this.list_tondeuses = list_tondeuses;
   }

   public Tondeuse getTondeuseAt(int numero)
   {
      return list_tondeuses.get(numero);
   }

   public void addTondeuse(Tondeuse tondeuse)
   {
      this.list_tondeuses.add(tondeuse);
   }

}
