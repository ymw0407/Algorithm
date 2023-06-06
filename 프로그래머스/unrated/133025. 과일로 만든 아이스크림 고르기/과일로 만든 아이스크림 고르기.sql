SELECT DISTINCT F.FLAVOR AS FLAVOR
FROM FIRST_HALF F
LEFT JOIN ICECREAM_INFO I ON F.FLAVOR = I.FLAVOR
WHERE I.INGREDIENT_TYPE = "fruit_based" AND F.TOTAL_ORDER >= 3000