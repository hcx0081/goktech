package com.goktech.service;

/**
 * {@code @Description:}
 */
public interface OwnerService {
    /**
     * 查询所有业主
     *
     * @return
     */
    void queryAllOwners() throws Exception;
    
    /**
     * 添加业主信息
     */
    void addOwner() throws Exception;
    
    /**
     * 查找账单
     */
    void queryBill() throws Exception;
    
    /**
     * 导入账单
     */
    void addBill() throws Exception;
    
    /**
     * 查找业主的账单
     *
     * @param phone
     */
    void findBillOfOwner(Long phone) throws Exception;
    
    /**
     * 缴费
     *
     * @param phone
     * @throws Exception
     */
    void pay(Long phone) throws Exception;
    
    /**
     * 显示报表
     *
     * @param phone
     */
    void showForm(Long phone) throws Exception;
}