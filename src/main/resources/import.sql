insert into credentials (active, create_date, password, id) values ('true', '2021-01-26 20:25:31.198', '123', 1);
insert into credentials (active, create_date, password, id) values ('true', '2021-01-26 20:25:31.198', '123', 2);
insert into credentials (active, create_date, password, id) values ('true', '2021-01-26 20:25:31.198', '123', 3);
insert into credentials (active, create_date, password, id) values ('true', '2021-01-26 20:25:31.198', '123', 4);
insert into credentials (active, create_date, password, id) values ('true', '2021-01-26 20:25:31.198', '123', 5);


insert into users_table ( email, first_name, last_name, phone_number, role, id) values ( 'alex.kulesh@gmail.com', 'Александр', 'Кулеш', '+375333256355', 3, 1);
insert into users_table ( email, first_name, last_name, phone_number, role, id) values ( 'juri@hs.com', 'Юрий', 'Барановский', '+37533414113', 2, 2);
insert into users_table ( email, first_name, last_name, phone_number, role, id) values ( 'alex@hs.com', 'Алексей', 'Иванов', '+375334444113', 2, 3);
insert into users_table ( email, first_name, last_name, phone_number, role, id) values ( 'sergey@hs.com', 'Сергей', 'Барташевич','+375297652111', 2, 4);
insert into users_table ( email, first_name, last_name, phone_number, role, id) values ( 'maria@hs.com', 'Мария', 'Василевич', '+375442345132', 1, 5);


insert into users_table_credentials (u1, u2) values (1, 1);
insert into users_table_credentials (u1, u2) values (2, 2);
insert into users_table_credentials (u1, u2) values (3, 3);
insert into users_table_credentials (u1, u2) values (4, 4);
insert into users_table_credentials (u1, u2) values (5, 5);


insert into request_form_table (completion_date , power_supply_work_scale, repair_work_scale, water_supply_work_scale , user_id, id) values ('05.02.2021', 1, 2, 1, 1, 1);
insert into request_form_table (completion_date , power_supply_work_scale, repair_work_scale, water_supply_work_scale , user_id, id) values ('06.02.2021', 3, 4, 1, 5, 2);

insert into workers_table (is_busy, worker_role, user_id, work_brigade_id, id) values (default, 0, 2, null, 1);
insert into workers_table (is_busy, worker_role, user_id, work_brigade_id, id) values (default, 1, 3, null, 2);
insert into workers_table (is_busy, worker_role, user_id, work_brigade_id, id) values (default, 2, 4, null, 3);
