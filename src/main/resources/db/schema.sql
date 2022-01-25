DROP TABLE IF EXISTS Roles CASCADE;

CREATE TABLE IF NOT EXISTS Roles
(
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(20) NOT NULL);

DROP TABLE IF EXISTS Users CASCADE;

CREATE TABLE IF NOT EXISTS Users (
                                     user_id SERIAL PRIMARY KEY,
                                     user_telegram_id BIGINT NOT NULL,
                                     user_name VARCHAR(20),
                                     user_role INT,
                                     user_status INT,
                                     user_team INT,
                                     FOREIGN KEY (user_role) REFERENCES Roles(role_id));

DROP TABLE IF EXISTS Teams CASCADE;

CREATE TABLE IF NOT EXISTS Teams (
                                     team_id SERIAL PRIMARY KEY,
                                     team_name VARCHAR(20) NOT NULL,
                                     team_lead INT,
                                     team_status INT default 0,
                                     FOREIGN KEY (team_lead) REFERENCES Users(user_id));

--DROP TABLE IF EXISTS UsersTeams CASCADE;
--
--CREATE TABLE IF NOT EXISTS UsersTeams (
--   ut_team_id SERIAL,
--   ut_user_id SERIAL,
--   FOREIGN KEY (ut_team_id) REFERENCES Teams(team_id),
--   FOREIGN KEY (ut_user_id) REFERENCES Users(user_id));

INSERT INTO Roles (role_name) VALUES ('USER'), ('ADMIN');