package com.eternal.web.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.eternal.web.domain.model.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TalkThemeDetailResponse {

	private BigDecimal id;

	private String title;

	private String description;

	private String thumbnailUrl;

	private BigDecimal goodCount;

	private BigDecimal badCount;

	private BigDecimal talkedCount;

	private List<Category> relatedCategoryList;

	private String postedUser;

	private LocalDateTime postedDateTime;
}
