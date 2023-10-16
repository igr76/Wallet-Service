package com.igr.walletservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
/** Сущность счёта */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    /** id владельца счёта */
    long id;
    /** Логин владельца счёта */
    String login;
    /** Состояние счёта */
    Double balance;
}
