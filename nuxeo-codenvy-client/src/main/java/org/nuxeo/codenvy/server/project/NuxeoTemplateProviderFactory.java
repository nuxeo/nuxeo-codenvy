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
package org.nuxeo.codenvy.server.project;

import com.codenvy.api.project.server.Project;
import com.codenvy.api.project.server.ValueProviderFactory;
import com.codenvy.api.project.shared.InvalidValueException;
import com.codenvy.api.project.shared.ValueProvider;
import com.codenvy.api.project.shared.ValueStorageException;
import com.google.inject.Singleton;
import org.nuxeo.codenvy.shared.NuxeoAttributes;

import java.util.Collections;
import java.util.List;

@Singleton
public class NuxeoTemplateProviderFactory implements ValueProviderFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return NuxeoAttributes.NUXEO_PROJECT_TEMPLATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValueProvider newInstance(final Project project) {
        return new ValueProvider() {
            @Override
            public List<String> getValues() throws ValueStorageException {
                return Collections.emptyList();
            }

            @Override
            public void setValues(List<String> value) throws
                    ValueStorageException, InvalidValueException {
            }
        };
    }
}
