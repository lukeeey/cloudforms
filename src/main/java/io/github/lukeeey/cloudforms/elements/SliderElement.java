package io.github.lukeeey.cloudforms.elements;

import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.function.DoubleConsumer;

@Getter
public class SliderElement implements IFormElement {
    private String text;
    private double minValue;
    private double maxValue;
    private double stepValue;
    private double defaultValue;

    private DoubleConsumer callback;

    public SliderElement(String text, double minValue, double maxValue) {
        this(text, minValue, maxValue, 1.0, minValue);
    }

    public SliderElement(String text, double minValue, double maxValue, double stepValue, double defaultValue) {
        this.text = text;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.stepValue = stepValue;
        this.defaultValue = defaultValue;
    }

    public SliderElement onSubmit(DoubleConsumer callback) {
        this.callback = callback;
        return this;
    }

    @Override
    public JsonObject serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("type", "slider");
        object.addProperty("text", text);
        object.addProperty("min", minValue);
        object.addProperty("max", maxValue);
        object.addProperty("step", stepValue);
        object.addProperty("default", defaultValue);
        return object;
    }
}
