drop table if exists t_clients cascade;
create table t_clients(
    "cli_id" serial not null,
    "cli_dni" varchar(10) not null,
    "cli_name" varchar(150) not null,
    "cli_age" int not null,
    "cli_genre" varchar(1) not null,
    "cli_direction" varchar(200),
    "cli_phone" varchar(10),
    "cli_username" varchar(20),
    "cli_password" varchar(255),
    "cli_state" boolean,
    "dtype" varchar(255) not null,
    unique ("cli_dni"),
    unique ("cli_username"),
    primary key ("cli_id")
);

drop table if exists t_accounts cascade;
create table t_accounts(
    "act_id" serial not null,
    "act_num" varchar(150) not null,
    "act_type" varchar(255) not null,
    "act_balance" float not null,
    "act_state" boolean,
    "act_cli_id" int not null,
    unique ("act_num"),
    primary key ("act_id"),
    constraint "fk_account_client"
        foreign key ("act_cli_id")
            references t_clients ("cli_id")
);

drop table if exists t_movements cascade;
create table t_movements(
    "mov_id" serial not null,
    "mov_date" timestamp not null,
    "mov_balance" float not null,
    "mov_value" float not null,
    "mov_type" varchar(255) not null,
    "mov_state" boolean,
    "mov_act_id" int not null,
    primary key ("mov_id"),
    constraint "fk_movement_account"
        foreign key ("mov_act_id")
            references t_accounts ("act_id")
);

INSERT INTO t_clients (dtype, cli_age, cli_direction, cli_dni, cli_genre, cli_name, cli_phone, cli_password, cli_state, cli_username) VALUES('Client', 23, 'Colombia y paraguay', '0150749059', 'M', 'Bryam Vega', '0985164142', 'nttdata.123', true, 'bvega');
INSERT INTO t_clients (dtype, cli_age, cli_direction, cli_dni, cli_genre, cli_name, cli_phone, cli_password, cli_state, cli_username) VALUES('Client', 23, 'Colombia y paraguay', '0101093037', 'M', 'Gabriel Reinoso', '0985164142', 'nttdata.123', true, 'greino');

INSERT INTO t_accounts (act_balance, act_num, act_state, act_type, act_cli_id)VALUES(945, '00001', true,'AHORRO' , 1);

INSERT INTO t_movements (mov_date, mov_balance ,mov_value, mov_state, mov_type, mov_act_id)VALUES('2022-07-15', 1000, 60, true, 'DEPOSITO', 1);
INSERT INTO t_movements (mov_date, mov_balance ,mov_value, mov_state, mov_type, mov_act_id)VALUES('2022-07-30', 1030,30, true, 'DEPOSITO', 1);
INSERT INTO t_movements (mov_date, mov_balance ,mov_value, mov_state, mov_type, mov_act_id)VALUES('2022-08-20', 910, 120, true, 'RETIRO', 1);
INSERT INTO t_movements (mov_date, mov_balance ,mov_value, mov_state, mov_type, mov_act_id)VALUES('2022-09-02', 885, 25, true, 'RETIRO', 1);
INSERT INTO t_movements (mov_date, mov_balance ,mov_value, mov_state, mov_type, mov_act_id)VALUES('2022-09-08', 945, 60, true, 'DEPOSITO', 1);