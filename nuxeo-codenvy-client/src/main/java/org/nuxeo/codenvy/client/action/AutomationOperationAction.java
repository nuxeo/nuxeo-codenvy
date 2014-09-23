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
package org.nuxeo.codenvy.client.action;

import com.codenvy.ide.api.action.Action;
import com.codenvy.ide.api.action.ActionEvent;
import com.google.gwt.user.client.Window;
import org.nuxeo.codenvy.client.NuxeoResource;

/**
 * Automation operation action to generate a new operation contribution.
 */
public class AutomationOperationAction extends Action {

    public static final String TITLE = "Nuxeo Automation Operation";

    public static final String DESCRIPTION = "Create a new Nuxeo Automation " +
            "operation.";

    public AutomationOperationAction() {
        super(TITLE, DESCRIPTION, NuxeoResource.INSTANCE.operation());
    }

    public void actionPerformed(ActionEvent event) {
        Window.prompt("Create a new operation with name:", "");
    }
}