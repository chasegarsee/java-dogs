package com.lambdaschool.projectrestdogs.Services;


import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.models.MessageDetail;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSender
{
    private RabbitTemplate rt;

    public MessageSender(RabbitTemplate rt)
    {
        this.rt = rt;
    }

    public void sendMessage(String m)
    {
        MessageDetail message = new MessageDetail(m);
        rt.convertAndSend(ProjectrestdogsApplication.QUEUE_NAME_HIGH, message);
    }

}
