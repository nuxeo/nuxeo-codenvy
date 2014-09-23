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

import com.codenvy.api.project.server.ProjectTypeDescriptionExtension;
import com.codenvy.api.project.server.ProjectTypeDescriptionRegistry;
import com.codenvy.api.project.shared.AttributeDescription;
import com.codenvy.api.project.shared.ProjectType;
import com.codenvy.ide.Constants;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.nuxeo.codenvy.shared.ProjectAttributes;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class NuxeoProjectTypeDescriptionExtension implements
        ProjectTypeDescriptionExtension {

    @Inject
    public NuxeoProjectTypeDescriptionExtension
            (ProjectTypeDescriptionRegistry registry) {
        registry.registerDescription(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProjectType> getProjectTypes() {
        final List<ProjectType> list = new ArrayList<>(1);
        list.add(new ProjectType(ProjectAttributes.NUXEO_ID,
                ProjectAttributes.NUXEO_NAME,
                ProjectAttributes.NUXEO_CATEGORY, null,
                ProjectAttributes.NUXEO_DEFAULT_RUNNER));
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AttributeDescription> getAttributeDescriptions() {
        final List<AttributeDescription> list = new ArrayList<>(4);
        list.add(new AttributeDescription(Constants.LANGUAGE));
        list.add(new AttributeDescription(Constants.RUNNER_NAME));
        list.add(new AttributeDescription(Constants.RUNNER_ENV_ID));
        list.add(new AttributeDescription(ProjectAttributes
                .NUXEO_PROJECT_TEMPLATE));
        return list;
    }
}
