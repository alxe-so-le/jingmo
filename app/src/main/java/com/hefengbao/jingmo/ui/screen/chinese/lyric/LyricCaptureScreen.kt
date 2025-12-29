package com.hefengbao.jingmo.ui.screen.chinese.lyric

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun LyricCaptureRoute(
    viewModel: LyricCaptureViewModel = hiltViewModel<LyricCaptureViewModel>(),
    onBackClick: () -> Unit,
) {
    val lyricEntity by viewModel.lyricEntity.collectAsState(initial = null)

    LyricCaptureScreen(
        onBackClick = onBackClick,
        lyricEntity = lyricEntity,
    )
}

@Composable
private fun LyricCaptureScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    lyricEntity: LyricEntity?,
) {
    CaptureScaffold(
        modifier = modifier,
        onBackClick = onBackClick,
    ) { tColor, tSize ->

        lyricEntity?.let { entity ->
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = entity.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = tColor,
                    fontSize = tSize.sp
                )

                var text = ""
                if (entity.writer != null) {
                    text += "填词：${entity.writer}"
                }
                if (entity.singer != null) {
                    if (text.isNotEmpty()) {
                        text += " / "
                    }
                    text += "演唱：${entity.singer}"
                }
                Text(
                    text = text,
                    color = tColor,
                    fontSize = (tSize - 2).sp,
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