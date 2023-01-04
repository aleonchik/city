package ru.leonchik.city.dao;

import ru.leonchik.city.domain.PersonRequest;
import ru.leonchik.city.domain.PersonResponse;
import ru.leonchik.city.exception.PersonCheckException;

import java.sql.*;

public class PersonCheckDao {
    private static final String SQL_REQUEST = "SELECT temporal FROM cr_address_person ap " +
            "    INNER JOIN cr_person p ON p.person_id = ap.person_id " +
            "    INNER JOIN cr_address a ON a.address_id = ap.address_id " +
            "    WHERE " +
            "        CURRENT_DATE >= ap.start_date AND (CURRENT_DATE <= ap.end_date OR ap.end_date IS null) " +
            "        AND upper(p.sur_name) = upper(?) " +
            "        AND upper(p.given_name) = upper(?) " +
            "        AND upper(p.patronymic) = upper(?) " +
            "        AND p.date_of_birth = ? " +
            "        AND a.street_code = ? " +
            "        AND upper(a.building) = upper(?) ";
//            "        AND upper(a.extension) = upper(?) " +
//            "        AND upper(a.apartment) = upper(?)";

    public PersonCheckDao() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        String sql = SQL_REQUEST;

        if(request.getExtension() != null) {
            sql += "        AND upper(a.extension) = upper(?) ";
        } else {
            sql += " and a.extension is null ";
        }

        if(request.getApartment() != null) {
            sql += "        AND upper(a.apartment) = upper(?) ";
        } else {
            sql += " and a.apartment is null ";
        }

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            int count = 1;

            stmt.setString(count++, request.getSurName());
            stmt.setString(count++, request.getGivenName());
            stmt.setString(count++, request.getPatronymic());
            stmt.setDate(count++, java.sql.Date.valueOf(request.getDateOfBirth()));
            stmt.setInt(count++, request.getStreetCode());
            stmt.setString(count++, request.getBuilding());

            if(request.getExtension() != null) {
                stmt.setString(count++, request.getExtension());
            }

            if(request.getApartment() != null) {
                stmt.setString(count++, request.getApartment());
            }

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }
        } catch (SQLException ex) {
            throw new PersonCheckException(ex);
        }

        return response;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost/alexey", "alexey", "alexey");
        // return null;
    }
}
