SELECT DATE_FORMAT(O.SALES_DATE, "%Y") AS YEAR, CONVERT(DATE_FORMAT(O.SALES_DATE, "%m"), SIGNED) AS MONTH, COUNT(DISTINCT(U.USER_ID)) AS PUCHASED_USERS, ROUND(COUNT(DISTINCT(U.USER_ID))/(
                                    SELECT count(*) 
                                    FROM USER_INFO 
                                    WHERE DATE_FORMAT(JOINED, '%Y') = '2021'
                                    ), 1) AS PUCHASED_RATIO
FROM USER_INFO U
RIGHT JOIN ONLINE_SALE O ON U.USER_ID = O.USER_ID
WHERE DATE_FORMAT(U.JOINED, "%Y") = "2021"
GROUP BY 1, 2
ORDER BY 1, 2



# SELECT
#     DATE_FORMAT(SALES_DATE, '%Y') AS YEAR,
#     CAST(DATE_FORMAT(SALES_DATE, '%m') AS SIGNED) AS MONTH,
#     count(distinct(o.user_id)) as PUCHASED_USERS,
#     round(
#         count(distinct(o.user_id)) / (
#                                     SELECT count(*) 
#                                     FROM USER_INFO 
#                                     WHERE DATE_FORMAT(JOINED, '%Y') = '2021'
#                                     ),
#         1) 
#         as PUCHASED_RATIO
# FROM ONLINE_SALE O
# JOIN USER_INFO U ON O.USER_ID = U.USER_ID
# WHERE DATE_FORMAT(JOINED, '%Y') = '2021'
# GROUP BY YEAR, MONTH;
