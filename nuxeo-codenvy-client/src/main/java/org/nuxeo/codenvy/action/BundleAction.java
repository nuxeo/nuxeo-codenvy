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
package org.nuxeo.codenvy.action;

import com.codenvy.ide.api.action.Action;
import com.codenvy.ide.api.action.ActionEvent;
import org.nuxeo.codenvy.NuxeoResource;

/**
 * Nuxeo server bundle action to create new project.
 */
public class BundleAction extends Action {

//    private WizardDialogFactory factory;
//
//    private DefaultWizard wizard;

    public static final String TITLE = "Nuxeo Server Bundle";

    public static final String DESCRIPTION = "Create a new Nuxeo Server " +
            "Bundle";

//    @Inject
//    public BundleAction(WizardDialogFactory factory,
//            @NuxeoWizard DefaultWizard wizard) {
//        super(TITLE, DESCRIPTION, NuxeoResource.INSTANCE.nx());
//        this.factory = factory;
//        this.wizard = wizard;
//    }

    public BundleAction() {
        super(TITLE, DESCRIPTION, NuxeoResource.INSTANCE.nx());
//        this.factory = factory;
//        this.wizard = wizard;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
//        WizardDialog dialog = factory.create(wizard);
//        dialog.show();
    }
}