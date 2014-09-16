/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.nuxeo.codenvy;

import com.codenvy.ide.api.action.ActionManager;
import com.codenvy.ide.api.action.DefaultActionGroup;
import com.codenvy.ide.api.action.IdeActions;
import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.util.loging.Log;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.nuxeo.codenvy.action.TextBoxes;

/**
 * Extension used to demonstrate how to use GIN.
 */
@Singleton
@Extension(title = "Nuxeo Plugin", version = "1.0.0")
public class NuxeoExtension {


    @Inject
    public NuxeoExtension(ActionManager actionManager, TextBoxes textBoxes) {
        Log.info(NuxeoExtension.class,"blabla");
        //Create new popup group 'Demo' and add it to main menu
        DefaultActionGroup demoGroup = new DefaultActionGroup("Demo", true, actionManager);
        actionManager.registerAction("demoGroup", demoGroup);
        DefaultActionGroup mainMenu = (DefaultActionGroup)actionManager.getAction(IdeActions.GROUP_MAIN_MENU);
        mainMenu.add(demoGroup);

        //add demoAction to demoGroup
        actionManager.registerAction("demoAction", textBoxes);
        demoGroup.add(textBoxes);
    }
}