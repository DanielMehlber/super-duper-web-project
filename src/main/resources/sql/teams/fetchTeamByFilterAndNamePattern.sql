SELECT team.*, game_team.game_id FROM team LEFT JOIN game_team ON team.id = game_team.team_id
    WHERE REGEXP_LIKE(name, ?) AND game_id = ?;