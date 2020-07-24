-- 基地客户信息
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_001', '张一', '310115198211280601', '18516691001', '1001', '1982-01-28', '0');
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_002', '张二', '310115198211280602', '18516691002', '1002', '1982-02-28', '0');
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_003', '张三', '310115198211280603', '18516691003', '1003', '1982-03-28', '0');
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_004', '张四', '310115198211280604', '18516691004', '1004', '1982-04-28', '0');
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_005', '张五', '310115198211280605', '18516691005', '1005', '1982-05-28', '0');
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_006', '张六', '310115198211280606', '18516691006', '1006', '1982-06-28', '1');
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_007', '赵七', '310115198211280607', '18516691007', '1007', '1982-07-28', '1');
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_008', '赵八', '310115198211280608', '18516691008', '1008', '1982-08-28', '1');
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_009', '赵九', '310115198211280609', '18516691009', '1009', '1982-09-28', '1');
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_010', '赵十', '310115198211280610', '18516691010', '1010', '1982-10-28', '1');
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_011', '赵十一', '310115198211280611', '18516691011', '1011', '1982-11-28', '1');
INSERT INTO t_customer (customer_id, customer_name, customer_idcard, customer_mobile, customer_faceid, customer_birthday, customer_sex) VALUES ('customer_012', '赵十二', '310115198211280612', '18516691012', '1012', '1982-11-28', '1');


-- 入住信息
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('1', 'customer_001', '2017-01-01 00:00:00', 'MA2AXP9G6', '101', '2017-01-02 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('2', 'customer_002', '2017-12-15 10:53:18', 'MA2AXP9G6', '102', '2017-02-02 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('3', 'customer_003', '2017-03-01 00:00:00', 'MA2AXP9G6', '103', '2017-03-02 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('4', 'customer_004', '2017-04-01 00:00:00', 'MA2AXP9G6', '104', '2017-04-02 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('5', 'customer_005', '2017-05-01 00:00:00', 'MA2AXP9G6', '105', '2017-05-02 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('6', 'customer_006', '2017-06-01 00:00:00', 'MA2AXP9G6', '106', '2017-06-02 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('7', 'customer_007', '2017-07-01 00:00:00', 'MA2AXP9G6', '201', '2017-07-02 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('8', 'customer_008', '2017-12-15 10:53:22', 'MA2AXP9G5', '202', '2017-08-02 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('9', 'customer_009', '2017-09-01 00:00:00', 'MA2AXP9G6', '203', '2017-09-02 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('10', 'customer_010', '2017-10-01 00:00:00', 'MA2AXP9G6', '204', '2017-10-02 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('11', 'customer_011', '2017-11-01 00:00:00', 'MA2AXP9G6', '205', '2017-11-05 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('12', 'customer_001', '2017-05-03 00:00:00', 'MA2AXP9G6', '101', '2017-05-04 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('13', 'customer_007', '2017-12-14 16:58:02', 'MA2AXP9G6', '106', '2017-08-03 00:00:00');
INSERT INTO t_customer_checkin (connectionid, customer_id, customer_checkin, organization_id, hotel_roomnum, customer_checkout) VALUES ('14', 'customer_012', '2017-12-15 11:09:29', 'MA2AXP9G6', '301', '2017-12-16 11:09:05');


