package org.serratec.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.serratec.exception.AgendamentoException;
import org.serratec.exception.EmailException;
import org.serratec.model.Agendamento;
import org.serratec.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Value("${spring.mail.username}")
	private String userName;
	
	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.email.remetente}")
	private String emailRemetente;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public JavaMailSender javaMailSender() {
		
		JavaMailSenderImpl enviarEmail = new JavaMailSenderImpl();
		Properties prop = new Properties();
		
		enviarEmail.setHost(host);
		enviarEmail.setPort(465);
		enviarEmail.setUsername(userName);
		enviarEmail.setPassword(password);
		enviarEmail.setProtocol("smtp");
		enviarEmail.setDefaultEncoding("UTF-8");
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.ssl.enable", true);
		enviarEmail.setJavaMailProperties(prop);

		return enviarEmail;
		}
	
	public void emailConfirmarConsulta(Agendamento agendamento) 
			throws EmailException, MessagingException, AgendamentoException {

        this.emailSender = javaMailSender();
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
        String destinatario = agendamento.getUsuario().getEmail();
			
        try {
        	
            helper.setFrom(emailRemetente);
            helper.setTo(destinatario);

            helper.setSubject("Confirma????o de consulta OdontoHataba");

            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("<html>\r\n"
                            +"<body>\r\n"
                            +"<h1>Consult??rio odontol??gico Hataba</h1>"
                            +"<div>\r\nOl?? " + agendamento.getUsuario().getNome().toUpperCase() + ",\r\n</div>"
                            +"<div>\r\n Voc?? tem uma consulta marcada com Dr(a) " + agendamento.getUsuario().getNome() + "\n</div>"
                            +"<div>No dia " + agendamento.getDia() + " as " + agendamento.getHora() + " horas.(amanh??)</div>"
                            +"<div>Clique no link para confirmar ou cancelar a consulta.</div>"
                            +"<div>Link</div>"
                            +"</body>"
                            +"</html>");
            helper.setText(sBuilder.toString(), true);
            emailSender.send(message);

        } catch (Exception e) {
        	throw new EmailException ("Houve erro ao enviar o email " + e.getMessage());
		}
	}
	
	public void emailPosConsulta(Agendamento agendamento) 
			throws EmailException, MessagingException, AgendamentoException {
		
		this.emailSender = javaMailSender();
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		String destinatario = agendamento.getUsuario().getEmail();
		
		try {
			
			helper.setFrom(emailRemetente);
			helper.setTo(destinatario);
			
			helper.setSubject("Agradecimento p??s consulta OdontoHataba");
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("<html>\r\n"
					+"<body>\r\n"
					+"<h1>Consult??rio odontol??gico Hataba</h1>"
					+"<div>\r\nOl?? " + agendamento.getUsuario().getNome() + ".\r\n</div>"
					+ "<div>Estou muito feliz em poder atender suas necessidades e pela oportunidade de faz??-lo satisfeito. Sua confian??a ?? important??ssima para mim e, por isso, agrade??o profundamente.\r\n"
					+ "\r\n</div>"
					+ "<div>Saiba que a minha miss??o ?? sempre corresponder com as suas expectativas atrav??s de um trabalho cuidadoso e de qualidade.\r\n"
					+ "\r\n</div>"
					+ "<div>Conte sempre com os meus servi??os e n??o hesite em me procurar para qualquer coisa. Muito obrigado, usuario e parceiro.</div>"
					+"</body>"
					+"</html>");
			helper.setText(sBuilder.toString(), true);
			emailSender.send(message);
			
		} catch (Exception e) {
			throw new EmailException ("Houve erro ao enviar o email " + e.getMessage());
		}
	}
}
