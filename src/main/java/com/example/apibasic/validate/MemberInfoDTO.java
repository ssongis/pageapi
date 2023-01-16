package com.example.apibasic.validate;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class MemberInfoDTO {

    @NotBlank@Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank@Size(min = 2, max = 5)
    private String userName;
    @JsonFormat(pattern = "yyMMdd")
    @Past // 과거 날짜인지 검사
    private LocalDate birthOfDate;

    @Valid // 컴포지션 객체 입력값 검증, 어떤 버전부터는 생략 가능(예전 버전에서는 사용해야 함)
    private Address address; // 주소 정보 (도로명주소 + 우편번호)
    @Valid
    private Card card; // 결제 카드 정보 (카드번호 + 카드만료기간)
}
