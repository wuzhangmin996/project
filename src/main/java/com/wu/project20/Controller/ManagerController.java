package com.wu.project20.Controller;

import com.wu.project20.Mapper.ManagerMapper;
import com.wu.project20.bean.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

    @Autowired
    ManagerMapper managerMapper;


    @GetMapping("manager/login/{id}")
    public Manager managerinfo(@PathVariable("id") String id ) {
        return managerMapper.selectManager(id);
    }
    @GetMapping("manager/update/{id}&&{password}")
    public void updateManager(@PathVariable("id") String id ,@PathVariable("password") String password){
        managerMapper.updateManager(id,password);
    }
}
