package com.dewen.handler;

import graphql.GraphQLError;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 一旦系统中自定义了 GraphQLErrorHandler 组件，那么 @ExceptionHandler 的处理方式就会失效。
 */
@Slf4j
@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        log.info("Handle errors: {}", errors);
        return Collections.singletonList(new GenericGraphQLError("出异常了..."));
    }
}