import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class NotificationSetting {
    // Configuration for email sending
    private static final String EMAIL = "ams@gmail.com"; // Replace it with our email
    private static final String EMAIL_PASSWORD = "appliances4ever$"; // Replace it with the password we have for our email

    // Configuration for SMS
    // Just checking but we're not creating real phone numbers and emails
    private static final String ACCOUNT_SID = "your_account_sid"; // replace it with twilio sid
    private static final String AUTH_TOKEN = "your_auth_token"; // replace it with twilio auth token
    private static final String PHONE_NUMBER = "your_twilio_phone_number";  // replace it with twilio phone number

    public static void main(String[] args) {
        // Monitor appliance status
        // Replace with methods to get user appliances info
        String applianceName = getApplianceName();
        String applianceLocation = getApplianceLocation();
        String applianceStatus = monitorApplianceStatus(applianceName, applianceLocation);

        // Get user information.
        //Replace with method to get user's email and phone number
        String userEmail = getUserEmail(); // Replace with actual method
        String userPhoneNumber = getUserPhoneNumber(); // Replace with actual method

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);//Initialize the Twilo client
        String encryptedMessage = encryptionMessage(applianceStatus, 3); // Encrypt the message
        sendEncryptedEmail(userEmail, "Appliance Status Alert", encryptedMessage); // Send encrypted email notification

    }

    // Monitor appliance status (Mocked Method)
    private static String monitorApplianceStatus(String appliances, String location) {
        // Return the name of Appliances,the location and status as a string
        boolean status = readSensorForApplianceStatus();
        return appliances + " in " + location + (status ? "running" : "not working");
    }


    // Sensor reading method (Mocked Method)
    private static boolean readSensorForApplianceStatus() {
        //Pretending there is a method to read a sensor and return the status
        boolean status = readApplianceStatusFromSensor();
        return status;
    }

    @org.jetbrains.annotations.NotNull
    private static String encryptionMessage(String message, int shift) {
        // Encrpts a message using Caesar Cipher
        StringBuilder encrypted = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char shiftedChar = (char) (((c - 'A' + shift) % 26) + 'A');
                encrypted.append(shiftedChar);
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString(); // Return the encrypted message as a string
    }

    //Send encryted email
    //If statement for appliance status is false to send message
    private static void sendEncryptedEmail(String recipientEmail, String subject, String encryptedMessage) {
        //Email configuation
        String fromEmail = EMAIL;
        String password = EMAIL_PASSWORD;
        String smtpHost = "smtp.x.com"; //ams smtp server
        int smtpPort = 587; //ams stmp port

        // Setup properties for the email server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        // Authenticate with email server
        Authenticator a = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
            /*
            On the website where I found the Authenticator class, it advises using the
            protected modifier, but I'm not familiar with how to use it.

            Authenticator a = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
            }
        };
             */

        //Create a session with the email server
        Session session = Session.getInstance(props, a);

        try {
            //Create MimeMessage
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(fromEmail));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            emailMessage.setSubject(subject);
            emailMessage.setText(encryptedMessage); //Set the encrypted message as the email content
            Transport.send(emailMessage); //Send the encrypted email

            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Send an encryted SMS
    //If statement for appliance status is false to send message
    private static void sendEncryptedSMS(String phoneNumber, String encryptedMessage) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(recipientPhoneNumber), // user phone number
                    new PhoneNumber(FROM_PHONE_NUMBER),    // ams Twilio phone number
                    encryptedMessage                       // Message body
            ).create();

            System.out.println("SMS sent successfully. SID: " + message.getSid());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to send SMS: " + e.getMessage());
        }
    }
}
