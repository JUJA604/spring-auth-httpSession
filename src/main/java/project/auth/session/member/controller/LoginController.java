package project.auth.session.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.auth.session.member.domain.Member;
import project.auth.session.member.service.MemberService;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(
        @RequestParam(name = "redirectURL", defaultValue = "/") String redirectURL,
        Model model
    ) {
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("redirectURL", redirectURL);
        return "login";
    }

    @GetMapping("/login/check")
    public String loginCheck() {
        return "loginCheck";
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("loginForm") LoginForm form,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        // 1. 유효성 검사
        if (bindingResult.hasErrors()) {
            return "login";
        }

        // 2. 회원정보 일치 검사
        Member loginMember = memberService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.rejectValue("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        String redirectURL = request.getParameter("redirectURL");

        return "redirect:" + (redirectURL != null ? redirectURL : "/");
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
