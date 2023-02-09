package creative.motion.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TelegramSetting {
    private String telegramApiToken;
    private long idServer;
}
