package com.sahansachintha.ee.impl;

import com.sahansachintha.ee.remote.UserDetails;
import jakarta.ejb.Stateless;

@Stateless
public class UserDetailsBean implements UserDetails {
    @Override
    public String getName() {
        return "Sahan";
    }

    @Override
    public String getEmail() {
        return "ks.official.sahan@gmail.com";
    }

    @Override
    public String getContact() {
        return "+94768701148";
    }
}
