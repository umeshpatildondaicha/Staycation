<%@page import="online_hotel_management.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Profile Page</title>
    <link rel="stylesheet" href="Profile CSS.css">
    <script src="https://kit.fontawesome.com/cacf32ff12.js" crossorigin="anonymous"></script>
    <link rel="shortcut icon" type="image/jpg" href="C:\Users\hp\Desktop\College\First Semester\Introduction To Web Technologies\Notepad ++ Files\Project\favicon.ico"/>
</head>
<body>


<%Customer customer = (Customer)session.getAttribute("customersession"); %>
<%Customer updatedCustomer=(Customer)session.getAttribute("customerupdate"); %>
<%if(updatedCustomer!=null){ 
customer=updatedCustomer;}
%>
    <div class="container">
        <div class="leftbox">
            <nav>
                <a onclick="tabs(0)" class="tab active">
                    <i class="fas fa-user"></i>
                </a>
                <a onclick="tabs(1)" class="tab">
                    <i class="far fa-credit-card"></i>
                </a>
            
            </nav>
        </div>
        <div class="rightbox">
            <div class="profile tabShow">
            <form action="customerupdate" method="post">
                <h1>Personal Information</h1>
                <h2>Name</h2>
                <input type="hidden" name="id" value=<%=customer.getCustomerId() %> >
                <input type="text" name="name" value=<%=customer.getCustomerName()%> disabled >
                <h2>Mobile Number</h2>
                <input type="tel" name="phone" value=<%=customer.getCustomerPhone()%> disabled >
                <h2>Email</h2>
                <input type="email" name="email" value=<%=customer.getCustomerEmail()%> disabled>
                <h2>Password</h2>
                <input type="text" name="password" value=<%=customer.getCustomerPassword()%> required="required">
                <h2>Address</h2>
               <input type="text" name="address" value=<%=customer.getAddress()%> required="required">
                <button class="btn">
                    update
                </button>  <button class="btn">
                    Home
                </button>
                </form>
            </div>
            <div class="Payment tabShow">
                <h1>My Booking</h1>
                <h2>Hotel Name</h2>
                <input type="text" class="input" placeholder="402, karvenagar 19, pune, Maharashtra">
                <h2>Location</h2>
                <input type="text" class="input" placeholder="190125">
                <h2>No. Of Rooms</h2>
                <input type="number" class="input" placeholder="enter gift code" >
                <h2> Amount</h2>
                <input type="number" class="input" placeholder="December 26, 2023">
                <h2> Total Amount</h2>
                <input type="number" class="input" placeholder="December 26, 2023">
                <br>
                <button class="btn">
                    Home
                </button>
            </div>
            
            </div>

        </div>

    <script src="Profile JS.js"></script>

</body>
</html>
