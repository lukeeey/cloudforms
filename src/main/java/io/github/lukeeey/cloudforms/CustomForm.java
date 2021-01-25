package io.github.lukeeey.cloudforms;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.github.lukeeey.cloudforms.elements.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a form that allows for elements such as toggles,
 * dropdowns and input boxes.
 *
 * @author lukeeey
 */
public class CustomForm extends Form {
    private static final Gson GSON = new Gson();

    private final List<IFormElement> elements = new LinkedList<>();

    public CustomForm(String title) {
        super(title, "custom_form");
    }

    /**
     * Add an element to this form.
     *
     * @param element the element to add
     */
    public CustomForm addElement(IFormElement element) {
        elements.add(element);
        return this;
    }

    @Override
    public JsonObject serialize() {
        JsonObject object = super.serialize();
        JsonArray content = new JsonArray();
        elements.forEach(element -> content.add(element.serialize()));
        object.add("content", content);
        return object;
    }

    @Override
    public void handleResponse(String data) {
        if (data == null || data.trim().equals("null")) {
            if (onClose != null) {
                onClose.accept();
            }
        } else {
            List<String> responses = GSON.fromJson(data, new TypeToken<LinkedList<String>>(){}.getType());

            for (int i = 0; i < responses.size(); i++) {
                if (responses.get(i) == null) {
                    continue;
                }
                String responseData = responses.get(i).trim();
                IFormElement element = elements.get(i);

                // also works for step slider
                if (element instanceof DropdownElement) {
                    ((DropdownElement) element).getCallback(Integer.parseInt(responseData));
                }
                else if (element instanceof ToggleElement) {
                    if (((ToggleElement) element).getCallback() != null) {
                        ((ToggleElement) element).getCallback().accept(Boolean.parseBoolean(responseData));
                    }
                }
                else if (element instanceof InputElement) {
                    if (((InputElement) element).getCallback() != null) {
                        ((InputElement) element).getCallback().accept(responseData);
                    }
                }
                else if (element instanceof SliderElement) {
                    if (((SliderElement) element).getCallback() != null) {
                        ((SliderElement) element).getCallback().accept(Double.parseDouble(responseData));
                    }
                }
            }
        }
    }
}
