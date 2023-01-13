package com.example.apibasic.post.repository;

import com.example.apibasic.post.entity.PostEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

// 게시물 데이터를 CRUD(생성, 조회, 수정, 삭제)
//@Component // 이 클래스로 만든 객체는 스프링이 관리 좀 해줘라
@Repository  //Component 포함하고 있음
public class PostRepository {

    // 메모리 저장소
    private static final Map<Long, PostEntity> posts = new HashMap<>();

    // 게시물 목록 조회
    public List<PostEntity> findAll() {
        List<PostEntity> postEntityList = new ArrayList<>();

        Set<Long> keySet = posts.keySet();
        for (Long postNo : keySet) {
            PostEntity postEntity = posts.get(postNo);
            postEntityList.add(postEntity);
        }
        return postEntityList;
    }

    // 게시물 개별 조회
    public PostEntity findOne(Long postNo) {
        return posts.get(postNo);
    }

    // 게시물 등록, 수정
    public boolean save(PostEntity postEntity) {
        PostEntity post = posts.put(postEntity.getPostNo(), postEntity);
        return true;
    }

    // 게시물 삭제
    public boolean delete(Long postNo) {
        PostEntity remove = posts.remove(postNo);
        return remove != null;
    }

}






