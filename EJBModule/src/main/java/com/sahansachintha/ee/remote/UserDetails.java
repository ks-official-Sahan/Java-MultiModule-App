package com.sahansachintha.ee.remote;

import jakarta.ejb.Local;
import jakarta.ejb.Remote;

//@Local // @Local by default
@Remote
public interface UserDetails {
    String getName();
    String getEmail();
    String getContact();
}
