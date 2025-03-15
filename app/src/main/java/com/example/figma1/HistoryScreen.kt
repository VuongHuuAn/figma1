package com.example.figma1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.figma1.ui.theme.DarkerGrotesque


@Composable
fun HistoryScreen(
    onBackClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        HistoryTopBar(onBackClick = onBackClick)

        // Tabs
        TabSection(selectedTab=selectedTab, onChangeSelected = { selectedTab = it})

        // Content based on selected tab
        when (selectedTab) {
            0 -> TokensContent()
            1 -> NFTContent()
        }
    }
}
// Tab Tokens & NFT
data class TabItem(val title: String, val value: String)
@Composable
private fun TabSection(onChangeSelected: (Int)->Unit,selectedTab: Int) {
    val tabs = listOf<TabItem>(
        TabItem("Tokens", "tokens"),
        TabItem("NFT", "nft")
    )
    Column {
        // Tab headers
        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            tabs.forEachIndexed { index, tab ->
                Text(
                    text = tab.title,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onChangeSelected(index) }
                        .padding(bottom = 8.dp),

                    color = if (selectedTab == index) Color.Black else Color.Gray,
                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }
        }
        // Indicator (thanh dieu huong)
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
                    .background(Color(0xFFA5E983))
                    .align(if (selectedTab == 0) Alignment.CenterStart else Alignment.CenterEnd)
            )
        }
    }
}

//NFT Content (FilterChip,NFT Transaction Item)
@Composable
private fun NFTContent() {
    var selectedFilter by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Filter section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                text = "All Time",
                selected = selectedFilter == "All Time",
                onSelected = { selectedFilter = "All Time" }
            )
            FilterChip(
                text = "All Type",
                selected = selectedFilter == "All Type",
                onSelected = { selectedFilter = "All Type" }
            )
        }

        // NFT Transaction List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            item { NFTTransactionItem("Send", "Egao #22", "12 December 2024, 15:40", "SUCCESS") }
            item { NFTTransactionItem("Send", "Azuki #60", "12 December 2024, 15:40", "SUCCESS") }
        }
    }
}
// UI NFT Transaction Item (NFT Screen)
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
                        lineHeight = (16 * 1.2).sp,
                        letterSpacing = (16 * 0.01).sp,
                        fontFamily = DarkerGrotesque,
                        fontWeight = FontWeight(100),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
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
                        .background(Color(0xFFBABABA))
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .offset(x = 38.dp, y = 38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFBABABA))
                            .border(
                                width = 0.5.dp,
                                color = Color(0xFFFFFFFF),
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}
// UI History Top Bar Header
@Composable
private fun HistoryTopBar(
    onBackClick: () -> Unit
) {
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
            modifier = Modifier
                .size(24.dp)
                .clickable { onBackClick() },
            tint = Color.Black
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

// Tokens Content (FilterSection, TransactionList)
@Composable
private fun TokensContent() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        FilterSection() // Filter section cho Token
        TransactionList() // Danh sách giao dịch
    }
}

// Filter Section của Tokens Screen
@Composable
private fun FilterSection() {
    var selectedFilter by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterChip(
            text = "All Time",
            selected = selectedFilter == "All Time",
            onSelected = { selectedFilter = "All Time" }
        )
        FilterChip(
            text = "All Assets",
            selected = selectedFilter == "All Assets",
            onSelected = { selectedFilter = "All Assets" }
        )
        FilterChip(
            text = "All Type",
            selected = selectedFilter == "All Type",
            onSelected = { selectedFilter = "All Type" }
        )
    }
}
// UI Filter Chip của Filter Section (Tokens Screen)
@Composable
private fun FilterChip(
    text: String,
    selected: Boolean = false,
    onSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(
                width = 0.5.dp,
                color = if (selected) Color.Black else Color(0xFFE5E5E5),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onSelected() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = text,
                color = if (selected) Color.Black else Color(0xFF002731),
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                lineHeight = 16.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = if (selected) Color.Black else Color(0xFF002731)
            )
        }
    }
}
// Transaction List của Tokens Screen
@Composable
private fun TransactionList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        item { TransactionItem("Swap", "SOL", "- ETH 0,00047", "SUCCESS", "12 December 2024, 15:40") }
        item { TransactionItem("Swap", "ETH", "- ETH 0,00047", "PENDING", "12 December 2024, 15:40") }
        item { TransactionItem("Send", "ETH", "- ETH 0,00029", "FAILED", "12 December 2024, 15:40") }
        item { TransactionItem("Receive", "BNB", "+ BNB 1,00234", "SUCCESS", "12 December 2024, 15:40") }
    }
}
// UI Transaction Item của Transaction List (Tokens Screen)
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
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(24.dp)
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
                            modifier = Modifier.padding(end = 4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(Color.Black)
                            )

                            Box(
                                modifier = Modifier
                                    .size(9.33.dp)
                                    .clip(CircleShape)
                                    .background(Color.Black)
                                    .border(
                                        width = 0.2.dp,
                                        color = Color.White,
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
                            lineHeight = (16 * 1.2).sp,
                            letterSpacing = (16 * 0.01).sp,
                            fontFamily = DarkerGrotesque,
                            fontWeight = FontWeight(500),
                            textAlign = TextAlign.Start,
                            modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
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

@Preview(showBackground = true,backgroundColor = 0xFF002731)
@Composable
fun PreviewButton() {
    Button(onClick = {}) {
        Text(text = "Button")
    }
}





