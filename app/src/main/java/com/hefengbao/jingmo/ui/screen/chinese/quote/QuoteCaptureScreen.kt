package com.hefengbao.jingmo.ui.screen.chinese.quote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun QuoteCaptureRoute(
    viewModel: QuoteCaptureViewModel = hiltViewModel<QuoteCaptureViewModel>(),
    onBackClick: () -> Unit,
) {
    val quoteEntity by viewModel.quoteEntity.collectAsState(null)

    QuoteCaptureScreen(
        onBackClick = onBackClick,
        quoteEntity = quoteEntity,
    )
}

@Composable
private fun QuoteCaptureScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    quoteEntity: QuoteEntity?,
) {
    CaptureScaffold(
        modifier = modifier,
        onBackClick = onBackClick,
    ) { tColor, tSize ->
        quoteEntity?.let { entity ->
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = entity.content,
                    color = tColor,
                    fontSize = tSize.sp,
                    lineHeight = (tSize + 16).sp
                )
                Text(
                    text = "—— ${entity.author}·${entity.from}",
                    fontSize = (tSize - 2).sp,
                    fontStyle = FontStyle.Italic,
                    color = tColor,
                    lineHeight = 3.em
                )
            }
        }
    }
}