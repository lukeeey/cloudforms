package io.github.lukeeey.cloudforms.elements;

import com.google.gson.JsonObject;
import io.github.lukeeey.cloudforms.CustomForm;

/**
 * Represents an element in a {@link CustomForm}.
 *
 * @author lukeeey
 */
public interface IFormElement {
    JsonObject serialize();
}
