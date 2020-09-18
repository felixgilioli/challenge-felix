SELECT
    C.ID as id,
    P.FULL_NAME AS name,
    P.DOCUMENT AS document,
    C.FINAL_VALUE AS amount,
    C.ENTRY_TIME as entry,
    C.HAS_VEHICLE AS vehicle
FROM CHECKIN C
INNER JOIN PERSON P ON P.ID = C.PERSON_ID
WHERE 1=1

/* if (filterPersonSituation == "PRESENT_IN_HOTEL") */
AND (C.ENTRY_TIME <= CURRENT_TIMESTAMP AND C.DEPARTURE_TIME IS NULL)
OR CURRENT_TIMESTAMP BETWEEN C.ENTRY_TIME AND C.DEPARTURE_TIME
/* endif  */

/* if (filterPersonSituation == "NO_PRESENT_IN_HOTEL") */
AND CURRENT_TIMESTAMP NOT BETWEEN C.ENTRY_TIME AND C.DEPARTURE_TIME
/* endif  */

/* if (filterPersonName != null) */
AND LOWER(P.FULL_NAME) LIKE :filterPersonName
/* endif  */

/* if (filterPersonDocument != null) */
AND P.DOCUMENT = :filterPersonDocument
/* endif  */

/* if (filterPersonPhoneNumber != null) */
AND P.PHONE_NUMBER = :filterPersonPhoneNumber
/* endif  */

ORDER BY C.ID
