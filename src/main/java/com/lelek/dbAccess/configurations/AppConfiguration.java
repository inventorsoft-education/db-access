package com.lelek.dbAccess.configurations;

import com.lelek.dbAccess.dao.MessageDao;
import com.lelek.dbAccess.service.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private JavaMailSender javaMailSender;

    private MessageDao messageDao;

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public CommandLineRunner schedulingRunner(TaskExecutor executor) {
        return args -> {
            SenderService senderService = new SenderService(javaMailSender, messageDao);
            senderService.setDaemon(true);
            executor.execute(senderService);
        };
    }
}
