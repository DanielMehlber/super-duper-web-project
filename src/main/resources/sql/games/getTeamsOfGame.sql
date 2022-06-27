SELECT id, name, slogan, tags FROM team
    INNER JOIN game_team ON team.id = game_team.team_id
    WHERE game_team.game_id = ?;