package ru.leonchik.city.web;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.leonchik.city.dao.PersonCheckDao;
import ru.leonchik.city.dao.PoolConnectionBuilder;
import ru.leonchik.city.domain.PersonRequest;
import ru.leonchik.city.domain.PersonResponse;
import ru.leonchik.city.exception.PersonCheckException;

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
    @Singleton
    public class CheckPersonService {

        private static final Logger logger = LoggerFactory.getLogger(CheckPersonService.class);

        private PersonCheckDao dao;

        @PostConstruct
        public void init() {
            logger.info("SERVICE is created");
            dao = new PersonCheckDao();
            dao.setConnectionBuilder(new PoolConnectionBuilder());
        }

        /*@PreDestroy
        public void destroy() {
            logger.info("FINISH");
        }*/

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
            logger.info(request.toString());
//            throw new PersonCheckException("EXCEPTION: ");
            return dao.checkPerson(request);
        }
}