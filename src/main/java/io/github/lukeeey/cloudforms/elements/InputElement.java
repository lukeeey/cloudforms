package io.github.lukeeey.cloudforms.elements;

import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class InputElement implements IFormElement {
    private String text;
    private String defaultText;
    private String placeholder;

    private Consumer<String> callback;

    public InputElement(String text) {
        this(text, "", "");
    }

    public InputElement(String text, String placeholder) {
        this(text, placeholder, "");
    }

    public InputElement(String text, String placeholder, String defaultText) {
        this.text = text;
        this.placeholder = placeholder;
        this.defaultText = defaultText;
    }

    public InputElement onSubmit(Consumer<String> consumer) {
        this.callback = consumer;
        return this;
    }

    @Override
    public JsonObject serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("type", "input");
        object.addProperty("text", text);
        object.addProperty("default", defaultText);
        object.addProperty("placeholder", placeholder);
        return object;
    }
}
