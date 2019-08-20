package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.DatabaseContext;
import model.User;

public class UserDAO {
	
	//get all users in data
	public ArrayList<User> getAllUsers() throws Exception{
		ArrayList<User> list = new ArrayList<>();
		String select = "SELECT * FROM users";
		Connection conn = new DatabaseContext().getConnection();
		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		String username, password;
		while(rs.next()) {
			username = rs.getString("username");
			password = rs.getString("userpassword");
			User user = new User(username, password);
			list.add(user);
		}
		rs.close();
		ps.close();
		conn.close();
		return list;
	}
	
	//add new user to data
	public void addUser(User user) throws Exception {
		String insert = "INSERT INTO users VALUE(?,?)";
		Connection conn = new DatabaseContext().getConnection();
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
}
