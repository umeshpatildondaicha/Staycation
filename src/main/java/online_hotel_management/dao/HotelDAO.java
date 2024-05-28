package online_hotel_management.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import online_hotel_management.dto.Hotel;

public class HotelDAO {

	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("onkar").createEntityManager();
	}

	public Hotel signUpHotel(Hotel hotel) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(hotel);
		entityTransaction.commit();
		return hotel;
	}

	public Hotel logInHotel(String hotelEmail, String hotelPassword) {
		EntityManager entityManager = getEntityManager();

		Query query = entityManager.createQuery("SELECT h FROM Hotel h WHERE h.hotelEmail=?1");
		query.setParameter(1, hotelEmail);

		Hotel hotel = (Hotel) query.getSingleResult();
		if (hotel != null) {
			if (hotel.getHotelPassword().equals(hotelPassword)) {
				System.out.println("Login Success!");
				return hotel;
			} else {
				System.out.println("Incorrect Password!");
				return hotel;
			}
		} else {
			System.out.println("Hotel Not Registered!");
			return null;
		}
	}

	public Hotel findByHotelId(int hotelId) {
		EntityManager entityManager = getEntityManager();
		return entityManager.find(Hotel.class, hotelId);
	}

	public List<Hotel> findByHotelName(String hotelName) {
		EntityManager entityManager = getEntityManager();

		Query query = entityManager.createQuery("SELECT h FROM Hotel h WHERE h.hotelName = ?1");
		query.setParameter(1, hotelName);
		List<Hotel> list = query.getResultList();
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}

	public List<Hotel> findHotelByLoc(String hotelLoc) {
		EntityManager entityManager = getEntityManager();

		Query query = entityManager.createQuery("SELECT h FROM Hotel h WHERE h.hotelLocation = ?1");
		query.setParameter(1, hotelLoc);
		List<Hotel> list = query.getResultList();
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}
	public Hotel findByEmail(String hotelEmail) {
		EntityManager entityManager=getEntityManager();
		Query query=entityManager.createQuery("SELECT h FROM Hotel h WHERE h.hotelEmail=?1");
		query.setParameter(1, hotelEmail);
		Hotel hotel=(Hotel) query.getSingleResult();
		return hotel;
	}

	public Hotel updateHotelPassword(String hotelEmail, String oldPass, String newPass) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Query query = entityManager.createQuery("SELECT h FROM Hotel h WHERE h.hotelEmail=?1");
		query.setParameter(1, hotelEmail);

		Hotel hotel = (Hotel) query.getSingleResult();
		if (hotel != null) {
			if (hotel.getHotelPassword().equals(oldPass)) {
				hotel.setHotelEmail(hotelEmail);
				hotel.setHotelPassword(newPass);
				entityTransaction.begin();
				entityManager.merge(hotel);
				entityTransaction.commit();
				return hotel;
			} else {
				System.out.println("Please Enter Valid Password");
				return hotel;
			}
		} else {
			System.out.println("Hotel Not Registered!");
			return null;
		}
	}

	public Hotel updateRoomPrice(int hotelId, double roomPrice) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Hotel hotel = entityManager.find(Hotel.class, hotelId);
		if (hotel != null) {
			hotel.setHotelId(hotelId);
			hotel.setRoomPrice(roomPrice);
			entityTransaction.begin();
			entityManager.merge(hotel);
			entityTransaction.commit();
			return hotel;
		} else {
			System.out.println("Invalid Hotel Id");
			return null;
		}
	}

	public Hotel updateTotalRooms(int hotelId, int newTotalRooms) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Hotel hotel = entityManager.find(Hotel.class, hotelId);
		if (hotel != null) {
			hotel.setHotelId(hotelId);
			hotel.setTotalRooms(newTotalRooms);
			entityTransaction.begin();
			entityManager.merge(hotel);
			entityTransaction.commit();
			return hotel;
		} else {
			System.out.println("Enter the valid Hotel ID!");
			return null;
		}
	}

	public Hotel updateAvailRoom(int hotelId, int newAvailRoom) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Hotel hotel = entityManager.find(Hotel.class, hotelId);
		if (hotel != null) {
			hotel.setHotelId(hotelId);
			hotel.setAvailRoom(newAvailRoom);
			entityTransaction.begin();
			entityManager.merge(hotel);
			entityTransaction.commit();
			return hotel;
		} else {
			System.out.println("Enter the valid Hotel ID!");
			return null;
		}
	}

	public Hotel updatePhone(int hotelId, long oldPhone, long newPhone) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Hotel hotel = entityManager.find(Hotel.class, hotelId);
		if (hotel != null) {
			long dbPhone = hotel.getHotelPhone();
			if (dbPhone == oldPhone) {
				hotel.setHotelId(hotelId);
				hotel.setHotelPhone(newPhone);
				entityTransaction.begin();
				entityManager.merge(hotel);
				entityTransaction.commit();
				return hotel;
			} else {
				System.out.println("Enter correct phone number!");
				return hotel;
			}
		} else {
			System.out.println("Enter correct Hotel ID!");
			return null;
		}
	}

	public Hotel deleteHotel(int hotelId) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Hotel hotel = entityManager.find(Hotel.class, hotelId);
		if (hotel != null) {
			entityTransaction.begin();
			entityManager.remove(hotel);
			entityTransaction.commit();
			return hotel;
		} else {
			System.out.println("Enter the correct Hotel ID!");
			return null;
		}
	}
	public void updateHotel(int id, Hotel hotel) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		Hotel dbHotel = entityManager.find(Hotel.class, id);

		if (dbHotel != null) {

			dbHotel.setRoomPrice(hotel.getRoomPrice());
			dbHotel.setHotelPassword(hotel.getHotelPassword());
			dbHotel.setHotelLocation(hotel.getHotelLocation());
			
			entityTransaction.begin();
			entityManager.merge(dbHotel);
			entityTransaction.commit();

		} else {

			System.out.println("Id not found!");

		}

	}

}
