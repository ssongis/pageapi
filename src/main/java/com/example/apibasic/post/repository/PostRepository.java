package com.example.apibasic.post.repository;

import com.example.apibasic.post.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

// 게시물 데이터를 CRUD(생성, 조회, 수정, 삭제)
//@Repository // 이 클래스로 만든 객체는 스프링이 관리좀해줘라
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    // 제목으로 검색 후 페이징처리
    Page<PostEntity> findByTitleContaining(String title, Pageable pageable);
}






