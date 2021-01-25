package io.github.lukeeey.cloudforms.elements.simple;

import com.google.gson.JsonObject;
import io.github.lukeeey.cloudforms.utils.Callback;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class FormButton {
    private final String text;
    @Setter
    private Callback callback;

    public JsonObject serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("text", text);
        return object;
    }
}
