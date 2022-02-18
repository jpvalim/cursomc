package com.jpv.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.jpv.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);

}
