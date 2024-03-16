package com.study.skeletonPj.common.util;

import java.io.IOException;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ModelMapperUtils {

	private static ModelMapper modelMapper = new ModelMapper();

	public static ModelMapper getModelMapper() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	public static <T> T mapToModel(@SuppressWarnings("rawtypes") Map map, Class<T> classType) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper.readValue(objectMapper.writeValueAsString(map), classType);
	}
}
