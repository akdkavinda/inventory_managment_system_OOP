package Serverlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import exception.ValidateException;
import modals.*;

/**
 * Servlet implementation class customers_Adddata
 */
@WebServlet("/customers_addata")
public class Customers_Addata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customers_Addata() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				HttpSession session = request.getSession(true);
				
				Database c = Database.getInstance();

				Customers cu = new Customers();

				String n = request.getParameter("name");
				String a = request.getParameter("address");
				String e = request.getParameter("email");
				int co = Integer.parseInt(request.getParameter("contact"));
				
				
				cu.setName(n);
				cu.setAddress(a);
				cu.setEmail(e);
				cu.setContact(co);
				
				try {
					cu.validate();
				} catch (ValidateException e1) {
					session.setAttribute("result", e1.getValidateException());
					
					response.sendRedirect("/Inventory/add/customers.jsp");
					return;
				}
					String query = cu.createquery();
	
					String result = c.adddata(query);
					
					session.setAttribute("result", result);
					
					//response.getWriter().append(result);
					
					response.sendRedirect("/Inventory/list/customers.jsp");
			}
}
