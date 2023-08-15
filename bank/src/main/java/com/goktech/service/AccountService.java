package com.goktech.service;

import com.goktech.pojo.Account;

import java.math.BigDecimal;

/**
 * {@code @Description:}
 */
public interface AccountService {
    /**
     * 添加账户
     *
     * @param cardId
     * @param password
     * @param balance
     */
    public void addAccount(long cardId, String password, BigDecimal balance);
    
    /**
     * 通过卡号查找用户
     *
     * @param cardId
     * @return
     */
    public Account findAccountByCardId(long cardId);
    
    /**
     * 根据卡号和密码查找用户
     *
     * @param cardId
     * @param password
     * @return
     */
    public Account findAccount(long cardId, String password);
    
    /**
     * 查询余额
     *
     * @param cardId
     * @param password
     * @return
     */
    public BigDecimal checkBalance(long cardId, String password);
    
    /**
     * 转账
     *
     * @param cardId
     * @param password
     * @param money
     * @param cardId2
     */
    public void transfer(long cardId, String password, BigDecimal money, long cardId2);
    
    
    /**
     * 存款
     *
     * @param cardId
     * @param password
     * @param money
     */
    public void deposit(long cardId, String password, BigDecimal money);
    
    /**
     * 取款
     *
     * @param cardId
     * @param password
     * @param money
     */
    public void withdrawal(long cardId, String password, BigDecimal money);
    
    /**
     * 修改用户密码
     *
     * @param cardId
     * @param password
     */
    public void changePsd(long cardId, String password);
    
    /**
     * 根据卡号查找密码
     *
     * @param cardId
     * @return
     */
    public String queryPsd(long cardId);
}