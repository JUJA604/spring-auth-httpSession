package project.auth.session.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.auth.session.member.domain.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/info")
    public String memberInfo(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "redirect:/login";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(loginMember == null) {
            return "redirect:/login";
        }

        model.addAttribute("member", loginMember);
        return "memberInfo";
    }
}
