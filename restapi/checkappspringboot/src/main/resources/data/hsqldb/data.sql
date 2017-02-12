INSERT INTO users(userid, username, countrycode, phonenumber, email, passwd, reg_date) values(1, 'Rikin Patel', '+91', '9900577698', 'riks.lovein@gmail.com', 'Testing1', DATE'2017-12-02');
INSERT INTO users(userid, username, countrycode, phonenumber, email, passwd, reg_date) values(2, 'Ranganath Pabbisetty', '+91', '9911577698', 'riks2.lovein@gmail.com', 'Testing2', DATE'2017-12-05');

INSERT INTO groups(groupid, groupname, description, ownerid, create_date) values(1,'Spring Group', 'Spring Developer Group', 1, DATE'2017-12-02');
INSERT INTO groups(groupid, groupname, description, ownerid, create_date) values(2,'SOLR Group', 'SOLR Developer Group', 1, DATE'2017-12-02');
INSERT INTO groups(groupid, groupname, description, ownerid, create_date) values(3,'WebService Group', 'SOLR Developer Group', 2, DATE'2017-12-05');
INSERT INTO groups(groupid, groupname, description, ownerid, create_date) values(4,'Docker Group', 'Docker Developer Group', 2, DATE'2017-12-05');

INSERT INTO users_groups(userid, groupid) values (1,1);
INSERT INTO users_groups(userid, groupid) values (1,2);
INSERT INTO users_groups(userid, groupid) values (1,3);
INSERT INTO users_groups(userid, groupid) values (2,1);
INSERT INTO users_groups(userid, groupid) values (2,3);
INSERT INTO users_groups(userid, groupid) values (2,4);

