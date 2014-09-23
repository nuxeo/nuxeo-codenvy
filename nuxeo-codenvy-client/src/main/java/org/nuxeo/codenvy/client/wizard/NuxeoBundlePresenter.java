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

import com.codenvy.api.project.gwt.client.ProjectServiceClient;
import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.ide.api.event.OpenProjectEvent;
import com.codenvy.ide.api.projecttype.wizard.ProjectWizard;
import com.codenvy.ide.api.wizard.AbstractWizardPage;
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.DtoUnmarshallerFactory;
import com.codenvy.ide.rest.Unmarshallable;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Nuxeo Wizard Server Bundle Presenter.
 */
public class NuxeoBundlePresenter extends AbstractWizardPage implements
        NuxeoPageView
                .ActionDelegate {
    private NuxeoPageView view;

    private ProjectServiceClient projectServiceClient;

    private EventBus eventBus;

    private DtoFactory dtoFactory;

    private DtoUnmarshallerFactory dtoUnmarshallerFactory;

    @Inject
    public NuxeoBundlePresenter(NuxeoPageView view, ProjectServiceClient
            projectServiceClient, EventBus eventBus, DtoFactory dtoFactory,
            DtoUnmarshallerFactory dtoUnmarshallerFactory) {
        super("Nuxeo project settings", null);
        this.view = view;
        this.projectServiceClient = projectServiceClient;
        this.eventBus = eventBus;
        this.dtoFactory = dtoFactory;
        this.dtoUnmarshallerFactory = dtoUnmarshallerFactory;
        view.setDelegate(this);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
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
        // nothing to do
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeOptions() {
        // nothing to do
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canSkip() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(view);
        ProjectDescriptor project = wizardContext.getData(ProjectWizard
                .PROJECT);
        if (project != null) {
            Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                @Override
                public void execute() {
                    // wait for client perform all actions to continue
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commit(@NotNull final CommitCallback callback) {
        Map<String, List<String>> options = new HashMap<>();
        options.put("nuxeo_template", Arrays.asList("nuxeo_template"));

        final ProjectDescriptor projectDescriptorToUpdate = dtoFactory
                .createDto(ProjectDescriptor.class);
        projectDescriptorToUpdate.withProjectTypeId(wizardContext.getData
                (ProjectWizard.PROJECT_TYPE).getProjectTypeId());

        boolean visibility = wizardContext.getData(ProjectWizard
                .PROJECT_VISIBILITY);
        projectDescriptorToUpdate.setVisibility(visibility ? "public" :
                "private");
        projectDescriptorToUpdate.setAttributes(options);
        final String name = wizardContext.getData(ProjectWizard.PROJECT_NAME);
        final ProjectDescriptor project = wizardContext.getData(ProjectWizard
                .PROJECT);
        if (project != null) {
            if (project.getName().equals(name)) {
                updateProject(project, projectDescriptorToUpdate, callback);
            } else {
                projectServiceClient.rename(project.getPath(), name, null,
                        new AsyncRequestCallback<Void>() {
                            @Override
                            protected void onSuccess(Void result) {
                                project.setName(name);

                                updateProject(project,
                                        projectDescriptorToUpdate,
                                        callback);
                            }

                            @Override
                            protected void onFailure(Throwable exception) {
                                callback.onFailure(exception);
                            }
                        });
            }

        } else {
            createProject(callback, projectDescriptorToUpdate, name);
        }
    }


    private void updateProject(final ProjectDescriptor project,
            ProjectDescriptor projectDescriptorToUpdate,
            final CommitCallback callback) {
        Unmarshallable<ProjectDescriptor> unmarshaller =
                dtoUnmarshallerFactory.newUnmarshaller(ProjectDescriptor.class);
        projectServiceClient
                .updateProject(project.getPath(), projectDescriptorToUpdate,
                        new AsyncRequestCallback<ProjectDescriptor>
                                (unmarshaller) {
                            @Override
                            protected void onSuccess(ProjectDescriptor result) {
                                eventBus.fireEvent(new OpenProjectEvent(result
                                        .getName()));
                                wizardContext.putData(ProjectWizard.PROJECT,
                                        result);
                                callback.onSuccess();
                            }

                            @Override
                            protected void onFailure(Throwable exception) {
                                callback.onFailure(exception);
                            }
                        });
    }

    private void createProject(final CommitCallback callback,
            ProjectDescriptor projectDescriptor, final String name) {
        projectServiceClient
                .createProject(name, projectDescriptor,
                        new AsyncRequestCallback<ProjectDescriptor>(
                                dtoUnmarshallerFactory.newUnmarshaller
                                        (ProjectDescriptor.class)) {
                            @Override
                            protected void onSuccess(ProjectDescriptor result) {
                                eventBus.fireEvent(new OpenProjectEvent
                                        (result.getName()));
                                wizardContext.putData(ProjectWizard.PROJECT,
                                        result);
                                callback.onSuccess();
                            }

                            @Override
                            protected void onFailure(Throwable exception) {
                                callback.onFailure(exception);
                            }
                        }
                );
    }
}