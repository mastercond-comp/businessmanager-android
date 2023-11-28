package ru.mastercond;

import java.util.ArrayList;
import java.util.List;


//НЕОБХОДИМО УВЕЛИЧИТЬ КОЛИЧЕСТВО ПЕРЕМЕННЫХ С УЧЕТОМ ВЫГРУЗКИ НА САЙТ

public class TOVARIUSLUGI {

   private String Name;
   private String Type;
   private String Price;
   private String Valuta;
   private String Kolvo;
   private String EdIzm;
   private String Stoimost;
   private String idnumber;
 
  public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber) {
  this.Name=Name;
  this.Type=Type;
  this.Price=Price;
  this.Valuta=Valuta;
  this.Kolvo=Kolvo;
  this.EdIzm=EdIzm;
  this.Stoimost=Stoimost;
  this.idnumber=idnumber;
   }
 
   public String getName() {
       return Name;
   }
   
   public void setName(String Name) {
       this.Name = Name;
   }
   
   
   public String getType() {
       return Type;
   }
   
   public void setType(String Type) {
       this.Type = Type;
   }
   
   public String getPrice() {
       return Price;
   }
   
   public void setPrice(String Price) {
       this.Price = Price;
   }
   
   public String getValuta() {
       return Valuta;
   }
   
   public void setValuta(String Valuta) {
       this.Valuta = Valuta;
   }
   
   public String getKolvo() {
       return Kolvo;
   }
   
   public void setKolvo(String Kolvo) {
       this.Kolvo = Kolvo;
   }
   
   public String getStoimost() {
       return Stoimost;
   }
   
   public void setEdIzm(String Stoimost) {
       this.EdIzm = EdIzm;
   }
   
   public String getEdIzm() {
       return EdIzm;
   }
   
   public void setStoimost(String Stoimost) {
       this.Stoimost = Stoimost;
   }
   
   public String getidnumber() {
       return idnumber;
   }

}
