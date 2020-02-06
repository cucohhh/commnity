package com.life.demo.Dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO {
    private List<QuestionDTO> question;
    private boolean showPreious;
    private boolean showFirstPage;
    private boolean showLast;
    private boolean showLastPage;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public List<QuestionDTO> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionDTO> question) {
        this.question = question;
    }

    public boolean isShowPreious() {
        return showPreious;
    }

    public void setShowPreious(boolean showPreious) {
        this.showPreious = showPreious;
    }

    public boolean isShowFirstPage() {
        return showFirstPage;
    }

    public void setShowFirstPage(boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShowLast() {
        return showLast;
    }

    public void setShowLast(boolean showLast) {
        this.showLast = showLast;
    }

    public boolean isShowLastPage() {
        return showLastPage;
    }

    public void setShowLastPage(boolean showLastPage) {
        this.showLastPage = showLastPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPagination(Integer totalCount, Integer page, Integer size) {



        if(totalCount / size ==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount /size +1;
        }

        if(page<1){
            page =1;
        }
        if(page>totalPage){
            page = totalPage;
        }
        this.page = page;
        pages.add(page);
        for (int i = 1; i <=3 ; i++) {
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i <= totalPage){
                pages.add(page+i);
            }

        }


        //when  showPreious
        if(page ==1){
            showPreious=false;
        }else{
            showPreious=true;
        }
        //when  showLast
        if(page == totalPage){
            showLast=false;
        }else {
            showLast = true;
        }

        //when showFirstPage
        if(pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }

        //when showLastPage
        if(pages.contains(totalPage)){
            showLastPage = false;
        }else{
            showLastPage = true;
        }
    }
}
