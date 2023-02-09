package creative.motion.logging;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.key.ZonedDateTimeKeySerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
@Builder
public class LogMessage {
    @JsonSerialize(
            using = ZonedDateTimeKeySerializer.class
    )
    private ZonedDateTime timestamp;

    private String errorCode;
    private String message;
    private String methodName;
}
