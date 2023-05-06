package co.ssup.tuBearHai.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
  ModalBottomSheetLayout(
    sheetContent = {

    }
  ) {
    Column(modifier = Modifier.fillMaxSize()) {

    }
  }
}

@Composable
fun BeerItem() {
  Row(modifier = Modifier.fillMaxWidth()) {

  }
}