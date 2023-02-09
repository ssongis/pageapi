package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

// 클라이언트에게 응답할 페이지 정보
/*
    pageInfo : {
        "startPage" : 41,
        "endPage" : 50,
        "currentPage" : 49,
        "prev" : true,
        "next" : false,
        "totalCount": 500
    }
 */
@Getter@Setter@ToString
public class PageResponseDTO<T> {

    private int startPage;
    private int endPage;
    private int currentPage;

    private boolean prev;
    private boolean next;

    private int totalCount;

    // 페이지 개수
    private static final int PAGE_COUNT = 10;

    public PageResponseDTO(Page<T> pageData) { // DB에서 오는 data
        this.totalCount = (int) pageData.getTotalElements();
        this.currentPage = pageData.getPageable().getPageNumber() + 1;
        this.endPage = (int) (Math.ceil((double) currentPage / PAGE_COUNT) * PAGE_COUNT); // 타입 주의, 맨마지막 구간에서 모순
        this.startPage = endPage - PAGE_COUNT + 1;

        // 페이지 마지막 구간에서 endPage값 보정
        // 실제 끝페이지 숫자를 구함
//        int realEnd = (int) Math.ceil((double) totalCount / pageData.getSize());
        int realEnd = pageData.getTotalPages();

        // 언제 보정해야돼? 마지막 구간에서만
        if(realEnd < endPage) this.endPage = realEnd;


        this.prev = startPage > 1; // prev(이전 페이지) => true 활성화
        this.next = endPage < realEnd; // next(다음 페이지) => true 활성화
    }

}






