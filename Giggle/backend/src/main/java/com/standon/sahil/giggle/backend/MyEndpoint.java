/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.standon.sahil.giggle.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;
import com.standon.sahil.giggle.JavaSrc;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.giggle.sahil.standon.com",
                ownerName = "backend.giggle.sahil.standon.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "giveJoke")
    public MyBean giveJoke() {
        MyBean response = new MyBean();
        /*response.setData("Heres a awesome joke, Laugh you brains out!! :)");*/
        response.setData(new JavaSrc().retrieveJoke());
        return response;
    }

}
