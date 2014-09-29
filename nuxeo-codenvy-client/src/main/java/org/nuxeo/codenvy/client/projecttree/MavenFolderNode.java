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
import com.codenvy.ide.api.editor.EditorAgent;
import com.codenvy.ide.api.projecttree.AbstractTreeNode;
import com.codenvy.ide.api.projecttree.TreeNode;
import com.codenvy.ide.api.projecttree.TreeSettings;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.collections.Collections;
import com.codenvy.ide.ext.java.client.projecttree.JavaFolderNode;
import com.codenvy.ide.ext.java.client.projecttree.JavaTreeStructure;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.DtoUnmarshallerFactory;
import com.codenvy.ide.rest.Unmarshallable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;

import javax.annotation.Nullable;

public class MavenFolderNode extends JavaFolderNode {

    public MavenFolderNode(TreeNode<?> parent, ItemReference data,
            JavaTreeStructure treeStructure, TreeSettings settings,
            EventBus eventBus, EditorAgent editorAgent,
            ProjectServiceClient projectServiceClient,
            DtoUnmarshallerFactory dtoUnmarshallerFactory) {
        super(parent, data, treeStructure, settings, eventBus, editorAgent,
                projectServiceClient, dtoUnmarshallerFactory);
    }

    protected static boolean isModule(ItemReference item) {
        return "project".equals(item.getType());
    }

    @Override
    public void refreshChildren(final AsyncCallback<TreeNode<?>> callback) {
        getModules(data, new AsyncCallback<Array<ProjectDescriptor>>() {
            @Override
            public void onSuccess(final Array<ProjectDescriptor> modules) {
                getChildren(data.getPath(),
                        new AsyncCallback<Array<ItemReference>>() {
                    @Override
                    public void onSuccess(Array<ItemReference> children) {
                        final boolean isShowHiddenItems = settings
                                .isShowHiddenItems();
                        Array<TreeNode<?>> newChildren = Collections
                                .createArray();
                        setChildren(newChildren);
                        for (ItemReference item : children.asIterable()) {
                            if (isShowHiddenItems || !item.getName()
                                    .startsWith(".")) {
                                AbstractTreeNode node = createChildNode(item,
                                        modules);
                                if (node != null) {
                                    newChildren.add(node);
                                }
                            }
                        }
                        callback.onSuccess(MavenFolderNode.this);
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        callback.onFailure(caught);
                    }
                });
            }

            @Override
            public void onFailure(Throwable caught) {
                callback.onFailure(caught);
            }
        });
    }

    protected void getModules(ItemReference folder,
            final AsyncCallback<Array<ProjectDescriptor>> callback) {
        final Unmarshallable<Array<ProjectDescriptor>> unmarshaller =
                dtoUnmarshallerFactory.newArrayUnmarshaller(ProjectDescriptor
                        .class);
        projectServiceClient.getModules(folder.getPath(),
                new AsyncRequestCallback<Array<ProjectDescriptor>>
                        (unmarshaller) {
            @Override
            protected void onSuccess(Array<ProjectDescriptor> result) {
                callback.onSuccess(result);
            }

            @Override
            protected void onFailure(Throwable exception) {
                callback.onFailure(exception);
            }
        });
    }

    @Nullable
    protected AbstractTreeNode<?> createChildNode(ItemReference item,
            Array<ProjectDescriptor> modules) {
        if (isModule(item)) {
            return ((MavenProjectTreeStructure) treeStructure).newModuleNode
                    (this, getModule(item, modules));
        } else if (isSourceFolder(item)) {
            return ((MavenProjectTreeStructure) treeStructure)
                    .newSourceFolderNode(MavenFolderNode.this, item);
        } else if (isFolder(item)) {
            return ((MavenProjectTreeStructure) treeStructure)
                    .newJavaFolderNode(MavenFolderNode.this, item);
        } else {
            return super.createChildNode(item);
        }
    }

    @Nullable
    private ProjectDescriptor getModule(ItemReference folderItem,
            Array<ProjectDescriptor> modules) {
        if (isModule(folderItem)) {
            for (ProjectDescriptor module : modules.asIterable()) {
                if (folderItem.getName().equals(module.getName())) {
                    return module;
                }
            }
        }
        return null;
    }
}
