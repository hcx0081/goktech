package com.goktech.dao.impl;

import com.goktech.dao.OwnerDao;
import com.goktech.pojo.Bill;
import com.goktech.pojo.Owner;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * {@code @Description:}
 */
class OwnerDaoImplTest {
    OwnerDao ownerDao = new OwnerDaoImpl();
    
    @Test
    void queryAllOwners() throws Exception {
        List<Owner> owners = ownerDao.queryAllOwners();
        System.out.println(owners);
    }
    
    @Test
    void test() throws Exception {
        List<Bill> bills = ownerDao.queryBill(8, null);
        System.out.println(bills);
    }
}