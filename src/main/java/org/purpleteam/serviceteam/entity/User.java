package org.purpleteam.serviceteam.entity;

import javax.persistence.*;

@Entity
@Table (name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column
    private long user_telegram_id;
    @Column
    private String user_name;
    @OneToOne
    @JoinColumn(name = "user_role", referencedColumnName = "role_id")
    private Role user_role;
    @Column
    private int user_status;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_team")
    private Team team;

    protected User() {}

    public User(long telegram_id, String name, Role role, int status) {
        this.user_telegram_id = telegram_id;
        this.user_name = name;
        this.user_role = role;
        this.user_status = status;
    }

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teamLead")
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public long getUser_telegram_id() {
        return user_telegram_id;
    }

    public void setUser_telegram_id(long user_telegram_id) {
        this.user_telegram_id = user_telegram_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Role getUser_role() {
        return user_role;
    }

    public void setUser_role(Role user_role) {
        this.user_role = user_role;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(short user_status) {
        this.user_status = user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    @Override
    public String toString() {
        return "{" +
                "telegram_id=" + user_telegram_id +
                ", name=\"" + user_name + "\"" +
                ", role=" + user_role +
                ", status=" + user_status +
                (null == team.getTeam_name() ? "" :
                ", team=\"" + team.getTeam_name() + "\"") +
                '}';
    }
}
