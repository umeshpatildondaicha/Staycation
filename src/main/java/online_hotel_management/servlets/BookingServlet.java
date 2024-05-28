package online_hotel_management.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online_hotel_management.dao.CustomerDAO;

@WebServlet("/bookingservlet")
public class BookingServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int hotelId=Integer.parseInt(req.getParameter("hotelId"));
		int customerId=Integer.parseInt(req.getParameter("customerId"));
		int noOfRooms=Integer.parseInt(req.getParameter("noOfRooms"));
		
		CustomerDAO dao=new CustomerDAO();
		dao.bookRoom(hotelId, customerId, noOfRooms);
//		RequestDispatcher dispatcher=req.getRequestDispatcher("BookHotel.jsp");
//		dispatcher.forward(req, resp);
		
		
	}

}
