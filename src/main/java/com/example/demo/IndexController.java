package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class IndexController {

    /**
     *
     */
    private static int usersIndexCounter = 0;

    /**
     *
     * @param headers
     * @param response
     * @return
     */
    @RequestMapping(value="/")
    public String index(@RequestHeader HttpHeaders headers, HttpServletResponse response) {

        response.setHeader("Cache-Control", "public");
        response.setHeader("Last-Modified", getNextUsersIndexCounter(headers));

        consoleLog(headers);

        return getTargetedAd(headers);
    }

    /**
     *
     * @param headers
     * @return
     */
    private static String getNextUsersIndexCounter(HttpHeaders headers) {

        List<String> ifModifiedSinceList = headers.get("if-modified-since");

        return ifModifiedSinceList == null ?
                String.valueOf(++usersIndexCounter) :
                String.valueOf(ifModifiedSinceList.get(0));
    }

    /**
     *
     * @param headers
     */
    private static void consoleLog(HttpHeaders headers) {

        if (isTrackedUser(headers))
            System.out.println("Old user detected : " + headers.get("if-modified-since").get(0));

        else {
            System.out.println("New user : " + usersIndexCounter);
        }
    }

    /**
     *
     * @param headers
     * @return
     */
    private static boolean isTrackedUser(HttpHeaders headers) {

        return headers.get("if-modified-since") != null;
    }

    /**
     *
     * @param headers
     * @return
     */
    private String getTargetedAd(HttpHeaders headers) {

        return isTrackedUser(headers) ?
                "ad-tracked-user.js" :
                "ad-new-user.js";
    }
}