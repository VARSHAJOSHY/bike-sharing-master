package com.gla.daoimpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.gla.dao.BookingDao;
import com.gla.model.BikeEntity;
import com.gla.model.BookingDetails;
import com.gla.model.CustomerEntity;
import com.gla.model.RepairRequest;
import com.gla.model.ReportDTO;
import com.gla.model.ReviewResponse;
import com.gla.model.StationEntity;
import com.gla.util.Constants;
import com.gla.util.HibernateUtilMySql;

@Repository
public class BookingDaoImpl implements BookingDao {


	private CustomerEntity getCustomerDetails(String email) {

		CustomerEntity details = null;

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(CustomerEntity.class);
			cr.add(Restrictions.eq("emailId", email));
			if (cr.list().size() > 0) {
				details = new CustomerEntity();
				details = (CustomerEntity) cr.uniqueResult();
			}
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}
		}
		return details;
	}

	@Override
	public JSONObject register(JSONObject ji) throws JSONException {

		JSONObject res = new JSONObject();
		if(!ji.has("first_name") || !ji.has("emailId")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		CustomerEntity cust = getCustomerDetails(ji.getString("emailId"));
		if (cust == null) {
			CustomerEntity newCustomer = new CustomerEntity();
			newCustomer.setFirstName(ji.getString("first_name"));
			newCustomer.setLastName(ji.getString("last_name"));
			newCustomer.setPhNo(ji.getString("ph_no"));
			newCustomer.setPassword(ji.getString("password"));
			newCustomer.setDob(ji.getString("dob"));
			newCustomer.setEmailId(ji.getString("emailId"));
			newCustomer.setRole(ji.getString("role"));
			newCustomer.setGender(ji.getString("gender"));
			newCustomer.setCreatedOn(new Date());
			boolean isSuccess = saveCustomerDetails(newCustomer);
			if (isSuccess) {
				res.put("response", "Saved Succesfully");
				res.put("status", Constants.success);
				return res;
			} else {
				res.put("response", "Data Insertion Failed");
				res.put("status", Constants.fail);
				return res;
			}
		} else {
			res.put("response", "User Already exists");
			res.put("status",  Constants.fail);
			return res;
		}
	}

	private boolean saveCustomerDetails(CustomerEntity details) {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(details);
			session.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}

		}
		return isSuccess;
	}
	
	public JSONObject loginRequest(JSONObject ji) throws JSONException {
		
		JSONObject result = new JSONObject();
		if(!ji.has("emailId") || !ji.has("password") || ji.getString("emailId").trim().equals("")
				|| ji.getString("password").trim().equals("")) {
			result.put("status", Constants.fail);
			result.put("page", "errorPage.jsp");
			return result;
		}
		
		String emailId = ji.getString("emailId");
		String password = ji.getString("password");
		
		CustomerEntity custDetails = getCustomerDetails(emailId,password);
		
		if(custDetails !=null) {
			if(custDetails.getRole().equals("C")) {
				result.put("status", Constants.fail);
				result.put("page", "customer.html");
				return result;
			}else if(custDetails.getRole().equals("O")) {
				result.put("status", Constants.fail);
				result.put("page", "operator.html");
				return result;
			}else if(custDetails.getRole().equals("M")) {
				result.put("status", Constants.fail);
				result.put("page", "manager.html");
				return result;
			}else {
				result.put("status", Constants.fail);
				result.put("page", "errorPage.html");
				return result;
			}
		}else {
			result.put("status", Constants.fail);
			result.put("page", "errorPage.html");
			return result;
		}
		
		
	}
	
	private CustomerEntity getCustomerDetails(String emailId, String password) {

		CustomerEntity details = null;

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(CustomerEntity.class);
			cr.add(Restrictions.eq("emailId", emailId));
			cr.add(Restrictions.eq("password", password));
			if (cr.list().size() > 0) {
				details = new CustomerEntity();
				details = (CustomerEntity) cr.uniqueResult();
			}
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}
		}
		return details;
	}

	@Override
	public JSONObject rentBike(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		if(!ji.has("bike_no") || !ji.has("userName")|| !ji.has("pickup_loc")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		
		BikeEntity bike = getBikeData(ji.getString("bike_no"));
		CustomerEntity custDetails = getCustomerDetails(ji.getString("userName"));
		StationEntity pickup_station = getStationData(ji.getInt("pickup_loc"));
		if(custDetails != null) {
			BookingDetails alreadyExists = getUserBookingDetails(custDetails.getCustId());
			if(alreadyExists == null) {
				if(bike !=null && bike.getBikeStatus().equals("A")) {
					BookingDetails bookingDetails = new BookingDetails();
					bookingDetails.setBike(bike);
					bookingDetails.setCustomer(custDetails);
					bookingDetails.setBookingDate(new Date());
					bookingDetails.setBookingStatus("A");
					bookingDetails.setStartLoc(pickup_station);
					boolean isSuccess = saveBookingDetails(bookingDetails);
					if (isSuccess) {
						bookingDetails.setBooking_no("RR/" + bookingDetails.getBookingId());
						if(updateBookingDetails(bookingDetails)) {
							bike.setBikeStatus("N");
							if (updateBikeMaster(bike)) {
								res.put("booking_no", bookingDetails.getBooking_no());
								res.put("response", "Bike booked Successfully!");
								res.put("status", Constants.success);
							} else {
								res.put("response", "Data insertion failed");
								res.put("status", Constants.fail);
							}
						}
						
					}else {
						res.put("response", "Data insertion failed");
						res.put("status", Constants.fail);
					}
					
				}else {
					res.put("response", "Bike currently in Use");
					res.put("status", Constants.fail);
				}	
			}else {
				res.put("response", "User ride already in progress");
				res.put("status", Constants.fail);
			}
			
			
		}else {
			res.put("response", "User does not exist in system");
			res.put("status", Constants.fail);
		}
		
		return res;
	}

	private BookingDetails getUserBookingDetails(Integer custId) {

		BookingDetails details = null;

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(BookingDetails.class);
			cr.add(Restrictions.eq("customer.custId", custId));
			cr.add(Restrictions.eq("bookingStatus", "A"));
				details = new BookingDetails();
				details = (BookingDetails) cr.uniqueResult();
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}
		}
		return details;
	}

	private BikeEntity getBikeData(String bike_no) {

		BikeEntity details = null;

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(BikeEntity.class);
			cr.add(Restrictions.eq("bikeId", Integer.parseInt(bike_no)));
			if (cr.list().size() > 0) {
				details = new BikeEntity();
				details = (BikeEntity) cr.uniqueResult();
			}
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}
		}
		return details;
	}
	
	private boolean saveBookingDetails(BookingDetails details) {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(details);
			session.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}

		}
		return isSuccess;
	}
	
	private boolean updateBookingDetails(BookingDetails details) {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(details);
			session.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}

		}
		return isSuccess;
	}
	
	private boolean updateBikeMaster(BikeEntity details) {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(details);
			session.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}

		}
		return isSuccess;
	}

	@Override
	public JSONObject fetchAllDefectedBikes() throws JSONException {
		JSONObject res = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<RepairRequest> repairs = getEntityForClass(RepairRequest.class, Restrictions.eq("reqStatus", "N"));
		
		for(RepairRequest repair: repairs) {
			JSONObject response = new JSONObject();
			response.put("bike_id", repair.getBike().getBikeId());
			response.put("bike_no", repair.getBike().getBikeNumber());
			response.put("bike_model", repair.getBike().getModelName());
			response.put("req_by_name", repair.getReqBy().getFirstName() + " " + repair.getReqBy().getLastName());
			response.put("station_id", repair.getBike().getOwnerStation().getStationId());
			response.put("station_name", repair.getBike().getOwnerStation().getStationName());
			response.put("create_date", repair.getCreatedDate());
			response.put("issues_desc", repair.getIssueDesc());
			response.put("repairBy", repair.getRepairedBy());
			response.put("req_status", repair.getReqStatus());
			jsonArray.put(response);
		}
		res.put("result", jsonArray);
		return res;
	}

	@Override
	public JSONObject reportDefect(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		if(!ji.has("bike_id") || !ji.has("email_id") || !ji.has("issue_desc")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		RepairRequest newRepair = new RepairRequest();
		BikeEntity bike = getEntityForClass(BikeEntity.class, Restrictions.eq("bikeId", ji.getInt("bike_id"))).stream().findFirst().orElse(null);
		CustomerEntity user = getEntityForClass(CustomerEntity.class, Restrictions.eq("emailId", ji.getString("email_id"))).stream().findFirst().orElse(null);
		if (bike == null || user == null) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		newRepair.setBike(bike);
		newRepair.setReqBy(user);
		newRepair.setCreatedDate(new Date());
		newRepair.setIssueDesc(ji.getString("issue_desc"));
		newRepair.setReqStatus("N");
		boolean isSuccess = saveDBEntity(newRepair);
		if (isSuccess) {
			updateBikeStatusInDB(bike.getBikeId(), "D");
			res.put("response", "Saved Succesfully");
			res.put("status", Constants.success);
			return res;
		} else {
			res.put("response", "Data Insertion Failed");
			res.put("status", Constants.fail);
			return res;
		}
	}

	@Override
	public JSONObject reviewRide(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		if(!ji.has("booking_id") || !ji.has("email_id") || !ji.has("review_desc") || !ji.has("no_of_stars")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		CustomerEntity customer = getEntityForClass(CustomerEntity.class, Restrictions.eq("emailId", ji.getString("email_id"))).stream().findFirst().orElse(null);
		if (customer == null) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		ReviewResponse newReview = new ReviewResponse();
		newReview.setBookingId(ji.getInt("booking_id"));
		newReview.setReviewedBy(customer.getCustId());
		newReview.setReviewDesc(ji.getString("review_desc"));
		newReview.setNoOfStars(ji.getInt("no_of_stars"));
		boolean isSuccess = saveDBEntity(newReview);
		if (isSuccess) {
			res.put("response", "Saved Succesfully");
			res.put("status", Constants.success);
			return res;
		} else {
			res.put("response", "Data Insertion Failed");
			res.put("status", Constants.fail);
			return res;
		}
	}

	@Override
	public JSONObject fetchTop5UserReview() throws JSONException {
		JSONObject res = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<ReviewResponse> reviews = getEntityForClass(ReviewResponse.class);
		Comparator<ReviewResponse> comparator = new Comparator<ReviewResponse>() {
			@Override
			public int compare(ReviewResponse o1, ReviewResponse o2) {
				return (o1.getReviewId() < o2.getReviewId()) ? 1 : -1;
			}
		};
		reviews.sort(comparator);
		if (reviews.size() > 5) {
			reviews = reviews.subList(0, 5);
		}
		List<Integer> reviewByIds = new ArrayList<Integer>();
		reviews.forEach(review -> reviewByIds.add(review.getReviewedBy()));
		List<CustomerEntity> reviewBys = getEntityForClass(CustomerEntity.class, Restrictions.in("custId", reviewByIds));
		for (ReviewResponse review: reviews) {
			CustomerEntity customer = reviewBys.stream().filter(c -> c.getCustId() == review.getReviewedBy()).findFirst().orElse(null);
			JSONObject response = new JSONObject();
			response.put("user_first_name", customer.getFirstName());
			response.put("user_last_name", customer.getLastName());
			response.put("review_desc", review.getReviewDesc());
			response.put("review_no_of_stars", review.getNoOfStars());
			response.put("review_id", review.getReviewId());
			jsonArray.put(response);
		}
		res.put("result", jsonArray);
		return res;
	} 

	@Override
	public JSONObject fetchAllBikeLocations(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<BikeEntity> bikeEntities = getEntityForClass(BikeEntity.class);
		for(int i = 0 ; i < bikeEntities.size() ;i++){
			jsonArray.put(bikeEntities.get(i));
		}
		res.put("reviewResponses", jsonArray);
		return res;
	}

	@Override
	public JSONObject updateBikeLocation(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		if(!ji.has("bike_id") || !ji.has("owner_station")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		StationEntity station = getStationData(ji.getInt("owner_station"));
		if (station == null) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		boolean isSuccess = updateBikeLocationInDB(ji.getInt("bike_id"), station);
		if (isSuccess) {
			res.put("response", "Saved Succesfully");
			res.put("status", Constants.success);
			return res;
		} else {
			res.put("response", "Data Insertion Failed");
			res.put("status", Constants.fail);
			return res;
		}
	}
	
	private StationEntity getStationData(int station_id) {
		StationEntity station = null;

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(StationEntity.class);
			cr.add(Restrictions.eq("stationId", station_id));
			if (cr.list().size() > 0) {
				station = (StationEntity) cr.uniqueResult();
			}
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;
			}
		}
		return station;
	}

	@Override
	public JSONObject endRide(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		if(!ji.has("ride_id") || !ji.has("dest_loc")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		
		Integer destLoc = ji.getInt("dest_loc");
		StationEntity destStation = getStationData(destLoc);
		
		BookingDetails bookingDetails = getBookingDetails(ji.getString("ride_id"));
		
		if(bookingDetails!= null) {
			
			bookingDetails.setBookingStatus("C");
			bookingDetails.setDropDate(new Date());
			bookingDetails.setDropLoc(destStation);
			
			
			if(updateBookingDetails(bookingDetails)) {
				BikeEntity bike = bookingDetails.getBike();
				bike.setBikeStatus("A");
				bike.setOwnerStation(bookingDetails.getDropLoc());
				if(updateBikeMaster(bike)) {
					res.put("response", "Ride ended Successfully!");
					res.put("status", Constants.success);
					return res;
				}else {
					res.put("response", "Data insertion failed");
					res.put("status", Constants.fail);
					return res;
				}
			}else {
				res.put("response", "Data insertion failed");
				res.put("status", Constants.fail);
				return res;
			}
		}else {
			res.put("response", "No booking found");
			res.put("status", Constants.fail);
			return res;
		}
	}
	
	private BookingDetails getBookingDetails(String ride_no) {

		BookingDetails details = null;

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(BookingDetails.class);
			cr.add(Restrictions.eq("booking_no", ride_no));
				details = new BookingDetails();
				details = (BookingDetails) cr.uniqueResult();
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}
		}
		return details;
	}

	private boolean updateBikeLocationInDB(int bike_id, StationEntity station) {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			
			BikeEntity bike = null;
			Criteria cr = session.createCriteria(BikeEntity.class);
			cr.add(Restrictions.eq("bikeId", bike_id));
			if (cr.list().size() > 0) {
				bike = new BikeEntity();
				bike = (BikeEntity) cr.uniqueResult();
			}
			
			bike.setOwnerStation(station);
			session.update(bike);
			session.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;
			}
		}
		return isSuccess;
	}

	@Override
	public JSONObject getPastStationIds(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		List<String> list = new LinkedList<String>();
		if(!ji.has("bike_id")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		
		String bike_id = ji.getString("bike_id");
		
		BikeEntity bike = getBikeData(bike_id);
		
		if(bike!= null) {
			StationEntity currentStation =  bike.getOwnerStation();
			
			List<BookingDetails> otherLocations = getStationsFromBooking(bike_id);
			
			if(otherLocations != null) {
				for(BookingDetails details : otherLocations) {
					if(details.getDropLoc()!= null)
					list.add(details.getDropLoc().getStationName());
				}
			}
			if(!list.isEmpty() && !list.get(list.size()-1).equals(currentStation.getStationName())) {
				list.add(currentStation.getStationName());
			}
			res.put("stations", list);
			res.put("status", Constants.success);
			return res;
		}else {
			res.put("status", Constants.fail);
			res.put("response", "Bike Data doesn't exist");
			return res;
		}
		
		
	}
	
	private List<BookingDetails> getStationsFromBooking(String bike_id) {
		List<BookingDetails> stations = null;

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(BookingDetails.class);
			//cr.createAlias("bike.bikeId", "bike_Id");
			cr.add(Restrictions.eq("bike.bikeId", Integer.parseInt(bike_id)));
			cr.addOrder(Order.asc("dropDate"));
			if (cr.list().size() > 0) {
				stations = new ArrayList<BookingDetails>();
				stations = (List<BookingDetails>)cr.list();
			}
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;
			}
		}
		return stations;
	}

	@Override
	public JSONObject fetchAllStations() throws JSONException {
		JSONObject res = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<StationEntity> stations = getEntityForClass(StationEntity.class);
		for(StationEntity station: stations){
			JSONObject response = new JSONObject();
			response.put("station_id", station.getStationId());
			response.put("station_address", station.getAddress());
			response.put("station_capacity", station.getTotalCapacity());
			response.put("station_name", station.getStationName());
			response.put("station_zipCode", station.getZipcode());
			jsonArray.put(response);
		}
		res.put("result", jsonArray);
		return res;
	}

	@Override
	public JSONObject fetchAllBikesByStationId(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		if (!ji.has("station_id")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		JSONArray jsonArray = new JSONArray();
		int station_id = ji.getInt("station_id");
		List<BikeEntity> bikes = getEntityForClass(BikeEntity.class);
		for(BikeEntity bike: bikes){
			if (bike.getOwnerStation().getStationId() != station_id) {
				continue;
			}
			
			JSONObject response = new JSONObject(bike.toString());
			jsonArray.put(response);
		}
		res.put("result", jsonArray);
		return res;
	}

	@Override
	public JSONObject getBikeDetailsByBikeId(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		if (!ji.has("bike_id")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		JSONArray jsonArray = new JSONArray();
		int bike_id = ji.getInt("bike_id");
		List<BikeEntity> bikes = getEntityForClass(BikeEntity.class, Restrictions.eq("bikeId", bike_id));
		for(BikeEntity bike: bikes){
			JSONObject response = new JSONObject(bike.toString());
			jsonArray.put(response);
		}
		res.put("result", jsonArray);
		return res;
	}

	@Override
	public JSONObject updateDefectStatus(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		if(!ji.has("bike_id") || !ji.has("repair_status") || !ji.has("bike_status") || !ji.has("repairBy_email_id")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		boolean repairStatus_isSuccess = updateRepairStatusInDB(ji.getInt("bike_id"), ji.getString("repair_status"), getCustomerDetails(ji.getString("repairBy_email_id")));
		boolean bikeStatus_isSuccess = updateBikeStatusInDB(ji.getInt("bike_id"), ji.getString("bike_status"));
		if (repairStatus_isSuccess && bikeStatus_isSuccess) {
			res.put("response", "Saved Succesfully");
			res.put("status", Constants.success);
			return res;
		} else {
			res.put("response", "Data Insertion Failed");
			res.put("status", Constants.fail);
			return res;
		}
	}
	
	private boolean updateRepairStatusInDB(int bike_id, String repair_status, CustomerEntity repairBy) {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			
			Criteria cr = session.createCriteria(RepairRequest.class);
			
			cr.add(Restrictions.eq("bike.bikeId", bike_id));
			List<RepairRequest> details = (List<RepairRequest>) cr.list();
			for (RepairRequest repair: details) {
				repair.setReqStatus(repair_status);
				repair.setRepairedDatetime(new Date());
				repair.setRepairedBy(repairBy);
				session.update(repair);
			}
			session.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;
			}
		}
		return isSuccess;
	}
	
	private boolean updateBikeStatusInDB(int bike_id, String bike_status) {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			
			BikeEntity bike = null;
			Criteria cr = session.createCriteria(BikeEntity.class);
			cr.add(Restrictions.eq("bikeId", bike_id));
			if (cr.list().size() > 0) {
				bike = new BikeEntity();
				bike = (BikeEntity) cr.uniqueResult();
			}
			
			bike.setBikeStatus(bike_status);;
			session.update(bike);
			session.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;
			}
		}
		return isSuccess;
	}


	public JSONObject getReportData(JSONObject ji) throws JSONException {

		JSONObject res = new JSONObject();
		if (!ji.has("method") && !ji.has("content")) {
			res.put("status", Constants.fail);
			res.put("response", "Insufficient Data");
			return res;
		}

		String method = ji.getString("method");
		JSONObject content = ji.getJSONObject("content");
			if (!content.has("year")) {
				res.put("status", Constants.fail);
				res.put("response", "Insufficient Data");
				return res;
			}else if(!method.equals("getProfitReport") && !content.has("month")) {
				res.put("status", Constants.fail);
				res.put("response", "Insufficient Data");
				return res;	
			}

		String year = content.getString("year");
		String month = content.has("month")? content.getString("month"):null;

		if (method.equals("getActiveStationReport")) {
			res = getActiveStationReport(year, month);
		} else if (method.equals("getActiveBikeReport")) {
			res = getActiveBikeReport(year, month);
		} else if (method.equals("getProfitReport")) {
			res = getProfitReport(year);
		} else if (method.equals("getDefectedBikeReport")) {
			res = getDefectedBikeReport(year, month);
		}

		return res;
	}

	
	private JSONObject getActiveStationReport(String year, String month) throws JSONException {
		JSONObject res = new JSONObject();
		JSONArray arr = new JSONArray();
		List<ReportDTO> reportData = fetchStationReportData(year,month);
		
		if (reportData != null && reportData.size() > 0) {
			for (ReportDTO dto : reportData) {
				JSONObject json = new JSONObject();
				json.put("stationId", dto.getStationId());
				json.put("stationName", dto.getStationName());
				json.put("noOfBikes", dto.getNoOfBikes());
				arr.put(json);
			}
		}
		res.put("result", arr);
		return res;

	}

	private List<ReportDTO> fetchStationReportData(String year, String month) {

		List<ReportDTO> dtoList = new ArrayList<ReportDTO>();

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "select bd.pickup_station as stationId,sm.station_name as stationName, count(bd.booking_id) as noOfBikes\r\n"
					+ " from booking_details bd \r\n" + " inner join bike_master bm on bd.bike_id = bm.bike_id \r\n"
					+ " left join station_master sm on sm.station_id = bd.pickup_station \r\n"
					+ " WHERE MONTH(booking_date_time) = " + month + " AND YEAR(booking_date_time)  = " + year
					+ " group by bd.pickup_station;";

			Query query = session.createSQLQuery(sql.toString())
					.addScalar("noOfBikes", StandardBasicTypes.INTEGER)
					.addScalar("stationId", StandardBasicTypes.INTEGER)
					.addScalar("stationName", StandardBasicTypes.STRING)
					.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));

			if (query != null && query.list().size() > 0) {
				dtoList = new ArrayList<ReportDTO>(query.list());
			}

		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}
		}
		return dtoList;

	}
	
	private JSONObject getActiveBikeReport(String year, String month) throws JSONException {

		JSONObject res = new JSONObject();
		JSONArray arr = new JSONArray();
		List<ReportDTO> reportData = fetchBikeReportData(year,month);
		
		if (reportData != null && reportData.size() > 0) {
			for (ReportDTO dto : reportData) {
				JSONObject json = new JSONObject();
				json.put("bikeId", dto.getBikeId());
				json.put("bikeName", dto.getBikeNo());
				json.put("noOfBikes", dto.getNoOfBikes());
				arr.put(json);
			}
		}
		res.put("result", arr);
		return res;
	}

	private List<ReportDTO> fetchBikeReportData(String year, String month) {

		List<ReportDTO> dtoList = new ArrayList<ReportDTO>();

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "select bm.bike_id as bikeId,bm.bike_number as bikeNo, count(bd.bike_id) as noOfBikes\r\n"
					+ " from booking_details bd \r\n" + " inner join bike_master bm on bd.bike_id = bm.bike_id \r\n"
					+ " inner join station_master sm on sm.station_id = bm.owner_station \r\n"
					+ " WHERE MONTH(booking_date_time) = " + month + " AND YEAR(booking_date_time)  = " + year
					+ " group by bm.bike_id;";

			Query query = session.createSQLQuery(sql.toString())
					.addScalar("noOfBikes", StandardBasicTypes.INTEGER)
					.addScalar("bikeId", StandardBasicTypes.INTEGER)
					.addScalar("bikeNo", StandardBasicTypes.STRING)
					.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));

			if (query != null && query.list().size() > 0) {
				dtoList = new ArrayList<ReportDTO>(query.list());
			}

		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}
		}
		return dtoList;

	}

	private JSONObject getProfitReport(String year) throws JSONException {
		JSONObject res = new JSONObject();
		JSONArray arr = new JSONArray();
		List<ReportDTO> reportData = fetchProfitReportData(year);
		
		if (reportData != null && reportData.size() > 0) {
			for (ReportDTO dto : reportData) {
				JSONObject json = new JSONObject();
				json.put("month", dto.getMonth());
				json.put("profit", dto.getProfit());
				arr.put(json);
			}
		}
		res.put("result", arr);
		return res;

	}
	
	private List<ReportDTO> fetchProfitReportData(String year) {

		List<ReportDTO> dtoList = new ArrayList<ReportDTO>();

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "select MONTH(booking_date_time) as month, sum(total_cost) as profit \r\n"
					+ " from booking_details \r\n"
					+ " WHERE YEAR(booking_date_time) = "+year
					+ " group by MONTH(booking_date_time);";

			Query query = session.createSQLQuery(sql.toString())
					.addScalar("month", StandardBasicTypes.STRING)
					.addScalar("profit", StandardBasicTypes.STRING)
					.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));

			if (query != null && query.list().size() > 0) {
				dtoList = new ArrayList<ReportDTO>(query.list());
			}

		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
		}
		return dtoList;
	}
	
	// Private function
	// get all entities with specific conditions
	private <T> List<T> getEntityForClass(Class<T> tClass, Criterion...criterions) {
		List<T> entities = null;

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(tClass);
			for (Criterion criterion: criterions) {
				cr.add(criterion);
			}
			entities = cr.list();
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;
			}
		}
		return entities;
	}
	
	// insert DB entity
	private boolean saveDBEntity(Object dbObject) {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(dbObject);
			session.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;
			}
		}
		return isSuccess;
	}

	private JSONObject getDefectedBikeReport(String year, String month) throws JSONException {
		JSONObject res = new JSONObject();
		JSONArray arr = new JSONArray();
		List<ReportDTO> reportData = fetchDefectedBikeReportData(year,month);
		
		if (reportData != null && reportData.size() > 0) {
			for (ReportDTO dto : reportData) {
				JSONObject json = new JSONObject();
				json.put("stationId", dto.getStationId());
				json.put("stationName", dto.getStationName());
				json.put("noOfBikes", dto.getNoOfBikes());
				arr.put(json);
			}
		}
		res.put("result", arr);
		return res;

	}

	private List<ReportDTO> fetchDefectedBikeReportData(String year, String month) {

		List<ReportDTO> dtoList = new ArrayList<ReportDTO>();

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "select sm.station_id as stationId,sm.station_name as stationName, count(rr.bike_id) as noOfBikes \r\n"
					+ " from repair_request rr\r\n"
					+ " inner join bike_master bm on rr.bike_id = bm.bike_id\r\n"
					+ " inner join station_master sm on bm.owner_station = sm.station_id\r\n"
					+ " WHERE MONTH(created_datetime) = " + month + " AND YEAR(created_datetime)  = " + year 
					+ " group by sm.station_id;";

			Query query = session.createSQLQuery(sql.toString())
					.addScalar("noOfBikes", StandardBasicTypes.INTEGER)
					.addScalar("stationId", StandardBasicTypes.INTEGER)
					.addScalar("stationName", StandardBasicTypes.STRING)
					.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));

			if (query != null && query.list().size() > 0) {
				dtoList = new ArrayList<ReportDTO>(query.list());
			}

		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}
		}
		return dtoList;

	}

	@Override
	public JSONObject calculateCost(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		if(!ji.has("ride_id")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		
		BookingDetails bookingDetails = getBookingDetails(ji.getString("ride_id"));
		
		if(bookingDetails!= null) {
			
			bookingDetails.setDropDate(new Date());
			long duration  = bookingDetails.getDropDate().getTime() - bookingDetails.getBookingDate().getTime();
			long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
			double totalCost = 0;
			double fixedPricePerHour = 1;
			if(diffInHours<1) {
				totalCost = 1;
			}else {
				totalCost = fixedPricePerHour * (diffInHours+1);
			}
			
			bookingDetails.setTotalCost(totalCost);
			if(updateBookingDetails(bookingDetails)) {
				res.put("ride_cost", totalCost);
				res.put("response", "Ride ended Successfully!");
				res.put("status", Constants.success);	
				return res;
			}
			
		}else {
			res.put("response", "No booking found");
			res.put("status", Constants.fail);
			return res;
		}
		return res;
	}

	@Override
	public JSONObject fetchFreeBikes(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		JSONArray arr = new JSONArray();
		if (!ji.has("station_id")) {
			res.put("status", Constants.fail);
			res.put("response", "Insufficient Data");
			return res;
		}
		
		List<BikeEntity> freeBikes =  getAvailableBikes(ji.getString("station_id"));
		for (BikeEntity bike : freeBikes) {
			JSONObject bikeData = new JSONObject();
			bikeData.put("bike_id", bike.getBikeId());
			bikeData.put("bike_no", bike.getBikeNumber());
			bikeData.put("model_name", bike.getModelName());
			bikeData.put("bike_specs", bike.getSpec());
			bikeData.put("station_id", bike.getOwnerStation().getStationId());
			bikeData.put("station_name", bike.getOwnerStation().getStationName());
			arr.put(bikeData);
		}
		
		res.put("content", arr);
		res.put("status", Constants.success);
		res.put("response", "Successful");
		
		return res;
	}

	private List<BikeEntity> getAvailableBikes(String stationId) {

		List<BikeEntity> details = new ArrayList<BikeEntity>();

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(BikeEntity.class);
			cr.add(Restrictions.eq("ownerStation.stationId", Integer.parseInt(stationId)));
			cr.add(Restrictions.eq("bikeStatus","A"));
			if (cr.list().size() > 0) {
				details = (List<BikeEntity>) cr.list();
			}
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}
		}
		return details;
	}

	@Override
	public JSONObject fetchActiveRideDetailsByEmailId(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		if (!ji.has("email_id")) {
			res.put("response", "Insufficient Data");
			res.put("status", Constants.fail);
			return res;
		}
		JSONArray jsonArray = new JSONArray();
		String email_id = ji.getString("email_id");
		List<BookingDetails> bookings = getEntityForClass(BookingDetails.class, Restrictions.eq("bookingStatus", "A"));
		for(BookingDetails booking: bookings){
			if (!booking.getCustomer().getEmailId().equalsIgnoreCase(email_id)) {
				continue;
			}
			JSONObject response = new JSONObject();
			response.put("booking_id", booking.getBookingId());
			response.put("booking_no", booking.getBooking_no());
			response.put("bike_id", booking.getBike().getBikeId());
			response.put("bike_no", booking.getBike().getBikeNumber());
			response.put("bike_model", booking.getBike().getModelName());
			response.put("begin_station_name", booking.getStartLoc().getStationName());
			jsonArray.put(response);
		}
		res.put("result", jsonArray);
		return res;
	}

	@Override
	public JSONObject fetchAllRides(JSONObject ji) throws JSONException {
		JSONObject res = new JSONObject();
		JSONArray arr = new JSONArray();
		if (!ji.has("user_id")) {
			res.put("status", Constants.fail);
			res.put("response", "Insufficient Data");
			return res;
		}
		
		List<BookingDetails> rides =  getUserRides(ji.getString("user_id"));
		for (BookingDetails booking : rides) {
			JSONObject rideData = new JSONObject();
			rideData.put("booking_id", booking.getBookingId());
			rideData.put("booking_no", booking.getBooking_no());
			rideData.put("bike_id", booking.getBike().getBikeId());
			rideData.put("bike_no", booking.getBike().getBikeNumber());
			rideData.put("bike_model", booking.getBike().getModelName());
			rideData.put("begin_station_name", booking.getStartLoc().getStationName());
			rideData.put("begin_date", booking.getBookingDate());
			rideData.put("drop_station_name", booking.getDropLoc()!= null ? booking.getDropLoc().getStationName():"");
			rideData.put("drop_date", booking.getDropDate());
			rideData.put("cost", booking.getTotalCost());
			rideData.put("status", booking.getBookingStatus());
			arr.put(rideData);
		}
		
		res.put("content", arr);
		res.put("status", Constants.success);
		res.put("response", "Successful");
		
		return res;
	}

	private List<BookingDetails> getUserRides(String userId) {

		List<BookingDetails> details = new ArrayList<BookingDetails>();

		Session session = null;
		try {
			session = HibernateUtilMySql.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(BookingDetails.class);
			cr.createAlias("customer", "cust");
			cr.add(Restrictions.eq("cust.emailId", userId));
			if(cr.list().size()>0) {
				details = (List<BookingDetails>) cr.list();
			}
		} catch (Exception e) {
			System.out.println("Exception Occured:" + e.toString());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
				session = null;

			}
		}
		return details;
	}

	
}
