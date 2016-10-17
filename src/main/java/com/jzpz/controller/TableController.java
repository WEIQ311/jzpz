package com.jzpz.controller;

import com.jzpz.domain.UserTable;
import com.jzpz.repository.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by weiQiang on 2016/10/10.
 */
@RestController
@RequestMapping(value = "table")
public class TableController {
    @Autowired
    private UserTableRepository userTableRepository;

    @RequestMapping(value = "save",method = RequestMethod.GET)
    public Page<UserTable> saveTable(Pageable pageable){
        List<UserTable> tables = new ArrayList<>();
        for(int i = 0;i<10;i++){
            tables.add(UserTable.builder().userName("用户"+(i+1)).insertTime(new Date()).updateTime(new Date()).orderNum(i+1).build());
        }
       // userTableRepository.save(tables);
        return userTableRepository.findAll(pageable);
    }
    @RequestMapping(value = "getAll",method = RequestMethod.GET)
    public void getAll(Pageable pageable,String userName){

    }
}
