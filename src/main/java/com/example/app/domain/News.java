package com.example.app.domain;

import java.util.Date;

import lombok.Data;

@Data
public class News {

	private Integer id;
	private String title;
	private String author;
	private Date postDate;
	private NewsDetail detail;

}
