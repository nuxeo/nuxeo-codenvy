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
package org.nuxeo.codenvy.client.event;

import com.google.gwt.event.shared.GwtEvent;
import org.nuxeo.codenvy.client.projecttree.ModuleNode;

public class BeforeModuleOpenEvent extends GwtEvent<BeforeModuleOpenHandler> {
    public static Type<BeforeModuleOpenHandler> TYPE = new
            Type<BeforeModuleOpenHandler>();

    private ModuleNode module;

    public BeforeModuleOpenEvent(ModuleNode module) {
        this.module = module;
    }

    public ModuleNode getModule() {
        return module;
    }

    public Type<BeforeModuleOpenHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(BeforeModuleOpenHandler handler) {
        handler.onBeforeModuleOpen(this);
    }
}
