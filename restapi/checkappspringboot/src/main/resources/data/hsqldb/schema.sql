DROP TABLE tasks IF EXISTS;
DROP TABLE users_groups IF EXISTS;
DROP TABLE groups IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE users (
  userid bigint generated by default as identity (start with 1, increment by 1) not null,
  username varchar(50) NOT NULL,
  countrycode varchar(5) NOT NULL,
  phonenumber varchar(10) NOT NULL,
  email varchar(50) NOT NULL,
  reg_date TIMESTAMP DEFAULT NULL,
  passwd varchar(100) DEFAULT NULL,
  PRIMARY KEY (userid)
);

CREATE TABLE groups (
  groupid bigint generated by default as identity (start with 1, increment by 1) not null,
  ownerid bigint NOT NULL,
  groupname varchar(50) NOT NULL,
  description varchar(100) NOT NULL,
  create_date TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (groupid),
  CONSTRAINT owner_1 FOREIGN KEY (ownerid) REFERENCES users (userid)
);

CREATE TABLE users_groups (
  userid bigint NOT NULL,
  groupid bigint NOT NULL,
  isowner boolean DEFAULT NULL,
  assign_date TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (userid,groupid),
  CONSTRAINT users_groups_ibfk_1 FOREIGN KEY (userid) REFERENCES users (userid),
  CONSTRAINT users_groups_ibfk_2 FOREIGN KEY (groupid) REFERENCES groups (groupid)
);

CREATE TABLE tasks (
  taskid bigint generated by default as identity (start with 1, increment by 1) NOT NULL,
  taskname varchar(50) NOT NULL,
  description varchar(100) DEFAULT NULL,
  task_creator bigint DEFAULT NULL,
  create_date timestamp default null,
  complitionby_date timestamp DEFAULT NULL,
  groupid bigint DEFAULT NULL,
  PRIMARY KEY (taskid),
  CONSTRAINT tasks_ibfk_1 FOREIGN KEY (task_creator) REFERENCES users (userid),
  CONSTRAINT tasks_ibfk_2 FOREIGN KEY (groupid) REFERENCES groups (groupid)
);