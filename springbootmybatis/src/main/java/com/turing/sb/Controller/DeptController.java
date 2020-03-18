package com.turing.sb.Controller;

import com.turing.sb.Service.DeptService;
import com.turing.sb.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/{id}")
    public Dept findById(@PathVariable("id") Integer id){
        Dept dept = deptService.findById(id);
        return dept;
    }
    @GetMapping("/add")
    public String adddept(){
        Dept dept=new Dept();
        dept.setDname("LIUBIN");
        dept.setLoc("CHASHA");
        int count=deptService.adddept(dept);
        System.out.print(count);
       return count>0?"增加成功":"增加失败";
    }

   @GetMapping("/delete/{id}")
   public String deletedept(@PathVariable("id")Integer id){
       int count = deptService.deletedept(id);
       return count>0?"删除成功":"删除失败";
   }

   @GetMapping("/update")
   public String updatedept(){
        Dept dept=new Dept();
        dept.setDeptno(41);
        dept.setDname("LIUBIN");
        dept.setLoc("SHANGXIA");
       int count = deptService.updatedept(dept);
       return count>0?"修改成功":"修改失败";
   }

   @GetMapping("/create")
   public String create(){
       deptService.adddepts();
       return "";
   }

}
