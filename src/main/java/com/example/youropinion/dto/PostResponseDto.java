package com.example.youropinion.dto;

import com.example.youropinion.entity.Comment;
import com.example.youropinion.entity.Post;
import com.example.youropinion.entity.SecondComments;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private String nickname;
    private String opinionA;
    private String opinionB;
    private Long opinionACnt;
    private Long opinionBCnt;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int commentCnt;
    private List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
    private List<SecondCommentResponseDto> SecondCommentResponseDtoList = new ArrayList<>();

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUser().getUsername();
        this.opinionA = post.getOpinionA();
        this.opinionB = post.getOpinionB();
        this.opinionACnt = post.getOpinionACnt();
        this.opinionBCnt = post.getOpinionBCnt();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.nickname= post.getUser().getNickname();
        this.commentCnt = post.getCommentList().size();
    }

    public void setCommentResponseDtoList(List<Comment> sortedCommentList) {
        for (Comment comment : sortedCommentList) {
            log.info(comment.getContent());
            this.commentResponseDtoList.add(new CommentResponseDto(comment));
        }
    }

    public void setSecondCommentResponseDtoList(List<SecondComments> sortedCommentList) {
        for (SecondComments comment : sortedCommentList) {
            log.info(comment.getContent());
            this.SecondCommentResponseDtoList.add(new SecondCommentResponseDto(comment));
        }
    }

}
