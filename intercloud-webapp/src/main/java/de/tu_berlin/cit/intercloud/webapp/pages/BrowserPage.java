package de.tu_berlin.cit.intercloud.webapp.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.CodeBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Alert;
import de.tu_berlin.cit.intercloud.client.model.LoggingModel;
import de.tu_berlin.cit.intercloud.client.model.rest.method.IRepresentationModel;
import de.tu_berlin.cit.intercloud.client.model.rest.method.MethodModel;
import de.tu_berlin.cit.intercloud.client.service.IIntercloudClient;
import de.tu_berlin.cit.intercloud.webapp.IntercloudWebSession;
import de.tu_berlin.cit.intercloud.webapp.components.ComponentUtils;
import de.tu_berlin.cit.intercloud.webapp.panels.BreadcrumbPanel;
import de.tu_berlin.cit.intercloud.webapp.panels.plugin.IRepresentationPanelPlugin;
import de.tu_berlin.cit.intercloud.webapp.panels.plugin.RepresentationPanelRegistry;
import de.tu_berlin.cit.intercloud.webapp.template.UserTemplate;
import de.tu_berlin.cit.intercloud.xmpp.rest.XmppURI;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BrowserPage extends UserTemplate {
    private static final Logger logger = LoggerFactory.getLogger(BrowserPage.class);

    private MethodTable methodTable;
    private RequestForm requestForm;
    private ResponseContainer responseContainer;
    private Alert alert;
    private Code xwadlCode;
    private Code restResponseCode;
    private Code restRequestCode;

    private final IModel<String> entity;
    private IModel<String> resourcePath;
    private ListModel<MethodModel> methodModelList = new ListModel<>();
    private IModel<LoggingModel> loggingModel = Model.of();

    public BrowserPage(IModel<XmppURI> uri) {
        super();
        this.entity = new Model<>(uri.getObject().getJID());
        this.resourcePath = new Model<>(uri.getObject().getPath());

        this.add(new BreadcrumbPanel("breadcrumb", uri, Model.of((XmppURI redirectUri) -> new BrowserPage(Model.of(redirectUri)))));

        // alert
        this.alert = newAlert("alert");
        this.add(this.alert);
        this.add(new XwadlForm("xwadlForm"));

        // Code section, kind of debugging
        this.xwadlCode = newCodePanel("xwadlCode", new LoadableDetachableModel<String>() {
            @Override
            protected String load() {
                return null != loggingModel.getObject() ? loggingModel.getObject().getXwadl() : null;
            }
        });
        this.add(this.xwadlCode);
        this.restRequestCode = newCodePanel("restRequestCode", new LoadableDetachableModel<String>() {
            @Override
            protected String load() {
                return null != loggingModel.getObject() ? loggingModel.getObject().getRestRequest() : null;
            }
        });
        this.add(this.restRequestCode);
        this.restResponseCode = newCodePanel("restResponseCode", new LoadableDetachableModel<String>() {
            @Override
            protected String load() {
                return null != loggingModel.getObject() ? loggingModel.getObject().getRestResponse() : null;
            }
        });
        this.add(this.restResponseCode);

        // method table
        this.methodTable = new MethodTable("methodTable", methodModelList);
        this.add(methodTable);

        // request / response
        this.requestForm = new RequestForm("requestForm");
        this.add(this.requestForm);
        this.responseContainer = new ResponseContainer("responseContainer");
        this.add(this.responseContainer);

        // request xwadl
        if (!resourcePath.getObject().trim().isEmpty()) {
            requestXwadl(entity.getObject(), resourcePath.getObject());
        }
    }

    private Alert newAlert(String markupId) {
        Alert alert = new Alert(markupId, Model.of(), Model.of());
        alert.type(Alert.Type.Danger);
        return ComponentUtils.displayNone(alert);
    }

    private Alert logError(Throwable t) {
        alert.type(Alert.Type.Danger);
        alert.withHeader(Model.of(ExceptionUtils.getMessage(t)));
        alert.withMessage(Model.of(ExceptionUtils.getStackTrace(t)));
        return ComponentUtils.displayBlock(alert);
    }

    private Code newCodePanel(String markupId, IModel<String> model) {
        Code code = new Code(markupId, model);
        code.setLanguage(CodeBehavior.Language.XML);
        return code;
    }

    private void requestXwadl(String entity, String path) {
        try {
            XmppURI uri = new XmppURI(entity, path);
            IIntercloudClient intercloudClient = IntercloudWebSession.get().getIntercloudService().newIntercloudClient(uri);
            this.loggingModel.setObject(intercloudClient.getLoggingModel());
            this.methodModelList.setObject(intercloudClient.getMethods());
            this.requestForm.setModel(null, null);
            this.responseContainer.setModel(null);
            ComponentUtils.displayNone(this.alert);
        } catch (Exception e) {
            logger.error("Failed to request xwadl from entity: {}, path: {}", entity, path, e);
            this.methodModelList.setObject(null);
            logError(e);
        }
    }

    private void executeMethod(IRepresentationModel representationModel, MethodModel methodModel) {
        try {
            IIntercloudClient intercloudClient = IntercloudWebSession.get().getIntercloudService()
                    .getIntercloudClient(methodModel.getUri());
            this.loggingModel.setObject(intercloudClient.getLoggingModel());
            IRepresentationModel representation = intercloudClient.executeMethod(methodModel, representationModel);
            // display response
            this.responseContainer.setModel(representation);
            // hide request
            this.requestForm.setModel(null, null);
            ComponentUtils.displayNone(this.alert);
        } catch (Exception e) {
            logError(e);
            logger.error("Failed to execute request. method: {}, representation: {}", methodModel, representationModel, e);
        }
    }

    private class XwadlForm extends Form {

        public XwadlForm(String markupId) {
            super(markupId);

            this.add(new Label("domain", BrowserPage.this.entity));
            this.add(new TextField<>("resourcePath", resourcePath).setRequired(true));
            AjaxButton button = new AjaxButton("getXwadlBtn") {
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    BrowserPage.this.requestXwadl(entity.getObject(), resourcePath.getObject());
                    setResponsePage(BrowserPage.this);
                }
            };
            this.add(button);
            this.setDefaultButton(button);
        }
    }

    private class MethodTable extends WebMarkupContainer {

        public MethodTable(String id, IModel<List<MethodModel>> methodList) {
            super(id);

            this.add(new ListView<MethodModel>("methodList", methodList) {
                @Override
                protected void populateItem(ListItem<MethodModel> listItem) {
                    MethodModel methodModel = listItem.getModelObject();
                    listItem.add(newLink("methodLink", methodModel));
                    listItem.add(newLabel("documentation", methodModel.getDocumentation()));
                    listItem.add(newLabel("request", methodModel.getRequestMediaType()));
                    listItem.add(newLabel("response", methodModel.getResponseMediaType()));
                }
            });
        }

        private Label newLabel(String markupId, String s) {
            Label label = new Label(markupId);
            if (null != s) {
                label.setDefaultModel(Model.of(s));
            }
            return label;
        }

        private AbstractLink newLink(String markupId, MethodModel methodModel) {
            AbstractLink link = new Link(markupId) {
                @Override
                public void onClick() {
                    if (null == methodModel.getRequestMediaType()) {
                        // execute method directly if no request media type is given
                        BrowserPage.this.executeMethod(null, methodModel);
                    } else {
                        // get request representation model
                        try {
                            IRepresentationModel representation = IntercloudWebSession.get().getIntercloudService()
                                    .getIntercloudClient(methodModel.getUri())
                                    .getRepresentationModel(methodModel);
                            // display request
                            BrowserPage.this.requestForm.setModel(representation, methodModel);
                            // hide response
                            BrowserPage.this.responseContainer.setModel(null);
                            ComponentUtils.displayNone(alert);
                        } catch (Exception e) {
                            logError(e);
                            logger.error("Could not create request model.", e);
                        }
                    }
                }
            }.setBody(Model.of(methodModel.getType()));

            if (null == methodModel.getRequestMediaType()) {
                link.add(new AttributeAppender("class", " btn-success"));
            } else if ("xml/occi".equals(methodModel.getRequestMediaType())) {
                link.add(new AttributeAppender("class", " btn-info"));
            } else {
                link.setEnabled(false);
                link.add(new AttributeAppender("class", " disables"));
            }
            return link;
        }
    }

    private class RequestForm extends Form {
        private Model<MethodModel> methodModel = Model.of();
        private Model<IRepresentationModel> representationModel = Model.of();
        private Panel requestPanel = null;

        public RequestForm(String markupId) {
            super(markupId);

            this.add(new AjaxButton("requestSubmit") {
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    BrowserPage.this.executeMethod(representationModel.getObject(), methodModel.getObject());
                    setResponsePage(BrowserPage.this);
                }
            });

            this.add(new EmptyPanel("requestPanel"));
        }

        @Override
        protected void onConfigure() {
            IRepresentationModel representation = representationModel.getObject();
            IRepresentationPanelPlugin panelPlugin = RepresentationPanelRegistry.getInstance().getPlugin(representation);
            if (null != panelPlugin) {
                this.requestPanel = panelPlugin.getRequestPanel("requestPanel", representation);
            } else {
                this.requestPanel = null;
            }

            super.onConfigure();
        }

        @Override
        protected void onBeforeRender() {
            if (null != this.requestPanel) {
                this.replace(this.requestPanel);
            }

            super.onBeforeRender();
        }

        @Override
        public boolean isVisible() {
            return null != this.requestPanel;
        }

        public void setModel(IRepresentationModel representation, MethodModel method) {
            this.representationModel.setObject(representation);
            this.methodModel.setObject(method);
        }
    }

    private class ResponseContainer extends WebMarkupContainer {
        private Model<IRepresentationModel> representationModel = Model.of();
        private Panel responsePanel = null;

        public ResponseContainer(String markupId) {
            super(markupId);

            this.add(new EmptyPanel("responsePanel"));
        }

        @Override
        protected void onConfigure() {
            IRepresentationModel representation = representationModel.getObject();
            IRepresentationPanelPlugin panelPlugin = RepresentationPanelRegistry.getInstance().getPlugin(representation);
            if (null != panelPlugin) {
                this.responsePanel = panelPlugin.getResponsePanel("responsePanel", representation);
            } else {
                this.responsePanel = null;
            }

            super.onConfigure();
        }

        @Override
        protected void onBeforeRender() {
            if (null != this.responsePanel) {
                this.replace(this.responsePanel);
            }

            super.onBeforeRender();
        }

        @Override
        public boolean isVisible() {
            return null != this.responsePanel;
        }

        public void setModel(IRepresentationModel representation) {
            this.representationModel.setObject(representation);
        }
    }
}
