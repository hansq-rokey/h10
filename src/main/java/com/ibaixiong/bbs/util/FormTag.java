package com.ibaixiong.bbs.util;

public class FormTag {
	public static enum tag {
		BBS {
			/**
			 * 社区
			 */
			@Override
			public String getTag() {
				return "bbs";
			}
		},
		PRODUCT {
			/**
			 * 社区讨论
			 */
			@Override
			public String getTag() {
				return "product";
			}
		},
		SCHOOL {
			/**
			 * 白熊学院
			 */
			@Override
			public String getTag() {
				return "school";
			}
		},
		ACTIVITY {
			/**
			 * 活动帖子
			 */
			@Override
			public String getTag() {
				return "activity";
			}
		};
		public abstract String getTag();
	}
}
