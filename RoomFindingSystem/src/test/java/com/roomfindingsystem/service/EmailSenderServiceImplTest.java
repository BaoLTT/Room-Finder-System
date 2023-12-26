package com.roomfindingsystem.service;

import com.roomfindingsystem.service.impl.EmailSenderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmailSenderServiceImplTest {
    @Test
    public void testSendEmail() {
        // Arrange
        JavaMailSender mockMailSender = mock(JavaMailSender.class);
        EmailSenderService emailSenderService = new EmailSenderServiceImpl(mockMailSender);

        String to = "recipient@example.com";
        String subject = "Test Subject";
        String message = "Test Message";

        // Act
        emailSenderService.sendEmail(to, subject, message);

        // Assert
        verify(mockMailSender, times(1)).send(any(SimpleMailMessage.class));

        // You can also capture the argument passed to the send method for further verification
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertEquals(to, sentMessage.getTo()[0]);
        assertEquals(subject, sentMessage.getSubject());
        assertEquals(message, sentMessage.getText());
    }


    @Test
    public void testSendEmail_EmptySubjectAndMessage() {
        // Arrange
        JavaMailSender mockMailSender = mock(JavaMailSender.class);
        EmailSenderService emailSenderService = new EmailSenderServiceImpl(mockMailSender);

        String to = "recipient@example.com";
        String subject = ""; // empty subject
        String message = ""; // empty message

        // Act
        emailSenderService.sendEmail(to, subject, message);

        // Assert
        verify(mockMailSender, times(1)).send(any(SimpleMailMessage.class));
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertEquals("", sentMessage.getSubject());
        assertEquals("", sentMessage.getText());
    }

    @Test
    public void testSendEmail1() {
        // Arrange
        JavaMailSender mockMailSender = mock(JavaMailSender.class);
        EmailSenderService emailSenderService = new EmailSenderServiceImpl(mockMailSender);

        String to = "recipient@example.com";
        String subject = "Test Subject";
        String message = "";

        // Act
        emailSenderService.sendEmail(to, subject, message);

        // Assert
        verify(mockMailSender, times(1)).send(any(SimpleMailMessage.class));

        // You can also capture the argument passed to the send method for further verification
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertEquals(to, sentMessage.getTo()[0]);
        assertEquals(subject, sentMessage.getSubject());
        assertEquals("", sentMessage.getText());
    }

    @Test
    public void testSendEmail2() {
        // Arrange
        JavaMailSender mockMailSender = mock(JavaMailSender.class);
        EmailSenderService emailSenderService = new EmailSenderServiceImpl(mockMailSender);

        String to = "recipient@example.com";
        String subject = "";
        String message = "Test Message";

        // Act
        emailSenderService.sendEmail(to, subject, message);

        // Assert
        verify(mockMailSender, times(1)).send(any(SimpleMailMessage.class));

        // You can also capture the argument passed to the send method for further verification
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertEquals(to, sentMessage.getTo()[0]);
        assertEquals("", sentMessage.getSubject());
        assertEquals(message, sentMessage.getText());
    }

    @Test
    public void testSendEmail4() {
        // Arrange
        JavaMailSender mockMailSender = mock(JavaMailSender.class);
        EmailSenderService emailSenderService = new EmailSenderServiceImpl(mockMailSender);

        String to = "";
        String subject = "Test Subject";
        String message = "Test Message";

        // Act
        emailSenderService.sendEmail(to, subject, message);

        // Assert
        verify(mockMailSender, times(1)).send(any(SimpleMailMessage.class));

        // You can also capture the argument passed to the send method for further verification
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertEquals("", sentMessage.getTo()[0]);
        assertEquals(subject, sentMessage.getSubject());
        assertEquals(message, sentMessage.getText());
    }

    @Test
    public void testSendEmail5() {
        // Arrange
        JavaMailSender mockMailSender = mock(JavaMailSender.class);
        EmailSenderService emailSenderService = new EmailSenderServiceImpl(mockMailSender);

        String to = "";
        String subject = "Test Subject";
        String message = "";

        // Act
        emailSenderService.sendEmail(to, subject, message);

        // Assert
        verify(mockMailSender, times(1)).send(any(SimpleMailMessage.class));

        // You can also capture the argument passed to the send method for further verification
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertEquals("", sentMessage.getTo()[0]);
        assertEquals(subject, sentMessage.getSubject());
        assertEquals("", sentMessage.getText());
    }

    @Test
    public void testSendEmail6() {
        // Arrange
        JavaMailSender mockMailSender = mock(JavaMailSender.class);
        EmailSenderService emailSenderService = new EmailSenderServiceImpl(mockMailSender);

        String to = "";
        String subject = "";
        String message = "Test Message";

        // Act
        emailSenderService.sendEmail(to, subject, message);

        // Assert
        verify(mockMailSender, times(1)).send(any(SimpleMailMessage.class));

        // You can also capture the argument passed to the send method for further verification
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertEquals("", sentMessage.getTo()[0]);
        assertEquals("", sentMessage.getSubject());
        assertEquals(message, sentMessage.getText());
    }

    @Test
    public void testSendEmail7() {
        // Arrange
        JavaMailSender mockMailSender = mock(JavaMailSender.class);
        EmailSenderService emailSenderService = new EmailSenderServiceImpl(mockMailSender);

        String to = "";
        String subject = "";
        String message = "";

        // Act
        emailSenderService.sendEmail(to, subject, message);

        // Assert
        verify(mockMailSender, times(1)).send(any(SimpleMailMessage.class));

        // You can also capture the argument passed to the send method for further verification
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertEquals("", sentMessage.getTo()[0]);
        assertEquals("", sentMessage.getSubject());
        assertEquals("", sentMessage.getText());
    }
}
