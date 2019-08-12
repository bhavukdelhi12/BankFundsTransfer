package com.banktransfer.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * This class contains the default API that is invoked whenever the server is started up.
 * This can also be declared using an index.html file as part of the web.xml file.
 */
@Path("/")
public class Default {

    // This method is the default method that is invoked whenever server starts up for the first time.
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "XYZ Banking System" + "</title>"
                + "<body><h1>" + "Welcome to XYZ Banking System" + "</h1>" + "<p>" + "Please pass the source and destination account number" +
                " along with the amount to be transferred as query params for the transfer to take place. The source " +
                "and destination accounts have ID's having value between [0-9] only and initial amount in each of them" +
                " is equal to " + "500$." + "</body></p>" + "</html> ";
    }
}
