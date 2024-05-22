package com.berkayg.postservice.service;



import com.berkayg.postservice.dto.request.PostSaveRequestDto;
import com.berkayg.postservice.dto.request.PostUpdateRequestDto;
import com.berkayg.postservice.dto.response.PostFindAllResponseDto;
import com.berkayg.postservice.entity.Post;
import com.berkayg.postservice.entity.enums.EStatus;
import com.berkayg.postservice.exception.ErrorType;
import com.berkayg.postservice.exception.PostServiceException;
import com.berkayg.postservice.manager.UserManager;
import com.berkayg.postservice.mapper.PostMapper;
import com.berkayg.postservice.repository.PostRepository;
import com.berkayg.postservice.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final JwtTokenManager jwtTokenManager;
    private final UserManager userManager;

    public String save(PostSaveRequestDto dto) {
        Long authId = jwtTokenManager.getIdFromToken(dto.getToken())
                .orElseThrow(() -> new PostServiceException(ErrorType.INVALID_TOKEN));
        String userId = userManager.getUserIdFromAuthId(authId);

        postRepository.save(Post.builder()
                .userId(userId)
                .content(dto.getContent())
                .photo(dto.getPhoto())
                .title(dto.getTitle())
                .build());
        return "Post başarıyla gönderildi.";

    }

    public List<PostFindAllResponseDto> findAllUserPostByToken(String token) {
        Long authId = jwtTokenManager.getIdFromToken(token)
                .orElseThrow(() -> new PostServiceException(ErrorType.INVALID_TOKEN));
        String userId = userManager.getUserIdFromAuthId(authId);

        List<Post> postList = postRepository.findAllByStatusAndUserId(EStatus.ACTIVE,userId);
        if(postList.isEmpty()){
            throw new PostServiceException(ErrorType.POST_NOT_FOUND);
        }
        List<PostFindAllResponseDto> dtoList=new ArrayList<PostFindAllResponseDto>();

        for(Post post : postList){
            PostFindAllResponseDto postFindAllResponseDto = PostMapper.INSTANCE.postToPostFindAllResponseDto(post);
            dtoList.add(postFindAllResponseDto);
        }
        return dtoList;
    }

    public List<PostFindAllResponseDto> findAllByToken(String token) {
        Long authId = jwtTokenManager.getIdFromToken(token)
                .orElseThrow(() -> new PostServiceException(ErrorType.INVALID_TOKEN));
        String userId = userManager.getUserIdFromAuthId(authId);

        List<Post> postList = postRepository.findAllByStatus(EStatus.ACTIVE);
        if(postList.isEmpty()){
            throw new PostServiceException(ErrorType.POST_NOT_FOUND);
        }
        List<PostFindAllResponseDto> dtoList=new ArrayList<PostFindAllResponseDto>();

        for(Post post : postList){
            PostFindAllResponseDto postFindAllResponseDto = PostMapper.INSTANCE.postToPostFindAllResponseDto(post);
            dtoList.add(postFindAllResponseDto);
        }
        return dtoList;
    }

    public void deletePost(String token, String postId){
        Long authId = jwtTokenManager.getIdFromToken(token)
                .orElseThrow(() -> new PostServiceException(ErrorType.INVALID_TOKEN));
        String userId = userManager.getUserIdFromAuthId(authId);
        Post post =
                postRepository.findById(postId).orElseThrow(() -> new PostServiceException(ErrorType.POST_NOT_FOUND));
        if(!post.getUserId().equals(userId)){
            throw new PostServiceException(ErrorType.POST_NOT_FOUND);
        }
        if(post.getStatus().equals(EStatus.DELETED)){
            throw new PostServiceException(ErrorType.POST_ALREADY_DELETED);
        }
        post.setStatus(EStatus.DELETED);
        postRepository.save(post);
    }

    public void updatePost(PostUpdateRequestDto dto){
        Long authId = jwtTokenManager.getIdFromToken(dto.getToken())
                .orElseThrow(() -> new PostServiceException(ErrorType.INVALID_TOKEN));
        String userId = userManager.getUserIdFromAuthId(authId);
        Post post =
                postRepository.findById(dto.getPostId()).orElseThrow(() -> new PostServiceException(ErrorType.POST_NOT_FOUND));
        if(!post.getUserId().equals(userId)){
            throw new PostServiceException(ErrorType.POST_NOT_FOUND);
        }
        if(post.getStatus().equals(EStatus.DELETED)){
            throw new PostServiceException(ErrorType.POST_ALREADY_DELETED);
        }
        post.setContent(dto.getContent());
        post.setPhoto(dto.getPhoto());
        post.setTitle(dto.getTitle());
        postRepository.save(post);
    }



}