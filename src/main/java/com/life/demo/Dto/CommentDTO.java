package com.life.demo.Dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long parent_id;
    private int type;
    private String content;
}
