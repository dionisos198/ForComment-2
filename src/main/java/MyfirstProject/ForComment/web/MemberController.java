package MyfirstProject.ForComment.web;

import MyfirstProject.ForComment.Domain.Member;
import MyfirstProject.ForComment.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.SQLOutput;
import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
       return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){
        if(result.hasErrors()){
            return "members/createMemberForm";
        }
        Member member=new Member();
        member.setID(form.getID());
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }
    @GetMapping(value="/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
    @GetMapping(value = "/members/{memberId}/edit")
    public String updateMemberForm(@PathVariable("memberId") String memberId, Model
            model) {
        Member member = (Member) memberService.findOne(memberId);
        MemberForm form = new MemberForm();
        form.setID(member.getID());
        form.setName(member.getName());
        form.setPassword(member.getPassword());
        model.addAttribute("form", form);
        return "members/updateMemberForm";
    }
    @PostMapping(value = "/members/{memberId}/edit")
    public String updateMember(@ModelAttribute("form") MemberForm form) {
        memberService.updateMember(form.getID(),form.getName(),form.getPassword());
        return "redirect:/members";
    }
}

