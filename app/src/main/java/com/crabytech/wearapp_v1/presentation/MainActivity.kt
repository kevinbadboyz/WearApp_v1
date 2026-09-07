/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.crabytech.wearapp_v1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.asynclayoutinflater.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Login
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.AlertDialog
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.EdgeButton
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.SurfaceTransformation
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import com.crabytech.wearapp_v1.presentation.theme.WearApp_v1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormLogin()
        }
    }
}

@Composable
fun SimpleRow() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Row 1", Modifier.background(Color.Red))
        Text("Row 2", Modifier.background(Color.Green))
        Text("Row 2", Modifier.background(Color.Blue))
    }
}

@Composable
fun SimpleColumn() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Column 1", Modifier.background(Color.Red))
        Text("Column 2", Modifier.background(Color.Green))
        Text("Column 2", Modifier.background(Color.Blue))
    }
}

@Composable
fun FormLogin() {
    val usernameState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val scrollState = rememberScrollState()
    var showDialog by remember { mutableStateOf(false) }

    WearApp_v1Theme {
        AppScaffold(containerColor = MaterialTheme.colorScheme.tertiary) {
            ScreenScaffold() {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text("StrokeApp")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        state = usernameState,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        placeholder = { Text("Nama Pengguna") },
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .height(48.dp)
                            .padding(horizontal = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        state = passwordState,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                        ),
                        placeholder = { Text("Kata Sandi") },
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .height(48.dp)
                            .padding(horizontal = 8.dp)
                    )
                    Button(
                        onClick = {showDialog = true}
                    ) {
                        Icon(imageVector = Icons.Rounded.Login, contentDescription = "Login")
                        Text(" Login")
                    }
//                    EdgeButton(
//                        onClick = {
//                            showDialog = true
//                        }, colors = ButtonDefaults.buttonColors(
//                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
//                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
//                        )
//                    ) {
//                        Text("Login")
//                    }
                }
            }
        }
    }

    SampleDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onCancel = {},
        onOk = {},
        textContent = usernameState.text.toString(),
    )
}

@Composable
fun SampleDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onOk: () -> Unit,
    modifier: Modifier = Modifier,
    textContent: String
) {
    AlertDialog(
        visible = showDialog,
        onDismissRequest = onDismiss,
        title = { Text("Informasi") },
        text = { Text("Nama Pengguna : $textContent") },
        confirmButton = {
            androidx.wear.compose.material3.AlertDialogDefaults.ConfirmButton(onClick = {
                onOk()
                onDismiss()
            })
        },
        dismissButton = {
            androidx.wear.compose.material3.AlertDialogDefaults.DismissButton(onClick = {
                onCancel()
                onDismiss()
            })
        }
    )
}

@Composable
fun WearApp(greetingName: String) {
    WearApp_v1Theme {
        AppScaffold {
            val listState = rememberTransformingLazyColumnState()
            val transformationSpec = rememberTransformationSpec()

            ScreenScaffold(
                scrollState = listState,
                edgeButton = {
                    EdgeButton(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        ),
                    ) {
                        Text("More")
                    }
                },
            ) { contentPadding -> // ScreenScaffold provides default padding; adjust as needed
                TransformingLazyColumn(contentPadding = contentPadding, state = listState) {
                    item {
                        ListHeader(
                            modifier = Modifier
                                .fillMaxWidth()
                                .transformedHeight(this, transformationSpec),
                            transformation = SurfaceTransformation(transformationSpec),
                        ) {
                            Text("StrokeApp")
                        }
                    }
                    item {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .transformedHeight(this, transformationSpec),
                            transformation = SurfaceTransformation(transformationSpec),
                        ) {
                            Text("Button A")
                        }
                    }
                    item {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .transformedHeight(this, transformationSpec),
                            transformation = SurfaceTransformation(transformationSpec),
                        ) {
                            Text("Button B")
                        }
                    }
                    item {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .transformedHeight(this, transformationSpec),
                            transformation = SurfaceTransformation(transformationSpec),
                        ) {
                            Text("Button C")
                        }
                    }

                }
            }
        }
    }
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun PreviewRow() {
    SimpleRow()
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun PreviewColumn() {
    SimpleColumn()
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun PreviewFormLogin() {
    FormLogin()
}

