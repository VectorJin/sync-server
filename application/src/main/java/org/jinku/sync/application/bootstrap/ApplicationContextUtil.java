package org.jinku.sync.application.bootstrap;

import com.google.common.base.Preconditions;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class ApplicationContextUtil implements ApplicationContextAware{

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        Preconditions.checkNotNull(applicationContext);
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) {
        Preconditions.checkNotNull(context);
        return context.getBean(requiredType);
    }

}