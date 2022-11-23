package ru.leonchik.city.dao;

import ru.leonchik.city.domain.PersonRequest;
import ru.leonchik.city.domain.PersonResponse;
import ru.leonchik.city.exception.PersonCheckException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCheckDao {
    private static final String SQL_REQUEST = "";

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_REQUEST)) {

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }
        } catch (SQLException ex) {
            throw new PersonCheckException(ex);
        }

        return response;
    }

    private Connection getConnection() {
        return null;
    }
}
