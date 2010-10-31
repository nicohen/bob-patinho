DELETE FROM qa;
DELETE FROM tts;
DELETE FROM summary;
DELETE FROM agent;
DELETE FROM supervisor;
DELETE FROM campaign_metric;
DELETE FROM campaign;
DELETE FROM metric;
DELETE FROM segurity;

/* METRICS */
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (1, 'I2C_PCT', 100, 0, '>=', '>=', '>=', '%', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (2, 'AVG_HLD_TM', 800, 500, '>=', '>=', '>=', 'seg', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (3, 'ACW_PCT', 451, 24, '>=', '>=', '>=', 'llamadas', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (4, 'AUX_TM', 100, 50, '>=', '>=', '>=', 'horas', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (5, 'AVAIL_PCT', 100, 0, '>=', '>=', '>=', '%', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (6, 'QA_MONITORS', 10000, 0, '>=', '>=', '>=', 'puntos', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (7, 'QA_PTS_ACHIEVED', 5000, 0, '>=', '>=', '>=', 'puntos', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (8, 'QA_PTS_POSSIBLE', 5000, 0, '>=', '>=', '>=', 'puntos', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (9, 'AVAIL_TM', 5000, 0, '>=', '>=', '>=', 'Minutos', '<');

/* CAMPAIGNS */
INSERT INTO campaign (CAMPAIGN_ID, CAMPAIGN_NAME, CAMPAIGN_DESCRIPTION, START_DATE, END_DATE, TYPE, OPTIM_VALUE, OBJETIVE_VALUE, MINIMUM_VALUE, UNSATISFACTORY_VALUE) VALUES (1, 'COCA-COLA', 'CAMPAÑA COCA ZERO', '01/01/01', '01/01/01', 'INBOUND', 4, 3, 2, 1);

/* SUPERVISORS */
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (1, 30321382, 'Pepe', 'Argento', 1);

/* AGENTS */
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (1, 1, '2010-09-09', 1500 ,'BOB', 'ACTIVO', 'PATINHO', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (2, 2, '2010-09-09', 1500 ,'HOMER', 'ACTIVO', 'SIMPSON', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (3, 3, '2010-09-09', 1500 ,'MARGE', 'ACTIVO', 'SIMPSON', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (4, 4, '2010-09-09', 1500 ,'BART', 'ACTIVO', 'SIMPSON', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (5, 5, '2010-09-09', 1500 ,'LIZA', 'ACTIVO', 'SIMPSON', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (6, 6, '2010-09-09', 1500 ,'MAGGIE', 'ACTIVO', 'SIMPSON', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (7, 7, '2010-09-09', 1500 ,'PATTY', 'ACTIVO', 'BOUVIER', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (8, 8, '2010-09-09', 1500 ,'SELMA', 'ACTIVO', 'BOUVIER', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (9, 9, '2010-09-09', 1500 ,'KENT', 'ACTIVO', 'BROCKMAN', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (10, 10, '2010-09-09', 1500 ,'CARL', 'ACTIVO', 'CARLSON', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (11, 11, '2010-09-09', 1500 ,'NED', 'ACTIVO', 'FLANDERS', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (12, 12, '2010-09-09', 1500 ,'ROD', 'ACTIVO', 'FLANDERS', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (13, 13, '2010-09-09', 1500 ,'TODD', 'ACTIVO', 'FLANDERS', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (14, 14, '2010-09-09', 1500 ,'BERNICE', 'ACTIVO', 'HIBBERT', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (15, 15, '2010-09-09', 1500 ,'JULIUS', 'ACTIVO', 'HIBBERT', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (16, 16, '2010-09-09', 1500 ,'JIMBO', 'ACTIVO', 'JONES', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (17, 17, '2010-09-09', 1500 ,'OTTO', 'ACTIVO', 'MAN', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (18, 18, '2010-09-09', 1500 ,'NELSON', 'ACTIVO', 'MUNTS', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (19, 19, '2010-09-09', 1500 ,'MARTIN', 'ACTIVO', 'PRINCE', 'PTE', 1, 1);
INSERT INTO agent (DOCKET, DNI, ENTRY_DATE, GROSS_SALARY, NAME, STATUS, SURNAME, WORKING_DAY, SUPERVISOR_ID, CAMPAIGN_ID) VALUES (20, 20, '2010-09-09', 1500 ,'APU', 'ACTIVO', 'NAHASAPEEMAPETILON', 'PTE', 1, 1);

/* CAMPAIGN_METRIC */
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (1, 9, 50, 40, 20, 20);

/* segurity*/
INSERT INTO segurity (USER,CLAVE) VALUES ('admin', 'admin');

/* SUMMARY */
INSERT INTO summary (DOCKET, DATE, AFTER_FOR_CALL, IN_CALL, LOGGEADO, QUANTITY_OF_CALLS, READY_FOR_CALL, TIME_IN_WAIT, TRANSFERRED_CALLS) VALUES(1, '2010-10-01', 1, 2, 3, 4, 5, 6, 7);
INSERT INTO summary (DOCKET, DATE, AFTER_FOR_CALL, IN_CALL, LOGGEADO, QUANTITY_OF_CALLS, READY_FOR_CALL, TIME_IN_WAIT, TRANSFERRED_CALLS) VALUES(1, '2010-10-02', 1, 2, 3, 4, 5, 6, 7);
INSERT INTO summary (DOCKET, DATE, AFTER_FOR_CALL, IN_CALL, LOGGEADO, QUANTITY_OF_CALLS, READY_FOR_CALL, TIME_IN_WAIT, TRANSFERRED_CALLS) VALUES(1, '2010-10-03', 1, 2, 3, 4, 5, 6, 7);
INSERT INTO summary (DOCKET, DATE, AFTER_FOR_CALL, IN_CALL, LOGGEADO, QUANTITY_OF_CALLS, READY_FOR_CALL, TIME_IN_WAIT, TRANSFERRED_CALLS) VALUES(1, '2010-10-04', 1, 2, 3, 4, 5, 6, 7);

/* QA */
INSERT INTO qa (DOCKET, DATE, EVALUATIONS_QUANTITY, POSIBLE_POINTS_QUANTITY, ACHIEVED_POINTS_QUANTITY) VALUES(1, '2010-10-01', 4, 30, 27);
INSERT INTO qa (DOCKET, DATE, EVALUATIONS_QUANTITY, POSIBLE_POINTS_QUANTITY, ACHIEVED_POINTS_QUANTITY) VALUES(1, '2010-10-02', 10, 20, 3);
INSERT INTO qa (DOCKET, DATE, EVALUATIONS_QUANTITY, POSIBLE_POINTS_QUANTITY, ACHIEVED_POINTS_QUANTITY) VALUES(1, '2010-10-03', 15, 50, 40);