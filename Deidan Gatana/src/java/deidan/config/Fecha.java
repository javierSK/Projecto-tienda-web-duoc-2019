/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deidan.config;

import java.util.Calendar;
import java.text.SimpleDateFormat;
/**
 *
 * @author bryan
 */
public class Fecha {

  public static Calendar calendar= Calendar.getInstance();
  private static String fecha;
  
  public Fecha(){
      
  }
    
  public  static String Fecha(){
      SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
      fecha= sdf.format(calendar.getTime());
      return fecha;
  }
  
  public static String FechaBD(){
      SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
      fecha=sdf.format(calendar.getTime());
      return fecha;
  }
  
  
}
