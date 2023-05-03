package danta.model.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 상태
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Status {
    /**
     * 활성화
     */
    TRUE("활성화"),
    /**
     * 비활성화
     */
    FALSE("비활성화");

    public static final String ENUM = "";
    public static final String DEFAULT = "'TRUE'";

    private final String value;
}
