<%@page import="java.util.Map"%>
<%@page import="online_hotel_management.dto.Hotel"%>
<%@page import="java.util.HashMap"%>
<%@page import="online_hotel_management.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="myBooking.css">
</head>
<body>
	
	<%
Customer customer = (Customer) session.getAttribute("customersession");
%>
<% HashMap<Hotel,Integer> bookedHotels=(HashMap)session.getAttribute("bookedhotels"); %>

	<% for(Map.Entry<Hotel, Integer> entry : bookedHotels.entrySet()){ %>
	  
<section id="popular-product">
		
		<div class="product-container">
			
			<div class="product-box">

				<div class="container">

					<div class="rightbox">

						<div class="Payment tabShow">
							<h1>My Bookings</h1>
							<h2>Hotel Name</h2>
							<input type="text" class="input"
								placeholder="<%=entry.getKey().getHotelName() %>">
							<h2>Phone</h2>
							<input type="text" class="input" placeholder="<%=entry.getKey().getHotelPhone() %>">
							<h2>Email</h2>
							<input type="number" class="input" placeholder="<%=entry.getKey().getHotelEmail() %>">
							<h2>Room price</h2>
							<input type="number" class="input" placeholder="<%=entry.getKey().getRoomPrice() %>">
							
							
							<form action="cancellingservlet" method="post">
                    <input type="hidden" name="hotelId" value="<%= entry.getKey().getHotelId() %>">
                    <input type="hidden" name="customerId" value="<%= customer.getCustomerId() %>">
                    <h2>No of rooms</h2>
                    <input type="number" name="noOfRooms" placeholder="Enter no of rooms to cancel">
                    <button type="submit" value="CancelBooking">Cancel</button>
                    

                </form>
                <button>Home</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
   
	 <% } %>
</body>
</html>


