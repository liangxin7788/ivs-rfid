package com.fun.business.sharon.biz.business.controller;

import com.fun.business.sharon.biz.business.bean.User;
import com.fun.business.sharon.biz.business.service.UserService;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName LoginController
 * @Description 登录相关接口
 * @Author liangixin
 * @Date 2019-12-28 15:32
 */
//@RequestMapping("/login")
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录接口")
    public GlobalResult<?> login(@RequestBody User user){
        if (StringUtil.isNotEmpty(user.getUserName()) && StringUtil.isNotEmpty(user.getPassword())) {
            try {
                user = userService.login(user);
            } catch (Exception e) {
                log.error("登录失败：" + e.getMessage(), e);
                return GlobalResult.newError("登录失败：" + e.getMessage());
            }
        }
        return GlobalResult.newSuccess(user);
    }


    public static void main(String[] args) {
//        List<String> collect = Stream.of("one", "two", "three", "four")
//                .filter(e -> e.length() > 3)
//                .peek(e -> System.out.println("Filtered value: " + e))
//                .map(String::toUpperCase)
//                .peek(e -> System.out.println("Mapped value: " + e))
//                .collect(Collectors.toList());
//        System.out.println(JSON.toJSONString(collect));


//        List<String> collect = Stream.of("one", "two", "three", "four")
//                .filter(e -> e.length() > 3)
//                .peek(e -> System.out.println("Filtered value: " + e))
//                .map(String::toUpperCase)
//                .peek(e -> System.out.println("Mapped value: " + e))
//                .collect(Collectors.toList());
//        System.out.println(JSON.toJSONString(collect));
//
//        List<Integer> collect1 = collect.stream().map(new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                int length = s.length();
//                return length;
//            }
//        }).collect(Collectors.toList());
//
//        System.out.println(JSON.toJSONString(collect1));
//
//        List<Integer> collect2 = collect.stream().map(item -> item.length()).collect(Collectors.toList());
//        System.out.println(JSON.toJSONString(collect2));


        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);

        // 求和方式1
//        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        System.out.println(sum2.get());
        // 求和方式3
        Integer sum3 = list.stream().reduce(5, Integer::sum);
        System.out.println(sum3);

        // 求乘积
//        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        // 求最大值方式1
//        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
//        Integer max2 = list.stream().reduce(1, Integer::max);

//        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3);
//        System.out.println("list求积：" + product.get());
//        System.out.println("list求和：" + max.get() + "," + max2);

    }

}
