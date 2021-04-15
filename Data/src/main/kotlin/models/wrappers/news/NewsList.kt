package models.wrappers.news

import com.google.gson.annotations.SerializedName
import models.core.news.News

class NewsList(@SerializedName("news")val list: List<News>) {
}