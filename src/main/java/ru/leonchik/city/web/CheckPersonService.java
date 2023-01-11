package ru.leonchik.city.web;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ru.leonchik.city.domain.PersonResponse;

/**
 * http://localhost:8080/city-register-1.0/rest/check/155?name=K155
 * SimpleId = 155 : simpleName = K155
 */

/*@Path("/check")
public class CheckPersonService {
    @GET
    @Path("/{id}")
    public String checkPerson(@PathParam("id") int simpleId,
                              @QueryParam("name") String simpleName) {
        return "SimpleId = " + simpleId + " : " + "simpleName = " + simpleName;
    }
}*/

@Path("/check")
public class CheckPersonService {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(@PathParam("id") int simpleId,
                                      @QueryParam("name") String simpleName) {
        return new PersonResponse();
    }
}