package com.esports.manager.teams.entities;

import java.util.List;

public class SearchableTeam extends Team {
    private List<Member> members;

    public SearchableTeam(Team team) {
        super(team.getName(), team.getSlogan(), team.getTags());
        super.setId(team.getId());
    }

    public List<Member> getMembers() {
        return this.members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public int getSize() {
        return members.size();
    }
}
