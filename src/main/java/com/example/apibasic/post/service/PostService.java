package com.example.apibasic.post.service;

import com.example.apibasic.post.dto.*;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Slf4j
// 스프링빈 등록
@Service
public class PostService {

    private final PostRepository postRepository;

    // 목록 조회 중간처리
    public PostListResponseDTO getList() {
        List<PostEntity> list = postRepository.findAll();

        if (list.isEmpty()) {
            throw new RuntimeException("조회 결과가 없어용~");
        }

        // 엔터티 리스트를 DTO리스트로 변환해서 클라이언트에 응답
        List<PostResponseDTO> responseDTOList = list.stream()
                .map(PostResponseDTO::new)
                .collect(toList());

        PostListResponseDTO listResponseDTO = PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .posts(responseDTOList)
                .build();

        return listResponseDTO;
    }

    // 개별 조회 중간처리
    public PostDetailResponseDTO getDetail(Long postNo) {
        PostEntity post = postRepository.findOne(postNo);

        if (post == null) throw new RuntimeException(postNo + "번 게시물이 존재하지 않음!!");

        // 엔터티를 DTO로 변환
        return new PostDetailResponseDTO(post);
    }

    // 등록 중간처리
    public boolean insert(final PostCreateDTO createDTO) {
        // dto를 entity변환 작업
        final PostEntity entity = createDTO.toEntity();

        return postRepository.save(entity);
    }

    // 수정 중간 처리
    public boolean update(final Long postNo, final PostModifyDTO modifyDTO) {
        // 수정 전 데이터 조회하기
        final PostEntity entity = postRepository.findOne(postNo);
        // 수정 진행
        String modTitle = modifyDTO.getTitle();
        String modContent = modifyDTO.getContent();

        if (modTitle != null) entity.setTitle(modTitle);
        if (modContent != null) entity.setContent(modContent);
        entity.setModifyDate(LocalDateTime.now());

        return postRepository.save(entity);
    }

    // 삭제 중간처리
    public boolean delete(final Long postNo) {
        return postRepository.delete(postNo);
    }
}









