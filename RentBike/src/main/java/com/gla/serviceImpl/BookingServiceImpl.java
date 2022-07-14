package com.gla.serviceImpl;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.gla.dao.BookingDao;
import com.gla.service.BookingService;

public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;

	@Override
	public JSONObject register(JSONObject ji) throws JSONException {
		return bookingDao.register(ji);
	}


	@Override
	public JSONObject loginRequest(JSONObject ji) throws JSONException {
		return bookingDao.loginRequest(ji);
	}


	@Override
	public JSONObject rentBike(JSONObject ji) throws JSONException {
		return bookingDao.rentBike(ji);
	}


	@Override
	public JSONObject fetchAllDefectedBikes() throws JSONException {
		return bookingDao.fetchAllDefectedBikes();
	}


	@Override
	public JSONObject reportDefect(JSONObject jo) throws JSONException {
		return bookingDao.reportDefect(jo);
	}


	@Override
	public JSONObject reviewRide(JSONObject jo) throws JSONException {
		return bookingDao.reviewRide(jo);
	}


	@Override
	public JSONObject fetchTop5UserReview() throws JSONException {
		return bookingDao.fetchTop5UserReview();
	}


	@Override
	public JSONObject fetchAllBikeLocations(JSONObject jo) throws JSONException {
		return bookingDao.fetchAllBikeLocations(jo);
	}


	@Override
	public JSONObject updateBikeLocation(JSONObject jo) throws JSONException {
		return bookingDao.updateBikeLocation(jo);
	}


	@Override
	public JSONObject endRide(JSONObject ji) throws JSONException {
		return bookingDao.endRide(ji);
	}


	@Override
	public JSONObject getPastStationIds(JSONObject ji) throws JSONException {
		return bookingDao.getPastStationIds(ji);
	}


	@Override
	public JSONObject fetchAllStations() throws JSONException {
		return bookingDao.fetchAllStations();
	}


	@Override
	public JSONObject fetchAllBikesByStationId(JSONObject ji) throws JSONException {
		return bookingDao.fetchAllBikesByStationId(ji);
	}


	@Override
	public JSONObject getBikeDetailsByBikeId(JSONObject ji) throws JSONException {
		return bookingDao.getBikeDetailsByBikeId(ji);
	}


	@Override
	public JSONObject updateDefectStatus(JSONObject ji) throws JSONException {
		return bookingDao.updateDefectStatus(ji);

	}
	
	@Override
	public JSONObject getReportData(JSONObject ji) throws JSONException {
		return bookingDao.getReportData(ji);

	}


	@Override
	public JSONObject calculateCost(JSONObject ji) throws JSONException {
		return bookingDao.calculateCost(ji);
	}


	@Override
	public JSONObject fetchFreeBikes(JSONObject ji) throws JSONException {
		return bookingDao.fetchFreeBikes(ji);
	}


	@Override
	public JSONObject fetchActiveRideDetailsByEmailId(JSONObject ji) throws JSONException {
		return bookingDao.fetchActiveRideDetailsByEmailId(ji);
	}


	@Override
	public JSONObject fetchAllRides(JSONObject ji) throws JSONException {
		return bookingDao.fetchAllRides(ji);
	}
}
