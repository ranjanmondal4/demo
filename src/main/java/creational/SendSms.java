package creational;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SendSms {
    public static void main(String[] args) {
        // Your Credentials
        String ACCESS_KEY = "AKIA4IEJ4AT4FXGU3CPA";
        String SECRET_KEY = "K8bth10zeJBNmHcMBfnLHPDuSzCN8pmQXlDnypFT";
        AmazonSNSClient snsClient = new AmazonSNSClient(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
        String message = "By the way, thanks for your help...";
        String phoneNumber = "+919711773480";  // Ex: +91XXX4374XX
        sendSMSMessage(snsClient, message, phoneNumber);
    }
    // Send SMS to a Phone Number
    public static void sendSMSMessage(AmazonSNSClient snsClient,
                                      String message, String phoneNumber) {
        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber));
        System.out.println(result); // Prints the message ID.
    }

    // https://medium.com/viithiisys/amazon-simple-notification-service-sns-via-sms-c29c65e2cebe
}
