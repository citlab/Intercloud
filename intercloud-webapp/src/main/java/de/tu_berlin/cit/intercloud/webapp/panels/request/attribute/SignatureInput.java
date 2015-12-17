package de.tu_berlin.cit.intercloud.webapp.panels.request.attribute;

import de.tu_berlin.cit.intercloud.client.model.occi.AttributeModel;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

class SignatureInput extends TextInput {
    public SignatureInput(String markupId, AttributeModel attribute) {
        super(markupId, attribute);
    }

    @Override
    public FormComponent getInputFormComponent() {
        return new TextField<String>("attributeValue", new PropertyModel<>(getAttribute(), "signature"));
    }
}
