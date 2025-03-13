package com.example.figma1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp





@Composable
fun BottomBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val darkBlue = Color(0xFF002731 )

    Column {

        Box(modifier = Modifier.weight(1f)) {
            when (selectedIndex) {
                0 -> HomeScreen()
                1 -> TokensScreen()
                2 -> SwapScreen()
                3 -> BrowserScreen()
                4 -> AssetScreen()
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(darkBlue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White)
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomNavItem(
                        iconResId = R.drawable.home,
                        label = "Home",
                        isSelected = selectedIndex == 0,
                        onClick = { selectedIndex = 0 }
                    )
                    BottomNavItem(
                        iconResId = R.drawable.token,
                        label = "Tokens",
                        isSelected = selectedIndex == 1,
                        onClick = { selectedIndex = 1 }
                    )
                    BottomNavItem(
                        iconResId = R.drawable.swap,
                        label = "Swap",
                        isSelected = selectedIndex == 2,
                        onClick = { selectedIndex = 2 }
                    )
                    BottomNavItem(
                        iconResId = R.drawable.browser,
                        label = "Browser",
                        isSelected = selectedIndex == 3,
                        onClick = { selectedIndex = 3 }
                    )
                    BottomNavItem(
                        iconResId = R.drawable.asset,
                        label = "Asset",
                        isSelected = selectedIndex == 4,
                        onClick = { selectedIndex = 4 }
                    )
                }
            }


        }
    }
}

@Composable
private fun BottomNavItem(
    iconResId: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            modifier = Modifier.size(21.dp)
        )
        Text(
            text = label,
            color = if (isSelected) Color.Black else Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}