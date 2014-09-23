/*******************************************************************************
 * (C) Copyright 2014 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 *       All rights reserved. This program and the accompanying materials
 *       are made available under the terms of the GNU Lesser General Public License
 *       (LGPL) version 2.1 which accompanies this distribution, and is available at
 *       http://www.gnu.org/licenses/lgpl-2.1.html
 *
 *       This library is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *       Lesser General Public License for more details.
 *
 *       Contributors:
 *       vpasquier <vpasquier@nuxeo.com>
 *******************************************************************************/
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
        super("Server Bundle", null);
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