package com.igr.walletservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/** Сущность истории */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class History {
    /** id счёта */
    double id;
    /** Время операции со счётом */
    LocalDateTime localDateTime;
    /** логин автора операции */
    String login;
    /** Старое состояние счёта */
    double beforeBalance;
    /** Новое состояние сяёта */
    double afterBalance;
}
