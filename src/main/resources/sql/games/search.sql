-- Author: Daniel Mehlber
SELECT * FROM game
    WHERE REGEXP_LIKE(name, ?) OR REGEXP_LIKE(description, ?);