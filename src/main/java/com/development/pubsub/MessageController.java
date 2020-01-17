package com.development.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author suresha
 * Created on 2020-01-15
 */
@RestController
public class MessageController {

    @Autowired
    private MyGcpPubApplication.pubsubOutboundGateway messagingGateway;

    @PostMapping("/postMessage")
    public RedirectView publishMessage(@RequestParam("message") String message) {
        messagingGateway.sendToPubsub(message);
        return new RedirectView("/");
    }

}
