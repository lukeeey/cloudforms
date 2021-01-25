package io.github.lukeeey.cloudforms.elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;
import io.github.lukeeey.cloudforms.utils.Callback;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@Getter
public class DropdownElement implements IFormElement {
    private List<String> options = new LinkedList<>();
    private String text;
    private int defaultOption;

    private Consumer<Integer> onSubmit;
    private List<Callback> callbacks = new LinkedList<>();

    public DropdownElement(String text) {
        this.text = text;
        this.defaultOption = 0;
    }

    public DropdownElement addOption(String text, boolean defaultOption, Callback callback) {
        callbacks.add(callback);
        return addOption(text, defaultOption);
    }

    public DropdownElement addOption(String text, boolean defaultOption) {
        if (defaultOption) {
            this.defaultOption = options.size();
        }
        options.add(text);
        return this;
    }

    public DropdownElement onSubmit(Consumer<Integer> consumer) {
        this.callbacks.clear();
        this.onSubmit = consumer;
        return this;
    }

    public void getCallback(int index) {
        if (onSubmit != null) {
            onSubmit.accept(index);
            return;
        }
        callbacks.get(index).accept();
    }

    @Override
    public JsonObject serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("type", "dropdown");
        object.addProperty("text", text);
        JsonArray array = new JsonArray();
        options.forEach(array::add);
        object.add("options", array);
        object.addProperty("default", defaultOption);
        return object;
    }
}
