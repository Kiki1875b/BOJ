SELECT 
    d.ID,
    d.EMAIL,
    d.FIRST_NAME,
    d.LAST_NAME
FROM DEVELOPERS d
WHERE (d.SKILL_CODE & (
          SELECT SUM(CODE)
          FROM SKILLCODES
          WHERE CATEGORY = 'Front End'
      )) > 0
ORDER BY d.ID ASC;