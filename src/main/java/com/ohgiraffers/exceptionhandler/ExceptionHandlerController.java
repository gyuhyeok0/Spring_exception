package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    /*
    메서드 내부에서 String str 변수를 선언하고 null로 초기화합니다.
    그 다음, System.out.println(str.charAt(0));을 통해 str의 첫 번째 문자를 출력하려고 시도합니다.
    그러나 str이 null이기 때문에 NullPointerException이 발생합니다.
     */


    // 에러 상황 정의
    @GetMapping("controller-null")
    public String nullPointerExceptionTest() {

        String str = null;
        System.out.println(str.charAt(0));


        return "/";
    }

    // 예외처리 어노테이션
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException exception) {

        System.out.println("controller 레벨의 exception 처리");

        return "error/nullPointer";
    }

    // 예외 상황 정의
    @GetMapping("controller-user")
    public String userException() throws MemberRegistException {

        boolean check = true;

        if(check) {
            throw new MemberRegistException("나는 당신 같은 회원 둔 적 없어.");
        }

        // 처리 위임 해서 자동 비활성화
        return "/";
    }

    // 예외 처리
    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionTest(MemberRegistException exception, Model model) {

        System.out.println("controller 레벨의 exception 처리");
        model.addAttribute("exception", exception);

        return "error/memberRegist";

    }

}
