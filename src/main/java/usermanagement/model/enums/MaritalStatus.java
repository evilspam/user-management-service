package usermanagement.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

public enum MaritalStatus {
    SINGLE,
    MARRIED;

    @JsonCreator
    public static MaritalStatus setValue(String key) {
        return Arrays.stream(MaritalStatus.values())
                .filter(item -> item.toString().equals(key.toUpperCase()))
                .findAny().orElse(null);
    }

    @JsonValue
    public String forJackson() {
        return name().toLowerCase();
    }
}
