package com.trafikverket.finder.notifier;

import com.trafikverket.finder.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Qualifier("mailNotifier")
@Slf4j
public class EmailNotifier implements Notifier {

  private final JavaMailSender emailSender;

  @Override
  public void send(String destination, String message) {
    log.debug("Sending an email");
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom(Constants.FROM_EMAIL_ADDRESS);
    mailMessage.setTo(destination);
    mailMessage.setSubject(Constants.EMAIL_SUBJECT);
    mailMessage.setText(message);
    emailSender.send(mailMessage);
  }
}
