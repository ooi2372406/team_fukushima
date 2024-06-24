--テーブルの作成
CREATE TABLE CLASS_NUM
(SCHOOL_CD CHAR(3) NOT NULL , CLASS_NUM VARCHAR(5) NOT NULL , PRIMARY KEY(SCHOOL_CD , CLASS_NUM));

--サンプルデータの挿入
INSERT INTO CLASS_NUM VALUES('oom' , '131');

--テーブルの作成
CREATE TABLE SCHOOL
(CD CHAR(3) PRIMARY KEY NOT NULL , NAME VARCHAR(20));

--サンプルデータの挿入
INSERT INTO SCHOOL VALUES('oom' , '学校名');

-- テーブルの作成
CREATE TABLE STUDENT (
    NO VARCHAR(10) PRIMARY KEY NOT NULL,
    NAME VARCHAR(10) DEFAULT NULL,
    ENT_YEAR INTEGER DEFAULT NULL,
    CLASS_NUM CHAR(3) DEFAULT NULL,
    IS_ATTEND BOOLEAN DEFAULT NULL,
    SCHOOL_CD CHAR(3) DEFAULT NULL
);

-- サンプルデータの挿入
INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES
('2125001','大原 一郎',2021,'201','TRUE','oom'),
('2125002','大原 花子',2021,'201','TRUE','oom'),
('2125003','大原 良子',2021,'201','TRUE','oom'),
('2125004','大原 二郎',2021,'201','TRUE','oom'),
('2125005','大原 三郎',2021,'202','TRUE','oom'),
('2125006','大原 幸子',2021,'202','TRUE','oom'),
('2125022','大原 四郎',2021,'202','TRUE','oom'),
('2125023','大原 典子',2021,'202','TRUE','oom'),
('2125024','大原 五郎',2021,'202','FALSE','oom'),
('2125025','大原 愛理',2021,'201','TRUE','oom');


-- データの表示(必要であれば使ってください）
SELECT NO, NAME, ENT_YEAR, CLASS_NUM,
       CASE
           WHEN IS_ATTEND = TRUE THEN '在学中'
           WHEN IS_ATTEND = FALSE THEN '退学済み'
           ELSE '不明'
       END AS ATTENDANCE_STATUS,
       SCHOOL_CD
FROM STUDENT;

--テーブルの作成
CREATE TABLE SUBJECT
(SCHOOL_CD CHAR(3) NOT NULL,
CD CHAR(3) NOT NULL,
NAME VARCHAR(20) DEFAULT NULL,
PRIMARY KEY(SCHOOL_CD , CD));

--サンプルデータの挿入
INSERT INTO SUBJECT VALUES
('oom' , 'A02' , '国語');

--テーブルの作成
CREATE TABLE TEACHER
(ID VARCHAR(10) PRIMARY KEY NOT NULL,
PASSWORD VARCHAR(30) DEFAULT NULL,
NAME VARCHAR(10) DEFAULT NULL,
SCHOOL_CD CHAR(3) DEFAULT NULL);

--サンプルデータの挿入
INSERT INTO TEACHER VALUES
('admin' , 'password' , '大原 太郎' , 'oom');

--テーブルの作成
CREATE TABLE TEST
(STUDENT_NO VARCHAR(10) NOT NULL,
SUBJECT_CD CHAR(3) NOT NULL,
SCHOOL_CD CHAR(10) NOT NULL,
NO INTEGER(10) NOT NULL,
POINT INTEGER(10) DEFAULT NULL,
CLASS_NUM VARCHAR(5) DEFAULT NULL,
PRIMARY KEY(STUDENT_NO , SUBJECT_CD , SCHOOL_CD , NO));

--サンプルデータの挿入
INSERT INTO TEST VALUES
('2231111' , 'A02' , 'oom' , 1 , 100 , '131');