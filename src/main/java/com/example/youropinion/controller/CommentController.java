package com.example.youropinion.controller;

import com.example.youropinion.dto.CommentRequestDto;
import com.example.youropinion.dto.RestApiResponseDto;
import com.example.youropinion.exception.TokenNotValidateException;
import com.example.youropinion.security.UserDetailsImpl;
import com.example.youropinion.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class
CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/posts/{id}/comment")
    public ResponseEntity<RestApiResponseDto> createComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.tokenValidate(userDetails);

        System.out.println("userDetails.getUser() = " + userDetails.getUser());
        return commentService.createComment(id,requestDto, userDetails.getUser());
    }


    // 댓글 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<RestApiResponseDto> updateComment(
            @PathVariable Long id,
            @Valid  @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.tokenValidate(userDetails);
        return commentService.updateComment(id,requestDto, userDetails.getUser());
    }


    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<RestApiResponseDto> deleteComment(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.tokenValidate(userDetails);
        return commentService.deleteComment(id,userDetails.getUser());
    }

    public void tokenValidate(UserDetailsImpl userDetails) {
        try{
            userDetails.getUser();
        }catch (Exception ex){
            throw new TokenNotValidateException("토큰이 유효하지 않습니다.");
        }
    }
}
