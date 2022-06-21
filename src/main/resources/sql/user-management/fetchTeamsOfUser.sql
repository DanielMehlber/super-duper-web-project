SELECT id FROM team
    INNER JOIN member ON member.teamId  = team.id
    WHERE member.username = ?;