package com.sahansachintha.ee;

import com.sahansachintha.ee.remote.AppSettings;
import com.sahansachintha.ee.remote.PriceCalculator;
import com.sahansachintha.ee.remote.ShoppingCart;
import com.sahansachintha.ee.remote.UserDetails;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/home")
public class Home extends HttpServlet {

    //@EJB(lookup = "java:global/EJBModule/UserInfo!com.sahansachintha.ee.remote.UserDetails") // if the Stateless session bean name changed this is required.
//    @EJB // dependency injection (remote -> @Remote is required)
//    private UserDetails userDetails;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        UserDetails userDetails = null;

        if (req.getSession().getAttribute("userbean") == null) {
            try {
                InitialContext ctx = new InitialContext();
                userDetails = (UserDetails) ctx.lookup("java:global/EJBModule/UserDetailsBean!com.sahansachintha.ee.remote.UserDetails");
                // UserDetails userDetails = (UserDetails) ctx.lookup("java:global/EJBModule/UserInfo");
                // UserDetails userDetails = (UserDetails) ctx.lookup("java:global/EJBModule/UserInfo!com.sahansachintha.ee.remote.UserDetails"); // When Stateless bean name changed
                // UserDetails userDetails = (UserDetails) ctx.lookup("XYZ"); // Works only if the EJB module is on Local Container (non-portable name is mappedName)

                req.getSession().setAttribute("userbean", userDetails);
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        } else {
            userDetails = (UserDetails) req.getSession().getAttribute("userbean");
        }

//        try {
//            InitialContext ctx = new InitialContext();
//            AppSettings settings = (AppSettings) ctx.lookup("java:global/EJBModule/AppSettingsBean");
//
//            resp.getWriter().println("<h2>" +settings.getAppDescription() + "</h2>");
//        } catch (NamingException e) {
//            throw new RuntimeException(e);
//        }

        try {
            InitialContext ctx = new InitialContext();

            PriceCalculator priceCalculator = (PriceCalculator) ctx.lookup("java:global/EJBModule/PriceCalculatorBean!com.sahansachintha.ee.remote.PriceCalculator");
            resp.getWriter().println("<h2>" + priceCalculator.calculateTotalPriceString(150, 23) + "</h2>");

            ShoppingCart shoppingCart = null;
            if (req.getSession().getAttribute("cart") == null) {
                shoppingCart = (ShoppingCart) ctx.lookup("java:global/EJBModule/ShoppingCartBean!com.sahansachintha.ee.remote.ShoppingCart");
                req.getSession().setAttribute("cart", shoppingCart);
            } else {
                shoppingCart = (ShoppingCart) req.getSession().getAttribute("cart");
            }
            resp.getWriter().println("<h3>" + shoppingCart.getCartName() + "</h3>");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        resp.getWriter().println("Hello " + userDetails.getName());
    }
}

/*
[2025-05-28T14:45:14.773235+05:30] [GF 7.0.24] [INFO] [AS-EJB-00054] [jakarta.enterprise.ejb.container] [tid: _ThreadID=97 _ThreadName=admin-listener(4)] [levelValue: 800] [[
  Portable JNDI names for EJB UserDetailsBean: [java:global/EJBModule/UserDetailsBean!com.sahansachintha.ee.remote.UserDetails, java:global/EJBModule/UserDetailsBean]]]

[2025-05-28T14:45:14.773235+05:30] [GF 7.0.24] [INFO] [AS-EJB-00055] [jakarta.enterprise.ejb.container] [tid: _ThreadID=97 _ThreadName=admin-listener(4)] [levelValue: 800] [[
  Glassfish-specific (Non-portable) JNDI names for EJB UserDetailsBean: [com.sahansachintha.ee.remote.UserDetails#com.sahansachintha.ee.remote.UserDetails, com.sahansachintha.ee.remote.UserDetails]]]

[2025-05-29T15:38:28.506518+05:30] [GF 7.0.24] [INFO] [AS-EJB-00054] [jakarta.enterprise.ejb.container] [tid: _ThreadID=98 _ThreadName=admin-listener(5)] [levelValue: 800] [[
  Portable JNDI names for EJB UserInfo: [java:global/EJBModule/UserInfo!com.sahansachintha.ee.remote.UserDetails, java:global/EJBModule/UserInfo]]]

[2025-05-29T15:38:28.506518+05:30] [GF 7.0.24] [INFO] [AS-EJB-00055] [jakarta.enterprise.ejb.container] [tid: _ThreadID=98 _ThreadName=admin-listener(5)] [levelValue: 800] [[
  Glassfish-specific (Non-portable) JNDI names for EJB UserInfo: [com.sahansachintha.ee.remote.UserDetails#com.sahansachintha.ee.remote.UserDetails, com.sahansachintha.ee.remote.UserDetails]]]


[2025-05-29T15:38:28.482518+05:30] [GF 7.0.24] [INFO] [AS-EJB-00054] [jakarta.enterprise.ejb.container] [tid: _ThreadID=98 _ThreadName=admin-listener(5)] [levelValue: 800] [[
  Portable JNDI names for EJB AppSettingsBean: [java:global/EJBModule/AppSettingsBean, java:global/EJBModule/AppSettingsBean!com.sahansachintha.ee.remote.AppSettings]]]

[2025-05-29T15:38:28.482518+05:30] [GF 7.0.24] [INFO] [AS-EJB-00055] [jakarta.enterprise.ejb.container] [tid: _ThreadID=98 _ThreadName=admin-listener(5)] [levelValue: 800] [[
  Glassfish-specific (Non-portable) JNDI names for EJB AppSettingsBean: [com.sahansachintha.ee.remote.AppSettings#com.sahansachintha.ee.remote.AppSettings, com.sahansachintha.ee.remote.AppSettings]]]
*/