package com.mbs.api.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author 'Bilol Tuxtamurodov' on 15.03.2026
 * @project Lesson_email_sending
 * @contact @BilolTuxtamurodov
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SmsApproveDTO {
    String email;
    Integer code;
}
