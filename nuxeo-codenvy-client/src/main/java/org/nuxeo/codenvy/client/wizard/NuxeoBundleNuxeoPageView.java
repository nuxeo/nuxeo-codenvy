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
package org.nuxeo.codenvy.client.wizard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class NuxeoBundleNuxeoPageView extends Composite implements
        NuxeoPageView {
    private static NuxeoPageViewImplUiBinder ourUiBinder = GWT.create
            (NuxeoPageViewImplUiBinder.class);

    private final DockLayoutPanel rootElement;

    interface NuxeoPageViewImplUiBinder extends UiBinder<DockLayoutPanel,
            NuxeoBundleNuxeoPageView> {
    }

    public NuxeoBundleNuxeoPageView() {
        rootElement = ourUiBinder.createAndBindUi(this);
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        // nothing to do
    }

    @Override
    public Widget asWidget() {
        return rootElement;
    }

}