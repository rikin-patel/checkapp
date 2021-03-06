INSERT INTO users(userid, username, countrycode, phonenumber, email, passwd, reg_date) values(1, 'Rikin Patel', '+91', '9900577698', 'riks.lovein@gmail.com', 'Testing1', DATE'2017-12-02');
INSERT INTO users(userid, username, countrycode, phonenumber, email, passwd, reg_date) values(2, 'Ranganath Pabbisetty', '+91', '9911577698', 'riks2.lovein@gmail.com', 'Testing2', DATE'2017-12-05');

INSERT INTO groups(groupid, groupname, description, ownerid, create_date) values(1,'Spring Group', 'Spring Developer Group', 1, DATE'2017-12-02');
INSERT INTO groups(groupid, groupname, description, ownerid, create_date) values(2,'SOLR Group', 'SOLR Developer Group', 1, DATE'2017-12-02');
INSERT INTO groups(groupid, groupname, description, ownerid, create_date) values(3,'WebService Group', 'SOLR Developer Group', 2, DATE'2017-12-05');
INSERT INTO groups(groupid, groupname, description, ownerid, create_date) values(4,'Docker Group', 'Docker Developer Group', 2, DATE'2017-12-05');

INSERT INTO users_groups(userid, groupid, isowner, assign_date) values (1,1,1,DATE'2017-12-02');
INSERT INTO users_groups(userid, groupid, isowner, assign_date) values (1,2,1,DATE'2017-12-02');
INSERT INTO users_groups(userid, groupid, isowner, assign_date) values (1,3,0,DATE'2017-12-05');
INSERT INTO users_groups(userid, groupid, isowner, assign_date) values (2,1,0,DATE'2017-12-05');
INSERT INTO users_groups(userid, groupid, isowner, assign_date) values (2,3,1,DATE'2017-12-05');
INSERT INTO users_groups(userid, groupid, isowner, assign_date) values (2,4,1,DATE'2017-12-05');

insert into tasks(taskid, taskname, description, task_creator, create_date, complitionby_date, groupid) values (1,'Buy Spring Books', 'Buy Spring Books', 1, DATE'2017-12-02', DATE'2017-12-02', 1);
insert into tasks(taskid, taskname, description, task_creator, create_date, complitionby_date, groupid) values (2,'Spring Development', 'Spring Development', 1, DATE'2017-12-02', DATE'2017-12-02', 1);
insert into tasks(taskid, taskname, description, task_creator, create_date, complitionby_date, groupid) values (3,'Buy SOLR Books', 'Buy SOLR Books', 1, DATE'2017-12-02', DATE'2017-12-02', 2);
insert into tasks(taskid, taskname, description, task_creator, create_date, complitionby_date, groupid) values (4,'SOLR Development', 'SOLR Development', 1, DATE'2017-12-02', DATE'2017-12-02', 2);