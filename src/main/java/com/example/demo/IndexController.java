package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * This class is used to attribute a unique id to identify each user by using the "Last-Modified" tag.
 * Each time the client side requests an advertisement, the server sends the appropriate script according
 * to the user's status whether he is tracked or not.
 */
@Controller
public class IndexController {

    /**
     * User's index counter.
     */
    private static int usersIndexCounter = 0;

    /**
     * When the client request a new advertisement, send a default or a targeted script according to the user's status
     * whether he is tracked or not.
     * @param headers Mapping string header names to a list of string values.
     * @param response Has methods to access HTTP headers and cookies.
     * @return A targeted advertisement according to the user's status.
     */
    @RequestMapping(value="/")
    public String index(@RequestHeader HttpHeaders headers, HttpServletResponse response) {

        /* Change the Last-modified tag to track a user. */
        response.setHeader("Cache-Control", "public");
        response.setHeader("Last-Modified", getNextUsersIndexCounter(headers));

        /* Show a log message to find out if the user is tracked or not. */
        consoleLog(headers);

        /* Send a targeted advertisement according to the user's status */
        return getTargetedAd(headers);
    }

    /**
     * Generates a new identifier each time we detects a new user, otherwise return the attributed index.
     * @param headers Mapping string header names to a list of string values.
     * @return New-index if is a new user. Old-index otherwise.
     */
    private static String getNextUsersIndexCounter(HttpHeaders headers) {

        List<String> ifModifiedSinceList = headers.get("if-modified-since");

        return ifModifiedSinceList == null ?
                String.valueOf(++usersIndexCounter) :
                String.valueOf(ifModifiedSinceList.get(0));
    }

    /**
     * Show a customised message and the attributed index according to the user's status.
     * @param headers Mapping string header names to a list of string values.
     */
    private static void consoleLog(HttpHeaders headers) {

        if (isTrackedUser(headers))
            System.out.println("Old user detected : " + headers.get("if-modified-since").get(0));

        else {
            System.out.println("New user : " + usersIndexCounter);
        }
    }

    /**
     * Check the user's status whether he is tracked or not.
     * @param headers Mapping string header names to a list of string values.
     * @return True if the user is tracked. False otherwise.
     */
    private static boolean isTrackedUser(HttpHeaders headers) {

        return headers.get("if-modified-since") != null;
    }

    /**
     * Returns a specific script depending on the user's status.
     * @param headers Mapping string header names to a list of string values.
     * @return A targeted script advertisement if the user is tracked. A default ad script else.
     */
    private String getTargetedAd(HttpHeaders headers) {

        return isTrackedUser(headers) ?
                "ad-tracked-user.js" :
                "ad-new-user.js";
    }
}