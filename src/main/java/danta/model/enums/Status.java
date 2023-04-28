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
    // TODO : Status Enum 적용
    /**
     * 활성화
     */
    VALID("활성화"),
    /**
     * 비활성화
     */
    INVALID("비활성화"),
    /**
     * 폐기
     */
    DELETE("폐기");

    public static final String ENUM = "";
    public static final String DESC = "상태 (VALID: 활성화, INVALID: 비활성화, DELETE: 폐기)";
    public static final String DEFAULT = "'VALID'";

    private final String value;
}
