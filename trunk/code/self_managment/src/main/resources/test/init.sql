/* METRICS */
INSERT INTO metric (METRIC_ID, METRIC_CODE, MINIMUM_MAX_VALUE, MINIMUM_MIN_VALUE, MINIMUM_SIGN, OBJETIVE_MAX_VALUE, OBJETIVE_MIN_VALUE, OBJETIVE_SIGN, OPTIM_MAX_VALUE, OPTIM_MIN_VALUE, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_MAX_VALUE, UNSATISFACTORY_MIN_VALUE, UNSATISFACTORY_SIGN) VALUES (1, 'QA_MONITORS', 49, 25, '>=', 74, 50, '>=', 200, 75, '>=', 'Puntos', 49, 25, '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MINIMUM_MAX_VALUE, MINIMUM_MIN_VALUE, MINIMUM_SIGN, OBJETIVE_MAX_VALUE, OBJETIVE_MIN_VALUE, OBJETIVE_SIGN, OPTIM_MAX_VALUE, OPTIM_MIN_VALUE, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_MAX_VALUE, UNSATISFACTORY_MIN_VALUE, UNSATISFACTORY_SIGN) VALUES (2, 'QA_PTS_ACHIEVED', 299, 200, '>=', 399, 300, '>=', 500, 400, '>=', 'Puntos', 299, 200, '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MINIMUM_MAX_VALUE, MINIMUM_MIN_VALUE, MINIMUM_SIGN, OBJETIVE_MAX_VALUE, OBJETIVE_MIN_VALUE, OBJETIVE_SIGN, OPTIM_MAX_VALUE, OPTIM_MIN_VALUE, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_MAX_VALUE, UNSATISFACTORY_MIN_VALUE, UNSATISFACTORY_SIGN) VALUES (3, 'QA_PTS_POSSIBLE', 601, 275, '>=', 701, 600, '>=', 1000, 700, '>=', 'Puntos', 601, 275, '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MINIMUM_MAX_VALUE, MINIMUM_MIN_VALUE, MINIMUM_SIGN, OBJETIVE_MAX_VALUE, OBJETIVE_MIN_VALUE, OBJETIVE_SIGN, OPTIM_MAX_VALUE, OPTIM_MIN_VALUE, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_MAX_VALUE, UNSATISFACTORY_MIN_VALUE, UNSATISFACTORY_SIGN) VALUES (4, 'AVAIL_TM', 5000, 4501, '<=', 4500, 4001, '<=', 4000, 2000, '<=', 'Minutos', 5000, 4501, '>');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MINIMUM_MAX_VALUE, MINIMUM_MIN_VALUE, MINIMUM_SIGN, OBJETIVE_MAX_VALUE, OBJETIVE_MIN_VALUE, OBJETIVE_SIGN, OPTIM_MAX_VALUE, OPTIM_MIN_VALUE, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_MAX_VALUE, UNSATISFACTORY_MIN_VALUE, UNSATISFACTORY_SIGN) VALUES (5, 'AVG_TALK_TM', 170, 160, '<=', 150, 140, '<=', 130, 120, '<=', 'Segundos/Llamada', 170, 160, '>');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MINIMUM_MAX_VALUE, MINIMUM_MIN_VALUE, MINIMUM_SIGN, OBJETIVE_MAX_VALUE, OBJETIVE_MIN_VALUE, OBJETIVE_SIGN, OPTIM_MAX_VALUE, OPTIM_MIN_VALUE, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_MAX_VALUE, UNSATISFACTORY_MIN_VALUE, UNSATISFACTORY_SIGN) VALUES (6, 'AUX_TM', 7500, 5001, '<=', 5000, 3001, '<=', 3000, 500, '<=', 'Minutos', 7500, 5001, '>');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MINIMUM_MAX_VALUE, MINIMUM_MIN_VALUE, MINIMUM_SIGN, OBJETIVE_MAX_VALUE, OBJETIVE_MIN_VALUE, OBJETIVE_SIGN, OPTIM_MAX_VALUE, OPTIM_MIN_VALUE, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_MAX_VALUE, UNSATISFACTORY_MIN_VALUE, UNSATISFACTORY_SIGN) VALUES (7, 'NCH', 1501, 1300, '>=', 1701, 1500, '>=', 5, 0, '<=', 'Llamadas', 1501, 1300, '<');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MINIMUM_MAX_VALUE, MINIMUM_MIN_VALUE, MINIMUM_SIGN, OBJETIVE_MAX_VALUE, OBJETIVE_MIN_VALUE, OBJETIVE_SIGN, OPTIM_MAX_VALUE, OPTIM_MIN_VALUE, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_MAX_VALUE, UNSATISFACTORY_MIN_VALUE, UNSATISFACTORY_SIGN) VALUES (8, 'TPCT', 12, 9, '<=', 8, 6, '<=', 450, 300, '<=', '%', 12, 9, '>');
INSERT INTO metric (METRIC_ID, METRIC_CODE, MINIMUM_MAX_VALUE, MINIMUM_MIN_VALUE, MINIMUM_SIGN, OBJETIVE_MAX_VALUE, OBJETIVE_MIN_VALUE, OBJETIVE_SIGN, OPTIM_MAX_VALUE, OPTIM_MIN_VALUE, OPTIM_SIGN, METRIC_UNIT, UNSATISFACTORY_MAX_VALUE, UNSATISFACTORY_MIN_VALUE, UNSATISFACTORY_SIGN) VALUES (9, 'SCHED_ADG', 900, 551, '<=', 550, 451, '<=', 450, 300, '<=', 'Minutos', 900, 551, '>');

/* CAMPAIGNS */
INSERT INTO campaign (CAMPAIGN_ID, CAMPAIGN_NAME, CAMPAIGN_DESCRIPTION, START_DATE, END_DATE, TYPE, OPTIM_VALUE, OBJETIVE_VALUE, MINIMUM_VALUE) VALUES (1, 'COCA-COLA', 'CAMPA�A COCA ZERO', '2010-06-01', '2010-11-30', 'INBOUND', 4, 3, 2);
INSERT INTO campaign (CAMPAIGN_ID, CAMPAIGN_NAME, CAMPAIGN_DESCRIPTION, START_DATE, END_DATE, TYPE, OPTIM_VALUE, OBJETIVE_VALUE, MINIMUM_VALUE) VALUES (2, 'CLARIN', 'CAMPA�A CLARIN 365', '2010-06-01', '2010-11-30', 'OUTBOUND', 5, 4, 2);
INSERT INTO campaign (CAMPAIGN_ID, CAMPAIGN_NAME, CAMPAIGN_DESCRIPTION, START_DATE, END_DATE, TYPE, OPTIM_VALUE, OBJETIVE_VALUE, MINIMUM_VALUE) VALUES (3, 'LA NACION', 'CAMPA�A CLUB LA NACION', '2010-06-01', '2010-11-30', 'INBOUND', 6, 5, 3);

/* SUPERVISORS */
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (120, 30321382, 'scoca1', 'scoca1', 1);
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (121, 33435582, 'scoca2', 'scoca2', 1);
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (122, 14536582, 'sclarin1', 'sclarin1', 2);
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (123, 34543643, 'sclarin2', 'sclarin2', 2);
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (124, 26547784, 'slanacion1', 'slanacion1', 3);
INSERT INTO supervisor (SUPERVISOR_ID, DNI, NAME, SURNAME, CAMPAIGN_ID) VALUES (125, 26547645, 'slanacion2', 'slanacion2', 3);

/* CAMPAIGN_METRIC */
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (1, 1, 130, 60, 40, 40);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (1, 2, 400, 350, 200, 200);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (1, 3, 850, 675, 300, 300);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (2, 4, 4000, 4500, 5000, 5000);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (2, 5, 130, 150, 170, 170);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (2, 6, 2200, 4000, 7500, 7500);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (3, 7, 1700, 1500, 1300, 1300);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (3, 8, 3, 6, 9, 9);
INSERT INTO campaign_metric (CAMPAIGN_ID, METRIC_ID, OPTIM, OBJECTIVE, MINIMUM, UNSATISFACTORY) VALUES (3, 9, 450, 550, 900, 900);