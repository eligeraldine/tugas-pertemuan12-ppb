package com.loginpage.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loginpage.R
import com.loginpage.viewmodel.LoginViewModel

val PrimaryOrange = Color(0xFFFF5722)
val LightOrangeBg = Color(0xFFFFF0E6)

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val context = LocalContext.current

    LaunchedEffect(viewModel.loginMessage) {
        viewModel.loginMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.clearMessage()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.illustration),
            contentDescription = "Login Illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "LOGIN", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = viewModel.username,
            onValueChange = { viewModel.username = it },
            placeholder = { Text("Username") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Username Icon") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightOrangeBg,
                unfocusedContainerColor = LightOrangeBg,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLeadingIconColor = PrimaryOrange,
                unfocusedLeadingIconColor = PrimaryOrange,
                focusedPlaceholderColor = PrimaryOrange.copy(alpha = 0.5f),
                unfocusedPlaceholderColor = PrimaryOrange.copy(alpha = 0.5f),
                focusedTextColor = PrimaryOrange,
                unfocusedTextColor = PrimaryOrange
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            placeholder = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightOrangeBg,
                unfocusedContainerColor = LightOrangeBg,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLeadingIconColor = PrimaryOrange,
                unfocusedLeadingIconColor = PrimaryOrange,
                focusedPlaceholderColor = PrimaryOrange.copy(alpha = 0.5f),
                unfocusedPlaceholderColor = PrimaryOrange.copy(alpha = 0.5f),
                focusedTextColor = PrimaryOrange,
                unfocusedTextColor = PrimaryOrange
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { viewModel.onLoginClick() },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
        ) {
            Text(text = "Login", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Forgot password ?", fontSize = 12.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { Toast.makeText(context, "Forgot Password", Toast.LENGTH_SHORT).show() }
            )
            Text(
                text = "Help", fontSize = 12.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { Toast.makeText(context, "Help", Toast.LENGTH_SHORT).show() }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) { append("Not Registered ? ") }
            withStyle(style = SpanStyle(color = PrimaryOrange, fontWeight = FontWeight.Bold)) { append("Create account") }
        }

        Text(
            text = annotatedString, fontSize = 14.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clickable { Toast.makeText(context, "Create Account Clicked", Toast.LENGTH_SHORT).show() }
        )
    }
}