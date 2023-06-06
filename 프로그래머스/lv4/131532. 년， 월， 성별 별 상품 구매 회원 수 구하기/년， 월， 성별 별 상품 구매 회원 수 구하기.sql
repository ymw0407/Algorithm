SELECT DATE_FORMAT(S.SALES_DATE, "%Y") AS YEAR, CONVERT(DATE_FORMAT(S.SALES_DATE, "%m"), SIGNED) AS MONTH, I.GENDER, COUNT(DISTINCT S.USER_ID) AS USERS
FROM USER_INFO I
JOIN ONLINE_SALE S ON I.USER_ID = S.USER_ID
WHERE I.GENDER IS NOT NULL
GROUP BY 1, 2, 3
ORDER BY 1, 2, 3