package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.NamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.*;

/*
 * Created by artem on 02.04.17.
 */
public class UsersUtil {
    private static final Set<Role> ROLES = new HashSet<Role>(Arrays.asList(Role.values()));
    public static final List<User> USERS = Arrays.asList(
            new User(null, "name1", "email1", "password1", MealsUtil.DEFAULT_CALORIES_PER_DAY, true, ROLES),
            new User(null, "name2", "emai2", "passwor2", MealsUtil.DEFAULT_CALORIES_PER_DAY, true, ROLES),
            new User(null, "name3", "email3", "password3", MealsUtil.DEFAULT_CALORIES_PER_DAY, true, ROLES)
    );

    public static List<User> getFiltered(List<User> usersList) {
        usersList.sort(Comparator.comparing(NamedEntity::getName));

        return usersList;
    }
}
