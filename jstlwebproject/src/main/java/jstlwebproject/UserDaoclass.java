package jstlwebproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoclass {
	private String jdbcURL="jdbc:mysql://localhost:3306/web";
	private String jdbcUserName="root";
	private String jdbcPassword="root";
	private static final String INSERT_USER_SQL="INSERT INTO register" +" (name,email,country)values "+"(?,?,?)";
	private static final String SELECT_USER_BY_ID="select id,name,email,country from register where id=?";
	private static final String SELECT_aLL_USERS="select * from register";
	private static final String DELETE_USER_SQL="delete from register where id=?";
	private static final String UPDATE_USER_SQL="update register set name=? ,email=?,country=? where id=?";
	public UserDaoclass()
	{
	
	}
	protected Connection getConnection()
	{
		Connection connection=null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return connection;
	}
	public void insertUser(User user)
	{
		try {
			Connection connection=getConnection();
			PreparedStatement ps=connection.prepareStatement(INSERT_USER_SQL);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCountry());
			ps.executeUpdate();
			System.out.println("Insert");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public User selectUser(int id)
	{
		User user=null;
		try
		{
		Connection connection=getConnection();
		PreparedStatement ps=connection.prepareStatement(SELECT_USER_BY_ID);
		ps.setInt(1,id );
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			String name=rs.getString("name");
			String email=rs.getString("email");
			String country=rs.getString("country");
			 user = new User(id,name,email,country);
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	public List<User> selectAllUsers()
	{
		List<User> list = new ArrayList<>();
		try
		{
			Connection connection=getConnection();
			PreparedStatement ps=connection.prepareStatement(SELECT_aLL_USERS);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String country=rs.getString("country");
				list.add(new User(id,name,email,country));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public boolean deleteUser(int id)throws SQLException
	{
		boolean rowDeleted=false;
		try {
		Connection connection=getConnection();
		PreparedStatement ps=connection.prepareStatement(DELETE_USER_SQL);
		ps.setInt(1, id);
		rowDeleted=ps.executeUpdate()>0;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rowDeleted;
	}
	public boolean updateUser(User user)throws SQLException
	{
		boolean rowUpdated=false;
		try {
			Connection connection=getConnection();
			PreparedStatement ps=connection.prepareStatement(UPDATE_USER_SQL);
			ps.setString(1,user.getName());
			ps.setString(2,user.getEmail());
			ps.setString(3,user.getCountry());
			ps.setInt(4, user.getId());
			rowUpdated=ps.executeUpdate()>0;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rowUpdated;
		
	}
	public void printSQLException (SQLException ex)
	{
		for(Throwable e:ex)
		{
			if(e instanceof SQLException)
			{
				e.printStackTrace(System.err);
				System.err.println("SQL State :"+((SQLException)e).getSQLState());
				System.err.println("Error code:"+((SQLException)e).getErrorCode());
				System.err.println("Message:"+((SQLException)e).getMessage());
				Throwable t = ex.getCause();
				while(t!=null)
				{
					System.out.println("Cause"+t);
					t=t.getCause();
				}
			}
		}
	}
	
}
