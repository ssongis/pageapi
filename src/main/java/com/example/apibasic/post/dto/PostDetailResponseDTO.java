package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PostDetailResponseDTO extends PostResponseDTO {

    private LocalDateTime modDate;

    public PostDetailResponseDTO(PostEntity entity) {
        super(entity);
        this.modDate = entity.getModifyDate();
    }
}