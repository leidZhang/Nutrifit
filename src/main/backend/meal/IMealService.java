import  java.util.HashSet;
import  java.util.List;
import  java.util.Set;
import  org.springframework.stereotype.Service;
import  org.springframework.util.StringUtils;

@Service
public  class  MealService  {
     private  Map<String,  Meal>  mealMap  =  new  HashMap<>();
     private  ConnectionUtil  connectionUtil;

     public  MealService(ConnectionUtil  connectionUtil)  {
          this.connectionUtil  =  connectionUtil;
     }

     public  void  addMeal(Meal  meal)  {
         checkFoodId(meal.getFoodId());
         mealMap.put(meal.getFoodId(),  meal);
     }

     public  Meal  getMealById(String  foodId)  {
         checkFoodId(foodId);
         return  mealMap.get(foodId);
     }

     public  List<Meal>  getAllMeals()  {
         return  new  ArrayList<>(mealMap.values());
     }

     public  void  deleteMealById(String  foodId)  {
         checkFoodId(foodId);
         mealMap.remove(foodId);
     }

     public  void  updateMeal(Meal  updatedMeal)  {
         checkFoodId(updatedMeal.getFoodId());
         mealMap.put(updatedMeal.getFoodId(),  updatedMeal);
     }

     private  void  checkFoodId(String  foodId)  {
         if  (StringUtils.isEmpty(foodId))  {
              throw  new  IllegalArgumentException("Food  ID  cannot  be  empty");
         }
     }

     //  添加一个方法来获取所有营养成分
     public  List<Nutrient>  getAllNutrients()  {
         List<Meal>  meals  =  getAllMeals();
         List<Nutrient>  nutrients  =  new  ArrayList<>();
         for  (Meal  meal  :  meals)  {
             nutrients.addAll(meal.getNutrients());
         }
         return  nutrients;
     }
}

