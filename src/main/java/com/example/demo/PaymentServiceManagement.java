package com;

import model.PaymentManagement;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")

public class PaymentServiceManagement {
	PaymentManagement PaymentObj = new PaymentManagement();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("pyId") String pyId, 
			@FormParam("payDes") String payDes, 
			@FormParam("payDate") String payDate,
			@FormParam("payAmount") String payAmount) {
		String output = PaymentObj.insertPayment(pyId, payDes, payDate, payAmount);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject PayObject = new JsonParser().parse(paymentData).getAsJsonObject();

		// Read the values from the JSON object
		String pyId = PayObject.get("pyId").getAsString();
		String payDes = PayObject.get("payDes").getAsString();
		String payDate = PayObject.get("payDate").getAsString();
		String payAmount = PayObject.get("payAmount").getAsString();
		
		String output = PaymentObj.updatePayment(pyId, payDes, payDate, payAmount);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element
		String pyId = doc.select("pyId").text();
		String output = PaymentObj.deletePayment(pyId);
		return output;
	}
}
