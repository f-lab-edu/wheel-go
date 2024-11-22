package com.wheelgo.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Spring 애플리케이션 초기화를 담당하는 클래스.
 * WebApplicationInitializer를 구현하여 서블릿 컨테이너 시작 시 Spring 설정을 자동으로 로드.
 */
public class MyWebAppInitializer implements WebApplicationInitializer {

    /**
     * 서블릿 컨테이너 시작 시 호출되는 메서드.
     * Spring 애플리케이션 컨텍스트와 DispatcherServlet을 등록.
     *
     * @param servletContext 현재 애플리케이션의 서블릿 컨텍스트 객체.
     *                       서블릿, 리스너, 필터 등을 등록하거나 구성할 수 있음.
     * @throws ServletException 서블릿 초기화 오류 발생 시 던질 수 있음.
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Spring WebApplicationContext 초기화
        // AnnotationConfigWebApplicationContext는 Java Config를 기반으로 동작.
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();

        appContext.register(ServletAppContext.class);

        // DispatcherServlet 생성 및 Spring 애플리케이션 컨텍스트와 연결.
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        // 서블릿 컨텍스트에 DispatcherServlet 등록.
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", dispatcherServlet);

        // 서블릿 초기화 시점 설정 (가장 먼저 초기화되도록 설정: 1).
        registration.setLoadOnStartup(1);

        // DispatcherServlet이 모든 요청("/")을 처리하도록 매핑.
        registration.addMapping("/");
    }
}
