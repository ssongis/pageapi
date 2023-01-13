package com.example.apibasic.req;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@Slf4j
public class ApiController2 {

    //요청 파라미터 읽기
    @GetMapping("/param1")
    public String param1(
            String username //파라미터 키값과 변수명이 같으면 이노테이션 생략가능
            , @RequestParam("age") int userAge
    ){
        log.info("/param1?username={}&age={} GET!!",username,userAge);
        log.info("My name is {},age is {}.",username,userAge);
        return "";
    }

    @GetMapping("/param2")
    public String param2(OrderInfo orderInfo){
        log.info("/param2?orderNo={}&goodsName={}&goodAmount={}",orderInfo.getOrderNo(),orderInfo.getGoodsName(),orderInfo.getGoodsAmount());
        log.info("oderInfo:{}",orderInfo);
        return "";
    }

    // 요청 바디 읽기
    @PostMapping("/req-body")
    public String reqBody(@RequestBody OrderInfo orderInfo){
        log.info("=============orderInfo===============");
        log.info("orderNo : {}",orderInfo.getOrderNo());
        log.info("orderName : {}",orderInfo.getGoodsName());
        log.info("orderAmount : {}",orderInfo.getGoodsAmount());
        return "";
    }

    // 커맨드 객체 : 클라이언트가 보낸 파라미터 이름과 필드명이 정확히 일치해야 함
    @Getter@Setter@ToString
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class OrderInfo{
        private Long orderNo;
        private String goodsName;
        private int goodsAmount;
    }
}
