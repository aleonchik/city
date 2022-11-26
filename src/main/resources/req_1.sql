--SELECT temporal, upper(p.sur_name), upper('Васильев' COLLATE "en_US") FROM cr_address_person ap
--    INNER JOIN cr_person p ON p.person_id = ap.person_id
--    INNER JOIN cr_address a ON a.address_id = ap.address_id
--    WHERE p.sur_name = 'Васильев' AND p.given_name = 'Павел' AND p.patronymic = 'Николаевич' and p.date_of_birth = '1995-03-18'
--        AND a.street_code = 1 AND a.building = '10' and a.extension = '2' and a.apartment = '121';

--SELECT temporal FROM cr_address_person ap
--    INNER JOIN cr_person p ON p.person_id = ap.person_id
--    INNER JOIN cr_address a ON a.address_id = ap.address_id
--    WHERE upper(p.sur_name) = upper('Васильев') AND upper(p.given_name) = upper('Павел')
--        AND upper(p.patronymic) = upper('Николаевич') and p.date_of_birth = '1995-03-18'
--        AND a.street_code = 1
--        AND upper(a.building) = upper('10') and upper(a.extension) = upper('2') and upper(a.apartment) = upper('121');

SELECT temporal FROM cr_address_person ap
    INNER JOIN cr_person p ON p.person_id = ap.person_id
    INNER JOIN cr_address a ON a.address_id = ap.address_id
    WHERE upper(p.sur_name) = upper('Васильев') AND upper(p.given_name) = upper('Павел')
        AND upper(p.patronymic) = upper('Николаевич') and p.date_of_birth = '1995-03-18'
        AND a.street_code = 1
        AND upper(a.building) = upper('10') and upper(a.extension) = upper('2') and upper(a.apartment) = upper('121');

