package com.wheelgo;

import com.wheelgo.config.AppConfig;
import com.wheelgo.config.ServletAppContext;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class EmbeddedTomcatApplication {

    public static void main(String[] args) {
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(8080);

            // 빈 컨텍스트 생성
            String webappDir = new File("src/main/webapp").getAbsolutePath();
            Context context = tomcat.addContext("", webappDir);

            // Spring 애플리케이션 컨텍스트 생성 및 설정 클래스 등록
            AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
            appContext.register(ServletAppContext.class, AppConfig.class);

            // **ServletContext를 애플리케이션 컨텍스트에 설정**
            appContext.setServletContext(context.getServletContext());

            // 애플리케이션 컨텍스트 초기화
            appContext.refresh();

            // DispatcherServlet 생성 및 등록
            DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
            Tomcat.addServlet(context, "dispatcher", dispatcherServlet).setLoadOnStartup(1);
            context.addServletMappingDecoded("/", "dispatcher");

            // Tomcat 시작
            tomcat.start();
            System.out.println("Tomcat started on port: " + tomcat.getConnector().getPort());

            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
