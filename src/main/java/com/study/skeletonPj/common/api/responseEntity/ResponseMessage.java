package com.study.skeletonPj.common.api.responseEntity;

public class ResponseMessage {
	public static final String LOGIN_SUCCESS = "로그인 성공";
	public static final String LOGIN_FAIL = "로그인 실패";
	public static final String READ_DATA = "단일 데이터 조회 성공";
	public static final String READ_DATAS = "다중 데이터 조회 성공";
	public static final String NOT_FOUND_DATA = "데이터를 찾을 수 없습니다.";
	public static final String CREATED_DATA = "데이터 추가 성공";
	public static final String PUT_DATA = "데이터 정보 수정(덮어쓰기) 성공";
	public static final String PATCH_DATA = "데이터 정보 수정 성공";
	public static final String DELETE_DATA = "데이터 삭제 성공";
	public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
	public static final String DB_ERROR = "데이터베이스 에러";
}
