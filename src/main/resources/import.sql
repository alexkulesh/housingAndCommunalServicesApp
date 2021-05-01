insert into credentials (active, create_date, password, id) values ('true', '2021-01-26 20:25:31.198', '123', 1);
insert into credentials (active, create_date, password, id) values ('true', '2021-01-26 20:25:31.198', '123', 2);
insert into credentials (active, create_date, password, id) values ('true', '2021-01-26 20:25:31.198', '123', 3);
insert into credentials (active, create_date, password, id) values ('true', '2021-01-26 20:25:31.198', '123', 4);
insert into credentials (active, create_date, password, id) values ('true', '2021-01-26 20:25:31.198', '123', 5);


insert into users_table ( email, first_name, last_name, birth_date, phone_number, role, id) values ( 'alex.kulesh@gmail.com', 'Александр', 'Кулеш', '04-05-1995', '+375333256355', 2, 1);
insert into users_table ( email, first_name, last_name, birth_date, phone_number,  role, id) values ( 'juri@hs.com', 'Юрий', 'Барановский', '09-02-1990', '+37533414113', 1, 2);
insert into users_table ( email, first_name, last_name, birth_date, phone_number,  role, id) values ( 'alex@hs.com', 'Алексей', 'Иванов', '02-03-1980', '+375334444113', 1, 3);
insert into users_table ( email, first_name, last_name, birth_date, phone_number, role, id) values ( 'sergey@hs.com', 'Сергей', 'Барташевич', '04-09-1987', '+375297652111', 1, 4);
insert into users_table ( email, first_name, last_name, birth_date, phone_number,  role, id) values ( 'maria@hs.com', 'Мария', 'Василевич', '01-03-2000', '+375442345132', 0, 5);


insert into users_table_credentials (u1, u2) values (1, 1);
insert into users_table_credentials (u1, u2) values (2, 2);
insert into users_table_credentials (u1, u2) values (3, 3);
insert into users_table_credentials (u1, u2) values (4, 4);
insert into users_table_credentials (u1, u2) values (5, 5);


insert into request_form_table (completion_date , power_supply_work_scale, repair_work_scale, water_supply_work_scale, user_id, id) values ('05.02.2021', 1, 2, 1, 1, 1);
insert into request_form_table (completion_date , power_supply_work_scale, repair_work_scale, water_supply_work_scale, user_id, id) values ('06.02.2021', 3, 4, 1, 5, 2);

insert into users_table_requests (r1, r2) values (1, 1);
insert into users_table_requests (r1, r2) values (5, 2);

insert into workers_table (is_busy, worker_role, user_id, work_brigade_id, id) values (false, 0, 2, null, 1);
insert into workers_table (is_busy, worker_role, user_id, work_brigade_id, id) values (false, 1, 3, null, 2);
insert into workers_table (is_busy, worker_role, user_id, work_brigade_id, id) values (false, 2, 4, null, 3);


SELECT setval(pg_get_serial_sequence('request_form_table', 'id'), coalesce(max(id),0) + 1, false) FROM request_form_table;
SELECT setval(pg_get_serial_sequence('users_table', 'id'), coalesce(max(id),0) + 1, false) FROM users_table;
SELECT setval(pg_get_serial_sequence('credentials', 'id'), coalesce(max(id),0) + 1, false) FROM credentials;
SELECT setval(pg_get_serial_sequence('workers_table', 'id'), coalesce(max(id),0) + 1, false) FROM workers_table;
