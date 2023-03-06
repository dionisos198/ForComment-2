package MyfirstProject.ForComment.web;

import MyfirstProject.ForComment.Domain.GoodStatus;

import javax.validation.constraints.NotEmpty;

public class ActivityForm {
    private Long ID;
    @NotEmpty(message="활동 이름을 입력해주세요")
    private String name;
    @NotEmpty(message="활동이 좋은 활동인지 안좋은 활동인지 입력해주세요")
    private String status;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
