package com.example.youropinion.controller;

import com.example.youropinion.dto.PostRequestDto;
import com.example.youropinion.dto.RestApiResponseDto;
import com.example.youropinion.exception.TokenNotValidateException;
import com.example.youropinion.security.UserDetailsImpl;
import com.example.youropinion.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 전체 게시글 조회
    @GetMapping("/posts")
    public @ResponseBody ResponseEntity<RestApiResponseDto> getPostList() {
        return postService.getPostList();
    }

    // 게시글 작성
    @PostMapping("/post")
    public @ResponseBody ResponseEntity<RestApiResponseDto> createPost(
            @Valid @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.tokenValidate(userDetails);
        return postService.createPost(requestDto,userDetails.getUser());

    }

    // 게시글 수정
    @PutMapping("/posts/{id}")
    public @ResponseBody ResponseEntity<RestApiResponseDto> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.tokenValidate(userDetails);
        return postService.updatePost(id, requestDto, userDetails.getUser());
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public @ResponseBody ResponseEntity<RestApiResponseDto> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.tokenValidate(userDetails);
        return postService.deletePost(id, userDetails.getUser());
    }

    public void tokenValidate(UserDetailsImpl userDetails) {
        try{
            userDetails.getUser();
        }catch (Exception ex){
            throw new TokenNotValidateException("토큰이 유효하지 않습니다.");
        }
    }

}
