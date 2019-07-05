package com.journaldev.spring.controller;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.journaldev.spring.dbconnection.DBConnection;
import com.journaldev.spring.model.User;

@RestController
public class RestApiController {

	@Autowired
	private Users user;
	@Autowired
	private MessageBean msg;

	RestStatementAPI restApi = new RestStatementAPI();

	/** Get api for displaying all users **/
	@RequestMapping(value = "/displayUsers", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<Integer, LinkedList<Users>> getUsers() throws Exception {
		System.out.println("display uesrs");
		DBConnection dbcon = new DBConnection();
		Connection con = dbcon.connectToDB();
		Map<Integer, LinkedList<Users>> allData = restApi.getUsersDetails(con);
		con.close();
		return allData;
	}

	/**
	 * Get api for displaying all users based on id(0-normal users,1-admin
	 * users,2-merchants)
	 **/
	@RequestMapping(value = "/displayViewers/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<Integer, LinkedList<Users>> getUsersBasedOnID(@PathVariable int id) throws Exception {

		System.out.println("display users based on id");
		DBConnection dbcon = new DBConnection();
		Connection con = dbcon.connectToDB();
		Map<Integer, LinkedList<Users>> allData = restApi.displayUsersBasedOnID(con, id);
		con.close();
		return allData;
	}

	/** Post Api for adding users **/
	/*
	 * request body required { "username": "value", "password": "value",
	 * "firstname": "value", "lastname": "value", "contact": "value", "address":
	 * "value", "email": "value", "usertype": int val }
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/createUsers", headers = { "content-type=application/json" })
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public MessageBean addTopics(@RequestBody Users usersjson) throws Exception {

		System.out.println("inserting users");
		DBConnection dbcon = new DBConnection();
		Connection con = dbcon.connectToDB();
		int i = restApi.insertUsers(con, usersjson);
		con.close();
		if (i == 1) {
			msg.setStatus(202);
			msg.setMessage("Inserted successfully");
			return msg;
		} else {
			msg.setStatus(500);
			msg.setMessage("Inserted unsuccessfully.Please try it later.");
			return msg;
		}

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/changeUsersType")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public MessageBean updateTopics(@RequestBody Users user) throws Exception {
		System.out.println("updating user");
		DBConnection dbcon = new DBConnection();
		Connection con = dbcon.connectToDB();
		int i = restApi.updateUsers(con, user, user.getEmail());
		System.out.println(i);
		con.close();
		if (i == 1) {
			msg.setStatus(200);
			msg.setMessage("Updated successfully");
			return msg;
		} else {
			msg.setStatus(500);
			msg.setMessage("Updated unsuccessfully or Already updated.");
			return msg;
		}
	}
}
