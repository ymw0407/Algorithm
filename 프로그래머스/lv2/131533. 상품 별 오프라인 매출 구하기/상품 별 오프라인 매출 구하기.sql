SELECT P.PRODUCT_CODE, SUM(P.PRICE * O.SALES_AMOUNT) AS SALES
FROM PRODUCT P
RIGHT JOIN OFFLINE_SALE O ON P.PRODUCT_ID = O.PRODUCT_ID
GROUP BY 1
ORDER BY 2 DESC, 1