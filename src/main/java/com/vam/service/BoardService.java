package com.vam.service;

import java.util.List;

import com.vam.model.BoardVO;
import com.vam.model.Criteria;

public interface BoardService {
    /* 게시글 등록 */
    public void enroll(BoardVO board);

    /* 게시글 목록 */
    public List<BoardVO> getList();

    /* 게시글 목록(페이징 적용) */
    public List<BoardVO> getListPaging(Criteria cri);

    /* 게시글 수정 */
    public int modify(BoardVO board);

    /* 게시글 조회 */
    public BoardVO getPage(int bno);

    /* 게시글 삭제 */
    public int delete(int bno);

    /* 게시물 총 갯수 */
    public int getTotal();
}
