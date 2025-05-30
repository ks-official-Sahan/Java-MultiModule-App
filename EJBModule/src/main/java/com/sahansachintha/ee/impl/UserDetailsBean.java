package com.sahansachintha.ee.impl;

import com.sahansachintha.ee.remote.AppSettings;
import com.sahansachintha.ee.remote.UserDetails;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;

//@Stateless(name = "UserInfo", mappedName = "XYZ")
//@Stateless // Stateless session cannot manage any states because the container reuses any bean from the pool randomly.
//@Stateful // Stateful session beans manage states, creates object per each session, per each user.
@Singleton
@Startup // Creates a object of this session bean when the app is being deployed.
public class UserDetailsBean implements UserDetails, Serializable {

    int count;

    @EJB
    private transient AppSettings settings; // dependency injection (local -> @Local by default - no need to worry)
    // @EJB private AppSettings settings1;

    @PostConstruct
    public void init() {
        System.out.println("UserDetailsBean initialized: " + this.hashCode());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("UserDetailsBean destroyed");
    }

    @Remove // for singleton session beans
    public void remove() {
        System.out.println("UserDetailsBean removed");
    }

    @Override
    // @Lock(LockType.READ) // LockType.READ by default, so no need to use @Lock annotation
    // @Lock(LockType.WRITE) // @Lock(LockType.WRITE) = @Lock, this will lock the method accessing by 2 threads at once, just like synchronization
    @Lock
    public String getName() {
        // return "Sahan";

        // try {
        //     InitialContext ctx = new InitialContext(); // skip this using dependency injection
        //     AppSettings settings = (AppSettings) ctx.lookup("java:global/EJBModule/AppSettingsBean");
        //     return settings.getAppName();
        // } catch (NamingException e) {
        //     throw new RuntimeException(e);
        // }

        // System.out.println(settings);
        // System.out.println(settings1);

        // try {
        //     Thread.sleep(5000);
        // } catch (InterruptedException e) {
        //     throw new RuntimeException(e);
        // }

        count++;
        return settings.getAppName() + " - " + settings.getAppVersion() + " : " + count;
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
