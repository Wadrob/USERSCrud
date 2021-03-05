package pl.coderslab.program;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username=?, password=?, email=? WHERE id = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String DELETE_ALL_USERS = "DELETE FROM users";

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        User user = new User();
    }

    public User create(User user){
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, user.username);
            preparedStatement.setString(2, user.email);
            String haspassword = hashPassword(user.password);
            preparedStatement.setString(3, haspassword);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                long id = rs.getLong(1);
                System.out.println("Dodano do bazy z id: " + id);
            }
            return user;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public User read (int userId) {
        User user = new User();
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(READ_USER_QUERY)) {
            String id = Integer.toString(userId);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user.id = rs.getInt("id");
                user.email = rs.getString("email");
                user.username = rs.getString("username");
                user.password = rs.getString("password");
            }
            return user;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void update (User user){
        try ( Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY)){
            preparedStatement.setString(1, user.username);
            preparedStatement.setString(2, user.password);
            preparedStatement.setString(3, user.email);
            preparedStatement.setString(4, Integer.toString(user.id));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User [] findall(){
        User [] arr = new User[0];
        try (Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setId(rs.getInt("id"));
                arr = biggerarr(arr,user);
            }
            return arr;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public User [] biggerarr (User [] arr, User user){
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = user;
        return arr;
    }

    public void delete (int userId){
        try ( Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY)){
            preparedStatement.setString(1, Integer.toString(userId));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteAll (){
        try ( Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_USERS);
        PreparedStatement preparedStatement1 = connection.prepareStatement("ALTER TABLE users AUTO_INCREMENT = ?")){
            preparedStatement1.setString(1, Integer.toString(1));
            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String hashPassword (String password){
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

}