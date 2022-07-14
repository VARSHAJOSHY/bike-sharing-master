package com.gla.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

	JSONObject register(JSONObject jo) throws JSONException;

	JSONObject fetchAllDefectedBikes() throws JSONException;

	JSONObject reportDefect(JSONObject jo) throws JSONException;

	JSONObject reviewRide(JSONObject jo) throws JSONException;

	JSONObject fetchTop5UserReview() throws JSONException;

	JSONObject fetchAllBikeLocations(JSONObject jo) throws JSONException;

	JSONObject updateBikeLocation(JSONObject jo) throws JSONException;

	JSONObject loginRequest(JSONObject ji) throws JSONException;

	JSONObject rentBike(JSONObject jo) throws JSONException;

	JSONObject endRide(JSONObject ji) throws JSONException;

	JSONObject getPastStationIds(JSONObject ji) throws JSONException;

	JSONObject fetchAllStations() throws JSONException;
	
	JSONObject fetchAllBikesByStationId(JSONObject ji) throws JSONException;
	
	JSONObject getBikeDetailsByBikeId(JSONObject ji) throws JSONException;
	
	JSONObject updateDefectStatus(JSONObject ji) throws JSONException;

	JSONObject getReportData(JSONObject ji) throws JSONException;

	JSONObject calculateCost(JSONObject ji) throws JSONException;

	JSONObject fetchFreeBikes(JSONObject ji) throws JSONException;
	
	JSONObject fetchActiveRideDetailsByEmailId(JSONObject ji) throws JSONException;

	JSONObject fetchAllRides(JSONObject ji) throws JSONException;

}
