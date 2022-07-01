-- Author: Daniel Mehlber
SELECT id, name, description FROM game
ORDER BY RAND()
    LIMIT 1;