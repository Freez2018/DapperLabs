package com.example.demo;

import com.example.demo.data.User;
import com.example.demo.data.UserResponse;
import com.example.demo.services.UserTransformer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserTransformerTest {
    private static final String FIRSTNAME_1 = "firstname1";
    private static final String LASTNAME_1 = "lastname1";
    private static final String EMAIL_1 = "email1";
    private static final String FIRSTNAME_2 = "firstname2";
    private static final String LASTNAME_2 = "lastname2";
    private static final String EMAIL_2 = "email2";
    private static final String FIRSTNAME_3 = "firstname3";
    private static final String LASTNAME_3 = "lastname3";
    private static final String EMAIL_3 = "email3";

    @Test
    public void transformUserToUserResponse_multipleUser_returnsCorrectValues() {
        ArrayList<User> users = new ArrayList<>();
        users.add(buildUser(1, FIRSTNAME_1,
                LASTNAME_1,
                EMAIL_1));
        users.add(buildUser(2, FIRSTNAME_2,
                LASTNAME_2,
                EMAIL_2));
        users.add(buildUser(3, FIRSTNAME_3,
                LASTNAME_3,
                EMAIL_3));
        ArrayList<UserResponse> expected = new ArrayList<>();
        expected.add(buildUserResponse(FIRSTNAME_1,
                LASTNAME_1,
                EMAIL_1));
        expected.add(buildUserResponse(FIRSTNAME_2,
                LASTNAME_2,
                EMAIL_2));
        expected.add(buildUserResponse(FIRSTNAME_3,
                LASTNAME_3,
                EMAIL_3));

        List<UserResponse> actual = UserTransformer.transformUserToUserResponse(users);

        assertThat(actual, is(expected));
    }

    @Test
    public void transformUserToUserResponse_NullUser_returnsCorrectValues() {
        ArrayList<User> users = new ArrayList<>();
        users.add(buildUser(1, FIRSTNAME_1,
                LASTNAME_1,
                EMAIL_1));
        users.add(null);
        users.add(buildUser(3, FIRSTNAME_3,
                LASTNAME_3,
                EMAIL_3));
        ArrayList<UserResponse> expected = new ArrayList<>();
        expected.add(buildUserResponse(FIRSTNAME_1,
                LASTNAME_1,
                EMAIL_1));
        expected.add(buildUserResponse(FIRSTNAME_3,
                LASTNAME_3,
                EMAIL_3));

        List<UserResponse> actual = UserTransformer.transformUserToUserResponse(users);

        assertThat(actual, is(expected));
    }

    @Test
    public void transformUserToUserResponse_UserListEmpty_returnsCorrectValues() {
        ArrayList<User> users = new ArrayList<>();
        List<UserResponse> actual = UserTransformer.transformUserToUserResponse(users);
        assertThat(actual.isEmpty(), is(true));
    }

    private User buildUser(int id,
                           String firstname,
                           String lastname,
                           String email) {
        User.Builder builder = new User.Builder();
        builder.withFirstName(firstname);
        builder.withLastName(lastname);
        builder.withId(id);
        builder.withEmail(email);
        return builder.build();
    }

    private UserResponse buildUserResponse(String firstname,
                                           String lastname,
                                           String email) {
        return new UserResponse(firstname, lastname, email);
    }
}
