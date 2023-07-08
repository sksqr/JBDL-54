package com.example.L07jdbcdemo;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {

    public Product getById(Integer id){
        Connection connection = null;
        Product product =null;
        try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_system", "user2", "Pass@123");

            Statement statement = connection.createStatement();
            String sql = "Select * from product where id="+id;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                product = new Product(resultSet.getInt("id"),resultSet.getString("name"));
                break;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
        finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return product;
    }

    public List<Product> getAllProducts(){
        Connection connection = null;
        List<Product> productList =new ArrayList<>();
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_system", "user2", "Pass@123");

            Statement statement = connection.createStatement();
            String sql = "Select * from product";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Product product = new Product(resultSet.getInt("id"),resultSet.getString("name"));
                productList.add(product);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
        finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return productList;
    }

}
