package com.lambdaschool.projectrestdogs.Services;


import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.models.MessageDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener
{
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = ProjectrestdogsApplication.QUEUE_NAME_HIGH)
    public void receiveMessage(MessageDetail msg)
    {
        logger.info("Received Message {}", msg.toString());
    }

    @RabbitListener(queues = ProjectrestdogsApplication.QUEUE_NAME_LOW)
    public void receiveLowMessage(MessageDetail msg)
    {
        logger.info("Received Message {}", msg.toString());
    }
}
