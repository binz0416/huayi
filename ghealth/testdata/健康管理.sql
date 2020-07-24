-- 健康档案
INSERT INTO t_archives (archives_id, customer_id, archives_create_time, advise) VALUES ('310115198211280600_20171212', 'customer_001', '2017-11-28', '正常1');
INSERT INTO t_archives (archives_id, customer_id, archives_create_time, advise) VALUES ('310115198211280600_20171216', 'customer_002', '2017-12-28 00:00:00', '正常2');


-- 健康档案详细
INSERT INTO t_archives_detail (archives_detail_id, archives_id, inspection_time, type, sc_id, advise) VALUES ('1', '310115198211280600_20171212', '2017-01-01 00:00:00', '1', '1', '正常');
INSERT INTO t_archives_detail (archives_detail_id, archives_id, inspection_time, type, sc_id, advise) VALUES ('2', '310115198211280600_20171212', '2017-01-02 00:00:00', '2', '1', '正常');
INSERT INTO t_archives_detail (archives_detail_id, archives_id, inspection_time, type, sc_id, advise) VALUES ('3', '310115198211280600_20171212', '2017-01-04 00:00:00', '2', '3', '正常');
INSERT INTO t_archives_detail (archives_detail_id, archives_id, inspection_time, type, sc_id, advise) VALUES ('4', '310115198211280600_20171212', '2017-04-28 00:00:00', '3', '1', '正常');
INSERT INTO t_archives_detail (archives_detail_id, archives_id, inspection_time, type, sc_id, advise) VALUES ('5', '310115198211280600_20171212', '2017-05-01 00:00:00', '4', '3', '正常');
INSERT INTO t_archives_detail (archives_detail_id, archives_id, inspection_time, type, sc_id, advise) VALUES ('6', '310115198211280600_20171212', '2017-04-01 00:00:00', '5', '3', '正常');
INSERT INTO t_archives_detail (archives_detail_id, archives_id, inspection_time, type, sc_id, advise) VALUES ('7', '310115198211280600_20171212', '2017-05-01 00:00:00', '6', '3', '正常');
INSERT INTO t_archives_detail (archives_detail_id, archives_id, inspection_time, type, sc_id, advise) VALUES ('8', '310115198211280600_20171216', '2017-12-18 00:00:00', '2', '6', '正常');
INSERT INTO t_archives_detail (archives_detail_id, archives_id, inspection_time, type, sc_id, advise) VALUES ('9', '310115198211280600_20171216', '2017-02-01 00:00:00', '4', '2', '正常');
INSERT INTO t_archives_detail (archives_detail_id, archives_id, inspection_time, type, sc_id, advise) VALUES ('10', '310115198211280600_20171216', '2017-02-01 00:00:00', '5', '2', '正常');


