package com.david.MailTest.controller;

import com.david.MailTest.constants.ApiResponse;
import com.david.MailTest.constants.CustomMessages;
import com.david.MailTest.dto.RegisterRequest;
import com.david.MailTest.model.Mail;
import com.david.MailTest.model.User;
import com.david.MailTest.repository.UserRepository;
import com.david.MailTest.service.MailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Date;
import java.util.Optional;

@RestController
public class MailController {

    @Inject
    MailService mailService;

    @Inject
    UserRepository userRepo;

    private final String MAIL_FROM = "admin@safeside.tk";

    @ApiOperation("To Send Email")
    @PostMapping("/sendMail")
    public ResponseEntity sendEmail(@RequestParam("userId") Long userId, @RequestParam("message") String message){

        Optional<User> user = userRepo.findById(userId);
        String userEmail = user.get().getEmail();

        Mail mail = new Mail();
        mail.setMailFrom(MAIL_FROM);
        mail.setMailTo(userEmail);
        mail.setMailSubject("CactusTech");
        mail.setMailContent(message);

        mailService.sendEmail(mail);
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success));
    }

    @ApiOperation("To signup a user")
    @PostMapping("/signup")
    public ResponseEntity sendMail(RegisterRequest registerRequest) {

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setCreatedDate(new Date());

        userRepo.save(user);
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success));

    }
}
