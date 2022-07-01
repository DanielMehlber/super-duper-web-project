-- Author: Maximilian Rublik
SELECT * FROM team WHERE REGEXP_LIKE(name, ?);