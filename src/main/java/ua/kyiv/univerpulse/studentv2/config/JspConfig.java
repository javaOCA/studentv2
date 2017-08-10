package ua.kyiv.univerpulse.studentv2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;

@Configuration
public class JspConfig {

    private static final Long MAX_UPLOAD_SIZE_PER_FILE = 5 * 1024 *1024L;
    private static final Long MAX_UPLOAD_SIZE = 10 * 1024 *1024L;

    @Bean
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(MAX_UPLOAD_SIZE_PER_FILE);
        resolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
        return resolver;
    }
}
