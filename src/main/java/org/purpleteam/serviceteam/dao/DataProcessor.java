package org.purpleteam.serviceteam.dao;

import org.purpleteam.serviceteam.entity.Role;
import org.purpleteam.serviceteam.entity.Team;
import org.purpleteam.serviceteam.entity.User;

import java.util.List;

public class DataProcessor {
    public static DataProcessor instance;
    private final UserDao userDao = new UserDao();
    private final TeamDao teamDao = new TeamDao();
    private final RoleDao roleDao = new RoleDao();

    public static DataProcessor getInstance() {
        if (instance == null)
            return new DataProcessor();
        return instance;
    }
    public int createRole(Role role) {return roleDao.create(role);}
    public Role getRoleById(int id) {return roleDao.findById(id);}
    public int updateRole(Role role) {return roleDao.update(role);}
    public int deleteRole(Role role) {return roleDao.delete(role);}
    public List<Role> getAllRoles() {return roleDao.findAll();}

    public int createUser(User user) {return userDao.create(user);}
    public User getUserById(int id) {return userDao.findById(id);}
    public int updateUser(User user) {return userDao.update(user);}
    public int deleteUser(User user) {return userDao.delete(user);}
    public List<User> getAllUsers() {return userDao.findAll();}
    public User getUserByName(String name) {return  userDao.findByName(name);}
    public User getByTelegramId(long id) {return userDao.findByTelegramId(id);}

    public int createTeam(Team team) {return teamDao.create(team);}
    public Team getTeamById(int id) {return teamDao.findById(id);}
    public int updateTeam(Team team) {return teamDao.update(team);}
    public int deleteTeam(Team team) {return teamDao.delete(team);}
    public List<Team> getAllTeams() {return teamDao.findAll();}
    public Team getTeamByName(String name) {return  teamDao.findByName(name);}

}
