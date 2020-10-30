package com.andersen.patterns.behavioral.chainofresponsibility;

public class LoggerApp {
    public static void main(String[] args) {
        Logger fileLogger = new FileLogger(Logger.INFO);
        Logger emailLogger = new EmailLogger(Logger.DEBUG);
        Logger smsLogger = new SMSLogger(Logger.ERROR);
        fileLogger.setNext(emailLogger);
        emailLogger.setNext(smsLogger);
        smsLogger.setNext(null);

        fileLogger.writeMessage("Warning!", Logger.INFO);
    }
}
