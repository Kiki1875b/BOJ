-- 코드를 작성해주세요
WITH EMPLOYEE_GRADE AS (
    SELECT 
        e.EMP_NO,
        CASE 
            WHEN AVG(e.SCORE) >= 96 THEN 'S'
            WHEN AVG(e.SCORE) >= 90 THEN 'A'
            WHEN AVG(e.SCORE) >= 80 THEN 'B'
            ELSE 'C'
        END AS LETTER_GRADE
    FROM HR_GRADE e
    GROUP BY e.EMP_NO
)
SELECT 
    e.EMP_NO,
    e.EMP_NAME,
    eg.LETTER_GRADE AS GRADE,
    CASE eg.LETTER_GRADE
        WHEN 'C' THEN e.SAL * 0
        WHEN 'B' THEN e.SAL * 0.1
        WHEN 'A' THEN e.SAL * 0.15
        WHEN 'S' THEN e.SAL * 0.2
    END AS BONUS
FROM HR_EMPLOYEES e JOIN EMPLOYEE_GRADE eg ON e.EMP_NO = eg.EMP_NO
ORDER BY EMP_NO ASC