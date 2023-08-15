package com.goktech.service.impl;

import com.goktech.dao.AccountDao;
import com.goktech.dao.impl.AccountDaoImpl;
import com.goktech.pojo.Account;
import com.goktech.service.AccountService;

import java.math.BigDecimal;

/**
 * {@code @Description:}
 */
public class AccountServiceImpl implements AccountService {
    
    private AccountDao accountDao = new AccountDaoImpl();
    
    
    /**
     * 添加账户
     *
     * @param cardId
     * @param password
     * @param balance
     */
    @Override
    public void addAccount(long cardId, String password, BigDecimal balance) {
        accountDao.addAccount(cardId, password, balance);
    }
    
    /**
     * 通过卡号查找用户
     *
     * @param cardId
     * @return
     */
    @Override
    public Account findAccountByCardId(long cardId) {
        Account account = accountDao.findAccountByCardId(cardId);
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
        Account account = accountDao.findAccount(cardId, password);
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
        BigDecimal balance = accountDao.checkBalance(cardId, password);
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
        accountDao.transfer(cardId, password, money, cardId2);
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
        accountDao.deposit(cardId, password, money);
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
        accountDao.withdrawal(cardId, password, money);
    }
    
    /**
     * 修改用户密码
     *
     * @param cardId
     * @param password
     */
    @Override
    public void changePsd(long cardId, String password) {
        accountDao.changePsd(cardId, password);
    }
    
    /**
     * 根据卡号查找密码
     *
     * @param cardId
     * @return
     */
    @Override
    public String queryPsd(long cardId) {
        String password = accountDao.queryPsd(cardId);
        return password;
    }
}