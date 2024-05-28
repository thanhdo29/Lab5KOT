package com.example.lab5

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Bai1()
                    Bai2()
                }
            }
        }
    }
}

@Composable
fun LoginScreen(){
    val context= LocalContext.current
    var  username by remember {
        mutableStateOf("")
    }
    var  password by remember {
        mutableStateOf("")
    }
    var showDialog by remember {
        mutableStateOf(false)
    }

    var dialogMessage by remember {
        mutableStateOf("")
    }

    if (showDialog){
        DialogComponent(onConfirmation = { /*TODO*/ }, dialogTitle = "Notification", dialogMessage = dialogMessage)
    }
    Column(
        modifier= Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = com.example.lab5.R.drawable.ic_launcher_foreground),
            contentDescription ="Logo" )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = username, onValueChange = {username=it}, label = { Text(text = "Username")})
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text(text = "Password")}, visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(8.dp))
        RememberMeSwitch()
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (username.length==0 || password.length==0 ){
                dialogMessage="Login successfully"
            }else{
                dialogMessage="Please enter username and password"
            }
            showDialog=true
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        )
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
fun DialogComponent(
    onConfirmation:()->Unit,
    dialogTitle: String,
    dialogMessage: String
){
    Dialog(onDismissRequest = { }) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.padding(20 .dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10 .dp)
        ) {
            Column(
                modifier = Modifier.padding(16 .dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = dialogTitle, style =MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = dialogMessage, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = { /*TODO*/ },
                        modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Okay")
                }
            }
        }
    }
}

@Composable
fun RememberMeSwitch(){
    var isChecked by remember {
        mutableStateOf(false)
    }
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 12.dp)) {
        Switch(checked = isChecked, onCheckedChange = {isChecked=it})
        Text(text = "Remember me?", modifier = Modifier.padding(start = 12 .dp))
    }
}

@Composable
fun Bai1(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray),contentAlignment = Alignment.Center){
        Card(
            modifier= Modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4 .dp)
        ) {
            LoginScreen()
        }
    }
}

@Composable
fun Bai2(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val isChecked= remember {
            mutableStateOf(false)
        }

        if (isChecked.value){
            Image(painter = painterResource(id = com.example.lab5.R.drawable.bong_off),
                contentDescription =null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth)
        }else{
            Image(painter = painterResource(id = com.example.lab5.R.drawable.bong_on),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth)
        }

        Spacer(modifier = Modifier.height(24 .dp))

        Switch(checked = isChecked.value, onCheckedChange = { isChecked.value = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                uncheckedThumbColor = Color.Gray,
                checkedTrackColor = Color.Green.copy(alpha = 0.5f),
                uncheckedTrackColor = Color.Gray.copy(alpha = 0.75f)
            )
            )
    }
}


@Composable
fun CategoryApp(){
    val categories= listOf("Fiction", "Mystery","Science Fiction","Fantasy", "Adventure", "Historical", "Horror", "Romance")
    val suggestions= listOf("Biography", "Cookbook", "Poetry", "Self-help", "Thiriller")
    var selectedCategories by remember {
        mutableStateOf(setOf<String>())
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab5Theme {
        Bai1()
    }
}