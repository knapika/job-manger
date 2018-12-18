package com.example.javaserver.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostingsDTO {
    private List<PostingDTO> postings;
    private PostingDTO posting;

    public PostingDTO getPosting() {
        return posting;
    }

    public void setPosting(PostingDTO posting) {
        this.posting = posting;
    }

    public List<PostingDTO> getPostings() {
        return postings;
    }

    public void setPostings(List<PostingDTO> postings) {
        this.postings = postings;
    }
}
