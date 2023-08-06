package com.assessment.Assessment.response;

import lombok.Data;



@Data
public class PaginatedCommentsResponse {

//    private List<User> users;

    private int currentPage;

    private int noOfUsers;

    private int noOfActivated;

    private int noOfDeactivated;

    private int noOfTotalPages;

    private int pageSize;

}

