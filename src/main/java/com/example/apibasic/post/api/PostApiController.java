package com.example.apibasic.post.api;

import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 리소스 : 게시물 (Post)
/*
     게시물 목록 조회:  /posts       - GET
     게시물 개별 조회:  /posts/{id}  - GET
     게시물 등록:      /posts       - POST
     게시물 수정:      /posts/{id}  - PATCH
     게시물 삭제:      /posts/{id}  - DELETE
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostApiController {

    // PostRepository에게 의존하는 관계
    private final PostRepository postRepository;

    //@Autowired // 스프링 컨테이너에게 의존객체를 자동주입해달라
//    public PostApiController(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }


    // 게시물 목록 조회
    @GetMapping
    public ResponseEntity<?> list() {
        log.info("/posts GET request");
        List<PostEntity> list = postRepository.findAll();
        return ResponseEntity
                .ok()
                .body(list)
                ;
    }

    // 게시물 개별 조회
    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable Long postNo) {
        log.info("/posts/{} GET request", postNo);

        PostEntity post = postRepository.findOne(postNo);
        return ResponseEntity
                .ok()
                .body(post)
                ;
    }

    // 게시물 등록
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostEntity entity) {
        log.info("/posts POST request");
        log.info("게시물 정보: {}", entity);

        boolean flag = postRepository.save(entity);
        return flag
                ? ResponseEntity.ok().body("INSERT-SUCCESS")
                : ResponseEntity.badRequest().body("INSERT-FAIL")
                ;
    }

    // 게시물 수정
    @PatchMapping("/{postNo}")
    public ResponseEntity<?> modify(@PathVariable Long postNo) {
        log.info("/posts/{} PATCH request", postNo);
        return null;
    }
    // 게시물 삭제
    @DeleteMapping("/{postNo}")
    public ResponseEntity<?> remove(@PathVariable Long postNo) {
        log.info("/posts/{} DELETE request", postNo);
        return null;
    }
}