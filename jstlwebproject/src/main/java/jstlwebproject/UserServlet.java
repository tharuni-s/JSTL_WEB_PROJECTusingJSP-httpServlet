package jstlwebproject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/")
public class UserServlet extends HttpServlet{
	private static final long serialVersionUID=1L;
	private UserDaoclass userDao;
	public  UserServlet()
	{
		this.userDao=new UserDaoclass();
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		doGet(request,response);
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		String action = request.getServletPath();
		try {
			switch(action)
			{
			case "/new":
				showNewForm(request,response);
				break;
			case "/insert":
				insertUser(request,response);
				break;
			case "/delete":
				deleteUser(request,response);
				break;
			case "/edit":
				showEditForm(request,response);
				break;
			case "/update":
				updateUser(request,response);
				break;
			default:
				listUser(request,response);
				break;
			}
			
		}
		catch(SQLException ex)
		{
			throw new ServletException(ex);
		}
	}
	private void listUser(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException,SQLException
	{
		List<User> listUser=userDao.selectAllUsers();
		request.setAttribute("a", listUser);
		RequestDispatcher rd=request.getRequestDispatcher("user-list.jsp");
		rd.forward(request, response);
	}
	private void showNewForm(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
    {
    	RequestDispatcher rd=request.getRequestDispatcher("user-form.jsp");
		rd.forward(request, response);
    }
    private void  showEditForm(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException,SQLException
    {
    	int id=Integer.parseInt(request.getParameter("id"));
    	User existingUser=userDao.selectUser(id);
    	RequestDispatcher rd=request.getRequestDispatcher("user-form.jsp");
    	request.setAttribute("user", existingUser);
		rd.forward(request, response);
    }
    private void insertUser(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException,SQLException
    {
    	String name=request.getParameter("name");
    	String email=request.getParameter("email");
    	String country=request.getParameter("country");
    	User newUser =new User(name,email,country);
    	userDao.insertUser(newUser);
    	response.sendRedirect("list");
    }
    private void updateUser(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException,SQLException
    {
    	int id=Integer.parseInt(request.getParameter("id"));
    	String name=request.getParameter("name");
    	String email=request.getParameter("email");
    	String country=request.getParameter("country");
       	User newUser=new User(id,name,email,country);
       	userDao.updateUser(newUser);
       	response.sendRedirect("list");
    }
    private void deleteUser(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException,SQLException
    {
    	int id=Integer.parseInt(request.getParameter("id"));
    	userDao.deleteUser(id);
    	response.sendRedirect("list");
    	
    }
    
   }

