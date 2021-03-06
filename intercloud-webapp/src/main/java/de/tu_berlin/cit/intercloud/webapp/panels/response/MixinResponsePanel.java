package de.tu_berlin.cit.intercloud.webapp.panels.response;

import de.tu_berlin.cit.intercloud.client.model.occi.MixinModel;
import org.apache.wicket.model.IModel;

public class MixinResponsePanel extends CategoryResponsePanel {
    public MixinResponsePanel(String id, IModel<MixinModel> mixinModel) {
        super(id, mixinModel);
    }

    @Override
    protected String getType() {
        return "Mixin";
    }
}
