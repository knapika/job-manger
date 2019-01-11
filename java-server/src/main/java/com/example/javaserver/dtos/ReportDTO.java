package com.example.javaserver.dtos;

public class ReportDTO<T>{
    private T data;
    private Integer status;

    public ReportDTO() {
    }

    public ReportDTO(T data, Integer status) {
        this.data = data;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
