package com.turing.sb.Service.impl;

import com.turing.sb.Service.DeptService;
import com.turing.sb.entity.Dept;
import com.turing.sb.entity.DeptExample;
import com.turing.sb.entity.DeptExample.Criteria;
import com.turing.sb.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptServiceimpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Dept findById(Integer id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public int adddept(Dept dept) {
        return deptMapper.insertSelective(dept);
    }

    @Override
    public int deletedept(Integer id) {
        return deptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updatedept(Dept dept) {
        return deptMapper.updateByPrimaryKeySelective(dept);
    }

//    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
//    @Transactional
    @Override
    public void adddepts() {
        //事务默认在CRUD方式上
        for(int i=0;i<10;i++){
            if(i==3){
                throw new RuntimeException("我报错了");
            }
            Dept dept=new Dept();
            dept.setDname("高手");
            dept.setLoc("长沙");
            deptMapper.insert(dept);
        }
    }
}
