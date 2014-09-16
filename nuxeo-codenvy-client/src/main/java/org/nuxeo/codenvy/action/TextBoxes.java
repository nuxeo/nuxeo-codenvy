/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.nuxeo.codenvy.action;

import com.codenvy.ide.api.action.Action;
import com.codenvy.ide.api.action.ActionEvent;
import com.google.gwt.user.client.Window;

public class TextBoxes extends Action {
    // If you register the action from Java code, this constructor is used to set the menu item name
    // (optionally, you can specify the menu description and an icon to display next to the menu item).
    // You can omit this constructor when registering the action in the plugin.xml file.
    public TextBoxes() {
     // Set the menu item name.
        super("Text _Boxes");
     // Set the menu item name, description and icon.
     // super("Text _Boxes","Item description", Resources.INSTANCE.icon());
    }
   
    public void actionPerformed(ActionEvent event) {
       String txt= Window.prompt("What is your name?", "");
       Window.alert("Hello, " + txt + "!\n I am glad to see you.");
    }
}