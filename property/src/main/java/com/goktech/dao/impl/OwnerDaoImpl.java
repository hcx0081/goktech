package com.goktech.dao.impl;

import com.goktech.dao.OwnerDao;
import com.goktech.pojo.Bill;
import com.goktech.pojo.Owner;
import com.goktech.utils.DpUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * {@code @description:}
 */
public class OwnerDaoImpl implements OwnerDao {
    /**
     * 查询所有业主
     *
     * @return
     */
    @Override
    public List<Owner> queryAllOwners() throws Exception {
        Connection connection = DpUtils.getConnectionByDruid();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select id,name,phone,room,live from owner";
        List<Owner> owners = queryRunner.query(connection, sql, new BeanListHandler<>(Owner.class));
        DpUtils.closeResourceByDBUtils(null, null, connection);
        return owners;
    }
    
    /**
     * 添加业主信息
     */
    @Override
    public void addOwner(String id, String name, long phone, String room, Date live) throws Exception {
        Connection connection = DpUtils.getConnectionByDruid();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into owner(id,name,phone,room,live) values (?,?,?,?,?)";
        queryRunner.update(connection, sql, id, name, phone, room, live);
        DpUtils.closeResourceByDBUtils(null, null, connection);
    }
    
    /**
     * 查询账单
     *
     * @param month
     * @param type
     * @return
     */
    @Override
    public List<Bill> queryBill(Integer month, String type) throws Exception {
        Connection connection = DpUtils.getConnectionByDruid();
        QueryRunner queryRunner = new QueryRunner();
        
        if (type == null && month != null) {
            String sql = "select billid billId,date,money,status,type,pay_time payTime from bill where month(date)=?";
            List<Bill> bills = queryRunner.query(connection, sql, new BeanListHandler<>(Bill.class), month);
            DpUtils.closeResourceByDBUtils(null, null, connection);
            return bills;
        }
        if (month == null && type != null) {
            String sql = "select billid billId,date,money,status,type,pay_time payTime from bill where type=?";
            List<Bill> bills = queryRunner.query(connection, sql, new BeanListHandler<>(Bill.class), type);
            DpUtils.closeResourceByDBUtils(null, null, connection);
            return bills;
        }
        if (type == null && month == null) {
            String sql = "select billid billId,date,money,status,type,pay_time payTime from bill";
            List<Bill> bills = queryRunner.query(connection, sql, new BeanListHandler<>(Bill.class));
            DpUtils.closeResourceByDBUtils(null, null, connection);
            return bills;
        }
        
        return null;
    }
    
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
    @Override
    public void addBill(String billId, Long phone, Date date, BigDecimal money, String status, String type) throws Exception {
        Connection connection = DpUtils.getConnectionByDruid();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into bill(billid,phone,date,money,status,type) values (?,?,?,?,?,?)";
        queryRunner.update(connection, sql, billId, phone, date, money, status, type);
        DpUtils.closeResourceByDBUtils(null, null, connection);
    }
    
    /**
     * 查找业主的账单
     *
     * @param phone
     * @return
     */
    @Override
    public List<Bill> findBillOfOwner(Long phone) throws Exception {
        Connection connection = DpUtils.getConnectionByDruid();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select billid billId,date,money,status,type,pay_time payTime from bill where phone=?";
        List<Bill> bills = queryRunner.query(connection, sql, new BeanListHandler<>(Bill.class), phone);
        DpUtils.closeResourceByDBUtils(null, null, connection);
        return bills;
    }
    
    /**
     * 查找业主的未缴费的账单
     *
     * @param phone
     * @return
     */
    @Override
    public List<Bill> findNotPayBillOfOwner(Long phone) throws Exception {
        Connection connection = DpUtils.getConnectionByDruid();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select billid billId,date,money,status,type,pay_time payTime from bill where phone=? and status='未缴费'";
        List<Bill> bills = queryRunner.query(connection, sql, new BeanListHandler<>(Bill.class), phone);
        DpUtils.closeResourceByDBUtils(null, null, connection);
        return bills;
    }
    
    /**
     * 根据账单号缴费
     *
     * @param billId
     * @param status
     * @param payTime
     * @throws Exception
     */
    @Override
    public void pay(String billId, String status, Timestamp payTime) throws Exception {
        Connection connection = DpUtils.getConnectionByDruid();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update bill set status=?,pay_time=? where billid =?";
        queryRunner.update(connection, sql, status, payTime, billId);
        DpUtils.closeResourceByDBUtils(null, null, connection);
    }
    
    /**
     * 根据账单号查找账单
     *
     * @param billId
     * @return
     */
    @Override
    public Bill findNotPayBill(String billId) throws Exception {
        Connection connection = DpUtils.getConnectionByDruid();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select billid billId,date,money,status,type,pay_time payTime from bill where billId=? and status='未缴费'";
        Bill bill = queryRunner.query(connection, sql, new BeanHandler<>(Bill.class), billId);
        DpUtils.closeResourceByDBUtils(null, null, connection);
        return bill;
    }
    
    /**
     * 查找指定业主当月账单
     *
     * @param phone
     * @param month
     * @return
     * @throws Exception
     */
    @Override
    public List<Bill> queryBillByMonth(Long phone, Integer month) throws Exception {
        Connection connection = DpUtils.getConnectionByDruid();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select billid billId,date,money,status,type,pay_time payTime from bill where phone =? and month(date)=?";
        List<Bill> bills = queryRunner.query(connection, sql, new BeanListHandler<>(Bill.class), phone, month);
        DpUtils.closeResourceByDBUtils(null, null, connection);
        return bills;
    }
    
    
}