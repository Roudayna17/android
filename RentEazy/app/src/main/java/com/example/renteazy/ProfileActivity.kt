package com.example.renteazy
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileScreen()
        }
    }
}

@Composable
fun ProfileScreen() {
    var userName by remember { mutableStateOf("Nom d'utilisateur") }
    var userEmail by remember { mutableStateOf("user@example.com") }
    var userNameInput by remember { mutableStateOf(userName) }
    var userEmailInput by remember { mutableStateOf(userEmail) }
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 📌 Image de profil
        Image(
            painter = painterResource(id = R.drawable.profil),
            contentDescription = "Photo de profil",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 📌 Champ de modification du nom
        BasicTextField(
            value = userNameInput,
            onValueChange = { userNameInput = it },
            textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 📌 Champ de modification de l'email
        BasicTextField(
            value = userEmailInput,
            onValueChange = { userEmailInput = it },
            textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 📌 Boutons "Enregistrer" et "Annuler"
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                if (userNameInput.isNotEmpty() && userEmailInput.isNotEmpty()) {
                    userName = userNameInput
                    userEmail = userEmailInput
                    toastMessage = "Profil mis à jour avec succès"
                } else {
                    toastMessage = "Veuillez remplir tous les champs"
                }
                showToast = true
            }) {
                Text("Enregistrer")
            }

            Button(onClick = {
                userNameInput = userName
                userEmailInput = userEmail
            }) {
                Text("Annuler")
            }
        }

        if (showToast) {
            ToastMessage(message = toastMessage)
        }
    }
}

@Composable
fun ToastMessage(message: String) {
    Snackbar(
        modifier = Modifier.padding(16.dp),
        action = {
            Text("OK", color = Color.White)
        }
    ) {
        Text(message, color = Color.White)
    }
}
