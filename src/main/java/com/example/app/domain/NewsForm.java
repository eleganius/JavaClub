package com.example.app.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NewsForm {

	//News
	@NotBlank
	private String title;
	private String author;
	private Date postDate = new Date();

	//NewsDetail
	@NotBlank
	private String article;

}
