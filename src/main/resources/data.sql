-- user in journal
insert into account(account_name, password) values ('springboot', 'springboot123');
insert into account(account_name, password) values ('springsecurity', 'springsecurity123');

-- journal data
insert into entry(title,summary,created) values ('title1','summary1','2016-01-02 00:00:00.00');
insert into entry(title,summary,created) values ('title2','summary2','2016-01-03 00:00:00.00');
insert into entry(title,summary,created) values ('title3','summary3','2016-02-02 00:00:00.00');