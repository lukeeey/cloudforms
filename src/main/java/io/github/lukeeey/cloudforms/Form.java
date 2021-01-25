package io.github.lukeeey.cloudforms;

import com.google.gson.JsonObject;
import io.github.lukeeey.cloudforms.utils.Callback;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents a form.
 *
 * @author lukeeey
 */
@Getter
@RequiredArgsConstructor
public abstract class Form {
    private final String title;
    private final String type;

    protected Callback onClose;

    public Form onClose(Callback callback) {
        this.onClose = callback;
        return this;
    }

    public JsonObject serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("title", title);
        object.addProperty("type", type);
        return object;
    }

    public abstract void handleResponse(String data);
}
