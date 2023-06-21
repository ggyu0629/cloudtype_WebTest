package com.example.smartdumbbell_web.Login.Controller;

import com.example.smartdumbbell_web.Login.DTO.SignUpDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Controller
public class SignUpController {

    private WebClient webClient;

    public SignUpController(){
        this.webClient = WebClient.create();
    }

    @PostMapping("/SignUp")
    public String SignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");

        if (!password.equals(confirm)) {
            return "redirect:/SignUp?error=passwordMismatch";
        }

        SignUpDTO signUpDTO = new SignUpDTO();

        signUpDTO.setId(request.getParameter("id"));
        signUpDTO.setPassword(request.getParameter("password"));
        signUpDTO.setName(request.getParameter("name"));
        signUpDTO.setRole(request.getParameter("role"));
        signUpDTO.setInstitution(request.getParameter("institution"));

        String SignUpURL = "http://localhost:8080/WebRegister/SignUp";

        boolean res = Boolean.TRUE.equals(webClient.method(HttpMethod.POST)
                .uri(SignUpURL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(signUpDTO))
                .retrieve()
                .bodyToMono(boolean.class)
                .block());

        if(res)
            return "redirect:/login";

        return "redirect:/SignUp?error=existing";

    }
}
