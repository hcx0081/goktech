package com.goktech;

import com.alibaba.excel.EasyExcel;
import com.goktech.entity.Gkstudent;
import com.goktech.entity.StudentData;
import com.goktech.service.GkstudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ExcelApplicationTests {
    
    String PATH = "D:\\IDEA\\IntelliJ IDEA 2021.3.3\\Workspace\\goktech\\excel\\";
    @Autowired
    private GkstudentService gkstudentService;
    
    /**
     * 将数据导入到Excel
     */
    @Test
    void create() {
        
        List<Gkstudent> gkstudentList = gkstudentService.list();
        
        ArrayList<StudentData> gkstudents = new ArrayList<>();
        for (Gkstudent gkstudent : gkstudentList) {
            StudentData studentData = new StudentData();
            BeanUtils.copyProperties(gkstudent, studentData, "status");
            if (gkstudent.getStatus().getStatus() == 1) {
                studentData.setStatus("在校");
            } else {
                studentData.setStatus("辍学");
            }
            gkstudents.add(studentData);
        }
        
        String fileName = PATH + "学生信息.xlsx";
        EasyExcel.write(fileName, StudentData.class)
                 .sheet("学生表格")
                 .doWrite(gkstudents);
    }
    
}
