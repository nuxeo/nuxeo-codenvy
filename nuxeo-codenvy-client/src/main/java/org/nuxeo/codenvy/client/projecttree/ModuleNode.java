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
package org.nuxeo.codenvy.client.projecttree;

import com.codenvy.api.project.gwt.client.ProjectServiceClient;
import com.codenvy.api.project.shared.dto.ItemReference;
import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.ide.api.icon.IconRegistry;
import com.codenvy.ide.api.projecttree.TreeNode;
import com.codenvy.ide.api.projecttree.TreeSettings;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.DtoUnmarshallerFactory;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import org.nuxeo.codenvy.client.event.BeforeModuleOpenEvent;

public class ModuleNode extends MavenProjectNode {

    public ModuleNode(TreeNode<?> parent, ProjectDescriptor data,
            MavenProjectTreeStructure treeStructure, TreeSettings settings,
            EventBus eventBus, ProjectServiceClient projectServiceClient,
            DtoUnmarshallerFactory dtoUnmarshallerFactory,
            IconRegistry iconRegistry) {
        super(parent, data, treeStructure, settings, eventBus,
                projectServiceClient, dtoUnmarshallerFactory);
        setDisplayIcon(iconRegistry.getIcon("maven.module").getSVGImage());
    }

    @Override
    protected void getChildren(String path,
            AsyncCallback<Array<ItemReference>> callback) {
        if (!isOpened()) {
            eventBus.fireEvent(new BeforeModuleOpenEvent(this));
        }
        super.getChildren(path, callback);
    }
}
