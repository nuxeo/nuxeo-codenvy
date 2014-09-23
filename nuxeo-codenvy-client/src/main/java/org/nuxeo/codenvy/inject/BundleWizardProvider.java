package org.nuxeo.codenvy.inject;

import com.codenvy.ide.api.wizard.DefaultWizard;
import com.codenvy.ide.api.wizard.DefaultWizardFactory;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class BundleWizardProvider implements Provider<DefaultWizard> {
    private DefaultWizardFactory wizardFactory;

    @Inject
    public BundleWizardProvider(DefaultWizardFactory wizardFactory) {
        this.wizardFactory = wizardFactory;
    }

    @Override
    public DefaultWizard get() {
        return wizardFactory.create("Wizard");
    }
}