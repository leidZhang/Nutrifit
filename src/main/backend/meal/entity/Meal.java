import  java.util.HashMap;
import  java.util.Map;

public  class  Meal  {
     private  int  id;
     private  Date  date;
     private  String  type;
     private  long  totalCalories;
     private  Map<String,  Integer>  totalNutritions;

     public  Meal(int  id,  Date  date,  String  type,  long  totalCalories,  Map<String,  Integer>  totalNutritions)  {
          this.id  =  id;
          this.date  =  date;
          this.type  =  type;
          this.totalCalories  =  totalCalories;
          this.totalNutritions  =  totalNutritions;
     }

     public  int  getId()  {
         return  id;
     }

     public  void  setId(int  id)  {
          this.id  =  id;
     }

     public  Date  getDate()  {
         return  date;
     }

     public  void  setDate(Date  date)  {
          this.date  =  date;
     }

     public  String  getType()  {
         return  type;
     }

     public  void  setType(String  type)  {
          this.type  =  type;
     }

     public  long  getTotalCalories()  {
         return  totalCalories;
     }

     public  void  setTotalCalories(long  totalCalories)  {
          this.totalCalories  =  totalCalories;
     }

     public  Map<String,  Integer>  getTotalNutritions()  {
         return  totalNutritions;
     }

     public  void  setTotalNutritions(Map<String,  Integer>  totalNutritions)  {
          this.totalNutritions  =  totalNutritions;
     }

     public  void  addNutrition(String  key,  Integer  value)  {
          totalNutritions.put(key,  totalNutritions.getOrDefault(key,  0)  +  value);
     }

     @Override
     public  String  toString()  {
         return  "Meal{"  +
                 "id="  +  id  +
                 ",  date="  +  date  +
                 ",  type='"  +  type  +  '\''  +
                 ",  totalCalories="  +  totalCalories  +
                 ",  totalNutritions="  +  totalNutritions  +
                 '}';
     }
}
