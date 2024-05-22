package com.berkayg.postservice.controller;
import com.berkayg.postservice.dto.request.PostSaveRequestDto;
import com.berkayg.postservice.dto.request.PostUpdateRequestDto;
import com.berkayg.postservice.dto.response.PostFindAllResponseDto;
import com.berkayg.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

import static com.berkayg.postservice.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(POST)
public class PostController {
    private final PostService postService;

    @PostMapping(SAVE)
    public ResponseEntity<String> save(@RequestBody PostSaveRequestDto dto){
        return ResponseEntity.ok(postService.save(dto));
    }

    @GetMapping(FINDALLUSERPOSTBYTOKEN)
    public ResponseEntity<List<PostFindAllResponseDto>> findAllUserPostByToken(String token){
        List<PostFindAllResponseDto> allPostByToken = postService.findAllUserPostByToken(token);

        return ResponseEntity.ok(allPostByToken);
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<PostFindAllResponseDto>> findAllByToken(String token){
        List<PostFindAllResponseDto> allPostByToken = postService.findAllByToken(token);
        return ResponseEntity.ok(allPostByToken);
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<String> deletePost(@RequestParam String token,String postId){
        postService.deletePost(token,postId);
        return ResponseEntity.ok("Post deleted successfully");
    }
    @PutMapping(UPDATE)
    public ResponseEntity<String> updatePost(@RequestBody PostUpdateRequestDto dto){
        postService.updatePost(dto);
        return ResponseEntity.ok("Post updated successfully");
    }
}