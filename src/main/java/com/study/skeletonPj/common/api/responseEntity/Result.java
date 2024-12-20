package com.study.skeletonPj.common.api.responseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Result<T> {

	private int statusCode;
	private String responseMessage;
	private T data;

	public Result(final int statusCode, final String responseMessage) {
		this.statusCode = statusCode;
		this.responseMessage = responseMessage;
		this.data = null;
	}

	public static<T> Result<T> res(final int statusCode, final String responseMessage) {
		return res(statusCode, responseMessage, null);
	}

	public static<T> Result<T> res(final int statusCode, final String responseMessage, final T t) {
		return Result.<T>builder()
				.data(t)
				.statusCode(statusCode)
				.responseMessage(responseMessage)
				.build();
	}
}