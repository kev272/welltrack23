package com.kevin.welltrack23.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.kevin.welltrack23.navigation.ROUT_HOME
import com.kevin.welltrack23.navigation.ROUT_LOGIN
import com.kevin.welltrack23.navigation.ROUT_REGISTER
import com.kevin.welltrack23.navigation.ROUT_UPLOAD_TASK
import com.kevin.welltrack23.navigation.ROUT_VIEW_TASK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Trainers", fontSize = 20.sp) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Blue,
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White),
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF070BDC), Color(0xFF5A5E64))
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
                                contentDescription = "Home", tint = Color.White
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
                                Icons.Default.List,
                                contentDescription = "Info", tint = Color.White
                            )
                        },label = { Text("Login", color = Color.White) },

                        selected = selectedIndex == 1,
                        onClick = {


                                selectedIndex = 1
                                navController.navigate(ROUT_VIEW_TASK)

                                // Handle the case where the user is not logged in
                                navController.navigate(ROUT_LOGIN) // Navigate to the login screen or show a toast/snackbar

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

                Spacer(modifier = Modifier.height(20.dp))

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
                                        colors = listOf(Color(0xFF595253), Color(0xFF0072FF))
                                    ),

                                    ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Image(
                                painter = painterResource(R.drawable.img_13),
                                contentDescription = "home",
                                modifier = Modifier.size(90.dp).clip(shape = CircleShape),
                            )
                            Text(text = "Name:John Mally", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Phone.No:+2536736726", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Speciality:Advanced Upperbody", fontSize = 15.sp, textAlign = TextAlign.Center)





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
                                        colors = listOf(Color(0xFF6B6566), Color(0xFF0072FF))
                                    ),

                                    ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Image(
                                painter = painterResource(R.drawable.img_7),
                                contentDescription = "home",
                                modifier = Modifier.size(90.dp).clip(shape = CircleShape),
                            )
                            Text(text = "Name:Rann Bane", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Phone.No:+2532634536", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Speciality:Advanced Fullbody", fontSize = 15.sp, textAlign = TextAlign.Center)


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
                                        colors = listOf(Color(0xFF706868), Color(0xFF0072FF))
                                    ),

                                    ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Image(
                                painter = painterResource(R.drawable.img_11),
                                contentDescription = "home",
                                modifier = Modifier.size(90.dp).clip(shape = CircleShape),
                            )
                            Text(text = "Name:Drake Tim", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Phone.No:+2532341232", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Speciality:Beginner Upperbody", fontSize = 15.sp, textAlign = TextAlign.Center)


                        }


                    }
                    //End of card3




                }
                //End of Row

                Spacer(modifier = Modifier.height(20.dp))
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
                                        colors = listOf(Color(0xFF8F8989), Color(0xFF0072FF))
                                    ),

                                    ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Image(
                                painter = painterResource(R.drawable.img_12),
                                contentDescription = "home",
                                modifier = Modifier.size(90.dp).clip(shape = CircleShape),
                            )
                            Text(text = "Name:Sally Marri", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Phone.No:+2532364643", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Speciality:Weightloss&lowerbody", fontSize = 15.sp, textAlign = TextAlign.Center)


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
                                        colors = listOf(Color(0xFF9D9597), Color(0xFF0072FF))
                                    ),

                                    ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Image(
                                painter = painterResource(R.drawable.img_10),
                                contentDescription = "home",
                                modifier = Modifier.size(90.dp).clip(shape = CircleShape),
                            )
                            Text(text = "Name:Jocob Rann", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Phone.No:+2532534627", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Speciality:Advanced Lowerbody", fontSize = 15.sp, textAlign = TextAlign.Center)


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
                                        colors = listOf(Color(0xFF8F8989), Color(0xFF0072FF))
                                    ),

                                    ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Image(
                                painter = painterResource(R.drawable.img_9),
                                contentDescription = "home",
                                modifier = Modifier.size(90.dp).clip(shape = CircleShape),
                            )
                            Text(text = "Name:John Mally", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Phone.No:+2536736726", fontSize = 15.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Speciality:Beginner Fullbody", fontSize = 15.sp, textAlign = TextAlign.Center)

                        }


                    }
                    //End of card3




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
                            colors = listOf(Color(0xFF70686C), Color(0xFF0072FF))
                        ),

                        )// Cute pastel pink background
                    .padding(top = 56.dp) // Start below the top bar
            ) {
                DrawerContent(navController, drawerState, scope)
            }
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
            0xFFFAF6F7
        )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = title, fontSize = 18.sp, color = Color(0xFFF0ECF5)) // Readable text
    }
    Spacer(modifier = Modifier.height(8.dp))
}




@Preview(showBackground = true)
@Composable
fun AboutScreenPreview(){
    AboutScreen(navController = rememberNavController())
}