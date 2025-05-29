package com.sahansachintha.ee.remote;

import jakarta.ejb.Local;

//@Local // @Local by default
@Local
public interface AppSettings {
    String getAppName();
    String getAppVersion();
    String getAppDescription();
}
