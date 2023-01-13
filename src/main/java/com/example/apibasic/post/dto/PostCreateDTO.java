package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostCreateDTO {

    private String writer;
    private String title;
    private String content;
    private List<String> hashTags;


    // PostEntity로 변환하는 유틸 메서드
    public PostEntity toEntity(){
        return  PostEntity.builder()
                .postNo(PostEntity.sequence++)
                .writer(this.writer)
                .content(this.content)
                .title(this.title)
                .hashTags(this.hashTags)
                .createDate(LocalDateTime.now())
                .build();
    }
}
