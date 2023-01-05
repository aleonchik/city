package ru.leonchik.city.web;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/check")
public class CheckPersonService {
    @GET
    public String checkPerson() {
        return "Simple STRING";
    }
}
