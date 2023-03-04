
CREATE TYPE level_worker AS ENUM ('Trainee', 'Junior', 'Middle', 'Senior');

CREATE TABLE worker
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(1000) NOT NULL CHECK ( LENGTH(name) >=2 AND LENGTH(name) <=1000 ),
    birthday DATE NOT NULL CHECK ( birthday > '1900-12-31'),
    level level_worker  NOT NULL ,
    salary INT CHECK ( salary >= 100 AND salary <=100000 )
);

CREATE TABLE client
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(1000) NOT NULL CHECK ( LENGTH(name) >= 2 AND LENGTH(name) <= 1000 )
);

CREATE TABLE  project
(
    id SERIAL PRIMARY KEY ,
    client_id INT REFERENCES client (id),
    start_date DATE,
    finish_date DATE
);
CREATE TABLE  project_worker
(
    project_id INT REFERENCES project(id),
    worker_id INT REFERENCES worker,
    PRIMARY KEY (project_id, worker_id)

);