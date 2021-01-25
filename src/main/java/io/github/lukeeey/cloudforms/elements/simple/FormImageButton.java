package io.github.lukeeey.cloudforms.elements.simple;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class FormImageButton extends FormButton {
    private final ImageType imageType;
    private final String location;

    public FormImageButton(String text, String location) {
        this(text, location, ImageType.PATH);
    }

    public FormImageButton(String text, String location, ImageType imageType) {
        super(text);
        this.imageType = imageType;
        this.location = location;
    }

    @Override
    public JsonObject serialize() {
        JsonObject object = super.serialize();
        object.addProperty("type", imageType.name().toUpperCase());
        object.addProperty("data", location);
        return object;
    }

    public enum ImageType {
        URL,
        PATH
    }
}
