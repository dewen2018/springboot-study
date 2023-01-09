package com.dewen.graphql.user.resolver;

import com.dewen.graphql.user.pojo.City;
import com.dewen.graphql.user.pojo.User;
import com.dewen.graphql.user.pojo.UserInput;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class UserQueryResolver implements GraphQLQueryResolver {

    public Collection<User> userList() {
        User user1 = User.builder()
                .id(UUID.randomUUID())
                .username("coder小黑")
                .nickname("coder小黑没有昵称")
                .city(City.hangzhou)
                .build();

        User user2 = User.builder()
                .id(UUID.randomUUID())
                .username("今晚打老虎")
                .nickname("爱老虎油")
                .city(City.shanghai)
                .build();
        return Arrays.asList(user1, user2);
    }

    public User user(String username, String nickname) {
        return User.builder()
                .id(UUID.randomUUID())
                .username(username)
                .nickname(nickname)
                .build();
    }

    public List<User> users(UserInput input) {
        User user1 = User.builder()
                .id(UUID.randomUUID())
                .username(input.getUsername())
                .nickname(input.getNickname())
                .city(City.hangzhou)
                .build();

        User user2 = User.builder()
                .id(UUID.randomUUID())
                .username(input.getUsername() + "今晚打老虎")
                .nickname(input.getNickname() + "爱老虎油")
                .city(City.shanghai)
                .build();
        return Arrays.asList(user1, user2);
    }
}