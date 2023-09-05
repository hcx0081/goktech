package com.goktech.dao;

import com.goktech.pojo.Bill;
import com.goktech.pojo.Owner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * {@code @description:}
 */
public interface OwnerDao {
    
    /**
     * 查询所有业主
     *
     * @return
     */
    List<Owner> queryAllOwners() throws Exception;
    
    /**
     * 添加业主信息
     */
    void addOwner(String id, String name, long phone, String room, Date live) throws Exception;
    
    /**
     * 查询账单
     *
     * @param month
     * @param type
     * @return
     */
    List<Bill> queryBill(Integer month, String type) throws Exception;
    
    /**
     * 给指定业主导入账单
     *
     * @param phone
     * @param billId
     * @param date
     * @param money
     * @param status
     * @param type
     * @throws Exception
     */
    void addBill(String billId, Long phone, Date date, BigDecimal money, String status, String type) throws Exception;
    
    /**
     * 查找业主的账单
     *
     * @param phone
     * @return
     */
    List<Bill> findBillOfOwner(Long phone) throws Exception;
    
    
    /**
     * 查找业主的未缴费的账单
     *
     * @param phone
     * @return
     */
    List<Bill> findNotPayBillOfOwner(Long phone) throws Exception;
    
    /**
     * 根据账单号缴费
     *
     * @param billId
     * @param status
     * @param payTime
     * @throws Exception
     */
    void pay(String billId, String status, Timestamp payTime) throws Exception;
    
    /**
     * 根据账单号查找账单
     *
     * @param billId
     * @return
     */
    Bill findNotPayBill(String billId) throws Exception;
    
    /**
     * 查找指定业主当月账单
     *
     * @param phone
     * @param month
     * @return
     * @throws Exception
     */
    List<Bill> queryBillByMonth(Long phone, Integer month) throws Exception;
}