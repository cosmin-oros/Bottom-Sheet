package com.example.bottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bottomsheet.ui.theme.BottomSheetTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomSheetTheme {
                val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
                val scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = sheetState
                )
                val scope = rememberCoroutineScope()

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Bottom Sheet",
                            fontSize = 60.sp
                        )
                    }
                },
                    sheetBackgroundColor = Color.Cyan
                //if you want it to be hidden
                //sheetPeekHeight = 0.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Button(onClick = {
                            scope.launch {
                                if (sheetState.isCollapsed){
                                    sheetState.expand()
                                }else{
                                    sheetState.collapse()
                                }
                            }
                        }) {
                            Text(text = "Bottom sheet fraction: ${sheetState.progress.fraction}")
                        }
                    }
                }
            }
        }
    }
}

