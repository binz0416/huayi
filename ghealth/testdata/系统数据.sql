-- 菜单
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10100', '客户管理', 'fa fa-users', '/custom', '0');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10101', '客户管理', 'img_menuItem', '/custommanager', '10100');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10200', '档案管理', 'fa fa-book', '/archives', '0');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10201', '健康档案', 'img_menuItem', '/archivesmanager', '10200');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10300', '健康管理', 'fa fa-heart', '/heart', '0');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10301', '数据解读', 'img_menuItem', '/dataunscramble', '10300');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10302', '健康方案', 'img_menuItem', '/projectmanager', '10300');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10400', '数据采集', 'fa fa-database', '/data', '0');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10401', '综合报告', 'img_menuItem', '/comprehensive', '10400');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10403', '体检报告', 'img_menuItem', '/physicalreport', '10400');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10404', '毒素检测', 'img_menuItem', '/toxindetection', '10400');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10500', '餐饮服务', 'fa fa-coffee', '/meal', '0');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('10501', '点餐送餐', 'img_menuItem', '/setmeal', '10600');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('99900', '系统管理', 'fa fa-cog', '/system', '0');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('99901', '用户管理', 'img_menuItem', '/users', '10500');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('99902', '权限配置', 'img_menuItem', '/auth', '10500');
INSERT INTO t_menuitem (menu_id, name, icon, url, parentid) VALUES ('99903', '系统配置', 'img_menuItem', '/config', '10500');


-- 用户
INSERT INTO t_userinfo (userid, organization_id, username, password, fullname, sex, email, tel, position, update_date_time, create_date_time, updateuser) VALUES ('1', 'MA2AXP9G6', 'miffy', '5f4dcc3b5aa765d61d8327deb882cf99', '测试', '1', 'aaa@bb.cc', '0', '', '2017-10-02 15:51:13', '2017-10-02 15:51:13', 'zhuhui');
INSERT INTO t_userinfo (userid, organization_id, username, password, fullname, sex, email, tel, position, update_date_time, create_date_time, updateuser) VALUES ('2', 'MA2AXP9G6', 'zhuhui', '5f4dcc3b5aa765d61d8327deb882cf99', 'zhuhui', '0', 'aaa@bb.cc', '0', '', '2017-10-02 15:51:13', '2017-10-02 15:51:13', 'zhuhui');
INSERT INTO t_userinfo (userid, organization_id, username, password, fullname, sex, email, tel, position, update_date_time, create_date_time, updateuser) VALUES ('3', 'MA2AXP9G6', 'doctor', '5f4dcc3b5aa765d61d8327deb882cf99', '医师', '0', 'aaa@bb.cc', '0', '', '2017-10-02 15:51:13', '2017-10-02 15:51:13', 'zhuhui');
INSERT INTO t_userinfo (userid, organization_id, username, password, fullname, sex, email, tel, position, update_date_time, create_date_time, updateuser) VALUES ('4', 'MA2AXP9G6', 'staff', '5f4dcc3b5aa765d61d8327deb882cf99', '员工', '0', 'aaa@bb.cc', '0', '', '2017-10-02 15:51:13', '2017-10-02 15:51:13', 'zhuhui');
INSERT INTO t_userinfo (userid, organization_id, username, password, fullname, sex, email, tel, position, update_date_time, create_date_time, updateuser) VALUES ('5', 'MA2AXP9G6', 'admin', '5f4dcc3b5aa765d61d8327deb882cf99', '管理员', '0', 'aaa@bb.cc', '0', '', '2017-10-02 15:51:13', '2017-10-02 15:51:13', 'zhuhui');
INSERT INTO t_userinfo (userid, organization_id, username, password, fullname, sex, email, tel, position, update_date_time, create_date_time, updateuser) VALUES ('6', 'MA2AXP9G6', 'binz', '5f4dcc3b5aa765d61d8327deb882cf99', 'binz', '0', 'aaa@bb.cc', '0', '', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '');
INSERT INTO t_userinfo (userid, organization_id, username, password, fullname, sex, email, tel, position, update_date_time, create_date_time, updateuser) VALUES ('7', 'MA2AXP9G6', 'nancy', '5f4dcc3b5aa765d61d8327deb882cf99', 'nancy', '0', 'aaa@bb.cc', '0', '', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '');


-- 选项
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('1', 'phy', '体检项目', 'height', '身高(cm)', '1', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('2', 'phy', '体检项目', 'weight', '体重(kg)', '2', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('3', 'phy', '体检项目', 'waist', '腰围(cm) ', '3', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('4', 'phy', '体检项目', 'breech', '臀围(cm)', '4', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('5', 'phy', '体检项目', 'sbp', '收缩压(mmHg)', '5', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('6', 'phy', '体检项目', 'dbp', '舒张压(mmHg) ', '6', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('7', 'phy', '体检项目', 'glu', '空腹血糖(mmol/L)', '7', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('8', 'phy', '体检项目', 'tc', '总胆固醇(mmol/L) ', '8', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('9', 'phy', '体检项目', 'tlc', '甘油三脂(mmol/L)', '9', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('10', 'phy', '体检项目', 'hdl', '高密度胆固醇(mmol/L)', '10', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('11', 'phy', '体检项目', 'ldl', '低密度脂蛋白胆固醇(mmol/L)', '11', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('12', 'phy', '体检项目', 'sgpt', '肝功能.血清谷丙转氨酶', '12', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('13', 'phy', '体检项目', 'sgot', '血清谷草转氨酶', '13', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('14', 'phy', '体检项目', 'alb', '白蛋白', '14', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('15', 'phy', '体检项目', 'tbil', '总胆红素', '15', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('16', 'phy', '体检项目', 'dbil', '结合胆红素', '16', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('17', 'phy', '体检项目', 'hbalc', '糖化血红蛋白', '17', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('18', 'phy', '体检项目', 'renal', '肾功能', '18', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('19', 'phy', '体检项目', 'scre', '血清肌酐', '19', '18');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('20', 'phy', '体检项目', 'bun', '血尿素氮', '20', '18');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('21', 'phy', '体检项目', 'bk', '血钾浓度', '21', '18');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('22', 'phy', '体检项目', 'natrium', '血钠浓度', '22', '18');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('23', 'phy', '体检项目', 'niddmType', '糖尿病类型', '23', '18');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('24', 'toxin', '毒素检测', 'Voitoxin', '呕吐毒素', '1', '');
INSERT INTO t_options (options_id, type, type_name, key_word, key_value, show_order, parentid) VALUES ('25', 'toxin', '毒素检测', 'Zearalenone', '赤霉烯酮', '2', '');


-- 角色
INSERT INTO t_role (role_id, role_name, role_shortname, role_description, create_date_time, update_date_time, deleted) VALUES ('1', '管理员团队', '管理员', '系统管理员', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');


-- 权限
INSERT INTO t_user_role (connectionid, user_id, role_id, create_date_time, update_date_time, deleted) VALUES ('1', '1', '1', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_user_role (connectionid, user_id, role_id, create_date_time, update_date_time, deleted) VALUES ('2', '2', '1', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_user_role (connectionid, user_id, role_id, create_date_time, update_date_time, deleted) VALUES ('3', '3', '1', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_user_role (connectionid, user_id, role_id, create_date_time, update_date_time, deleted) VALUES ('4', '4', '1', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_user_role (connectionid, user_id, role_id, create_date_time, update_date_time, deleted) VALUES ('5', '5', '1', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_user_role (connectionid, user_id, role_id, create_date_time, update_date_time, deleted) VALUES ('6', '6', '1', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_user_role (connectionid, user_id, role_id, create_date_time, update_date_time, deleted) VALUES ('7', '7', '1', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');


-- 菜单权限
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('1', '1', '10100', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('2', '1', '10101', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('3', '1', '10200', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('4', '1', '10201', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('5', '1', '10300', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('6', '1', '10301', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('7', '1', '10302', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('8', '1', '10400', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('9', '1', '10401', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('11', '1', '10403', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('12', '1', '10404', 'list|additional|edit|dele|upload|download', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('13', '1', '10500', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('14', '1', '10501', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('15', '1', '99900', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('16', '1', '99901', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');
INSERT INTO t_role_menu (connectionid, role_id, menu_id, permission_action, create_date_time, update_date_time, deleted) VALUES ('17', '1', '99902', 'list|additional|edit|dele', '2017-10-02 15:51:13', '2017-10-02 15:51:13', '0');


-- 活动
INSERT INTO t_action (action_id, action_name, action_description, create_date_time, update_date_time, deleted) VALUES ('additional', '新增', '添加数据的权限', '2017-10-01 15:48:49', '2017-10-01 15:48:49', '0');
INSERT INTO t_action (action_id, action_name, action_description, create_date_time, update_date_time, deleted) VALUES ('dele', '删除', '删除数据的权限', '2017-10-01 15:48:49', '2017-10-01 15:48:49', '0');
INSERT INTO t_action (action_id, action_name, action_description, create_date_time, update_date_time, deleted) VALUES ('download', '下载', '下载文件', '2017-10-01 15:48:49', '2017-10-01 15:48:49', '0');
INSERT INTO t_action (action_id, action_name, action_description, create_date_time, update_date_time, deleted) VALUES ('edit', '编辑', '修改数据的权限', '2017-10-01 15:48:49', '2017-10-01 15:48:49', '0');
INSERT INTO t_action (action_id, action_name, action_description, create_date_time, update_date_time, deleted) VALUES ('list', '查看', '菜单显示的权限', '2017-10-01 15:48:49', '2017-10-01 15:48:49', '0');
INSERT INTO t_action (action_id, action_name, action_description, create_date_time, update_date_time, deleted) VALUES ('upload', '上传', '上传文件', '2017-10-01 15:48:49', '2017-10-01 15:48:49', '0');


-- 机构
INSERT INTO t_organization (organization_id, organization_name) VALUES ('MA2AXP9G6', '云健康基地');


-- 系统参数配置
INSERT INTO t_config (config_id, config_name, config_value, remark) VALUES ('1', '参数23', '参数值2', '参数备注2');
INSERT INTO t_config (config_id, config_name, config_value, remark) VALUES ('2', 'aa33', 'bb44', 'cc55');
INSERT INTO t_config (config_id, config_name, config_value, remark) VALUES ('3', 'test1', 'test2', 'test3');
INSERT INTO t_config (config_id, config_name, config_value, remark) VALUES ('4', '11', '22', '33');
INSERT INTO t_config (config_id, config_name, config_value, remark) VALUES ('5', '88', '88', '88');
INSERT INTO t_config (config_id, config_name, config_value, remark) VALUES ('', '', '', '');


