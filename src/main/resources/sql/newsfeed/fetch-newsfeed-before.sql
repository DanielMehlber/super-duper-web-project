-- Author: Daniel Mehlber
SELECT * FROM newsfeed
        WHERE date < ?
        ORDER BY date DESC
        LIMIT ?;