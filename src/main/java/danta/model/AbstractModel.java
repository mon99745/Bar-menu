package danta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import danta.converter.DateTimeFormatConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public class AbstractModel {
    /**
     * 등록일시(형식: yyyy-MM-dd HH:mm:ss)
     */
    @JsonProperty(index = 910)
    @Schema(description = "등록일시(형식: yyyy-MM-dd HH:mm:ss)", hidden = true)
    @JsonSerialize(converter = DateTimeFormatConverter.DateToStringConverter.class)
    @JsonDeserialize(converter = DateTimeFormatConverter.StringToDateConverter.class)
    @Column(nullable = true, insertable=false, updatable=false)
    protected Date regDate;

    /**
     * 수정일시(형식: yyyy-MM-dd HH:mm:ss)
     */
    @JsonProperty(index = 920)
    @Schema(description = "수정일시(형식: yyyy-MM-dd HH:mm:ss)", hidden = true)
    @JsonSerialize(converter = DateTimeFormatConverter.DateToStringConverter.class)
    @JsonDeserialize(converter = DateTimeFormatConverter.StringToDateConverter.class)
    @Column(nullable = true, insertable=false, updatable=false)
    protected Date modDate;
}
