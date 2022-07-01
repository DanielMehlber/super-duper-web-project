-- Author: Daniel Mehlber
SELECT game.* FROM game_team gt LEFT JOIN game ON gt.game_id = game.id
    WHERE team_id = ?