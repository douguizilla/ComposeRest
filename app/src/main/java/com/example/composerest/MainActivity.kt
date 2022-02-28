package com.example.composerest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composerest.model.User
import com.example.composerest.ui.theme.ComposeRestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp1()
                }
            }
        }
    }
}

@Composable
fun MyApp1(
    viewModel: UserViewModel = hiltViewModel(),
) {

    val users by viewModel.users.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)

    MyApp(
        onAddClick = {
        viewModel.addUser()
        },
        users = users,
        isLoading = isLoading
    )
}

@Composable
fun MyApp(
    onAddClick : (() -> Unit)? = null,
    users: List<User>,
    isLoading : Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compose Rest + Room")},
                actions = {
                    IconButton(onClick = {
                        onAddClick?.invoke()
                    }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                    }
                }
            )
        }
    ) {
        LazyColumn{
            var itemCount = users.size
            if(isLoading) itemCount ++

            items(count = itemCount){ index ->
                var auxIndex = index
                if(isLoading){
                    if(auxIndex == 0)
                        return@items Text(text = "IsLoading")
                    auxIndex--
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRestTheme {
        MyApp(onAddClick = null, users = emptyList(), isLoading = false)
    }
}