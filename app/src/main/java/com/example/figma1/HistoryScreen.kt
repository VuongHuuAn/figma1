package com.example.figma1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        HistoryTopBar()

        // Tabs
        TabSection(selectedTab) { selectedTab = it }

        // Content based on selected tab
        when (selectedTab) {
            0 -> TokensContent()
            1 -> NFTContent()
        }
    }
}

@Composable
private fun TabSection(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Tokens",
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 8.dp)
                    .clickable { onTabSelected(0) },
                color = if (selectedTab == 0) Color.Black else Color.Gray,
                fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center
            )
            Text(
                text = "NFT",
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 8.dp)
                    .clickable { onTabSelected(1) },
                color = if (selectedTab == 1) Color.Black else Color.Gray,
                fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }

        // Indicator
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0xFFEEEEEE))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(2.dp)
                    .background(Color(0xFF4CAF50))
                    .align(if (selectedTab == 0) Alignment.CenterStart else Alignment.CenterEnd)
            )
        }
    }
}

@Composable
private fun TokensContent() {
    Column {
        FilterSection()
        TransactionList()
    }
}

@Composable
private fun NFTContent() {
    Column {
        // Filter section cho NFT
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip("All Time")
            FilterChip("All Type")
        }

        // NFT Transaction List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item { NFTTransactionItem("Send", "Egao #22", "12 December 2024, 15:40", "SUCCESS") }
            item { NFTTransactionItem("Send", "Azuki #60", "12 December 2024, 15:40", "SUCCESS") }
        }
    }
}

@Composable
private fun NFTTransactionItem(
    type: String,
    nftName: String,
    date: String,
    status: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE5E5E5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = date,
                    color = Color(0xFF737373),
                    fontSize = 14.sp,
                    lineHeight = 16.8.sp,
                    letterSpacing = 0.14.sp,
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = status,
                    color = Color(0xFF4CAF50),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0x1F4CAF50))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(1.dp)
                    .background(Color(0xFFE5E5E5))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = type,
                        color = Color(0xFF698C97),
                        fontSize = 16.sp,
                        lineHeight = 19.2.sp,
                        letterSpacing = 0.16.sp,
                        fontWeight = FontWeight(500)
                    )
                    Text(
                        text = nftName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE5E5E5))
                )
            }
        }
    }
}

@Composable
private fun HistoryTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "History",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        // Empty box for alignment
        Box(modifier = Modifier.size(24.dp))
    }
}

@Composable
private fun FilterSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 20.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterChip("All Time")
        FilterChip("All Assets")
        FilterChip("All Type")
    }
}

@Composable
private fun FilterChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(
                width = 0.5.dp,
                color = Color(0xFF002731),
                shape = RoundedCornerShape(20.dp)

            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = text,
                color = Color(0xFF002731),
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                lineHeight = 16.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color(0xFF002731)
            )
        }
    }
}
@Composable
private fun TransactionList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item { TransactionItem("Swap", "SOL", "- ETH 0,00047", "SUCCESS", "12 December 2024, 15:40") }
        item { TransactionItem("Swap", "ETH", "- ETH 0,00047", "PENDING", "12 December 2024, 15:40") }
        item { TransactionItem("Send", "ETH", "- ETH 0,00029", "FAILED", "12 December 2024, 15:40") }
        item { TransactionItem("Receive", "BNB", "+ BNB 1,00234", "SUCCESS", "12 December 2024, 15:40") }
    }
}

@Composable
private fun TransactionItem(
    type: String,
    coin: String,
    amount: String,
    status: String,
    date: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                when (status) {
                    "SUCCESS" -> Color(0xFFDBF6CD)
                    "PENDING" -> Color(0xFFFEF9C3)
                    else -> Color(0xFFFEE2E2)
                }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
        ) {
            // Phần trên với date và status
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = date,
                    color = Color(0xFF737373),
                    fontSize = 14.sp,
                    lineHeight = 16.8.sp,
                    letterSpacing = 0.14.sp,
                    fontWeight = FontWeight(500)
                )

                Text(
                    text = status,
                    color = when(status) {
                        "SUCCESS" -> Color(0xFF0DA750)
                        "PENDING" -> Color(0xFFCABA04)
                        else -> Color(0xFFEF4444)
                    },
                    fontSize = 12.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            when (status) {
                                "SUCCESS" -> Color(0xFFDBF6CD)
                                "PENDING" -> Color(0xFFFEF9C3)
                                else -> Color(0xFFFEE2E2)
                            }
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(1.dp)
                    .background(Color(0xFFE5E5E5))
            )

            // Phần dưới với type, coin và amount
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 20.dp, top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (type == "Receive") {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color.Black)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(9.33.dp)
                                    .clip(CircleShape)
                                    .background(Color.Black)
                                    .border(
                                        width = 0.42.dp,
                                        color = Color(0xFFFFFFFF),
                                        shape = CircleShape
                                    )
                                    .align(Alignment.BottomEnd)
                                    .offset(x = 4.dp, y = 4.dp)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.padding(
                            start = if (type != "Receive") 40.dp else 0.dp
                        )
                    ) {
                        Text(
                            text = type,
                            color = Color(0xFF698C97),
                            fontSize = 16.sp,
                            lineHeight = 19.2.sp,
                            letterSpacing = 0.16.sp,
                            fontWeight = FontWeight(500)
                        )
                        Text(
                            text = coin,
                            color = Color(0xFF1E293B),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Text(
                    text = amount,
                    color = Color(0xFF1E293B),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}



