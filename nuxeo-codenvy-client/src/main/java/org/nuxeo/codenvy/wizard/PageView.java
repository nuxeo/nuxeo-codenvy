package org.nuxeo.codenvy.wizard;

import com.codenvy.ide.api.mvp.View;
import com.google.inject.ImplementedBy;

/**
 *
 */
@ImplementedBy(BundlePageView.class)
public interface PageView extends View<PageView.ActionDelegate> {
    /**
     * Required for delegating functions in view.
     */
    public interface ActionDelegate {
        void isValidated();
    }

    boolean isValidated();

}