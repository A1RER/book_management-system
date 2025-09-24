package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.Book;
import com.java.util.StringNull;

/**
 * 该类用于处理与书籍相关的数据访问操作，包含查询、添加、更新和删除书籍信息的方法。
 * @author admin
 */
public class BookDao {
    /**
     * 根据书籍对象中的信息进行模糊查询书籍信息。
     * @param con 数据库连接对象，用于与数据库建立连接
     * @param book 书籍对象，包含书籍的名称、作者、出版社等信息，用于作为查询条件
     * @return 返回一个 ResultSet 对象，包含满足查询条件的书籍信息
     * @throws Exception 当数据库操作出现异常时抛出该异常
     */
    public ResultSet query(Connection con, Book book) throws Exception {
        // 用于存储查询语句的字符串缓冲区
        StringBuffer sql = new StringBuffer("select * from book");

        // 如果书籍名称不为空，则添加书籍名称的模糊查询条件
        if (StringNull.isNotEmpty(book.getBook_name())) {
            sql.append(" and book_name like '%"+book.getBook_name()+"%'");
        }

        // 如果书籍作者不为空，则添加书籍作者的模糊查询条件
        if (StringNull.isNotEmpty(book.getBook_writer())) {
            sql.append(" and book_writer like '%"+book.getBook_writer()+"%'");
        }

        // 如果书籍出版社不为空，则添加书籍出版社的模糊查询条件
        if (StringNull.isNotEmpty(book.getBook_publish())) {
            sql.append(" and book_publish like '%"+book.getBook_publish()+"%'");
        }

        // 将查询语句中的第一个 "and" 替换为 "where"，以形成正确的 SQL 查询语句
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql.toString().replaceFirst("and", "where"));
        // 执行查询操作并返回结果集
        return pstmt.executeQuery();
    }

    /**
     * 根据书籍 ID 查询书籍信息。
     * @param con 数据库连接对象，用于与数据库建立连接
     * @param book 书籍对象，包含要查询的书籍的 ID
     * @return 返回一个 ResultSet 对象，包含满足查询条件的书籍信息
     * @throws Exception 当数据库操作出现异常时抛出该异常
     */
    public ResultSet query2(Connection con, Book book) throws Exception {
        // 用于存储查询语句的字符串
        String sql = "select * from book where book_id=?";
        // 创建预编译的 SQL 语句对象
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        // 设置预编译语句中的第一个参数为书籍的 ID
        pstmt.setInt(1, book.getBook_id());
        // 执行查询操作并返回结果集
        return pstmt.executeQuery();
    }

    /**
     * 根据书籍对象中的信息更新书籍的状态。
     * @param con 数据库连接对象，用于与数据库建立连接
     * @param book 书籍对象，包含要更新的书籍的 ID 和新的状态信息
     * @return 返回一个整数，表示受影响的行数
     * @throws Exception 当数据库操作出现异常时抛出该异常
     */
    public int update(Connection con, Book book) throws Exception {
        // 用于存储更新语句的字符串
        String sql = "update book set book_status=? where book_id=?";
        // 创建预编译的 SQL 语句对象
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        // 设置预编译语句中的第一个参数为书籍的新状态
        pstmt.setString(1, book.getBook_status());
        // 设置预编译语句中的第二个参数为书籍的 ID
        pstmt.setInt(2, book.getBook_id());
        // 执行更新操作并返回受影响的行数
        return pstmt.executeUpdate();
    }

    /**
     * 向数据库中添加一本新的书籍信息。
     * @param con 数据库连接对象，用于与数据库建立连接
     * @param book 书籍对象，包含要添加的书籍的所有信息
     * @return 返回一个整数，表示受影响的行数
     * @throws Exception 当数据库操作出现异常时抛出该异常
     */
    public int add(Connection con, Book book) throws Exception {
        // 用于存储插入语句的字符串
        String sql = "insert into book values(?,?,?,?,?)";
        // 创建预编译的 SQL 语句对象
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        // 设置预编译语句中的第一个参数为书籍的 ID
        pstmt.setInt(1, book.getBook_id());
        // 设置预编译语句中的第二个参数为书籍的名称
        pstmt.setString(2, book.getBook_name());
        // 设置预编译语句中的第三个参数为书籍的作者
        pstmt.setString(3, book.getBook_writer());
        // 设置预编译语句中的第四个参数为书籍的出版社
        pstmt.setString(4, book.getBook_publish());
        // 设置预编译语句中的第五个参数为书籍的状态
        pstmt.setString(5, book.getBook_status());
        // 执行插入操作并返回受影响的行数
        return pstmt.executeUpdate();
    }

    /**
     * 根据书籍 ID 删除数据库中的书籍信息。
     * @param con 数据库连接对象，用于与数据库建立连接
     * @param bookId 要删除的书籍的 ID
     * @return 返回一个整数，表示受影响的行数
     * @throws Exception 当数据库操作出现异常时抛出该异常
     */
    public int delete(Connection con, int bookId) throws Exception {
        // 用于存储删除语句的字符串
        String sql = "delete from book where book_id = ?";
        // 创建预编译的 SQL 语句对象
        PreparedStatement pstmt = con.prepareStatement(sql);
        // 设置预编译语句中的第一个参数为要删除的书籍的 ID
        pstmt.setInt(1, bookId);
        // 执行删除操作并返回受影响的行数
        return pstmt.executeUpdate();
    }
}