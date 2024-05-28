package online_hotel_management.dao;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import online_hotel_management.dto.Customer;
import online_hotel_management.dto.Hotel;

public class CustomerDAO {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("onkar");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public void saveCustomer(Customer customer) {
		entityTransaction.begin();

		entityManager.persist(customer);
		entityTransaction.commit();
	}

	public String loginCustomer(String customerEmail, String password) {

		Query query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.customerEmail=?1");
		query.setParameter(1, customerEmail);

		Customer customer = (Customer) query.getSingleResult();

		if (customer != null) {
			if (customer.getCustomerPassword().equals(password)) {

				return "Login Success";

			} else {
				return "Invalid Password";
			}

		} else {

			return "Customer is not Registered!";

		}

	}

	public void findById(int id) {
		Customer customer = entityManager.find(Customer.class, id);
		if (customer != null) {
			System.out.println(customer);
		} else {
			System.out.println("Customer with id " + id + "not found");
		}
	}
	
	public Customer findByEmail(String customerEmail) {
		Query query=entityManager.createQuery("SELECT c FROM Customer c WHERE c.customerEmail=?1");
		query.setParameter(1, customerEmail);
		Customer customer=(Customer) query.getSingleResult();
		return customer;
	}

	public void deleteCustomer(int id) {
		Customer customer = entityManager.find(Customer.class, id);
		if (customer != null) {
			entityTransaction.begin();
			entityManager.remove(customer);
			entityTransaction.commit();
		} else {
			System.out.println("Id not Found");
		}
	}

	public void updateCustomer(int id, Customer customer) {
		Customer dbCustomer = entityManager.find(Customer.class, id);

		if (dbCustomer != null) {

			dbCustomer.setCustomerPassword(customer.getCustomerPassword());
			dbCustomer.setAddress(customer.getAddress());

			entityTransaction.begin();
			entityManager.merge(dbCustomer);
			entityTransaction.commit();

		} else {

			System.out.println("Id not found!");

		}

	}

	public void updatePhone(int customerId, long oldPhone, long newPhone, Customer customer) {
		Customer dbCustomer = entityManager.find(Customer.class, customerId);

		if (dbCustomer != null) {
			long dbPhone = customer.getCustomerPhone();
			if (dbPhone == oldPhone) {
				customer.setCustomerId(customerId);

				customer.setCustomerPhone(newPhone);
				entityTransaction.begin();
				entityManager.merge(customer);
				entityTransaction.commit();

			} else {
				System.out.println("Wrong Phone Number");

			}
		} else {

			System.out.println("Id not found!");

		}

	}

	public void updateAddress(int customerId, Customer customer, String address) {
		Customer dbCustomer = entityManager.find(Customer.class, customerId);

		if (dbCustomer != null) {
			if (dbCustomer.getCustomerId() == customerId) {
				customer.setCustomerId(customerId);

				customer.setAddress(address);
				entityTransaction.begin();
				entityManager.merge(customer);
				entityTransaction.commit();

			} else {
				System.out.println("Wrong Phone Number");

			}
		} else {

			System.out.println("Id not found!");

		}

	}

	public void updatePassword(int customerId, String oldPass, String newPass, Customer customer) {
		Customer dbCustomer = entityManager.find(Customer.class, customerId);

		if (dbCustomer != null) {
			String dbPhone = customer.getCustomerPassword();
			if (dbPhone == oldPass) {
				customer.setCustomerId(customerId);

				customer.setCustomerPassword(newPass);
				entityTransaction.begin();
				entityManager.merge(customer);
				entityTransaction.commit();

			} else {
				System.out.println("Wrong Pass Number");

			}
		} else {

			System.out.println("Id not found!");

		}

	}

	public Customer bookRoom(int hotelId, int customerId, int noOfRooms) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Hotel hotel = entityManager.find(Hotel.class, hotelId);
		Customer customer = entityManager.find(Customer.class, customerId);

		if (hotel != null && customer != null) {
			if (noOfRooms <= hotel.getAvailRoom()) {
				hotel.setHotelId(hotelId);
				hotel.setAvailRoom(hotel.getAvailRoom() - noOfRooms);

				HashMap<Integer, Integer> dbMap = customer.getBookedRoom();

				if (dbMap != null) {
					dbMap.put(hotelId, noOfRooms);
					customer.setBookedRoom(dbMap);
				} else {
					HashMap<Integer, Integer> booked = new HashMap<Integer, Integer>();
					booked.put(hotelId, noOfRooms);
					customer.setBookedRoom(booked);
				}
				entityTransaction.begin();
				entityManager.merge(hotel);
				entityManager.merge(customer);
				entityTransaction.commit();
				return customer;
			} else {
				System.out.println(noOfRooms + " rooms not available!");
				return customer;
			}
		} else {
			System.out.println("Please Enter Correct HotelId and CustomerId!");
			return null;
		}
	}

	public Customer cancelRoom(int hotelId, int customerId, int noofRooms) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Hotel hotel = entityManager.find(Hotel.class, hotelId);
		Customer customer = entityManager.find(Customer.class, customerId);

		if (hotel != null && customer != null) {
			HashMap<Integer, Integer> map = customer.getBookedRoom();
			int dbRoom = map.get(hotelId);

			if (dbRoom == noofRooms) {
				map.remove(hotelId);
				customer.setBookedRoom(map);
				hotel.setAvailRoom(hotel.getAvailRoom() + noofRooms);
				entityTransaction.begin();
				entityManager.merge(customer);
				entityManager.merge(hotel);
				entityTransaction.commit();
				return customer;
			} else if (dbRoom > noofRooms) {
				int newr = dbRoom - noofRooms;
				map.replace(hotelId, newr);
				customer.setBookedRoom(map);
				hotel.setAvailRoom(hotel.getAvailRoom() + noofRooms);
				entityTransaction.begin();
				entityManager.merge(customer);
				entityManager.merge(hotel);
				entityTransaction.commit();
				return customer;
			}else {
				System.out.println("Enter correct number of rooms!");
				return null;
			}		
		}else {
			System.out.println("Enter correct ids!");
			return null;
		}
	}

}
