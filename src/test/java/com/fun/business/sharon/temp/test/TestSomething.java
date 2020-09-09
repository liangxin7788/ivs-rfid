package com.fun.business.sharon.temp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liangxin
 * @Description: 测试集中营
 * @date 2020/5/30 0030 15:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSomething {

    /**
     * 统计每个字符出现的次数
     */
    @Test
    public void test1(){
        String str = "aannafaeaxgfgaknvnlkzodon";
        char[] chars = str.toCharArray();
        Map<Character, Integer> chMap = new HashMap<>();
        for (char ch : chars) {
            if(!chMap.containsKey(ch)){
                chMap.put(ch, 1);
            }else {
                chMap.put(ch, chMap.get(ch) + 1);
            }
        }
        System.out.println(chMap);
    }

    /**
     * base64编码、解码
     */
    @Test
    public void test2() throws IOException {
        String str = "sky";
        // 编码
        BASE64Encoder encoder = new BASE64Encoder();
        String encodeResult = encoder.encode(str.getBytes());

        System.out.println("编码结果为：" + encodeResult);
        // 解码
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] bytes = base64Decoder.decodeBuffer(encodeResult);

        System.out.println("解码结果为：" + new String(bytes));
    }

}
