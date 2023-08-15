package com.goktech.dao.impl;

import com.goktech.dao.AccountDao;
import com.goktech.pojo.Account;
import com.goktech.utils.DpUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * {@code @Description:}
 */
public class AccountDaoImpl implements AccountDao {
    /**
     * 添加账户
     *
     * @param cardId
     * @param password
     * @param balance
     */
    @Override
    public void addAccount(long cardId, String password, BigDecimal balance) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "insert into account(cardId,password,balance) values (?,?,?)";
            queryRunner.update(connection, sql, cardId, password, balance);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
    }
    
    /**
     * 通过卡号查找用户
     *
     * @param cardId
     * @return
     */
    @Override
    public Account findAccountByCardId(long cardId) {
        Connection connection = null;
        Account account = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select * from account where cardid=?";
            account = queryRunner.query(connection, sql, new BeanHandler<>(Account.class), cardId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        return account;
    }
    
    /**
     * 根据卡号和密码查找用户
     *
     * @param cardId
     * @param password
     * @return
     */
    @Override
    public Account findAccount(long cardId, String password) {
        Connection connection = null;
        Account account = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select * from account where cardid=? and password=?";
            account = queryRunner.query(connection, sql, new BeanHandler<>(Account.class), cardId, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        return account;
    }
    
    /**
     * 查询余额
     *
     * @param cardId
     * @param password
     * @return
     */
    @Override
    public BigDecimal checkBalance(long cardId, String password) {
        Connection connection = null;
        BigDecimal balance = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select balance from account where cardid=? and password=?";
            balance = queryRunner.query(connection, sql, new ScalarHandler<>(), cardId, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        
        return balance;
    }
    
    /**
     * 转账
     *
     * @param cardId
     * @param password
     * @param money
     * @param cardId2
     */
    @Override
    public void transfer(long cardId, String password, BigDecimal money, long cardId2) {
        
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            
            // 关闭自动提交
            connection.setAutoCommit(false);
            
            String sql = "update account set balance=balance-? where  cardid=? and password=?";
            queryRunner.update(connection, sql, money, cardId, password);
            
            String sql2 = "update account set balance=balance+? where cardid=?";
            queryRunner.update(connection, sql2, money, cardId2);
            
            connection.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                
                // 有异常回滚
                connection.rollback();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                
                connection.setAutoCommit(true);
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        
    }
    
    /**
     * 存款
     *
     * @param cardId
     * @param password
     * @param money
     */
    @Override
    public void deposit(long cardId, String password, BigDecimal money) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "update account set balance=balance+? where cardid=? and password=?";
            queryRunner.update(connection, sql, money, cardId, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
    }
    
    /**
     * 取款
     *
     * @param cardId
     * @param password
     * @param money
     */
    @Override
    public void withdrawal(long cardId, String password, BigDecimal money) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "update account set balance=balance-? where cardid=? and password=?";
            queryRunner.update(connection, sql, money, cardId, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
    }
    
    /**
     * 修改用户密码
     *
     * @param cardId
     * @param password
     */
    @Override
    public void changePsd(long cardId, String password) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "update account set password=? where cardid=?";
            queryRunner.update(connection, sql, password, cardId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
    }
    
    /**
     * 根据卡号查找密码
     *
     * @param cardId
     * @return
     */
    @Override
    public String queryPsd(long cardId) {
        Connection connection = null;
        String password = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select password  from account where cardid=?";
            password = queryRunner.query(connection, sql, new ScalarHandler<>(), cardId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        return password;
    }
    
    
}