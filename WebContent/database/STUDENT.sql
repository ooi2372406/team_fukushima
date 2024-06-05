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
('2231111', '大原太郎', 2023, '131', TRUE, 'oom'),
('2231112', '大原次郎', 2023, '131', FALSE, 'oom');


-- データの表示(必要であれば使ってください）
SELECT NO, NAME, ENT_YEAR, CLASS_NUM,
       CASE
           WHEN IS_ATTEND = TRUE THEN '在学中'
           WHEN IS_ATTEND = FALSE THEN '退学済み'
           ELSE '不明'
       END AS ATTENDANCE_STATUS,
       SCHOOL_CD
FROM STUDENT;