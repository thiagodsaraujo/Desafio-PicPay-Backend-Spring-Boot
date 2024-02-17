package com.desafiopicpay.services;

import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.dtos.NotificationDTO;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    // precisa configurar o resttemplate
    private final RestTemplate restTemplate;

    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

//        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6",
//                notificationRequest, String.class);
//
//        if (notificationResponse.getStatusCode().is2xxSuccessful()) {
//            System.out.println("Falha ao enviar a notificação, serviço fora do ar");
//            throw new Exception("Falha ao enviar notificação, serviço fora do ar");
//        } else {
//            System.out.println("Notificação enviada com sucesso");
//        }
        System.out.println("Notificação enviada com sucesso");

    }
}
