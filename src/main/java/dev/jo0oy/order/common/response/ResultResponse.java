package dev.jo0oy.order.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse<T> {

    private boolean success;
    private ZonedDateTime responseTime;
    private T data;

    public static <T> ResultResponse<T> res(final boolean success) {
        return res(success, null);
    }

    public static <T> ResultResponse<T> res(final boolean success, final T data) {
        return ResultResponse.<T>builder()
                .success(success)
                .responseTime(ZonedDateTime.now())
                .data(data)
                .build();
    }
}
