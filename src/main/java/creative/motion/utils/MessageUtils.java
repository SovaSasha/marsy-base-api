package creative.motion.utils;

import com.ibm.icu.text.Transliterator;
import jakarta.annotation.Nullable;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.Objects;

@UtilityClass
public class MessageUtils {
    public static final String CYRILLIC_TO_LATIN = "Cyrillic-Latin";
    @Autowired
    private MessageSource messageSource;

    /**
     * Транслитерация текста
     *
     * @param text 'текст'
     * @return 'text'
     */
    public static String transliterat(String text) {
        Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        String result = toLatinTrans.transliterate(text);
        return result;
    }

    /**
     * Получить текст ошибки
     *
     * @param key ключь текста
     */
    public static String getErrorText(String key) {
        return getErrorText(key, null);
    }

    /**
     * Получить текст ошибки
     *
     * @param key        ключь текста
     * @param locateName язык текста
     */
    public static String getErrorText(String key, String locateName) {
        Locale locale;
        if (Objects.nonNull(locateName)) {
            locale = Locale.forLanguageTag(locateName);
        } else {
            locale = LocaleContextHolder.getLocale();
        }
        return messageSource.getMessage(key, null, key, locale);
    }
}
