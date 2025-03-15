package com.example.tuan3_bai1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("ui_list") { UIListScreen(navController) }
        composable("text_detail") { TextDetailScreen(navController) }
    }
}

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier.size(350.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Jetpack Compose", fontSize = 40.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                "Jetpack Compose is a modern toolkit for building native Android applications using a declarative programming approach.",
                fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Button(
            onClick = { navController.navigate("ui_list") },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00A6FF))
        ) {
            Text("I'm ready", fontSize = 20.sp, color = Color.White)
        }
    }
}

@Composable
fun UIListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text("UI Components List", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Blue, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))

        val uiGroups = listOf(
            "Display" to listOf("Text" to "Displays text", "Image" to "Displays an image"),
            "Input" to listOf("TextField" to "Input field for text", "PasswordField" to "Input field for password"),
            "Layout" to listOf("Column" to "Arrange elements vertically", "Row" to "Arrange elements horizontally")
        )

        uiGroups.forEach { (groupTitle, components) ->
            Text(groupTitle, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray, modifier = Modifier.padding(start = 8.dp, top = 4.dp))
            components.forEach { (title, description) ->
                UIItem(title, description) {
                    if (title == "Text") navController.navigate("text_detail")
                }
            }
        }
    }
}

@Composable
fun UIItem(title: String, description: String, onClick: () -> Unit) {
    var isClicked by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { isClicked = true; onClick() },
        colors = CardDefaults.cardColors(containerColor = if (isClicked) Color.DarkGray else Color.Cyan),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(title, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(description, fontSize = 18.sp, color = Color.Black)
        }
    }
}

@Composable
fun TextDetailScreen(navController: NavHostController, spaceWeight: Float = 0.3f) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, start = 16.dp, end = 16.dp), // Lùi xuống 10cm
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Text Detail",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                textAlign = TextAlign.Center
            )

            // Ảnh nút quay lại (bên trái)
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.CenterStart)
                    .clickable { navController.popBackStack() } // Quay lại khi ấn vào
            )
        }

        Spacer(modifier = Modifier.weight(spaceWeight)) // Thay đổi khoảng cách giữa tiêu đề và ảnh

        Image(
            painter = painterResource(id = R.drawable.img_1),
            contentDescription = "Detail Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )

        Spacer(modifier = Modifier.weight(1f)) // Đảm bảo căn giữa
    }
}

