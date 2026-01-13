-- ===================================
-- 图书管理系统数据库更新脚本 v1.0
-- 更新日期: 2026-01-13
-- 更新内容: 密码MD5加密
-- ===================================

-- 注意事项:
-- 1. 本脚本会将所有现有用户的密码转换为MD5格式
-- 2. 转换后，原密码将无法使用，请通知用户
-- 3. 建议在执行前备份数据库: mysqldump -u root -p book_admin > backup.sql
-- 4. 测试账号的MD5密码已在下方提供

-- ===================================
-- 步骤1: 备份原有数据
-- ===================================
CREATE TABLE IF NOT EXISTS reader_backup AS SELECT * FROM reader;
CREATE TABLE IF NOT EXISTS admin_backup AS SELECT * FROM admin;

-- ===================================
-- 步骤2: 更新reader表的密码为MD5
-- ===================================
-- 方法1: 如果原密码是123456，更新为MD5值
UPDATE reader SET reader_password = 'e10adc3949ba59abbe56e057f20f883e' WHERE reader_password = '123456';

-- 方法2: 如果原密码是admin，更新为MD5值
UPDATE reader SET reader_password = '21232f297a57a5a743894a0e4a801fc3' WHERE reader_password = 'admin';

-- 方法3: 批量更新（如果MySQL版本支持MD5函数）
-- UPDATE reader SET reader_password = MD5(reader_password);

-- ===================================
-- 步骤3: 更新admin表的密码为MD5
-- ===================================
-- 管理员密码123456的MD5值
UPDATE admin SET admin_password = 'e10adc3949ba59abbe56e057f20f883e' WHERE admin_password = '123456';

-- 管理员密码admin的MD5值
UPDATE admin SET admin_password = '21232f297a57a5a743894a0e4a801fc3' WHERE admin_password = 'admin';

-- ===================================
-- 步骤4: 插入测试数据（可选）
-- ===================================
-- 测试管理员账号: 用户名admin, 密码123456
INSERT IGNORE INTO admin VALUES(99, 'admin', '13800138000', 'e10adc3949ba59abbe56e057f20f883e');

-- 测试读者账号: 用户名test, 密码123456
INSERT IGNORE INTO reader VALUES(99, 'test', '13900139000', 'e10adc3949ba59abbe56e057f20f883e');

-- ===================================
-- 常用密码的MD5值参考
-- ===================================
-- 123456 -> e10adc3949ba59abbe56e057f20f883e
-- admin  -> 21232f297a57a5a743894a0e4a801fc3
-- 000000 -> 670b14728ad9902aecba32e22fa4f6bd
-- 111111 -> 96e79218965eb72c92a549dd5a330112
-- password -> 5f4dcc3b5aa765d61d8327deb882cf99

-- ===================================
-- 验证更新结果
-- ===================================
SELECT '读者表密码长度检查(应该都是32位):' AS message;
SELECT reader_id, reader_name, LENGTH(reader_password) as pwd_length
FROM reader
WHERE LENGTH(reader_password) != 32;

SELECT '管理员表密码长度检查(应该都是32位):' AS message;
SELECT admin_id, admin_name, LENGTH(admin_password) as pwd_length
FROM admin
WHERE LENGTH(admin_password) != 32;

-- 如果上述查询有结果，说明还有密码未转换成功
