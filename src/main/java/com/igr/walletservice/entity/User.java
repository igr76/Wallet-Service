package com.igr.walletservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
/** Сущность пользователей */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    /** id пользователя */
    long id;
    /** Логин пользователя */
    String login;
    /** Пароль пользователя */
    String password;
}
