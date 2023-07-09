package com.example.L08jdbcdemo;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {


    public Product createProductWithPSWithTxn(Product product){
        Connection con = null;
        boolean autocommit = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_system", "user1", "Pass@123");

            autocommit = con.getAutoCommit();
            con.setAutoCommit(false);
            String insertStm = "insert into product values (null,?,?)";
            try (
                    PreparedStatement preparedStatement = con.prepareStatement(insertStm,Statement.RETURN_GENERATED_KEYS);
            )
            {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setDouble(2,product.getPrice());
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        product.setId(generatedKeys.getInt(1));
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {

            if(con != null){
                try {
                    con.setAutoCommit(autocommit);
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return product;
    }

    public Product createProductWithPS(Product product){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_system", "user1", "Pass@123");

            String insertStm = "insert into product values (null,?,?)";
            try (
                PreparedStatement preparedStatement = con.prepareStatement(insertStm,Statement.RETURN_GENERATED_KEYS);
            )
            {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setDouble(2,product.getPrice());
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        product.setId(generatedKeys.getInt(1));
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return product;
    }

    public Product createProduct(Product product){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_system", "user1", "Pass@123");
            Statement statement = con.createStatement();
            String insertStm = "insert into product values (null,'"+product.getName()+"',"+product.getPrice()+")";
            int affectedRows = statement.executeUpdate(insertStm, Statement.RETURN_GENERATED_KEYS);
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return product;
    }


    public Product getByIdWithPS(Integer id){
        Connection connection = null;
        Product product =null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_system", "user2", "Pass@123");
            String sql = "Select id, name, price from product where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                product = new Product(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getDouble("price"));
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

    public Product getById(Integer id){
        Connection connection = null;
        Product product =null;
        try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_system", "user2", "Pass@123");

            Statement statement = connection.createStatement();
            String sql = "Select * from product where id="+id;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                product = new Product(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getDouble("price"));
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
                Product product = new Product(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getDouble("price"));
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
