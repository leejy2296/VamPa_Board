package com.vam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.model.BoardVO;
import com.vam.model.Criteria;
import com.vam.model.PageMakerDTO;
import com.vam.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService bservice;

//    /* 게시글 목록 화면 */
//    @GetMapping("/list")
//    // => @RequestMapping(value="list", method=RequestMethod.GET)
//    public void boardListGET(Model model) {
//        log.info("게시판 목록 페이지 진입");
//        model.addAttribute("list", bservice.getList());
//    }

    /* 게시판 목록 페이지 접속(페이징 적용) */
    @GetMapping("/list")
    public void boardListGET(Model model, Criteria cri) {

        log.info("boardListGET");
        model.addAttribute("list", bservice.getListPaging(cri));
        int total = bservice.getTotal();
        PageMakerDTO pageMake = new PageMakerDTO(cri, total);
        model.addAttribute("pageMaker", pageMake);

    }

    /* 게시글 등록 화면 */
    @GetMapping("/enroll")
    // => @RequestMapping(value="enroll", method=RequestMethod.GET)
    public void boardEnrollGET() {

        log.info("게시판 등록 페이지 진입");

    }

    /* 게시글 등록 처리 */
    @PostMapping("/enroll")
    public String boardEnrollPOST(BoardVO board, RedirectAttributes rttr) {

        log.info("BoardVO : " + board);
        bservice.enroll(board);
        rttr.addFlashAttribute("result", "등록성공");
        return "redirect:/board/list";
    }

    /* 게시글 상세 조회 */
    @GetMapping("/get")
    public void boardGetDetailPOST(int bno, Model model) {

        model.addAttribute("pageInfo", bservice.getPage(bno));

    }

    /* 수정 페이지 이동 */
    @GetMapping("/modify")
    public void boardModifyGET(int bno, Model model) {

        model.addAttribute("pageInfo", bservice.getPage(bno));

    }

    /* 페이지 수정 */
    @PostMapping("/modify")
    public String boardModifyPOST(BoardVO board, RedirectAttributes rttr) {

        bservice.modify(board);
        rttr.addFlashAttribute("result", "수정성공");
        return "redirect:/board/list";

    }

    /* 페이지 삭제 */
    @PostMapping("/delete")
    public String boardDeletePOST(int bno, RedirectAttributes rttr) {

        bservice.delete(bno);
        rttr.addFlashAttribute("result", "삭제성공");
        return "redirect:/board/list";
    }

}
