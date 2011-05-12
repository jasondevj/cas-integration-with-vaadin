/*
 *   (C) Copyright 2010-2011 hSenid Software International (Pvt) Limited.
 *   All Rights Reserved.
 *
 *   These materials are unpublished, proprietary, confidential source code of
 *   hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
 *   of hSenid Software International (Pvt) Limited.
 *
 *   hSenid Software International (Pvt) Limited retains all title to and intellectual
 *   property rights in these materials.
 *
 */
package hailu.vaadin.authentication.sample;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * $LastChangedDate$
 * $LastChangedBy$
 * $LastChangedRevision$
 */
public class SampleVaadinApplication extends SampleVaadinApplication {


    @Override
    public void init() {
        final Window mainWindow = new Window("Vaadin Cas Sample");
        mainWindow.setSizeFull();
        final VerticalLayout newContent = new VerticalLayout();
        newContent.addComponent(new Label("Sample Vaadin"));
        mainWindow.setContent(newContent);
        this.setMainWindow(mainWindow);
    }
}
