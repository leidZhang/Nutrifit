import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;
import  java.sql.Connection;
import  java.sql.PreparedStatement;
import  java.sql.ResultSet;
import  java.sql.SQLException;
import  java.util.ArrayList;
import  java.util.List;
import  org.springframework.util.StringUtils;

@Service
public  class  MealService  {

     @Autowired
     private  ConnectionUtil  connectionUtil;

     @Autowired
     private  SqlUtil  sqlUtil;

     public  Meal  getById(int  id,  Connection  connection)  throws  SQLException  {
         String  query  =  "SELECT  *  FROM  meals  WHERE  id  =  ?";
         PreparedStatement  preparedStatement  =  connection.prepareStatement(query);
         preparedStatement.setInt(1,  id);
         ResultSet  resultSet  =  preparedStatement.executeQuery();
         if  (resultSet.next())  {
             Meal  meal  =  new  Meal();
             meal.setId(resultSet.getInt("id"));
             meal.setName(resultSet.getString("name"));
             meal.setDescription(resultSet.getString("description"));
             meal.setDate(resultSet.getDate("date"));
             return  meal;
         }
         return  null;
     }

     public  List<Meal>  getByDate(Date  date,  Connection  connection)  throws  SQLException  {
         String  query  =  "SELECT  *  FROM  meals  WHERE  date  =  ?";
         PreparedStatement  preparedStatement  =  connection.prepareStatement(query);
         preparedStatement.setDate(1,  java.sql.Date.valueOf(date));
         ResultSet  resultSet  =  preparedStatement.executeQuery();
         List<Meal>  meals  =  new  ArrayList<>();
         while  (resultSet.next())  {
             Meal  meal  =  new  Meal();
             meal.setId(resultSet.getInt("id"));
             meal.setName(resultSet.getString("name"));
             meal.setDescription(resultSet.getString("description"));
             meal.setDate(resultSet.getDate("date"));
             meals.add(meal);
         }
         return  meals;
     }

     public  Meal  createMeal(Meal  meal,  Connection  connection)  throws  SQLException  {
         String  query  =  "INSERT  INTO  meals  (name,  description,  date)  VALUES  (?,  ?,  ?)";
         PreparedStatement  preparedStatement  =  connection.prepareStatement(query);
         preparedStatement.setString(1,  meal.getName());
         preparedStatement.setString(2,  meal.getDescription());
         preparedStatement.setDate(3,  java.sql.Date.valueOf(meal.getDate()));
         int  rowsAffected  =  preparedStatement.executeUpdate();
         if  (rowsAffected  >  0)  {
             meal.setId(sqlUtil.getLastInsertId(connection));
         }
         return  meal;
     }

     public  Meal  updateMeal(Meal  meal,  Connection  connection)  throws  SQLException  {
         String  query  =  "UPDATE  meals  SET  name  =  ?,  description  =  ?,  date  =  ?  WHERE  id  =  ?";
         PreparedStatement  preparedStatement  =  connection.prepareStatement(query);
         preparedStatement.setString(1,  meal.getName());
         preparedStatement.setString(2,  meal.getDescription());
         preparedStatement.setDate(3,  java.sql.Date.valueOf(meal.getDate()));
         preparedStatement.setInt(4,  meal.getId());
         int  rowsAffected  =  preparedStatement.executeUpdate();
         if  (rowsAffected  >  0)  {
             return  meal;
         }
         return  null;
     }

     public  void  deleteMeal(int  id,  Connection  connection)  throws  SQLException  {
         String  query  =  "DELETE  FROM  meals  WHERE  id  =  ?";
         PreparedStatement  preparedStatement  =  connection.prepareStatement(query);
         preparedStatement.setInt(1,  id);
         int  rowsAffected  =  preparedStatement.executeUpdate();
     }
	
     public  List<Meal>  getAllMeals(Connection  connection)  throws  SQLException  {
         String  query  =  "SELECT  *  FROM  meals";
         PreparedStatement  preparedStatement  =  connection.prepareStatement(query);
         ResultSet  resultSet  =  preparedStatement.executeQuery();
         List<Meal>  meals  =  new  ArrayList<>();
         while  (resultSet.next())  {
         Meal  meal  =  new  Meal();
         meal.setId(resultSet.getInt("id"));
         meal.setName(resultSet.getString("name"));
         meal.setDescription(resultSet.getString("description"));
         meal.setDate(resultSet.getDate("date"));
         meals.add(meal);
         }
         return  meals;
     }
}
