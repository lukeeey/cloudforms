package io.github.lukeeey.cloudforms.elements;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LabelElement implements IFormElement {
    private final String text;

    @Override
    public JsonObject serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("type", "label");
        object.addProperty("text", text);
        return object;
    }
}
