package org.routemaster.api.auth.domain.user.email.impl.util.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.auth.domain.user.email.impl.data.EmailUserReady;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailUserVerificationMailUtils {

    private static final String SUBJECT  = "[Route Master] 회원 검증 코드를 확인해주세요.";
    private static final String TEXT = "검증 코드: {}";
    private final JavaMailSender javaMailSender;

    private String createSubject(EmailUserReady basicUserReady) {
        return SUBJECT;
    }

    private String createText(EmailUserReady basicUserReady) {
        return TEXT.replace("{}", basicUserReady.getVerificationCode());
    }

    @Async
    public void sendVerificationCode(EmailUserReady basicUserReady) {
        String email = basicUserReady.getUsername();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setTo(email);
            messageHelper.setSubject(createSubject(basicUserReady));
            messageHelper.setText(createText(basicUserReady), true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(message);
    }
}
