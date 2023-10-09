package com.igr.walletservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    String login;
    Double balance;
}
