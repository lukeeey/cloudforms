package io.github.lukeeey.cloudforms;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

/**
 * Represents a form with text and a yes and no button.
 *
 * This form uses the Noto Sans font (which is quite small)
 * and not the Mojangles font, so usage of this form is discouraged
 * if an {@link SimpleForm} can be used instead.
 *
 * @author lukeeey
 */
@Getter
@Setter
public class ModalForm extends Form {
    private String text;
    private String yesButton;
    private String noButton;

    private Consumer<Boolean> onSubmit;

    public ModalForm(String title) {
        this(title, "");
    }

    public ModalForm(String title, String text) {
        this(title, text, "Yes", "No");
    }

    public ModalForm(String title, String text, String yesButton, String noButton) {
        super(title, "modal");
        this.text = text;
        this.yesButton = yesButton;
        this.noButton = noButton;
    }

    public ModalForm onSubmit(Consumer<Boolean> consumer) {
        this.onSubmit = consumer;
        return this;
    }

    @Override
    public JsonObject serialize() {
        JsonObject object = super.serialize();
        object.addProperty("content", text);
        object.addProperty("button1", yesButton);
        object.addProperty("button2", noButton);
        return object;
    }

    @Override
    public void handleResponse(String data) {
        if (data == null || data.trim().equals("null")) {
            if (onClose != null) {
                onClose.accept();
            }
        } else {
            boolean value = Boolean.parseBoolean(data.trim());

            if (onSubmit != null) {
                onSubmit.accept(value);
            }
        }
    }
}
