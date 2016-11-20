CREATE DATABASE `checkapp` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `groups` (
  `groupid` bigint(20) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(30) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `ownerid` bigint(20) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  PRIMARY KEY (`groupid`),
  KEY `ownerid` (`ownerid`),
  CONSTRAINT `groups_ibfk_1` FOREIGN KEY (`ownerid`) REFERENCES `users` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

CREATE TABLE `task_owner` (
  `taskid` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `assigned_date` date DEFAULT NULL,
  `assignedby` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`taskid`,`userid`),
  KEY `userid` (`userid`),
  KEY `assignedby` (`assignedby`),
  CONSTRAINT `task_owner_ibfk_1` FOREIGN KEY (`taskid`) REFERENCES `tasks` (`taskid`),
  CONSTRAINT `task_owner_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`),
  CONSTRAINT `task_owner_ibfk_3` FOREIGN KEY (`assignedby`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tasks` (
  `taskid` bigint(20) NOT NULL,
  `taskname` varchar(30) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `task_creator` bigint(20) DEFAULT NULL,
  `complitionby_date` date DEFAULT NULL,
  `iscompleted` tinyint(1) DEFAULT NULL,
  `complete_date` date DEFAULT NULL,
  `groupid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`taskid`),
  KEY `task_creator` (`task_creator`),
  KEY `groupid` (`groupid`),
  CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`task_creator`) REFERENCES `users` (`userid`),
  CONSTRAINT `tasks_ibfk_2` FOREIGN KEY (`groupid`) REFERENCES `groups` (`groupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `countrycode` varchar(5) NOT NULL,
  `phonenumber` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `reg_date` date DEFAULT NULL,
  `passwd` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

CREATE TABLE `users_groups` (
  `userid` bigint(20) NOT NULL,
  `groupid` bigint(20) NOT NULL,
  `isowner` tinyint(1) DEFAULT NULL,
  `assign_date` date DEFAULT NULL,
  PRIMARY KEY (`userid`,`groupid`),
  KEY `groupid` (`groupid`),
  CONSTRAINT `users_groups_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`),
  CONSTRAINT `users_groups_ibfk_2` FOREIGN KEY (`groupid`) REFERENCES `groups` (`groupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
