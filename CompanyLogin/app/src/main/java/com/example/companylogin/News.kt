package com.example.companylogin

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.companylogin.network.Article
import com.example.companylogin.network.NewsApi
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun News() {
    var articles by remember { mutableStateOf<List<Article>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        try {
            val response = NewsApi.retrofitService.getNews(
                "pub_6154257d93c85abc98cced0669be882e70550",
                "finland"
            )
            articles = response.results
        } catch (e: Exception) {
            println("Error fetching news: ${e.message}")
        } finally {
            isLoading = false
        }
    }

    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    } else {
        NewsList(articles = articles)
    }
}


@Composable
fun NewsList(articles: List<Article>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 110.dp)
    ) {
        items(articles) { article ->
            NewsItem(
                title = article.title ?: "No title",
                description = article.description ?: "No description",
                pubDate = article.pubDate ?: "No date",
                sourceName = article.source_name ?: "No source name"
            )
        }
    }
}


@Composable
fun NewsItem(
    title: String,
    description: String,
    pubDate: String,
    sourceName: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 5.dp, end = 16.dp, bottom = 5.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight(weight = 600)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = "Published: $pubDate | Source: $sourceName",
            color = Color.Gray,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 8.dp)
        )
        Divider(color = Color.Gray, thickness = 0.5.dp, modifier = Modifier.padding(top = 10.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewItem() {
    NewsItem(
        title = "Breaking News: Kotlin is the future of Android development!",
        description = "Kotlin continues to grow in popularity and is now the official language for Android development. Here’s why you should learn it.",
        pubDate = "2024-12-06",
        sourceName = "Tech News Daily"
    )
}

