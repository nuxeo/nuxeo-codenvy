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
package org.nuxeo.codenvy;

import com.codenvy.ide.api.action.ActionManager;
import com.codenvy.ide.api.action.DefaultActionGroup;
import com.codenvy.ide.api.action.IdeActions;
import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.util.loging.Log;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.nuxeo.codenvy.action.AutomationOperationAction;

/**
 * Extension used to demonstrate how to use GIN.
 */
@Singleton
@Extension(title = "Nuxeo Plugin", version = "1.0.0")
public class NuxeoExtension {


    @Inject
    public NuxeoExtension(ActionManager actionManager, AutomationOperationAction automationOperationAction) {
        Log.info(NuxeoExtension.class,"blabla");
        //Create new popup group 'Demo' and add it to main menu
        DefaultActionGroup demoGroup = new DefaultActionGroup("Demo", true, actionManager);
        actionManager.registerAction("demoGroup", demoGroup);
        DefaultActionGroup mainMenu = (DefaultActionGroup)actionManager.getAction(IdeActions.GROUP_MAIN_MENU);
        mainMenu.add(demoGroup);

        //add demoAction to demoGroup
        actionManager.registerAction("demoAction", automationOperationAction);
        demoGroup.add(automationOperationAction);
    }
}