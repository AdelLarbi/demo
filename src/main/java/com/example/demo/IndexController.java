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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class IndexController {

    private static int usersIndexCounter = 0;

    @RequestMapping(value="/")
    public String index(@RequestHeader HttpHeaders headers, HttpServletResponse response) {

        //System.out.println(headers.toString());
        response.setHeader("Cache-Control", "public");
        response.setHeader("Last-Modified", getNextUsersIndexCounter(headers));
        showNextUsersIndexCounter(headers);

        return "ad.js";
    }

    /**
     *
     * @return
     */
    private static String getNextUsersIndexCounter(HttpHeaders headers) {

        List<String> ifModifiedSinceList = headers.get("if-modified-since");

        return (ifModifiedSinceList == null) ?
                String.valueOf(++usersIndexCounter) :
                String.valueOf(ifModifiedSinceList.get(0));
    }

    /**
     *
     * @return
     */
    private static void showNextUsersIndexCounter(HttpHeaders headers) {

        if (headers.get("if-modified-since") != null)
            System.out.println("Old user detected : " + headers.get("if-modified-since").get(0));

        else {
            System.out.println("New user : " + usersIndexCounter);
        }
    }

    /*@RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public void showImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(request.getHeader("If-Modified-Since"));


        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

        try {
            File img = new File("static/pexels-photo.jpg");
            BufferedImage image = ImageIO.read(img);
            ImageIO.write(image, "jpeg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        byte[] imgByte = jpegOutputStream.toByteArray();

        response.setContentType("image/jpeg");
        response.setHeader("Last-Modified", "2");
        response.setHeader("Cache-Control", "public, max-age");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(imgByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }*/
}
