package io.github.lukeeey.cloudforms.elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class StepSliderElement extends DropdownElement {

    public StepSliderElement(String text) {
        super(text);
    }

    @Override
    public JsonObject serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("type", "step_slider");
        object.addProperty("text", getText());
        JsonArray array = new JsonArray();
        getOptions().forEach(array::add);
        object.add("steps", array);
        object.addProperty("default", getDefaultOption());
        return object;
    }
}
