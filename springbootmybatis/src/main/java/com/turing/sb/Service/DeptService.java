package com.turing.sb.Service;

import com.turing.sb.entity.Dept;
import org.springframework.stereotype.Service;


public interface DeptService {
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Dept findById(Integer id);

    /**
     * 增加一个部门
     * @param dept
     * @return
     */
    public int adddept(Dept dept);

    /**
     * 根据ID删除部门
     * @param id
     * @return
     */
    public int deletedept(Integer id);

    /**
     *修改部门
     * @param dept
     * @return
     */
    public int updatedept(Dept dept);

    /**
     * 增加很多个部门
     * @param
     * @return
     */
    public void adddepts();
}
