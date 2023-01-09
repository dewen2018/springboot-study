package com.dewen.graphql.user.resolver;

import com.dewen.commons.Result;
import com.dewen.graphql.user.pojo.UserInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserMutationResolver implements GraphQLMutationResolver {

    public Result addUser(UserInput input) {
        log.info(input.toString());
        return new Result(200, "success");
    }
}