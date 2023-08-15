package com.goktech.service.impl;

import com.goktech.dao.OwnerDao;
import com.goktech.dao.impl.OwnerDaoImpl;
import com.goktech.pojo.Bill;
import com.goktech.pojo.Owner;
import com.goktech.service.OwnerService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * {@code @Description:}
 */
public class OwnerServiceImpl implements OwnerService {
    Scanner scanner = new Scanner(System.in);
    private OwnerDao ownerDao = new OwnerDaoImpl();
    
    /**
     * 查询所有业主
     *
     * @return
     */
    @Override
    public void queryAllOwners() throws Exception {
        List<Owner> owners = ownerDao.queryAllOwners();
        System.out.println("[身份证]" + "\t\t" + "[姓名]" + "\t\t" + "[手机号]" + "\t\t" + "[楼栋房号]" + "\t" + "[入住日期]");
        for (Owner owner : owners) {
            System.out.println(owner.getId() + "\t\t" + owner.getName() + "\t\t\t" + owner.getPhone() + "\t"
                    + owner.getRoom() + "\t\t\t" + owner.getLive());
        }
    }
    
    /**
     * 添加业主信息
     */
    @Override
    public void addOwner() throws Exception {
        String id;
        while (true) {
            System.out.println("请输入业主身份证：");
            id = scanner.next();
            if (id.length() != 18) {
                System.out.println("身份证输入不正确，请重新输入！");
            } else {
                break;
            }
        }
        System.out.println("请输入业主姓名：");
        String name = scanner.next();
        
        Long phone;
        while (true) {
            System.out.println("请输入业主手机号码：");
            phone = scanner.nextLong();
            if (phone.toString().length() != 11) {
                System.out.println("手机号码输入不正确，请重新输入！");
            } else {
                break;
            }
        }
        
        System.out.println("请输入业主楼栋房号：");
        String room = scanner.next();
        
        Date live;
        while (true) {
            System.out.println("请输入业主入住日期（yyyy/MM/dd）：");
            String liveStr = scanner.next();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            try {
                live = format.parse(liveStr);
                break;
            } catch (ParseException e) {
                System.out.println("日期输入格式有误，请重新输入！");
            }
        }
        
        ownerDao.addOwner(id, name, phone, room, live);
        System.out.println("添加成功！");
    }
    
    /**
     * 查找账单
     */
    @Override
    public void queryBill() throws Exception {
        Integer month;
        System.out.println("请输入想要查询的月份（1-12）：");
        month = scanner.nextInt();
        if (month == -1) {
            while (true) {
                System.out.println("请输入想要查询的缴费类型（水费/电费/物业费）：");
                String type = scanner.next();
                if ("-1".equals(type)) {
                    List<Bill> bills = ownerDao.queryBill(null, null);
                    System.out.println("[账单号]" + "\t\t\t" + "[账单日期]" + "\t" + "[账单金额]" + "\t"
                            + "[账单状态]" + "\t" + "[缴费类型]" + "\t" + "[缴费时间]");
                    for (Bill bill : bills) {
                        System.out.println(bill.getBillId() + "\t" + bill.getDate() + "\t"
                                + bill.getMoney() + "\t\t" + bill.getStatus() + "\t\t" + bill.getType()
                                + "\t\t\t" + bill.getPayTime());
                    }
                    break;
                } else if ("水费".equals(type) || "电费".equals(type) || "物业费".equals(type)) {
                    List<Bill> bills = ownerDao.queryBill(null, type);
                    System.out.println("[账单号]" + "\t\t" + "[账单日期]" + "\t" + "[账单金额]" + "\t"
                            + "[账单状态]" + "\t" + "[缴费类型]" + "\t" + "[缴费时间]");
                    for (Bill bill : bills) {
                        System.out.println(bill.getBillId() + "\t" + bill.getDate() + "\t"
                                + bill.getMoney() + "\t\t" + bill.getStatus() + "\t\t" + bill.getType()
                                + "\t\t\t" + bill.getPayTime());
                    }
                    break;
                } else {
                    System.out.println("输入的缴费类型有误，请重新输入！");
                }
            }
        }
        if (month != -1) {
            while (true) {
                System.out.println("请输入想要查询的缴费类型（水费/电费/物业费）：");
                String type = scanner.next();
                if ("-1".equals(type)) {
                    List<Bill> bills = ownerDao.queryBill(month, null);
                    System.out.println("[账单号]" + "\t\t" + "[账单日期]" + "\t" + "[账单金额]" + "\t"
                            + "[账单状态]" + "\t" + "[缴费类型]" + "\t" + "[缴费时间]");
                    for (Bill bill : bills) {
                        System.out.println(bill.getBillId() + "\t" + bill.getDate() + "\t"
                                + bill.getMoney() + "\t\t" + bill.getStatus() + "\t\t" + bill.getType()
                                + "\t\t\t" + bill.getPayTime());
                    }
                    break;
                } else if ("水费".equals(type) || "电费".equals(type) || "物业费".equals(type)) {
                    List<Bill> bills = ownerDao.queryBill(month, type);
                    System.out.println("[账单号]" + "\t\t" + "[账单日期]" + "\t" + "[账单金额]" + "\t"
                            + "[账单状态]" + "\t" + "[缴费类型]" + "\t" + "[缴费时间]");
                    for (Bill bill : bills) {
                        System.out.println(bill.getBillId() + "\t" + bill.getDate() + "\t"
                                + bill.getMoney() + "\t\t" + bill.getStatus() + "\t\t" + bill.getType()
                                + "\t\t\t" + bill.getPayTime());
                    }
                    break;
                } else {
                    System.out.println("输入的缴费类型有误，请重新输入！");
                }
            }
        }
    }
    
    /**
     * 导入账单
     */
    @Override
    public void addBill() throws Exception {
        
        String billId = String.valueOf(System.currentTimeMillis());
        
        System.out.println("请输入账单所属的业主手机号码：");
        Long phone = scanner.nextLong();
        
        Random random = new Random();
        // 100.00-499.999999
        double money = 100.0 + random.nextDouble() * 400;
        
        String[] type = {"水费", "电费", "物业费"};
        int i = random.nextInt(3);
        
        ownerDao.addBill(billId, phone, new Date(), new BigDecimal(money), "未缴费", type[i]);
        System.out.println("导入成功！");
    }
    
    /**
     * 查找业主的账单
     *
     * @param phone
     */
    @Override
    public void findBillOfOwner(Long phone) throws Exception {
        List<Bill> bills = ownerDao.findBillOfOwner(phone);
        System.out.println("[账单号]" + "\t\t" + "[账单日期]" + "\t" + "[账单金额]" + "\t"
                + "[账单状态]" + "\t" + "[缴费类型]" + "\t" + "[缴费时间]");
        for (Bill bill : bills) {
            System.out.println(bill.getBillId() + "\t" + bill.getDate() + "\t"
                    + bill.getMoney() + "\t\t" + bill.getStatus() + "\t\t" + bill.getType()
                    + "\t\t\t" + bill.getPayTime());
        }
        
    }
    
    /**
     * 缴费
     *
     * @param phone
     * @throws Exception
     */
    @Override
    public void pay(Long phone) throws Exception {
        List<Bill> notPayBillOfOwner = ownerDao.findNotPayBillOfOwner(phone);
        System.out.println("[账单号]" + "\t\t\t" + "[账单日期]" + "\t" + "[账单金额]" + "\t"
                + "[账单状态]" + "\t" + "[缴费类型]" + "\t" + "[缴费时间]");
        for (Bill bill : notPayBillOfOwner) {
            System.out.println(bill.getBillId() + "\t" + bill.getDate() + "\t"
                    + bill.getMoney() + "\t\t" + bill.getStatus() + "\t\t" + bill.getType()
                    + "\t\t\t" + bill.getPayTime());
        }
        
        while (true) {
            System.out.println("请输入你要缴费的账单号：");
            String billId = scanner.next();
            
            if (ownerDao.findNotPayBill(billId) != null) {
                ownerDao.pay(billId, "已缴费", new Timestamp(System.currentTimeMillis()));
                System.out.println("缴费成功!");
                break;
            } else {
                System.out.println("输入的账单号有误，请重新输入！");
            }
        }
        
        System.out.println("当月的账单明细如下：");
        List<Bill> bills = ownerDao.queryBillByMonth(phone, LocalDate.now().getMonthValue());
        System.out.println("[账单号]" + "\t\t\t" + "[账单日期]" + "\t" + "[账单金额]" + "\t"
                + "[账单状态]" + "\t" + "[缴费类型]" + "\t" + "[缴费时间]");
        for (Bill bill : bills) {
            System.out.println(bill.getBillId() + "\t" + bill.getDate() + "\t"
                    + bill.getMoney() + "\t\t" + bill.getStatus() + "\t\t" + bill.getType()
                    + "\t\t\t" + bill.getPayTime());
        }
    }
    
    /**
     * 显示报表
     *
     * @param phone
     */
    @Override
    public void showForm(Long phone) throws Exception {
        List<Bill> bills = ownerDao.findBillOfOwner(phone);
        
        HashSet<Object> type = new HashSet<>();
        for (int i = 0; i < bills.size(); i++) {
            Bill bill = bills.get(i);
            // 注意bill.getDate()获取的类型是java.sql.Date类而不是java.utils.Date类
            if (bill.getDate() instanceof java.sql.Date) {
                if (((java.sql.Date) bill.getDate()).toLocalDate().getYear() == LocalDate.now().getYear()) {
                    type.add(bill.getType());
                }
            }
        }
        System.out.println("您当前有" + type + "账单");
        
        BigDecimal m = new BigDecimal(0);
        for (Bill bill : bills) {
            // 注意bill.getDate()获取的类型是java.sql.Date类而不是java.utils.Date类
            if (bill.getDate() instanceof java.sql.Date) {
                if (((java.sql.Date) bill.getDate()).toLocalDate().getMonthValue() == (LocalDate.now().getMonthValue() - 1)) {
                    if (bill.getMoney() == null) {
                        m.add(BigDecimal.valueOf(0));
                    } else {
                        m.add(bill.getMoney());
                    }
                }
            }
        }
        System.out.println("您上个月缴费的金额为" + m + "元");
        
        
        m = new BigDecimal(0);
        for (Bill bill : bills) {
            // 注意bill.getDate()获取的类型是java.sql.Date类而不是java.utils.Date类
            if (bill.getDate() instanceof java.sql.Date) {
                if (((java.sql.Date) bill.getDate()).toLocalDate().getYear() == (LocalDate.now().getYear() - 1)) {
                    if (bill.getMoney() == null) {
                        m.add(BigDecimal.valueOf(0));
                    } else {
                        m.add(bill.getMoney());
                    }
                }
            }
        }
        System.out.println("您去年的缴费金额为" + m + "元");
        
        
        System.out.println("您已缴费的账单如下：");
        System.out.println("[账单号]" + "\t\t\t" + "[账单日期]" + "\t" + "[账单金额]" + "\t"
                + "[账单状态]" + "\t" + "[缴费类型]" + "\t" + "[缴费时间]");
        for (Bill bill : bills) {
            if ("已缴费".equals(bill.getStatus())) {
                System.out.println(bill.getBillId() + "\t" + bill.getDate() + "\t"
                        + bill.getMoney() + "\t\t" + bill.getStatus() + "\t\t" + bill.getType()
                        + "\t\t\t" + bill.getPayTime());
            }
        }
        
        
    }
    
    
}