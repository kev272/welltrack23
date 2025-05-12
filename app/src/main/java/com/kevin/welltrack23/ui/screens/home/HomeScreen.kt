package com.kevin.welltrack23.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.kevin.welltrack23.R
import com.kevin.welltrack23.navigation.ROUT_ABOUT
import com.kevin.welltrack23.navigation.ROUT_ADD_PRODUCT
import com.kevin.welltrack23.navigation.ROUT_HOME
import com.kevin.welltrack23.navigation.ROUT_LOGIN
import com.kevin.welltrack23.navigation.ROUT_PRODUCT_LIST
import com.kevin.welltrack23.navigation.ROUT_REGISTER
import com.kevin.welltrack23.navigation.ROUT_UPLOAD_TASK
import com.kevin.welltrack23.navigation.ROUT_VIEW_TASK
import com.kevin.welltrack23.repository.ContentRepository
import com.kevin.welltrack23.ui.theme.Blue10
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Home", fontSize = 20.sp) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White,
                        ),
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF101262), Color(0xFFEFF2F6))
                ),

            ),
//BottomBar
            bottomBar = {
                NavigationBar(
                    containerColor = Color.Blue
                ) {
                    NavigationBarItem(
                        icon = {
                            androidx.compose.material3.Icon(
                                Icons.Default.Home,
                                contentDescription = "Home",
                            )

                        },label = { Text("Home", color = Color.White) },

                        selected = selectedIndex == 0,
                        onClick = {
                            selectedIndex = 0
                            navController.navigate(ROUT_HOME)
                        }
                    )

                    NavigationBarItem(
                        icon = {
                            androidx.compose.material3.Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = "Info",

                                tint = Color.White
                            )
                        },label = { Text("Trainers", color = Color.White) },

                        selected = selectedIndex == 1,
                        onClick = {
                            selectedIndex = 1
                             navController.navigate(ROUT_ABOUT)
                        }
                    )


                }

            } ,   ){ paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                        ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.welltrack),
                    contentDescription = "home",
                    modifier = Modifier.size(300.dp).clip(shape = CircleShape)
                )


                Row (modifier = Modifier.horizontalScroll(rememberScrollState()).padding(start = 20.dp, end = 20.dp)) {
                    //Card1
                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(250.dp)
                            ,
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF57494C), Color(0xFF0072FF))
                                    ),

                                    ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Image(
                                painter = painterResource(R.drawable.img_14),
                                contentDescription = "home",
                                modifier = Modifier.fillMaxWidth().height(70.dp)
                            )
                            Text(text = "Keep your health", fontSize = 15.sp, textAlign = TextAlign.Center, color = Color.White)

                            Text(text = "Live longwith a healthy life", fontSize = 15.sp, textAlign = TextAlign.Center, color = Color.White)


                        }


                    }
                    //End of card1
                    Spacer(modifier = Modifier.width(20.dp))
                    //Card2
                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(250.dp)
                            ,
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF6C5B61), Color(0xFF0072FF))
                                    ),

                                    ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Image(
                                painter = painterResource(R.drawable.img_16),
                                contentDescription = "home",
                                modifier = Modifier.fillMaxWidth().height(70.dp),
                            )
                            Text(text = "Lose Weight,Build Cofidence", fontSize = 15.sp,textAlign =TextAlign.Center,color = Color.White)

                            Text(text = "Tread those streets like a pro", fontSize = 15.sp,textAlign = TextAlign.Center,color = Color.White)


                        }


                    }
                    //End of card2
                    Spacer(modifier = Modifier.width(20.dp))
                    //Card3
                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(250.dp)
                        ,
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF524648), Color(0xFF0C66D3))
                                    ),

                                    ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Image(
                                painter = painterResource(R.drawable.img_15),
                                contentDescription = "home",
                                modifier = Modifier.fillMaxWidth().height(70.dp),
                            )
                            Text(text = "DataBase", fontSize = 15.sp, textAlign = TextAlign.Center,color = Color.White)
                            Text(text = "Build Your Energy,Use it in the best way", fontSize = 15.sp, textAlign = TextAlign.Center,color = Color.White)


                        }


                    }
                    //End of card3




                }
                //End of Row


                }
            }
        }

        // **Overlay when drawer is open** (Clicking it closes the drawer)
        if (drawerState.isOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.3f)) // Semi-transparent overlay
                    .clickable { scope.launch { drawerState.close() } } // Closes on click
            )
        }

        // **Navigation Drawer**
        if (drawerState.isOpen) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(280.dp) // Adjust width for better appearance
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF595656), Color(0xFF0072FF))
                        ),

                    )// Cute pastel pink background
                    .padding(top = 56.dp) // Start below the top bar
            ) {
                DrawerContent(navController, drawerState, scope)
            }
        }
    }


@Composable
fun DrawerContent(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // **Drawer Header with Close Button**
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Explore", fontSize = 24.sp, color = Color.White, modifier = Modifier.weight(1f))
            IconButton(onClick = { scope.launch { drawerState.close() } }) {
                Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
            }
        }

        Divider(color = Color.White, thickness = 1.dp)

        DrawerItem("Home", Icons.Default.Home) {
            navController.navigate(ROUT_HOME)
            scope.launch { drawerState.close() }
        }

        DrawerItem("View Tasks", Icons.Default.List) {
            navController.navigate(ROUT_VIEW_TASK)
            scope.launch { drawerState.close() }
        }

        DrawerItem("Add Task", Icons.Default.AddCircle) {
            navController.navigate(ROUT_UPLOAD_TASK)
            scope.launch { drawerState.close() }
        }

        DrawerItem("About", Icons.Default.Info) {
            navController.navigate(ROUT_ABOUT)
            scope.launch { drawerState.close() }
        }

        DrawerItem("Login", Icons.Default.Person) {
            navController.navigate(ROUT_LOGIN)
            scope.launch { drawerState.close() }
        }

        DrawerItem("Register", Icons.Default.Person) {
            navController.navigate(ROUT_REGISTER)
            scope.launch { drawerState.close() }
        }
    }
}

@Composable
fun DrawerItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp)
        , // Styled item
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, modifier = Modifier.size(24.dp), tint = Color(
            0xFFF8F6F7
        )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = title, fontSize = 18.sp, color = Color(0xFFF0ECF5)) // Readable text
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
