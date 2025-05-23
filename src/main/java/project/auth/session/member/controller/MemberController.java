package project.auth.session.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.auth.session.annotation.Login;
import project.auth.session.member.domain.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/info")
    public String memberInfo(@Login Member member, Model model) {
        if (member == null) {
            return "redirect:/login";
        }

        model.addAttribute("member", member);
        return "memberInfo";
    }
}
