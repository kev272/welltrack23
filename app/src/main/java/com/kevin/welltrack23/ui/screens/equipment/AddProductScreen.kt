package com.kevin.welltrack23.ui.screens.products

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kevin.welltrack23.R
import com.kevin.welltrack23.navigation.ROUT_ADD_PRODUCT
import com.kevin.welltrack23.navigation.ROUT_PRODUCT_LIST

import com.kevin.welltrack23.viewmodel.ProductViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController, viewModel: ProductViewModel) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showMenu by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Image Picker Launcher
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            Log.d("ImagePicker", "Selected image URI: $it")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Member", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Blue),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("List of Members") },
                            onClick = {
                                navController.navigate(ROUT_PRODUCT_LIST)
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Add Member") },
                            onClick = {
                                navController.navigate(ROUT_ADD_PRODUCT)
                                showMenu = false
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Product Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(" Name") },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.baseline_dynamic_feed_24), contentDescription = "Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Product Price
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Payment") },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.baseline_architecture_24), contentDescription = "Price") },
                    modifier = Modifier.fillMaxWidth()
                )



                Spacer(modifier = Modifier.height(10.dp))

                // Phone Number
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Trainer's No.") },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.phone), contentDescription = "Price") },
                    modifier = Modifier.fillMaxWidth()
                )


                Spacer(modifier = Modifier.height(16.dp))

                // Image Picker Box
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                        .clickable { imagePicker.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUri),
                            contentDescription = "Selected Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painter = painterResource(R.drawable.image), contentDescription = "Pick Image")
                            Text("Tap to pick image", color = Color.DarkGray)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Date and Time Picker

                Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                    Button(
                        onClick = {
                            val calendar = Calendar.getInstance()
                            val year = calendar.get(Calendar.YEAR)
                            val month = calendar.get(Calendar.MONTH)
                            val day = calendar.get(Calendar.DAY_OF_MONTH)

                            android.app.DatePickerDialog(
                                context,
                                { _, selectedYear, selectedMonth, selectedDay ->
                                    val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"

                                    // TimePicker inside DatePicker callback
                                    val hour = calendar.get(Calendar.HOUR_OF_DAY)
                                    val minute = calendar.get(Calendar.MINUTE)

                                    android.app.TimePickerDialog(
                                        context,
                                        { _, selectedHour, selectedMinute ->
                                            val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                                            date = "$selectedDate $selectedTime"
                                        },
                                        hour,
                                        minute,
                                        true
                                    ).show()
                                },
                                year,
                                month,
                                day
                            ).show()
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Gray),
                        modifier = Modifier
                            .height(65.dp)
                            .padding(top = 10.dp)
                    ) {
                        Text(text = "End of Membership")
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    OutlinedTextField(
                        value = date,
                        onValueChange = { /* No-op */ },
                        label = { Text("") },
                        readOnly = true,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .width(250.dp),
                        trailingIcon = {
                            Text(text = "")
                        },
                        singleLine = true
                    )

                }



                //End of a datefield


                // Add Product Button
                Button(
                    onClick = {
                        val priceValue = price.toDoubleOrNull()
                        if (priceValue != null) {
                            imageUri?.toString()?.let { viewModel.addProduct(name, priceValue, phone,it) }
                            navController.popBackStack()

                        }else{navController.navigate(ROUT_PRODUCT_LIST)}

                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {
                    Text("Add Member", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}

// Bottom Navigation Bar Component
@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFF1436A1),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PRODUCT_LIST) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Product List", tint = Color.White) },
            label = { Text("Members",color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_PRODUCT) },
            icon = { Icon(Icons.Default.AddCircle, contentDescription = "Add Product", tint = Color.White) },
            label = { Text("Add",color = Color.White) }
        )


        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PRODUCT_LIST) },
            icon = { Icon(painter = painterResource(R.drawable.profile), contentDescription = "", tint = Color.White) },
            label = { Text("Profile",color = Color.White) }
        )
    }
}
