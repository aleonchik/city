package ru.leonchik.city.dao;

import ru.leonchik.city.domain.PersonRequest;
import ru.leonchik.city.domain.PersonResponse;
import ru.leonchik.city.exception.PersonCheckException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCheckDao {
    private static final String SQL_REQUEST = "SELECT temporal FROM cr_address_person ap " +
            "    INNER JOIN cr_person p ON p.person_id = ap.person_id " +
            "    INNER JOIN cr_address a ON a.address_id = ap.address_id " +
            "    WHERE upper(p.sur_name) = upper(?) AND upper(p.given_name) = upper(?) " +
            "        AND upper(p.patronymic) = upper(?) and p.date_of_birth = ? " +
            "        AND a.street_code = ? " +
            "        AND upper(a.building) = upper(?) and upper(a.extension) = upper(?) and upper(a.apartment) = upper(?)";

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
