package org.example.project

import AppTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.TitleTopBarTypes
import getColorsTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import navigation.Navigation
import org.koin.compose.KoinContext


@Composable
@Preview
fun App() {

    PreComposeApp {

        KoinContext {
            //Vid 25
            val colors = getColorsTheme()
            //SessionCache.configDevice = configDevice
            AppTheme {
                val navigator = rememberNavigator()
                val titleTopBar = getTitleTopAppBar(navigator)
                val isEditOrAddExpenses = titleTopBar != TitleTopBarTypes.DASHBOARD.value
                //Vid 35
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            elevation = 0.dp,
                            title = {
                                Text(
                                    text = titleTopBar,
                                    fontSize = 25.sp,
                                    color = colors.textColor
                                )
                            },
                            navigationIcon = {
                                //Vid 36
                                if (isEditOrAddExpenses) {
                                    IconButton(
                                        onClick = {
                                            navigator.popBackStack()
                                        }
                                    ) {
                                        Icon(
                                            modifier = Modifier.padding(start = 16.dp),
                                            imageVector = Icons.Default.ArrowBackIosNew,
                                            tint = colors.textColor,
                                            contentDescription = "Flecha back"
                                        )
                                    }
                                } else {
                                    Icon(
                                        modifier = Modifier.padding(start = 16.dp),
                                        imageVector = Icons.Default.Apps,
                                        tint = colors.textColor,
                                        contentDescription = "Dashboard icon"
                                    )
                                }
                            },
                            backgroundColor = colors.backgroundColor
                        )
                    },
                    floatingActionButton = {
                        if (!isEditOrAddExpenses) {
                            FloatingActionButton(
                                modifier = Modifier.padding(8.dp),
                                onClick = {
                                    navigator.navigate("/addExpenses")
                                },
                                shape = RoundedCornerShape(50),
                                backgroundColor = colors.addIconColor,
                                contentColor = Color.White,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    tint = Color.White,
                                    contentDescription = "Floating icon"
                                )
                            }
                        }
                    }


                )// Scaffold
                {
                    //Vid 36
                    Navigation(navigator)
                }
            }//AppTheme
        }
    }
}


@Composable
fun getTitleTopAppBar(navigator: Navigator):String {
    var titleTopBar = TitleTopBarTypes.DASHBOARD

    val isOnAddExpenses = navigator.currentEntry.collectAsState(null).value?.route?.route.equals("/addExpenses/{id}?")
    if(isOnAddExpenses){
        titleTopBar = TitleTopBarTypes.ADD
    }

    val isOnEditExpense = navigator.currentEntry.collectAsState(null).value?.path<Long>("id")
    isOnEditExpense?.let{
        titleTopBar = TitleTopBarTypes.EDIT
    }
    return  titleTopBar.value
}