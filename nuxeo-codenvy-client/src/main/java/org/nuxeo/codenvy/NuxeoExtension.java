/*******************************************************************************
 * (C) Copyright 2014 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 *       All rights reserved. This program and the accompanying materials
 *       are made available under the terms of the GNU Lesser General Public
 *       License
 *       (LGPL) version 2.1 which accompanies this distribution,
 *       and is available at
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
package org.nuxeo.codenvy;

import com.codenvy.ide.api.action.ActionManager;
import com.codenvy.ide.api.action.DefaultActionGroup;
import com.codenvy.ide.api.action.IdeActions;
import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.util.loging.Log;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.nuxeo.codenvy.action.AutomationOperationAction;
import org.nuxeo.codenvy.action.BundleAction;

/**
 * Nuxeo Extension:
 * - Used to register actions (main Nuxeo menu with two actions).
 */
@Singleton
@Extension(title = "Nuxeo Plugin", version = "1.0.0")
public class NuxeoExtension {


    public static final String NUXEO = "Nuxeo";

    public static final String NUXEO_GROUP_ACTION = "nuxeoGroup";

    @Inject
    public NuxeoExtension(ActionManager actionManager,
            AutomationOperationAction automationOperationAction,
            BundleAction bundleAction) {
        Log.info(NuxeoExtension.class, "Registering Nuxeo Actions...");
        DefaultActionGroup nuxeoGroup = new DefaultActionGroup(NUXEO, true,
                actionManager);
        actionManager.registerAction(NUXEO_GROUP_ACTION, nuxeoGroup);
        DefaultActionGroup mainMenu = (DefaultActionGroup) actionManager
                .getAction(IdeActions.GROUP_MAIN_MENU);
        mainMenu.add(nuxeoGroup);
        actionManager.registerAction(automationOperationAction.toString(),
                automationOperationAction);
        actionManager.registerAction(bundleAction.toString(), bundleAction);
        nuxeoGroup.add(automationOperationAction);
        nuxeoGroup.add(bundleAction);
    }
}