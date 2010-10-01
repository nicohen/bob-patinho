DELETE FROM campaign_agent;
DELETE FROM agent;
DELETE FROM metric;

/* METRICS */
INSERT INTO metric VALUES(1, 'I2C_PCT', 100, 0, '>=', '>=', '>=', '%', '<');
INSERT INTO metric VALUES(2, 'AVG_HLD_TM', 800, 500, '>=', '>=', '>=', 'seg', '<');
INSERT INTO metric VALUES(3, 'ACW_PCT', 451, 24, '>=', '>=', '>=', 'llamadas', '<');
INSERT INTO metric VALUES(4, 'AUX_TM', 100, 50, '>=', '>=', '>=', 'horas', '<');
INSERT INTO metric VALUES(5, 'AVAIL_PCT', 100, 0, '>=', '>=', '>=', '%', '<');

/* AGENTS */
INSERT INTO agent VALUES(1, 1, '2010-09-09', 'BOB', 'ACTIVO', 'PATINHO');
INSERT INTO agent VALUES(2, 2, '2010-09-09', 'HOMER', 'ACTIVO', 'SIMPSON');
INSERT INTO agent VALUES(3, 3, '2010-09-09', 'MARGE', 'ACTIVO', 'SIMPSON');
INSERT INTO agent VALUES(4, 4, '2010-09-09', 'BART', 'ACTIVO', 'SIMPSON');
INSERT INTO agent VALUES(5, 5, '2010-09-09', 'LIZA', 'ACTIVO', 'SIMPSON');
INSERT INTO agent VALUES(6, 6, '2010-09-09', 'MAGGIE', 'ACTIVO', 'SIMPSON');
INSERT INTO agent VALUES(7, 7, '2010-09-09', 'PATTY', 'ACTIVO', 'BOUVIER');
INSERT INTO agent VALUES(8, 8, '2010-09-09', 'SELMA', 'ACTIVO', 'BOUVIER');
INSERT INTO agent VALUES(9, 9, '2010-09-09', 'KENT', 'ACTIVO', 'BROCKMAN');
INSERT INTO agent VALUES(10, 10, '2010-09-09', 'CARL', 'ACTIVO', 'CARLSON');
INSERT INTO agent VALUES(11, 11, '2010-09-09', 'NED', 'ACTIVO', 'FLANDERS');
INSERT INTO agent VALUES(12, 12, '2010-09-09', 'ROD', 'ACTIVO', 'FLANDERS');
INSERT INTO agent VALUES(13, 13, '2010-09-09', 'TODD', 'ACTIVO', 'FLANDERS');
INSERT INTO agent VALUES(14, 14, '2010-09-09', 'BERNICE', 'ACTIVO', 'HIBBERT');
INSERT INTO agent VALUES(15, 15, '2010-09-09', 'JULIUS', 'ACTIVO', 'HIBBERT');
INSERT INTO agent VALUES(16, 16, '2010-09-09', 'JIMBO', 'ACTIVO', 'JONES');
INSERT INTO agent VALUES(17, 17, '2010-09-09', 'OTTO', 'ACTIVO', 'MAN');
INSERT INTO agent VALUES(18, 18, '2010-09-09', 'NELSON', 'ACTIVO', 'MUNTS');
INSERT INTO agent VALUES(19, 19, '2010-09-09', 'MARTIN', 'ACTIVO', 'PRINCE');
INSERT INTO agent VALUES(20, 20, '2010-09-09', 'APU', 'ACTIVO', 'NAHASAPEEMAPETILON');

/* CAMPAIGN_AGENT */
/*INSERT INTO campaign_agent VALUES(1, 1);
INSERT INTO campaign_agent VALUES(1, 2);
INSERT INTO campaign_agent VALUES(1, 3);
INSERT INTO campaign_agent VALUES(1, 4);
INSERT INTO campaign_agent VALUES(1, 5);
INSERT INTO campaign_agent VALUES(1, 6);
INSERT INTO campaign_agent VALUES(1, 7);
INSERT INTO campaign_agent VALUES(1, 8);
INSERT INTO campaign_agent VALUES(1, 9);
INSERT INTO campaign_agent VALUES(1, 10);
INSERT INTO campaign_agent VALUES(1, 11);
INSERT INTO campaign_agent VALUES(1, 12);
INSERT INTO campaign_agent VALUES(1, 13);
INSERT INTO campaign_agent VALUES(1, 14);
INSERT INTO campaign_agent VALUES(1, 15);
INSERT INTO campaign_agent VALUES(1, 16);
INSERT INTO campaign_agent VALUES(1, 17);
INSERT INTO campaign_agent VALUES(1, 18);
INSERT INTO campaign_agent VALUES(1, 19);
INSERT INTO campaign_agent VALUES(1, 20);*/