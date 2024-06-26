package online_hotel_management.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online_hotel_management.dao.CustomerDAO;
import online_hotel_management.dto.Customer;

@WebServlet("/customersignup")
public class CustomerSignupController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String customerName=req.getParameter("customer_name");
		long customerPhone=Long.parseLong(req.getParameter("customer_phone"));
		String customerEmail=req.getParameter("customer_email");
		String customerAddress=req.getParameter("address");
		String customerPassword=req.getParameter("customer_password");
		long customerAadhar=Long.parseLong(req.getParameter("customer_aadhar"));
	
		Customer customer=new Customer();
		customer.setCustomerName(customerName);
		customer.setCustomerPassword(customerPassword);
		customer.setAddress(customerAddress);
		customer.setCustomerAadhar(customerAadhar);
		customer.setCustomerEmail(customerEmail);
		customer.setCustomerPhone(customerPhone);
		
		CustomerDAO dao= new CustomerDAO();
		dao.saveCustomer(customer);
		
		RequestDispatcher dispatcher=req.getRequestDispatcher("CustomerRegisterLogin.jsp");
		dispatcher.forward(req, resp);
	}
}