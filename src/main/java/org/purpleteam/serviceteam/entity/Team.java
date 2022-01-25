package org.purpleteam.serviceteam.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "Teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int team_id;
    @Column
    private String team_name;
    @Column
    private int team_status;
    @OneToMany (fetch = FetchType.EAGER, mappedBy = "team")
    private Set<User> users;

    protected Team(){}

    public Team(String name) {
        this.team_name = name;
        this.team_status = 0;
    }

    @OneToOne
    @JoinColumn(name = "team_lead", referencedColumnName = "user_id")
    private User teamLead;

    public User getTeamLead() {
        return this.teamLead;
    }

    public void setTeamLead(User teamLead) {
        this.teamLead = teamLead;
    }

    public int getTeam_id() {
        return team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public int getTeam_status() {
        return team_status;
    }

    public void setTeam_status(short team_status) {
        this.team_status = team_status;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Team{" +
                "team_id=" + team_id +
                ", team_name='" + team_name + '\'' +
                ", teamLead=" + teamLead +
                ", team_status=" + team_status +
                ", users=" + users +
                '}';
    }
}
