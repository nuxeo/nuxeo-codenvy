package org.nuxeo.codenvy.wizard;

import com.codenvy.ide.api.wizard.AbstractWizardPage;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 *
 */
public class BundlePresenter extends AbstractWizardPage implements PageView
        .ActionDelegate {
    private PageView view;

    @Inject
    public BundlePresenter(PageView view) {
        super("Page 1", null);
        this.view = view;
        this.view.setDelegate(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNotice() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCompleted() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focusComponent() {
//        view.setPage2Next(true);
//        view.setPage4Show(false);
//
//        wizardContext.putData(PAGE2_NEXT, true);
//        wizardContext.putData(PAGE4_SKIP, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeOptions() {
//        wizardContext.removeData(PAGE2_NEXT);
//        wizardContext.removeData(PAGE4_SKIP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(view);
    }

    @Override
    public void isValidated() {

    }
}