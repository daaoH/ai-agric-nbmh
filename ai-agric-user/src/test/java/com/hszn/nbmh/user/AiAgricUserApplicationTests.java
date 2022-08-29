package com.hszn.nbmh.user;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.StandardEnvironment;

@SpringBootTest
class AiAgricUserApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void testJasypt(){
        StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());

        //加密方法
        System.out.println(stringEncryptor.encrypt("123456"));
        //解密方法
        System.out.println(stringEncryptor.decrypt("i3cDFhs26sa2Ucrfz2hnQw=="));
    }

}
