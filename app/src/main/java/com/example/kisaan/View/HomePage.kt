package com.example.kisaan.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kisaan.BackgroundCream
import com.example.kisaan.Brown
import com.example.kisaan.DarkGreen
import com.example.kisaan.LightGreen
import com.example.kisaan.Model.NavItems
import com.example.kisaan.PrimaryGreen
import com.example.kisaan.R
import com.example.kisaan.ViewModel.AuthViewModel


data class Feature(
    val icon: ImageVector,
    val titleEn: String,
    val titleHi: String,
    val color: Color
)

@Composable
fun HomePage(modifier: Modifier = Modifier,navController: NavController,authViewModel: AuthViewModel) {
    var isHindi by remember { mutableStateOf(false) }

    var selectedBottomNavItem by remember { mutableStateOf(0) }

    val bottomNavItems = listOf(
        "Home" to "होम",
        "Profile" to "प्रोफ़ाइल",
        "Notifications" to "सूचनाएं",
        "Settings" to "सेटिंग्स"
    )
    val bottomNavIcons = listOf(
        Icons.Default.Home,
        Icons.Default.Person,
        Icons.Default.Notifications,
        Icons.Default.Settings
    )

    MaterialTheme {
        Scaffold(
            topBar = {
                KisaanTopAppBar(
                    isHindi = isHindi,
                    onLanguageChange = { isHindi = !isHindi }
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = PrimaryGreen
                ) {
                    bottomNavItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(bottomNavIcons[index], contentDescription = if (isHindi) item.second else item.first) },
                            label = { Text(if (isHindi) item.second else item.first) },
                            selected = selectedBottomNavItem == index,
                            onClick = {
                                selectedBottomNavItem = index
                                if (selectedBottomNavItem == 3) authViewModel.signOut()
                                navController.navigate(NavItems.LoginOrSignupScreen.route){
                                    popUpTo(0){
                                        inclusive=true
                                    }
                                }

                                      },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DarkGreen,
                                unselectedIconColor = Color.Gray,
                                selectedTextColor = DarkGreen,
                                unselectedTextColor = Color.Gray,
                                indicatorColor = LightGreen
                            )
                        )
                    }
                }
            },
            containerColor = BackgroundCream
        ) { innerPadding ->
            HomeScreen(Modifier.padding(innerPadding), isHindi)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KisaanTopAppBar(isHindi: Boolean, onLanguageChange: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Kisaan AI Assistant",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            Button(
                onClick = onLanguageChange,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Build, contentDescription = "Change Language", tint = DarkGreen)
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = if (isHindi) "English" else "हिंदी",
                        color = DarkGreen,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryGreen
        )
    )
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier, isHindi: Boolean) {
    val features =
        listOf(
            Feature(ImageVector.vectorResource(R.drawable.baseline_camera_alt_24), "Scan Crop", "फ़सल स्कैन", PrimaryGreen),
            Feature(ImageVector.vectorResource(R.drawable.baseline_event_24), "Crop Calendar", "फ़सल कैलेंडर", Color(0xFF8BC34A)),
            Feature(ImageVector.vectorResource(R.drawable.baseline_terrain_24), "Soil Health", "मिट्टी का स्वास्थ्य", Brown),
            Feature(ImageVector.vectorResource(R.drawable.baseline_show_chart_24), "Market Prices", "बाजार मूल्य", Color(0xFF009688)),
            Feature(ImageVector.vectorResource(R.drawable.baseline_sunny_24), "Weather", "मौसम", Color(0xFF2196F3)),
            Feature(ImageVector.vectorResource(R.drawable.baseline_gavel_24), "Govt. Schemes", "सरकारी योजनाएं", Color(0xFFFF9800)),
            Feature(ImageVector.vectorResource(R.drawable.baseline_people_24), "Community", "समुदाय", Color(0xFF673AB7)),
            Feature(ImageVector.vectorResource(R.drawable.baseline_account_balance_wallet_24), "Expenses", "खर्च", Color(0xFFE91E63)),
            Feature(ImageVector.vectorResource(R.drawable.baseline_question_mark_24), "Ask Expert", "विशेषज्ञ से पूछें", Color(0xFF03A9F4))
        )


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Welcome message
        Text(
            text = if (isHindi) "नमस्ते रमेश जी!" else "Welcome Ramesh Ji!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = DarkGreen
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(features) { feature ->
                FeatureTile(feature = feature, isHindi = isHindi)
            }
        }
    }
}

@Composable
fun FeatureTile(feature: Feature, isHindi: Boolean) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable {  },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(feature.color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = feature.icon,
                    contentDescription = if (isHindi) feature.titleHi else feature.titleEn,
                    modifier = Modifier.size(36.dp),
                    tint = feature.color
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = if (isHindi) feature.titleHi else feature.titleEn,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        }
    }
}

