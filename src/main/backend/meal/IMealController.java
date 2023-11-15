import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.http.HttpStatus;
import  org.springframework.http.ResponseEntity;
import  org.springframework.web.bind.annotation.*;

import  java.sql.Connection;
import  java.sql.SQLException;
import  java.util.List;

@RestController
@RequestMapping("/api/meals")
public  class  MealController  {

     @Autowired
     private  MealService  mealService;

     @Autowired
     private  ConnectionUtil  connectionUtil;


     @Override
     public  ResponseEntity<Meal>  getMealById(int  id)  {
          try  (Connection  connection  =  connectionUtil.getConnection())  {
             Meal  meal  =  mealService.getById(id,  connection);
             return  ResponseEntity.ok(meal);
         }  catch  (SQLException  e)  {
             return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new  ApiErrorResponse(e.getMessage()));
         }
     }

     @Override
     public  ResponseEntity<List<Meal>>  getMealsByDate(Date  date)  {
          try  (Connection  connection  =  connectionUtil.getConnection())  {
             List<Meal>  meals  =  mealService.getByDate(date,  connection);
             return  ResponseEntity.ok(meals);
         }  catch  (SQLException  e)  {
             return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new  ApiErrorResponse(e.getMessage()));
         }
     }

     @Override
     public  ResponseEntity<Meal>  createMeal(Meal  meal)  {
          try  (Connection  connection  =  connectionUtil.getConnection())  {
             Meal  savedMeal  =  mealService.createMeal(meal,  connection);
             return  ResponseEntity.ok(savedMeal);
         }  catch  (SQLException  e)  {
             return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new  ApiErrorResponse(e.getMessage()));
         }
     }

     @Override
     public  ResponseEntity<Meal>  updateMeal(Meal  meal)  {
          try  (Connection  connection  =  connectionUtil.getConnection())  {
             Meal  updatedMeal  =  mealService.updateMeal(meal,  connection);
             return  ResponseEntity.ok(updatedMeal);
         }  catch  (SQLException  e)  {
             return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new  ApiErrorResponse(e.getMessage()));
         }
     }

     @Override
     public  ResponseEntity<Void>  deleteMeal(int  id)  {
          try  (Connection  connection  =  connectionUtil.getConnection())  {
             mealService.deleteMeal(id,  connection);
             return  ResponseEntity.noContent().build();
         }  catch  (SQLException  e)  {
             return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new  ApiErrorResponse(e.getMessage()));
         }
     }
}
