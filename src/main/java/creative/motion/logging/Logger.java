package creative.motion.logging;

import creative.motion.config.BaseConfig;
import creative.motion.config.TelegramSetting;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Logger {
    private final TelegramSetting setting;
    private final BaseConfig baseConfig;
    private final RestTemplate restTemplate;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Logger.class);

    public void info(List<LogMessage> message) {
        sendMessage(message);

    }

    private void sendMessage(List<LogMessage> messages) {
        StringBuilder outMessage = new StringBuilder();
        outMessage.append("{\"")
                .append(baseConfig.getMyName())
                .append("\":[")
                .append(messages.stream()
                        .map(m -> "\"" + m.getErrorCode() + "\":\"" + m.getMessage() + "\"")
                        .collect(Collectors.joining(",")))
                .append("]}");
        String url = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s", setting.getTelegramApiToken(), setting.getIdServer(), outMessage);
        restTemplate.getForEntity(url, String.class);
    }
}
