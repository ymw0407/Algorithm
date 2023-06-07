SELECT U.USER_ID, U.NICKNAME, CONCAT(U.CITY, " ", U.STREET_ADDRESS1, " ", U.STREET_ADDRESS2) AS 전체주소, CONCAT_WS("-", SUBSTR(U.TLNO, 1, 3), SUBSTR(U.TLNO, 4, 4), SUBSTR(U.TLNO, 8, 4)) AS 전화번호
FROM USED_GOODS_BOARD B
LEFT JOIN USED_GOODS_USER U ON B.WRITER_ID = U.USER_ID
GROUP BY B.WRITER_ID
HAVING COUNT(B.WRITER_ID) >= 3
ORDER BY 1 DESC