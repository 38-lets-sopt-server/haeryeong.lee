package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.domain.BoardType;
import org.sopt.domain.Post;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.ApiResponse;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostListResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.global.code.status.SuccessStatus;
import org.sopt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Tag(name = "Post", description =  = "게시글 관련 API")
@RestController
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  // POST /posts ✅ 같이 구현
  @PostMapping
  @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.")
  @ApiResponses({
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "게시글이 성공적으로 생성되었습니다."),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없습니다.")
  })
  public ResponseEntity<ApiResponse<CreatePostResponse>> createPost(
      @RequestBody CreatePostRequest request
  ) {
    CreatePostResponse response = postService.createPost(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of(SuccessStatus.POST_CREATED, response));
  }

  // GET /posts 📝 과제
  @GetMapping
  @Operation(summary = "게시글 목록 조회", description = "페이지, 사이즈, 게시판 타입으로 게시글 목록을 조회합니다.")
  @ApiResponses({
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "게시글 목록이 성공적으로 조회되었습니다."),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 페이지 요청입니다.")
  })
  public ResponseEntity<ApiResponse<PostListResponse>> getAllPosts(
      @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
      @RequestParam(defaultValue = "0") int page,

      @Parameter(description = "한 페이지에 가져올 게시글 수", example = "10")
      @RequestParam(defaultValue = "10") int size,

      @Parameter(description = "게시판 타입", example = "FREE")
      @RequestParam(required = false) BoardType boardType
  ) {
    PostListResponse response = postService.getAllPosts(page, size, boardType);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(SuccessStatus.POSTS_FOUND, response));
  }

  // GET /posts/{id} 📝 과제
  @GetMapping("/{id}")
  @Operation(summary = "게시글 단건 조회", description = "게시글 ID로 특정 게시글을 조회합니다.")
  @ApiResponses({
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "게시글이 성공적으로 조회되었습니다."),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 게시글 ID입니다."),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없습니다.")
  })
  public ResponseEntity<ApiResponse<PostResponse>> getPost(
      @Parameter(description = "게시글 ID", example = "1")
      @PathVariable Long id
  ) {
    PostResponse response = postService.getPost(id);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(SuccessStatus.POST_FOUND, response));
  }

  // PUT /posts/{id} 📝 과제
  @PutMapping("/{id}")
  @Operation(summary = "게시글 수정", description = "게시글 ID로 제목, 내용, 질문 여부, 익명 여부를 수정합니다.")
  @ApiResponses({
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "게시글이 성공적으로 수정되었습니다."),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "유효성 검증에 실패했습니다."),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없습니다.")
  })
  public ResponseEntity<ApiResponse<PostResponse>> updatePost(
      @Parameter(description = "수정할 게시글 ID", example = "1")
      @PathVariable Long id,
      @RequestBody UpdatePostRequest request
  ) {
    PostResponse response = postService.updatePost(id, request);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(SuccessStatus.POST_UPDATED, response));
  }

  // DELETE /posts/{id} 📝 과제
  @DeleteMapping("/{id}")
  @Operation(summary = "게시글 삭제", description = "게시글 ID로 게시글을 삭제합니다.")
  @ApiResponses({
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "게시글이 성공적으로 삭제되었습니다."),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 게시글 ID입니다."),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없습니다.")
  })
  public ResponseEntity<ApiResponse<PostResponse>> deletePost(
      @Parameter(description = "삭제할 게시글 ID", example = "1")
      @PathVariable Long id
  ) {
    PostResponse response = postService.deletePost(id);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(SuccessStatus.POST_DELETED, response));
  }
}
