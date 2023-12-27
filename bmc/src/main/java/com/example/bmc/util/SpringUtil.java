package com.example.bmc.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SpringUtil {
	public static final String SPRING_CONFIG_NAME = "spring.config.name";
	private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();
	private static Environment environment;

	public static Optional<Environment> getEnvironment() {
		if (environment == null) {
			return Optional.empty();
		}
		return Optional.of(environment);
	}
}
