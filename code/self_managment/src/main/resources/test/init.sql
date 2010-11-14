/* METRICS */
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (1, 'QA_MONITORS', 10000, 0, '>=', '>=', '>=', 'Puntos', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (2, 'QA_PTS_ACHIEVED', 5000, 0, '>=', '>=', '>=', 'Puntos', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (3, 'QA_PTS_POSSIBLE', 5000, 0, '>=', '>=', '>=', 'Puntos', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (4, 'AVAIL_TM', 5000, 0, '>=', '>=', '>=', 'Minutos', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (5, 'AVG_TALK_TM', 86400, 0, '>=', '>=', '>=', 'Segundos/Llamada', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (6, 'AUX_TM', 3960, 0, '<=', '<=', '<=', 'Minutos', '>');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (7, 'NCH', 10000, 0, '>=', '>=', '>=', 'Llamadas', '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MAX_VALUE, MIN_VALUE, MINIMUM_SIGN, OBJETIVE_SIGN, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_SIGN) VALUES (8, 'TPCT', 100, 0, '<=', '<=', '<=', '%', '>');

/* CAMPAIGNS */
INSERT INTO campaign (CAMPAIGN_ID, CAMPAIGN_NAME, CAMPAIGN_DESCRIPTION, START_DATE, END_DATE, TYPE, OPTIM_VALUE, OBJETIVE_VALUE, MINIMUM_VALUE) VALUES (1, 'COCA-COLA', 'CAMPA�A COCA ZERO', '2010-06-01', '2010-11-30', 'INBOUND', 4, 3, 2);
INSERT INTO campaign (CAMPAIGN_ID, CAMPAIGN_NAME, CAMPAIGN_DESCRIPTION, START_DATE, END_DATE, TYPE, OPTIM_VALUE, OBJETIVE_VALUE, MINIMUM_VALUE) VALUES (2, 'CLARIN', 'CAMPA�A CLARIN 365', '2010-06-01', '2010-11-30', 'OUTBOUND', 5, 4, 2);
INSERT INTO campaign (CAMPAIGN_ID, CAMPAIGN_NAME, CAMPAIGN_DESCRIPTION, START_DATE, END_DATE, TYPE, OPTIM_VALUE, OBJETIVE_VALUE, MINIMUM_VALUE) VALUES (3, 'LA NACION', 'CAMPA�A CLUB LA NACION', '2010-06-01', '2010-11-30', 'INBOUND', 6, 5, 3);

/* SUPERVISORS */
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (120, 30321382, 'Romualdo', 'Cordoba', 1);
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (121, 33435582, 'Aristobulo', 'Del Valle', 1);
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (122, 14536582, 'Sabrina', 'Seis Dedos', 2);
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (123, 34543643, 'Gerardo', 'Panzotti', 2);
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (124, 26547784, 'German', 'Contreras', 3);
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (125, 26547645, 'Tatiana', 'Herman', 3);

/* CAMPAIGN_METRIC */
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (1, 1, 500, 400, 300, 200);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (1, 2, 400, 350, 200, 100);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (1, 3, 500, 275, 200, 150);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (2, 4, 6000, 5000, 4000, 2000);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (2, 5, 400, 350, 200, 100);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (2, 6, 13000, 10000, 8000, 4000);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (3, 7, 4000, 3000, 2000, 1000);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (3, 8, 20, 40, 60, 100);