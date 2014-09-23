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
package org.nuxeo.codenvy.server.inject;

import com.codenvy.api.project.server.ValueProviderFactory;
import com.codenvy.inject.DynaModule;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.nuxeo.codenvy.server.project.NuxeoTemplateProviderFactory;
import org.nuxeo.codenvy.server.project.type
        .NuxeoProjectTypeDescriptionExtension;
import org.nuxeo.codenvy.server.project.type.NuxeoProjectTypeExtension;

@DynaModule
public class NuxeoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NuxeoProjectTypeExtension.class);
        bind(NuxeoProjectTypeDescriptionExtension.class);
        Multibinder<ValueProviderFactory> multiBinder = Multibinder
                .newSetBinder(binder(), ValueProviderFactory.class);
        multiBinder.addBinding().to(NuxeoTemplateProviderFactory.class);
    }
}
