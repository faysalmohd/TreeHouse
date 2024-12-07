package com.example.companylogin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class NewsClass(
    val status: String,
    val totalResults: Int,
    val results: List<Article>
)

data class Article(
    val title: String,
    val description: String,
    val pubDate: String,
    val source_name: String
)

//data class Article(
//    val articleId: String,
//    val title: String,
//    val link: String,
//    val keywords: String,
//    val creator: String,
//    val videoUrl: String,
//    val description: String,
//    val pubDate: String,
//    val pubDateTZ: String,
//    val imgUrl: String,
//    val sourceId: String,
//    val sourcePriority: String,
//    val sourceName: String,
//    val sourceUrl: String,
//    val sourceIcon: String,
//    val language: String,
//    val country: List<String>,
//    val category: List<String>,
//    val aiTag: String,
//    val sentiment: String,
//    val sentimentState: String,
//    val aiRegion: String,
//    val aiOrg: String,
//    val duplicate: String
//)

private const val BASE_URL = "https://newsdata.io/api/1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {
    @GET("news")
    suspend fun getNews(
        @Query("apikey") apiKey: String,
        @Query("q") query: String
    ): NewsClass
}

object NewsApi {
    val retrofitService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}
