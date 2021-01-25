package io.github.lukeeey.cloudforms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.lukeeey.cloudforms.elements.simple.FormButton;
import io.github.lukeeey.cloudforms.elements.simple.FormImageButton;
import io.github.lukeeey.cloudforms.utils.Callback;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a form that contains a list of buttons.
 *
 * @author lukeeey
 */
@Getter
public class SimpleForm extends Form {
    private final List<FormButton> buttons = new LinkedList<>();

    @Setter
    private String text;

    public SimpleForm(String title) {
        this(title, "");
    }

    public SimpleForm(String title, String text) {
        super(title, "form");
        this.text = text;
    }

    /**
     * Add a button to this form with a callback.
     * The callback is called when the form is submitted.
     *
     * @param button the button to add
     * @param callback the callback
     */
    public SimpleForm addButton(FormButton button, Callback callback) {
        button.setCallback(callback);
        return addButton(button);
    }

    /**
     * Add a button without a callback.
     *
     * @param button the button to add
     */
    public SimpleForm addButton(FormButton button) {
        if (button instanceof FormImageButton) {
            buttons.add((FormImageButton) button);
            return this;
        }
        buttons.add(button);
        return this;
    }

    @Override
    public JsonObject serialize() {
        JsonObject object = super.serialize();
        JsonArray content = new JsonArray();
        buttons.forEach(button -> content.add(button.serialize()));
        object.add("buttons", content);
        object.addProperty("content", text);
        return object;
    }

    @Override
    public void handleResponse(String data) {
        if (data.trim().equals("null") || Integer.parseInt(data.trim()) == -1) {
            if (onClose != null) {
                onClose.accept();
            }
        } else {
            try {
                FormButton button = buttons.get(Integer.parseInt(data.trim()));
                if (button.getCallback() != null) {
                    button.getCallback().accept();
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new ArrayIndexOutOfBoundsException("The client returned invalid form data (" + data + " but max is " + buttons.size() + ")");
            }
        }
    }
}
