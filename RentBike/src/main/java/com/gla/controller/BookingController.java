package com.gla.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gla.service.BookingService;
import com.gla.util.Constants;

@Controller
public class BookingController {

	@Autowired
	private BookingService bookingService;

	/**
	 * Redirects to current active ride of logged in user
	 * @return
	 */
	@RequestMapping(value = "/activeride", method = RequestMethod.GET)
	public String activeride() {
		return "activeride";
	}
	
	/*
	 * Redirects to reports page as  manager
	 */
	@RequestMapping(value = "/allinonereport", method = RequestMethod.GET)
	public String allinonereport() {
		return "allinonereport";
	}
	
	/**
	 * returns page containing all rides of a logged in user
	 * @return
	 */
	@RequestMapping(value = "/allrides", method = RequestMethod.GET)
	public String allrides() {
		return "allrides";
	}
	
	/**
	 * Returns customer page after log-in
	 * logs in as customer
	 * @return
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String customer() {
		return "customer";
	}
	
	/**
	 * Returns manager page after log-in
	 * logs in as manager
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "manager";
	}
	
	/**
	 * Returns operator page after log-in
	 * logs in as operator
	 * @return
	 */
	@RequestMapping(value = "/operator", method = RequestMethod.GET)
	public String operator() {
		return "operator";
	}
	
	/**
	 * Redirects to searchbike page where logged in user can search for bikes and rent it
	 * @return
	 */
	@RequestMapping(value = "/searchbike", method = RequestMethod.GET)
	public String searchbike() {
		return "searchbike";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/trackbike", method = RequestMethod.GET)
	public String trackbike() {
		return "trackbike";
	}
	
	/**
	 * redirects to login screen
	 * @return
	 */
	@RequestMapping(value = "/login1", method = RequestMethod.GET)
	public String login1() {
		return "login1";
	}
	
	/**
	 * returns to logout page
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "logout";
	}
	
	/**
	 * Opens registration page as a customer
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	/**
	 * Opens registration page as a operator
	 * @return
	 */
	@RequestMapping(value = "/operRegister", method = RequestMethod.GET)
	public String operRegister() {
		return "operRegister";
	}
	
	/**
	 * Opens registration page as a manager
	 * @return
	 */
	@RequestMapping(value = "/mangRegister", method = RequestMethod.GET)
	public String mangRegister() {
		return "mangRegister";
	}
	
	/**
	 * opens FAQ section
	 * @return
	 */
	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public String faq() {
		return "faq";
	}
	
	/**
	 * svaes the newly registered data to DB
	 * @param paramters
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public String register(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				 jo = bookingService.register(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	/**
	 * Redirects to a page according to user role after logging in 
	 * @param paramters
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody String paramters) throws JSONException {
		JSONObject ji = new JSONObject();
		JSONObject jo = null;
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				jo = bookingService.loginRequest(ji);
			}else {
				jo = new JSONObject();
				jo.put("status", Constants.fail);
				jo.put("page", "errorPage.jsp");
			}
			}catch (Exception e) {
				e.printStackTrace();
				}
		
		return jo.toString();
	}

	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public String location() {
		return "locations";
	}

	@RequestMapping(value = "/fixdefect", method = RequestMethod.GET)
	public String fixdefect() {
		return "fixdefect";
	}

	@RequestMapping(value = "/changelocation", method = RequestMethod.GET)
	public String chnagelocation() {
		return "changelocation";
	}

	@RequestMapping(value = "/errorpage", method = RequestMethod.GET)
	public String errorpage() {
		return "errorpage";
	}

	@RequestMapping(value = "/timeout", method = RequestMethod.GET)
	public String timeout() {
		return "chnagelocation";
	}

	// @Liang
	@RequestMapping(value = "/fetchAllDefectedBikes", method = RequestMethod.POST)
	@ResponseBody
	public String fetchAllDefectedBikes() throws JSONException {
		JSONObject jo = new JSONObject();
		try {
			jo = bookingService.fetchAllDefectedBikes();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	// @Liang
	@RequestMapping(value = "/reportDefect", method = RequestMethod.POST)
	@ResponseBody
	public String reportDefect(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				jo = bookingService.reportDefect(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	// @Liang
	@RequestMapping(value = "/reviewRide", method = RequestMethod.POST)
	@ResponseBody
	public String reviewRide(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				jo = bookingService.reviewRide(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	// @Liang
	@RequestMapping(value = "/fetchTop5UserReview", method = RequestMethod.POST)
	@ResponseBody
	public String fetchTop5UserReview() throws JSONException {
		JSONObject jo = new JSONObject();
		try {
			jo = bookingService.fetchTop5UserReview();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}

	// @Liang
	@RequestMapping(value = "/fetchAllBikeLocations", method = RequestMethod.POST)
	@ResponseBody
	public String fetchAllBikeLocations(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				jo = bookingService.fetchAllBikeLocations(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	//	@Liang
	@RequestMapping(value = "/updateBikeLocation", method = RequestMethod.POST)
	@ResponseBody
	public String updateBikeLocation(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				jo = bookingService.updateBikeLocation(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}

	// @Liang
	@RequestMapping(value = "/fetchAllStations", method = RequestMethod.POST)
	@ResponseBody
	public String fetchAllStations() throws JSONException {
		JSONObject jo = new JSONObject();
		try {
			jo = bookingService.fetchAllStations();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
		
	// @Liang
	@RequestMapping(value = "/fetchAllBikesByStationId", method = RequestMethod.POST)
	@ResponseBody
	public String fetchAllBikesByStationId(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			ji = new JSONObject(paramters);
			jo = bookingService.fetchAllBikesByStationId(ji);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}

	// @Liang
	@RequestMapping(value = "/getBikeDetailsByBikeId", method = RequestMethod.POST)
	@ResponseBody
	public String getBikeDetailsByBikeId(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			ji = new JSONObject(paramters);
			jo = bookingService.getBikeDetailsByBikeId(ji);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}

	// @Liang
	@RequestMapping(value = "/fetchActiveRideDetailsByEmailId", method = RequestMethod.POST)
	@ResponseBody
	public String fetchActiveRideDetailsByEmailId(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			ji = new JSONObject(paramters);
			jo = bookingService.fetchActiveRideDetailsByEmailId(ji);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}

	// @Liang
	@RequestMapping(value = "/updateDefectStatus", method = RequestMethod.POST)
	@ResponseBody
	public String updateDefectStatus(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			ji = new JSONObject(paramters);
			jo = bookingService.updateDefectStatus(ji);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	@RequestMapping(value = "/rentBike", method = RequestMethod.POST)
	@ResponseBody
	public String rentBike(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				 jo = bookingService.rentBike(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	@RequestMapping(value = "/endRide", method = RequestMethod.POST)
	@ResponseBody
	public String endRide(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				 jo = bookingService.endRide(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	@RequestMapping(value = "/getPastStationIds", method = RequestMethod.POST)
	@ResponseBody
	public String getPastStationIds(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				 jo = bookingService.getPastStationIds(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	@RequestMapping(value = "/getReportData", method = RequestMethod.POST)
	@ResponseBody
	public String getReportData(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				 jo = bookingService.getReportData(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}

	@RequestMapping(value = "/calculateCost", method = RequestMethod.POST)
	@ResponseBody
	public String calculateCost(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				 jo = bookingService.calculateCost(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	@RequestMapping(value = "/fetchFreeBikes", method = RequestMethod.POST)
	@ResponseBody
	public String fetchFreeBikes(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				 jo = bookingService.fetchFreeBikes(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	@RequestMapping(value = "/fetchAllRides", method = RequestMethod.POST)
	@ResponseBody
	public String fetchAllRides(@RequestBody String paramters) throws JSONException {
		JSONObject ji;
		JSONObject jo = new JSONObject();
		try {
			if(paramters != null) {
				ji = new JSONObject(paramters);
				 jo = bookingService.fetchAllRides(ji);
			}else {
				jo.put("status", Constants.fail);
				jo.put("response", "Invalid Arguments");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
}
