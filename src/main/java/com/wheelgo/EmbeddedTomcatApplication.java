package com.wheelgo;

import com.wheelgo.config.AppConfig;
import com.wheelgo.config.ServletAppContext;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class EmbeddedTomcatApplication {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedTomcatApplication.class);

    public static void main(String[] args) {
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(8080);

            // 빈 컨텍스트 생성
            Context context = tomcat.addContext("", null);

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
            logger.info("Embedded Tomcat started", tomcat.getConnector().getPort());

            tomcat.getServer().await();
        } catch (Exception e) {
            logger.error("Embedded Tomcat started", e);
        }
    }
}
