package com.jzpz.service.Impl;

import com.jzpz.domain.Result;
import com.jzpz.domain.TableColumn;
import com.jzpz.service.CreateTableService;
import com.jzpz.util.JsonUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiQiang on 2016/9/24.
 */
@Service
public class CreateTableImpl implements CreateTableService {
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @PersistenceContext
    private EntityManager entityManager;
    @SuppressWarnings("unchecked")
	@Override
    public Result createTable(String tableName, String columns) {
        String message="";
        boolean flag = false;
        Map<String, String> tableMap = selectAllTables();
        if(StringUtils.isBlank(tableName)){
            message="表名为空";
        }else{
            //查询该表名是否已经存在
            //一张表中一般有一个主键
            //主键时不能为空和唯一都要选择
            //外键时要查看所对应的表是否存在
            //类型约束下,列长度是否能对应上
            insertTable();
        	if(null!=tableMap&&tableMap.containsKey(tableName)){
        		flag = false;
        		message = "该表已经存在";
        		return Result.builder().flag(flag).message(message).build();
        	}
            List<TableColumn> tableColumns = JsonUtils.getList4Json(columns,TableColumn.class);
            for (TableColumn tableColumn:tableColumns){
                System.out.println(tableColumn);
            }
            flag = true;
            message="建表成功!";
        }
        return Result.builder().flag(flag).message(message).build();
    }
    @SuppressWarnings("unchecked")
	private Map<String, String> selectAllTables(){
    	Map<String, String> tableMap = new HashMap<String,String>();
    	List<String> tables = new ArrayList<String>();
        //String sql="select name from sysobjects where xtype='u'";
        String sql ="show tables";
        Query query = entityManager.createNativeQuery(sql);
        tables = query.getResultList();
        if(null!=tables && tables.size()>0){
    	   for(String table:tables){
    		   tableMap.put(table, table);
    	   }
       }
        return tableMap;
    }
    private void insertTable (){
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, userName,password);
            stmt = conn.createStatement();
            String sql = "CREATE TABLE REGISTRATION " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
