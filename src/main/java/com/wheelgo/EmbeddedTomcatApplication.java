package com.wheelgo;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class EmbeddedTomcatApplication {

    public static void main(String[] args) {
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(8080);

            // webapp 디렉터리 설정
            String webappDir = new File("src/main/webapp").getAbsolutePath();
            tomcat.addWebapp("", webappDir);

            // Tomcat 시작
            tomcat.start();
            System.out.println("Tomcat started on port: " + tomcat.getConnector().getPort());

            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
