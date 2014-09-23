package org.nuxeo.codenvy.wizard;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 *
 */
public class BundlePageView extends Composite implements PageView {
    interface Page1ViewImplUiBinder extends UiBinder<Widget, BundlePageView> {
    }

    private ActionDelegate delegate;

    @Inject
    public BundlePageView(Page1ViewImplUiBinder ourUiBinder) {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidated() {
        return false;
    }

}