import  java.util.List;
import  java.util.Set;

public  class  Meal  {
     private  String  foodId;
     private  List<String>  nutrientNames;
     private  Double  nutrientValue;
     private  Set<Nutrient>  nutrients;

     public  Meal(String  foodId,  List<String>  nutrientNames,  Double  nutrientValue)  {
          this.foodId  =  foodId;
          this.nutrientNames  =  nutrientNames;
          this.nutrientValue  =  nutrientValue;
          this.nutrients  =  new  HashSet<>();
     }

     public  String  getFoodId()  {
         return  foodId;
     }

     public  void  setFoodId(String  foodId)  {
          this.foodId  =  foodId;
     }

     public  List<String>  getNutrientNames()  {
         return  nutrientNames;
     }

     public  void  setNutrientNames(List<String>  nutrientNames)  {
          this.nutrientNames  =  nutrientNames;
     }

     public  Double  getNutrientValue()  {
         return  nutrientValue;
     }

     public  void  setNutrientValue(Double  nutrientValue)  {
          this.nutrientValue  =  nutrientValue;
     }

     public  Set<Nutrient>  getNutrients()  {
         return  nutrients;
     }

     public  void  setNutrients(Set<Nutrient>  nutrients)  {
          this.nutrients  =  nutrients;
     }

     public  void  addNutrient(Nutrient  nutrient)  {
         nutrients.add(nutrient);
     }
}
