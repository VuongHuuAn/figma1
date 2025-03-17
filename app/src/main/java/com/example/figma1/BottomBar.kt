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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val darkBlue = Color(0xFF002731)

    val currentRoute = navController
        .currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
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
                            isSelected = currentRoute == Screen.Home.route,
                            onClick = { navController.navigate(Screen.Home.route) }
                        )
                        BottomNavItem(
                            iconResId = R.drawable.token,
                            label = "Tokens",
                            isSelected = currentRoute == Screen.Tokens.route,
                            onClick = { navController.navigate(Screen.Tokens.route) }
                        )
                        BottomNavItem(
                            iconResId = R.drawable.swap,
                            label = "Swap",
                            isSelected = currentRoute == Screen.Swap.route,
                            onClick = { navController.navigate(Screen.Swap.route) }
                        )
                        BottomNavItem(
                            iconResId = R.drawable.browser,
                            label = "Browser",
                            isSelected = currentRoute == Screen.Browser.route,
                            onClick = { navController.navigate(Screen.Browser.route) }
                        )
                        BottomNavItem(
                            iconResId = R.drawable.asset,
                            label = "Asset",
                            isSelected = currentRoute == Screen.Asset.route,
                            onClick = { navController.navigate(Screen.Asset.route) }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            AppNavigation(navController = navController)
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
            modifier = Modifier.size(21.dp),
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                if (isSelected) Color.Black else Color(0xFF9AAAAF)
            )
        )
        Text(
            text = label,
            color = if (isSelected) Color(0xFF002731) else Color(0xFF9AAAAF),
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}