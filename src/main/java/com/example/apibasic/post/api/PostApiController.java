package com.example.apibasic.post.api;

import com.example.apibasic.post.dto.*;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import com.example.apibasic.post.service.PostService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

    //PostService에게 의존하는 관계
    private final PostService postService;

    //@Autowired // 스프링 컨테이너에게 의존객체를 자동주입해달라
//    public PostApiController(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }


    // 게시물 목록 조회
    @GetMapping
    public ResponseEntity<?> list() {
        log.info("/posts GET request");

        try {
            PostListResponseDTO listResponseDTO = postService.getList();

            return ResponseEntity
                    .ok()
                    .body(listResponseDTO)
                    ;
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build()
                    ;
        }
    }

    // 게시물 개별 조회
    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable Long postNo) {
        log.info("/posts/{} GET request", postNo);

        try {
            PostDetailResponseDTO dto = postService.getDetail(postNo);

            return ResponseEntity
                    .ok()
                    .body(dto)
                    ;
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    // 게시물 등록
    @Parameters({
            @Parameter(name = "작성자", description = "게시물 작성자를 입력", example = "김철수")
            , @Parameter(name = "내용", description = "글 내용을 입력", example = "하하호호호~~~")
    })
    @PostMapping
    public ResponseEntity<?> create(
            @Validated @RequestBody PostCreateDTO createDTO
            , BindingResult result // 검증 에러 정보를 갖고 있는 객체
    ) {
        if (createDTO == null) { // 게시물 정보를 입력하지 않음, 클라이언트 에러
            return ResponseEntity
                    .badRequest()
                    .body("게시물 정보를 전달해주세요.");
        }

        if (result.hasErrors()) { // 검증에러가 발생할 시 true 리턴, 클라이언트 에러
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(err -> {
                log.warn("invalidated client data - {}", err.toString());
            });
            return ResponseEntity
                    .badRequest()
                    .body(fieldErrors);
        }
        log.info("/posts POST request");
        log.info("게시물 정보: {}", createDTO);
        try {
            PostDetailResponseDTO responseDTO = postService.insert(createDTO); // 서버 에러
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }

    }

    // 게시물 수정
    @PatchMapping("/{postNo}")
    public ResponseEntity<?> modify(
            @PathVariable Long postNo
            , @RequestBody PostModifyDTO modifyDTO
    ) {
        log.info("/posts/{} PATCH request", postNo);
        log.info("수정할 정보 : {}", modifyDTO);

        try {
            PostDetailResponseDTO responseDTO = postService.update(postNo, modifyDTO);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (RuntimeException e) {
            log.error("update fail!! : caused by - {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    // 게시물 삭제
    @DeleteMapping("/{postNo}")
    public ResponseEntity<?> remove(@PathVariable Long postNo) {
        log.info("/posts/{} DELETE request", postNo);

        try {
            postService.delete(postNo);
            return ResponseEntity.ok().body("Delete Sucess!");
        } catch (RuntimeException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }

    }
}