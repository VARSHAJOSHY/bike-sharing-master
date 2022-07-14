package com.gla.dao;

import org.json.JSONException;
import org.json.JSONObject;

public interface BookingDao {

	public JSONObject register(JSONObject ji)  throws JSONException;;

	public JSONObject fetchAllDefectedBikes()  throws JSONException;

	public JSONObject reportDefect(JSONObject ji)  throws JSONException;

	public JSONObject reviewRide(JSONObject ji)  throws JSONException;

	public JSONObject fetchTop5UserReview()  throws JSONException;

	public JSONObject fetchAllBikeLocations(JSONObject ji)  throws JSONException;

	public JSONObject updateBikeLocation(JSONObject ji)  throws JSONException;

	public JSONObject loginRequest(JSONObject ji) throws JSONException;

	public JSONObject rentBike(JSONObject ji) throws JSONException;

	public JSONObject endRide(JSONObject ji) throws JSONException;

	public JSONObject getPastStationIds(JSONObject ji) throws JSONException;

	public JSONObject fetchAllStations() throws JSONException;
	
	public JSONObject fetchAllBikesByStationId(JSONObject ji) throws JSONException;
	
	public JSONObject getBikeDetailsByBikeId(JSONObject ji) throws JSONException;
	
	public JSONObject updateDefectStatus(JSONObject ji) throws JSONException;
	
	public JSONObject getReportData(JSONObject ji) throws JSONException;

	public JSONObject calculateCost(JSONObject ji) throws JSONException;

	public JSONObject fetchFreeBikes(JSONObject ji) throws JSONException;

	public JSONObject fetchActiveRideDetailsByEmailId(JSONObject ji) throws JSONException;

	public JSONObject fetchAllRides(JSONObject ji) throws JSONException;

}
