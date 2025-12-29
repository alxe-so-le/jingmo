package com.hefengbao.jingmo.ui.screen.chinese.poetry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun ModernPoetryCaptureRoute(
    viewModel: ModernPoetryCaptureViewModel = hiltViewModel<ModernPoetryCaptureViewModel>(),
    onBackClick: () -> Unit,
) {
    val poetryEntity by viewModel.poetryEntity.collectAsState(null)

    ModernPoetryCaptureScreen(
        onBackClick = onBackClick,
        poetryEntity = poetryEntity,
    )
}

@Composable
fun ModernPoetryCaptureScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    poetryEntity: ModernPoetryEntity?,
) {
    CaptureScaffold(
        modifier = modifier,
        onBackClick = onBackClick,
    ) { tColor, tSize ->

        poetryEntity?.let { entity ->
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = entity.title,
                    color = tColor,
                    fontSize = tSize.sp
                )
                Text(
                    text = entity.author,
                    textAlign = TextAlign.Center,
                    color = tColor,
                    fontSize = (tSize-2).sp,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = entity.content,
                    color = tColor,
                    fontSize = tSize.sp,
                    lineHeight = (tSize + 16).sp
                )
            }
        }
    }
}