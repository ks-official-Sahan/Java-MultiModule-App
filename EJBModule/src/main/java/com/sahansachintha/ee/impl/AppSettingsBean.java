package com.sahansachintha.ee.impl;

import com.sahansachintha.ee.remote.AppSettings;
import jakarta.ejb.Stateless;

@Stateless
public class AppSettingsBean implements AppSettings {

    int count;

    @Override
    public String getAppName() {
        count++;
        return "EJB Application: " + count;
    }

    @Override
    public String getAppVersion() {
        return "1.0";
    }

    @Override
    public String getAppDescription() {
        return "EJB Application description";
    }
}
