$(document).ready(function() {
			/**
			 *  전역 변수 선언
			 */
			
			/**
			 *  초기 선언
			 */
			(function () {
				init();
				EventListener();
			})();
		
			/**
			 *  페이지 초기 영역
			 */
			function init() {
				
			}
		
			/**
			 *  이벤트 리스너 영역
			 */
			function EventListener() {
				$("#regist").click( () => {
					const data = {
						userId: $("#userId").val(),
						userPw: $("#userPw").val(),
					}
					httpRequest("/api/user", data, "POST", "regist");
				});
			}
		
			/**
			 *  사용자 함수 영역
			 */
		
			
			/**
			 *  데이터 통신 영역
			 */
			// AJAX 성공시
			function successHttpRequest(res, flag) {
				if(flag === 'regist'){
					alert(res.responseMessage);
					window.location.href = '/login';
				}
			}
		
			// AJAX 실패시
			function errorHttpRequest(res, flag) {
				if(flag === 'regist'){
					alert(res.responseMessage);
				}
			}
			
			// AJAX 파일 요청 성공 시
			function successHttpFileRequest(res, flag) {
				
			}
		
			// AJAX 파일 요청 실패 시
			function errorHttpFileRequest(res, flag) {
				
			}
		
			// AJAX 요청
			function httpRequest(url, data, method, flag) {
				$.ajax({
					url: url,
					method: method,
					data: JSON.stringify(data),
					dataType : "json",
					contentType: "application/json;",
					success: function (res) {
						successHttpRequest(res, flag);
					},
					error: function (res) {
						errorHttpRequest(res, flag);
					},
				});
			}
			
			// AJAX 파일 요청
			function httpFileRequest(url, data, flag) {
				$.ajax({
					url: url,
					data: JSON.stringify(data),
					dataType: 'json',
					processData: false,
					contentType: false,
					type: 'POST',
					success: function (res) {
						successHttpFileRequest(res, flag);
					},
					error: function (res) {
						errorHttpFileRequest(res, flag);
					}
				});
			}
});