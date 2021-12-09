package com.company;

import org.apache.log4j.*;
import org.apache.log4j.net.SMTPAppender;

public class Mailer {
    private static final Logger logger = Logger.getLogger(Mailer.class);

    public Mailer() {
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%5p %c{1}:%L - %m (%d{дата dd.MM.yyyy})%n");

        SMTPAppender appender = new SMTPAppender();
        //appender.setSMTPHost();
        //appender.setSMTPUsername();
        //appender.setSMTPPassword();

        appender.setFrom("myuser@mydomain.com");
        appender.setTo("myuser@mydomain.com");
        appender.setSubject("Log of messages");
        appender.setBufferSize(1);
        appender.setEvaluatorClass("TriggerLogEvent");

        appender.setLayout(patternLayout);
        appender.activateOptions();

        BasicConfigurator.configure(appender);
    }

    public void logMail(String mailString) {
        logger.fatal(mailString);
    }
}
