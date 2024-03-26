package com.study.skeletonPj.api.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.skeletonPj.api.user.domain.User;
import com.study.skeletonPj.api.user.service.UserService;
import com.study.skeletonPj.common.api.responseEntity.ResponseMessage;
import com.study.skeletonPj.common.api.responseEntity.Result;
import com.study.skeletonPj.common.api.responseEntity.StatusCode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "사용자 API", description = "update 2024.03.17")
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Operation(summary = "사용자 조회 (리스트)", description = "리스트로 응답")
	@GetMapping
	public ResponseEntity<Result<?>> getItemList(@ModelAttribute User.Veo user) {
		List<User.Vo> item = userService.getItemList(user);
		
		return new ResponseEntity<>(Result.res(StatusCode.OK, ResponseMessage.READ_DATAS, item), HttpStatus.OK);
	}
	
	@Operation(summary = "사용자 조회", description = "객체로 응답")
	@GetMapping("/{userId}")
	public ResponseEntity<Result<?>> getItem(@PathVariable String userId) {
		User.Vo item = userService.getItem(userId);
		
		return new ResponseEntity<>(Result.res(StatusCode.OK, ResponseMessage.READ_DATA ,item) ,HttpStatus.OK);
	}
	
	@Operation(summary = "사용자 생성", description = "객체를 요청")
	@ResponseBody
	@PostMapping
	public ResponseEntity<Result<?>> createItem(@RequestBody User.Veo user) {
		int cnt = userService.createItem(user);
		
		return new ResponseEntity<>(Result.res(StatusCode.CREATED, ResponseMessage.CREATED_DATA, cnt), HttpStatus.OK);
	}
	
	//@PreAuthorize("hasAuthority('ADMIN')")
	@Operation(summary = "사용자 삭제", description = "문자열을 요청")
	@DeleteMapping("/{userId}")
	public ResponseEntity<Result<?>> deleteItem(@PathVariable String userId) {
		int cnt = userService.deleteItem(userId);
		
		return new ResponseEntity<>(Result.res(StatusCode.OK, ResponseMessage.DELETE_DATA, cnt), HttpStatus.OK);
	}
	
	@Operation(summary = "사용자 수정", description = "객체를 요청")
	@PutMapping
	public ResponseEntity<Result<?>> putItem(@RequestBody User.Veo user) {
		int cnt = userService.putItem(user);
		
		return new ResponseEntity<>(Result.res(StatusCode.OK, ResponseMessage.PUT_DATA, cnt), HttpStatus.OK);
	}
	
	@Operation(summary = "사용자 수정", description = "객체를 요청")
	@PatchMapping
	public ResponseEntity<Result<?>> patchItem(@RequestBody User.Veo user) {
		int cnt = userService.patchItem(user);
		
		return new ResponseEntity<>(Result.res(StatusCode.OK, ResponseMessage.PATCH_DATA, cnt), HttpStatus.OK);
	}

}