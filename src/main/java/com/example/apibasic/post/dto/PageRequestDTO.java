package com.example.apibasic.post.dto;

import lombok.*;

// 클라이언트가 보낸 페이지 정보를 가진 객체
@Getter@ToString
@AllArgsConstructor
@Builder
public class PageRequestDTO {

    private int page; // 요청한 페이지 번호
    private int sizePerPage; // 한 페이지에 보여줄 데이터 수

    // 초기 요청시에 사용할 데이터
    public PageRequestDTO() {
        this.page = 1;
        this.sizePerPage = 10;
    }

    public void setPage(int page){
        if(page < 1 || page > Integer.MAX_VALUE){
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public void setSizePerPage(int sizePerPage) {
        if(sizePerPage < 10 || sizePerPage > 100){ //10 페이지 미만이거나 100 페이지 초과면 10페이지만 보기
            this.sizePerPage = 10;
            return;
        }
        this.sizePerPage = sizePerPage;
    }
}
