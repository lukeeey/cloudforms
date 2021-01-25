package io.github.lukeeey.cloudforms.elements;

import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class ToggleElement implements IFormElement {
    private String text;
    private boolean defaultValue;

    private Consumer<Boolean> callback;

    public ToggleElement(String text, boolean defaultValue) {
        this.text = text;
        this.defaultValue = defaultValue;
    }

    public ToggleElement onToggle(Consumer<Boolean> consumer) {
        this.callback = consumer;
        return this;
    }

    @Override
    public JsonObject serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("type", "toggle");
        object.addProperty("text", text);
        object.addProperty("default", defaultValue);
        return object;
    }
}
