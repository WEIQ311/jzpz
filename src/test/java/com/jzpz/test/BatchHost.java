package com.jzpz.test;

import com.jzpz.domain.HostConfig;
import com.jzpz.domain.Result;
import com.jzpz.util.JzpzUtil;
import org.junit.Test;

public class BatchHost {

    @Test
    public void batchHostTest(){
        Result result = JzpzUtil.bathHost(HostConfig.builder().ip("190.168").num1("88").num2("01-02").name1("ibd").name2("z").port("22").userName("root").passWord("rootdzwl").build());
        System.out.println(result);
    }
}
