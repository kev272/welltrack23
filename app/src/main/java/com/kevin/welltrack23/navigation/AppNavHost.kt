package com.Kevin.welltrack23.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kevin.welltrack23.data.ContentDatabase
import com.kevin.welltrack23.data.TaskDatabase
import com.kevin.welltrack23.data.UserDatabase
import com.kevin.welltrack23.navigation.ROUT_ABOUT
import com.kevin.welltrack23.navigation.ROUT_ADD_PRODUCT
import com.kevin.welltrack23.navigation.ROUT_EDIT_PRODUCT
import com.kevin.welltrack23.navigation.ROUT_HOME
import com.kevin.welltrack23.navigation.ROUT_LOGIN
import com.kevin.welltrack23.navigation.ROUT_PRODUCT_LIST
import com.kevin.welltrack23.navigation.ROUT_REGISTER
import com.kevin.welltrack23.navigation.ROUT_UPLOAD_CONTENT
import com.kevin.welltrack23.navigation.ROUT_UPLOAD_TASK
import com.kevin.welltrack23.navigation.ROUT_VIEW_CONTENT
import com.kevin.welltrack23.navigation.ROUT_VIEW_TASK
import com.kevin.welltrack23.repository.ContentRepository
import com.kevin.welltrack23.repository.TaskRepository
import com.kevin.welltrack23.repository.UserRepository
import com.kevin.welltrack23.ui.screens.about.AboutScreen
import com.kevin.welltrack23.ui.screens.auth.LoginScreen
import com.kevin.welltrack23.ui.screens.auth.RegisterScreen
import com.kevin.welltrack23.ui.screens.content.UploadContentScreen
import com.kevin.welltrack23.ui.screens.content.ViewContentScreen
import com.kevin.welltrack23.ui.screens.home.HomeScreen
import com.kevin.welltrack23.ui.screens.products.AddProductScreen
import com.kevin.welltrack23.ui.screens.products.EditProductScreen
import com.kevin.welltrack23.ui.screens.products.ProductListScreen
import com.kevin.welltrack23.ui.screens.task.UploadTaskScreen
import com.kevin.welltrack23.ui.screens.task.ViewTaskScreen
import com.kevin.welltrack23.viewmodel.AuthViewModel
import com.kevin.welltrack23.viewmodel.ContentViewModel
import com.kevin.welltrack23.viewmodel.ProductViewModel
import com.kevin.welltrack23.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_HOME,
    productViewModel: ProductViewModel = viewModel(),
) {



    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }

        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }


        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }








        // PRODUCTS
        composable(ROUT_ADD_PRODUCT) {
            AddProductScreen(navController, productViewModel)
        }

        composable(ROUT_PRODUCT_LIST) {
            ProductListScreen(navController, productViewModel)
        }

        composable(
            route = ROUT_EDIT_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                EditProductScreen(productId, navController, productViewModel)
            }
        }





        //CONTENT

        // Initialize Content Database and ViewModel
        val contentDatabase = ContentDatabase.getDatabase(context)
        val contentRepository = ContentRepository(contentDatabase.contentDao())
        val contentViewModel = ContentViewModel(contentRepository)

        composable(ROUT_UPLOAD_CONTENT) {
            UploadContentScreen(navController, contentViewModel)
        }
        composable(ROUT_VIEW_CONTENT) {
            ViewContentScreen(navController, contentViewModel) { id ->
                navController.navigate("upload_content?id=$id")
            }
        }

        //TASK

        // Initialize Content Database and ViewModel
        val taskDatabase = TaskDatabase.getDatabase(context)
        val taskRepository = TaskRepository(taskDatabase.taskDao())
        val taskViewModel = TaskViewModel(taskRepository)

        composable(ROUT_UPLOAD_TASK) {
            UploadTaskScreen(navController, taskViewModel)
        }
        composable(ROUT_VIEW_TASK) {
            ViewTaskScreen(navController, taskViewModel) { id ->
                navController.navigate("upload_task?id=$id")
            }
        }



    }
}
