
INSERT INTO worker(name, birthday, level, salary)
VALUES ('Dmytro Lu', '1965-11-04', 'Trainee',900),
       ('Kateryna Niko', '1982-04-12', 'Junior',1200),
       ('Lexa Nova', '1997-01-24', 'Middle',2500),
       ('Ivan Ibanov', '2000-07-08', 'Trainee',900),
       ('Nik Black', '1993-11-04', 'Senior',4900),
       ('Vova Monk', '1999-12-31', 'Middle',3200),
       ('Valera Solo', '2004-11-04', 'Junior',1700),
       ('John Dow', '1998-05-15', 'Middle',2900),
       ('Kim Lee', '2001-10-01', 'Junior',1300),
       ('Sergey Petrenko', '1986-09-13', 'Middle',900),
       ('Natasha Kobleva', '2003-02-03', 'Junior',1500),
       ('Max Sun', '1994-03-22', 'Junior',1800);

INSERT INTO client(name)
VALUES ('Shura'),
       ('Logitec'),
       ('ASUS'),
       ('Fuji'),
       ('HSBC'),
       ('Medico');

INSERT INTO project(client_id, start_date, finish_date)
VALUES (1,'2022-10-11','2024-10-01'),
       (1,'2021-05-01','2026-12-31'),
       (2,'2020-01-05','2024-09-11'),
       (2,'2021-08-16','2023-05-01'),
       (3,'2022-10-11','2024-10-01'),
       (3,'2022-02-22','2023-12-01'),
       (3,'2018-06-01','2025-09-01'),
       (4,'2019-07-02','2023-07-03'),
       (4,'2022-09-07','2027-03-06'),
       (5,'2023-01-03','2023-08-01'),
       (5,'2023-02-16','2023-04-18'),
       (6,'2023-01-06','2026-07-18'),
       (6,'2021-10-11','2023-02-21');

INSERT INTO project_worker(project_id, worker_id)
VALUES (6,3),
       (3,2),
       (1,4),
       (2,1),
       (3,12),
       (3,5),
       (3,11),
       (3,10),
       (4,5),
       (4,6),
       (4,8),
       (5,5),
       (5,7),
       (6,5),
       (6,9),
       (6,1);