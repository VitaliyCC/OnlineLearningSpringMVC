Drop table Iogin_info
/
Drop table Admin
/
Drop table Connecting_Teacher
/
Drop table Connecting_Student
/
Drop table Review
/
Drop table Report
/
Drop table Task
/
Drop table Subject
/
Drop table Teacher
/
Drop table Students
/


-- Create Types section


-- Create Tables section


Create table Students (
                          student_id Integer NOT NULL ,
                          id Integer Default 0 NOT NULL  UNIQUE ,
                          surname Varchar2 (20) NOT NULL ,
                          name Varchar2 (20) NOT NULL ,
                          patronymic Varchar2 (30),
                          primary key (student_id)
)
/

Create table Teacher (
                         teacher_id Integer NOT NULL ,
                         id Integer Default 0 NOT NULL  UNIQUE ,
                         surname Varchar2 (20) NOT NULL ,
                         name Varchar2 (20) NOT NULL ,
                         salary Integer,
                         patronymic Varchar2 (30),
                         primary key (teacher_id)
)
/

Create table Subject (
                         subject_id Integer Default -1 NOT NULL ,
                         subject_name Varchar2 (20) NOT NULL  UNIQUE ,
                         semester Varchar2 (30) NOT NULL ,
                         max_grade Integer,
                         primary key (subject_id)
)
/

Create table Task (
                      task_name Varchar2 (30) NOT NULL  UNIQUE ,
                      subject_id Integer Default -1 NOT NULL ,
                      subject Varchar2 (50) NOT NULL ,
                      max_grade Integer,
                      primary key (task_name,subject_id)
)
/

Create table Report (
                        report_id Integer NOT NULL  UNIQUE ,
                        solution Varchar2 (50) NOT NULL ,
                        send_date Date NOT NULL ,
                        student_id Integer NOT NULL ,
                        task_name Varchar2 (30) NOT NULL ,
                        primary key (report_id,student_id,task_name)
)
/

Create table Review (
                        review_id Integer NOT NULL  UNIQUE ,
                        teacher_id Integer NOT NULL ,
                        report_id Integer NOT NULL ,
                        grade Integer NOT NULL ,
                        time_review Date NOT NULL ,
                        primary key (review_id,teacher_id,report_id)
)
/

Create table Connecting_Student (
                                    student_id Integer NOT NULL ,
                                    subject_id Integer Default -1 NOT NULL ,
                                    id Integer NOT NULL ,
                                    primary key (student_id,subject_id,id)
)
/

Create table Connecting_Teacher (
                                    teacher_id Integer NOT NULL ,
                                    subject_id Integer Default -1 NOT NULL ,
                                    id Integer NOT NULL ,
                                    primary key (teacher_id,subject_id,id)
)
/

Create table Admin (
                       admin_id Integer NOT NULL ,
                       id Integer Default 0 NOT NULL  UNIQUE ,
                       name Varchar2 (30) NOT NULL ,
                       surname Varchar2 (30) NOT NULL ,
                       patronymic Varchar2 (30),
                       primary key (admin_id)
)
/

Create table Iogin_info (
                            id Integer Default 0 NOT NULL ,
                            password Varchar2 (500) NOT NULL ,
                            username Varchar2 (30) NOT NULL ,
                            role Varchar2 (30) NOT NULL ,
                            primary key (id)
)
/


-- Create Alternate keys section


-- Create Foreign keys section

Alter table Report add  foreign key (student_id) references Students (student_id)  on delete cascade
/

Alter table Connecting_Student add  foreign key (student_id) references Students (student_id)  on delete cascade
/

Alter table Review add  foreign key (teacher_id) references Teacher (teacher_id)  on delete set null
/

Alter table Connecting_Teacher add  foreign key (teacher_id) references Teacher (teacher_id)  on delete cascade
/

Alter table Connecting_Student add  foreign key (subject_id) references Subject (subject_id)  on delete cascade
/

Alter table Connecting_Teacher add  foreign key (subject_id) references Subject (subject_id)  on delete cascade
/

Alter table Task add  foreign key (subject_id) references Subject (subject_id)  on delete cascade
/

Alter table Report add  foreign key (task_name) references Task (task_name)  on delete cascade
/

Alter table Review add  foreign key (report_id) references Report (report_id)  on delete cascade
/


-------------------------------
INSERT INTO Iogin_info (id, password, username, role)
VALUES (1, '$2a$12$Kvc6ZRhW.fZYTc4w9mRIA.yCjVGUs0ie.jgm4K.16Ktl.AktqWf.m', 'VITALIIS', 'STUDENT');
INSERT INTO Iogin_info (id, password, username, role)
VALUES (2, '$2a$12$9CskWp6kiASko4rai6CuO.X8inijH.bv5g2IGRU6MqqCVIPgFb58O', 'VITALIIA', 'ADMIN');
INSERT INTO Iogin_info (id, password, username, role)
VALUES (3, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'VITALIIT', 'TEACHER');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC)
VALUES ('0', '1', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO TEACHER (teacher_id, id, surname, name, salary, patronymic)
VALUES (0, 3, 'KOVAL', 'VITALIY', 5000, 'V');
INSERT INTO Admin (admin_id, id, name, surname)
VALUES (0, 2, 'PETRO', 'STEPANENCO');

INSERT INTO SUBJECT (subject_id, subject_name, semester, max_grade)
VALUES (0, 'ACADEMIC INTEGRITY', 4, 100);
INSERT INTO Connecting_Teacher (id,teacher_id, subject_id)
VALUES (0,0, 0);
INSERT INTO Connecting_Student (id,student_id, subject_id)
VALUES (0,0, 0);
INSERT INTO TASK (TASK_NAME, SUBJECT_ID, SUBJECT, MAX_GRADE)
VALUES ('LAB#1', 0, 'https://mix.sumdu.edu.ua/2_ERD.pdf', '10');

INSERT INTO Report (REPORT_ID, SOLUTION, SEND_DATE, STUDENT_ID, TASK_NAME)
VALUES (0, 'https://mix.sumdu.edu.ua/index.html1', ' 19-02-22', 0, 'LAB#1');

INSERT INTO Review (review_id, teacher_id, report_id, grade, time_review)
VALUES (0, 0, 0, 6, ' 25-02-22');
------------------------------------------
Commit;
--------------------------------------------
delete from Connecting_Student
where subject_id is not null ;
delete from SUBJECT where SUBJECT_ID =0;
select *
from Connecting_Student;

select *
from Review
where report_id = 0;
select *
from Students
where student_id = 0;
select SB.subject_id, SB.subject_name, SB.semester, SB.max_grade
from Subject SB
         join CONNECTING_STUDENT CS
              on SB.subject_id = CS.SUBJECT_ID
         join STUDENTS S
             on CS.student_id = S.STUDENT_ID
where S.student_id=0;
SELECT * from STUDENTs NATURAL JOIN Iogin_info Where student_id=0;
delete from Students where student_id =0;
delete from Connecting_Student where student_id =0;
delete from Report where student_id =0;
delete from Iogin_info where id =0;

UPDATE Students set  ID=?, SURNAME=?, NAME=?, PATRONYMIC=? where student_id =?;

INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('5', '2', 'SAVOSTIA', 'VITALI', 'V');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('6', '3', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('7', '4', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('8', '5', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('9', '6', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('10', '7', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('11', '8', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('12', '9', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('13', '10', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('14', '11', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('15', '1', 'SAVOSTIAN', 'VITALIY', 'V');
INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES ('15', '1', 'SAVOSTIAN', 'VITALIY', 'V');
commit ;
INSERT INTO Iogin_info (id, password, username, role) VALUES (4, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'VITALIIT', 'TEACHER');
INSERT INTO Iogin_info (id, password, username, role) VALUES (5, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'VITALIIT', 'TEACHER');
INSERT INTO Iogin_info (id, password, username, role) VALUES (6, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'VITALIIT', 'TEACHER');
INSERT INTO Iogin_info (id, password, username, role) VALUES (7, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'VITALIIT', 'TEACHER');
INSERT INTO Iogin_info (id, password, username, role) VALUES (8, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'VITALIIT', 'TEACHER');
INSERT INTO Iogin_info (id, password, username, role) VALUES (9, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'VITALIIT', 'TEACHER');
INSERT INTO Iogin_info (id, password, username, role) VALUES (10, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'VITALIIT', 'TEACHER');
INSERT INTO Iogin_info (id, password, username, role) VALUES (11, '$2a$12$5FW3nAcugaWNuKTK/cxQE.HEykTAjJzUMPIrFW4MR1hJVat9Kd3OS', 'VITALIIT', 'TEACHER');