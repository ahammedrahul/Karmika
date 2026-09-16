package com.example.karmika.feature.customer.home.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

data class BannerItemData(
    val id: String,
    val titleLine1: String,
    val titleLine2: String,
    val subtitle: String,
    val buttonText: String = "Explore",
    val imageResId: Int = R.drawable.banner
)

val defaultBannerItems = listOf(
    BannerItemData(
        id = "b1",
        titleLine1 = "Home Services",
        titleLine2 = "Made Easy",
        subtitle = "Trusted professionals\nat your doorstep.",
        imageResId = R.drawable.banner
    ),
    BannerItemData(
        id = "b2",
        titleLine1 = "Special Discounts",
        titleLine2 = "Up to 30% Off",
        subtitle = "Book expert AC repair &\ncleaning today.",
        imageResId = R.drawable.banner
    ),
    BannerItemData(
        id = "b3",
        titleLine1 = "Quick Fixes",
        titleLine2 = "In 30 Mins",
        subtitle = "Plumbing & Electricals\nsaved in a flash.",
        imageResId = R.drawable.banner
    )
)

@Composable
fun HomeBanner(
    banners: List<BannerItemData> = defaultBannerItems,
    autoScrollDelayMillis: Long = 3500L,
    onExploreClick: (BannerItemData) -> Unit = {}
) {
    if (banners.isEmpty()) return

    // Infinite page setup
    val infinitePageCount = Int.MAX_VALUE
    val initialPage = (infinitePageCount / 2) - ((infinitePageCount / 2) % banners.size)
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { infinitePageCount }
    )

    // Auto-sliding Logic
    LaunchedEffect(pagerState) {
        while (true) {
            yield()
            delay(autoScrollDelayMillis)
            if (!pagerState.isScrollInProgress) {
                pagerState.animateScrollToPage(
                    page = pagerState.currentPage + 1,
                    animationSpec = tween(durationMillis = 600)
                )
            }
        }
    }

    val actualCurrentPage = pagerState.currentPage % banners.size

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) { page ->
            val item = banners[page % banners.size]
            BannerCardItem(
                item = item,
                onExploreClick = { onExploreClick(item) }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Pagination Dots
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(banners.size) { index ->
                val isSelected = index == actualCurrentPage
                Box(
                    modifier = Modifier
                        .size(if (isSelected) 8.dp else 6.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) Color(0xFF0F5A47) else Color(0xFFCBD5E1)
                        )
                )
            }
        }
    }
}

@Composable
private fun BannerCardItem(
    item: BannerItemData,
    onExploreClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
    ) {
        /* Full-coverage Background Image */
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        /* Content Overlay */
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 110.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            /* Left/Center Side: Shifted clear of the left leaf illustration */
            Column(
                modifier = Modifier
                    .weight(1.1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = item.titleLine1,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0F172A),
                    lineHeight = 18.sp
                )
                Text(
                    text = item.titleLine2,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0F172A),
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.subtitle,
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1E293B),
                    lineHeight = 13.sp
                )
            }

            /* Right Side: Badge + Button */
            Column(
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                /* "Your Home Our Priority" Badge */
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White.copy(alpha = 0.90f))
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Your Home",
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Cursive,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F5A47)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Icon(
                                imageVector = Icons.Outlined.Home,
                                contentDescription = null,
                                tint = Color(0xFF0F5A47),
                                modifier = Modifier.size(12.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(1.dp))

                        Text(
                            text = "Our Priority",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F5A47)
                        )
                    }
                }

                /* Explore Services Button */
                Button(
                    onClick = onExploreClick,
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0F5A47),
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
                    modifier = Modifier.height(28.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item.buttonText,
                            fontSize = 9.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(11.dp)
                        )
                    }
                }
            }
        }
    }
}