package org.purpleteam.serviceteam;

import org.purpleteam.serviceteam.dao.DataProcessor;
import org.purpleteam.serviceteam.entity.Team;
import org.purpleteam.serviceteam.entity.User;

public class ServiceTeam {

    public static void main(String[] args) {
        DataProcessor dp = new DataProcessor();
        User user = dp.getUserByName("Victor");
//        dp.createUser(user);
        Team team = dp.getTeamByName("Blue");
        team.setTeamLead(user);
        team.getUsers().add(user);
        user.setTeam(team);
//        dp.createTeam(team);
        System.out.println(user);
        System.out.println(team);
    }
}
