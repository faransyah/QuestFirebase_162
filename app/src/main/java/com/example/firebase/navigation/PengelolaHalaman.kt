package com.example.firebase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase.ui.view.HomeScreen
import com.example.firebase.ui.view.InsertMhsView

@Composable
fun PengelolaHalaman(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsert.route)
                },
            )
        }
        composable(DestinasiInsert.route){
            InsertMhsView(
                onBack = { navController.popBackStack()},
                onNavigate = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }
        composable(DestinasiDetail.route){
            InsertMhsView(
                onBack = { navController.popBackStack()},
                onNavigate = {
                    navController.navigate(DestinasiDetail.route)
                }
            )
        }

    }
}