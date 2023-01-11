package ru.leonchik.city.web;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ru.leonchik.city.domain.PersonRequest;
import ru.leonchik.city.domain.PersonResponse;

import java.time.LocalDate;

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

/*@Path("/check")
public class CheckPersonService {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(@PathParam("id") int simpleId,
                                      @QueryParam("name") String simpleName) {
        return new PersonResponse();
    }*/

    /*@Path("/check")
    public class CheckPersonService {
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public PersonRequest checkPerson() {
            PersonRequest pr = new PersonRequest();

            pr.setSurName("Васильева");
            pr.setGivenName("Ирина");
            pr.setPatronymic("Петровна");
            pr.setDateOfBirth(LocalDate.of(1997, 8, 21));
            pr.setStreetCode(1);
            pr.setBuilding("271");
            pr.setExtension("2");
            pr.setApartment("4");

            return pr;
        }*/
/*
URL http://localhost:8080/city-register-1.0/rest/check

In postman extension (Chrome)
Header Content-Type application/json

{
  "apartment": "4",
  "building": "271",
  "dateOfBirth": "21.08.1997",
  "extension": "2",
  "givenName": "Ирина",
  "patronymic": "Петровна",
  "streetCode": 1,
  "surName": "Васильева"
}
 */


    @Path("/check")
    public class CheckPersonService {
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public PersonResponse checkPerson(PersonRequest request) {
            System.out.println(request.toString());
            return new PersonResponse();
        }
}