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
import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.ide.api.app.AppContext;
import com.codenvy.ide.api.editor.EditorAgent;
import com.codenvy.ide.api.icon.IconRegistry;
import com.codenvy.ide.api.projecttree.AbstractTreeStructure;
import com.codenvy.ide.api.projecttree.TreeSettings;
import com.codenvy.ide.api.projecttree.TreeStructureProvider;
import com.codenvy.ide.rest.DtoUnmarshallerFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;

@Singleton
public class MavenProjectTreeStructureProvider implements
        TreeStructureProvider {
    private EventBus eventBus;

    private EditorAgent editorAgent;

    private AppContext appContext;

    private IconRegistry iconRegistry;

    private ProjectServiceClient projectServiceClient;

    private DtoUnmarshallerFactory dtoUnmarshallerFactory;

    @Inject
    public MavenProjectTreeStructureProvider(EventBus eventBus,
            EditorAgent editorAgent, AppContext appContext,
            IconRegistry iconRegistry,
            ProjectServiceClient projectServiceClient, DtoUnmarshallerFactory
            dtoUnmarshallerFactory) {
        this.eventBus = eventBus;
        this.editorAgent = editorAgent;
        this.appContext = appContext;
        this.iconRegistry = iconRegistry;
        this.projectServiceClient = projectServiceClient;
        this.dtoUnmarshallerFactory = dtoUnmarshallerFactory;
    }

    @Override
    public AbstractTreeStructure newTreeStructure(ProjectDescriptor project) {
        return new MavenProjectTreeStructure(TreeSettings.DEFAULT, project,
                eventBus, editorAgent, appContext, projectServiceClient,
                dtoUnmarshallerFactory, iconRegistry);
    }
}
