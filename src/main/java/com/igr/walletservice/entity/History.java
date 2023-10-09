package com.igr.walletservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class History {
    LocalDateTime localDateTime;
    String login;
    double beforeBalance;
    double afterBalance;
}
