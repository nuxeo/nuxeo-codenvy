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
package org.nuxeo.codenvy.client;

import com.codenvy.ide.api.action.ActionManager;
import com.codenvy.ide.api.action.DefaultActionGroup;
import com.codenvy.ide.api.action.IdeActions;
import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.api.icon.Icon;
import com.codenvy.ide.api.icon.IconRegistry;
import com.codenvy.ide.api.projecttree.TreeStructureProviderRegistry;
import com.codenvy.ide.util.loging.Log;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.nuxeo.codenvy.client.action.AutomationOperationAction;
import org.nuxeo.codenvy.client.action.BundleAction;
import org.nuxeo.codenvy.client.projecttree.MavenProjectTreeStructureProvider;
import org.nuxeo.codenvy.shared.NuxeoAttributes;

/**
 * Nuxeo Extension:
 * - Used to register actions (main Nuxeo menu with two actions).
 */
@Singleton
@Extension(title = "Nuxeo Plugin", version = "1.0.0")
public class NuxeoExtension {


    public static final String NUXEO = "Nuxeo Actions";

    public static final String NUXEO_GROUP_ACTION = "nuxeoGroup";

    public interface ParserResource extends ClientBundle {
        @Source("org/nuxeo/codenvy/client/nx.gif")
        ImageResource nuxeoCategoryIcon();
    }

    @Inject
    public NuxeoExtension(
            ActionManager actionManager,
            AutomationOperationAction automationOperationAction,
            BundleAction bundleAction, ParserResource parserResource,
            IconRegistry iconRegistry, MavenProjectTreeStructureProvider
            mavenProjectTreeStructureProvider, TreeStructureProviderRegistry
            treeStructureProviderRegistry) {

        // Handle actions
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

        // Handle Nuxeo icon
        iconRegistry.registerIcon(new Icon(NuxeoAttributes.NUXEO_ICON,
                parserResource.nuxeoCategoryIcon()));

        // Handle tree structure
        treeStructureProviderRegistry.registerProvider("nuxeo",
                mavenProjectTreeStructureProvider);

        Log.info(NuxeoExtension.class, "Nuxeo Plugin registration terminated.");
    }
}