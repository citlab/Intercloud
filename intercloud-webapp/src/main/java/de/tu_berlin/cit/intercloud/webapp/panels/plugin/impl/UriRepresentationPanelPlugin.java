package de.tu_berlin.cit.intercloud.webapp.panels.plugin.impl;

import de.tu_berlin.cit.intercloud.client.model.representation.impl.UriListRepresentationModel;
import de.tu_berlin.cit.intercloud.client.model.representation.impl.UriRepresentationModel;
import de.tu_berlin.cit.intercloud.webapp.panels.method.response.UriResponsePanel;
import de.tu_berlin.cit.intercloud.webapp.panels.plugin.api.IRepresentationPanelPlugin;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class UriRepresentationPanelPlugin implements IRepresentationPanelPlugin<UriRepresentationModel> {
    @Override
    public Class<UriRepresentationModel> getRepresentationModelClass() {
        return UriRepresentationModel.class;
    }

    @Override
    public Panel getRequestPanel(String markupId, UriRepresentationModel representationModel) {
        return null;
    }

    @Override
    public UriResponsePanel getResponsePanel(String markupId, UriRepresentationModel representationModel) {
        return new UriResponsePanel(markupId,
                Model.of(new UriListRepresentationModel(representationModel.getUri())));
    }
}