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
package org.nuxeo.codenvy.server.project.type;

import com.codenvy.api.project.server.ProjectTypeDescriptionRegistry;
import com.codenvy.api.project.server.ProjectTypeExtension;
import com.codenvy.api.project.shared.Attribute;
import com.codenvy.api.project.shared.ProjectTemplateDescription;
import com.codenvy.api.project.shared.ProjectType;
import com.codenvy.ide.Constants;
import com.codenvy.ide.server.ProjectTemplateDescriptionLoader;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.nuxeo.codenvy.shared.ProjectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Singleton
public class NuxeoProjectTypeExtension implements ProjectTypeExtension {
    private static final Logger LOG = LoggerFactory.getLogger(NuxeoProjectTypeExtension.class);
    private final ProjectType                      projectType;
    private final ProjectTemplateDescriptionLoader projectTemplateDescriptionLoader;

    @Inject
    public NuxeoProjectTypeExtension(ProjectTypeDescriptionRegistry registry,
            ProjectTemplateDescriptionLoader projectTemplateDescriptionLoader) {
        this.projectTemplateDescriptionLoader = projectTemplateDescriptionLoader;
        this.projectType = new ProjectType(ProjectAttributes.NUXEO_ID, ProjectAttributes.NUXEO_NAME, ProjectAttributes.NUXEO_CATEGORY, null,
                                           ProjectAttributes.NUXEO_DEFAULT_RUNNER);
        registry.registerProjectType(this);
    }

    /** {@inheritDoc} */
    @Override
    public ProjectType getProjectType() {
        return projectType;
    }

    /** {@inheritDoc} */
    @Override
    public List<Attribute> getPredefinedAttributes() {
        final List<Attribute> list = new ArrayList<>(1);
        list.add(new Attribute(Constants.LANGUAGE, "java"));
        return list;
    }

    /** {@inheritDoc} */
    @Override
    public List<ProjectTemplateDescription> getTemplates() {
        final List<ProjectTemplateDescription> list = new ArrayList<>();
        try {
            projectTemplateDescriptionLoader.load(getProjectType().getId(), list);
        } catch (IOException e) {
            LOG.error("Unable to load external templates for project type: {}", getProjectType().getId());
        }
        return list;
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, String> getIconRegistry() {
        return null;
    }
}
