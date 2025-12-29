/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.idiom

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun IdiomCaptureRoute(
    viewModel: IdiomCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val idiomEntity by viewModel.idiomEntity.collectAsState(initial = null)

    IdiomCaptureScreen(
        onBackClick = onBackClick,
        idiomEntity = idiomEntity,
    )
}

@Composable
private fun IdiomCaptureScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    idiomEntity: IdiomEntity?,
) {
    CaptureScaffold(
        onBackClick = onBackClick,
    ) { tColor, tSize ->


        idiomEntity?.let {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp, 48.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = idiomEntity.pinyin,
                        style = MaterialTheme.typography.bodyMedium,
                        color = tColor
                    )
                    Text(
                        text = idiomEntity.word,
                        style = MaterialTheme.typography.bodyLarge,
                        color = tColor
                    )
                }
                idiomEntity.explanation?.let {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(
                            text = "释义",
                            color = tColor,
                        )
                        Text(
                            text = it,
                            color = tColor,
                            fontSize = tSize.sp,
                            lineHeight = (tSize + 16).sp
                        )
                    }
                }

                idiomEntity.source?.let { source ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "出处", color = tColor)

                        val text = buildAnnotatedString {
                            source.text?.let { append(it) }
                            source.book?.let {
                                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                    append("《${it}》")
                                }
                            }
                        }

                        Text(
                            text = text,
                            color = tColor,
                            fontSize = tSize.sp,
                            lineHeight = (tSize + 16).sp
                        )
                    }
                }

                idiomEntity.quote?.let { quote ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "名著用例", color = tColor)

                        val text = buildAnnotatedString {
                            quote.text?.let { append(it) }
                            quote.book?.let {
                                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                    append("《${it}》")
                                }
                            }
                        }

                        Text(
                            text = text,
                            color = tColor,
                            fontSize = tSize.sp,
                            lineHeight = (tSize + 16).sp
                        )
                    }
                }

                idiomEntity.usage?.let {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "用法介绍", color = tColor)
                        Text(
                            text = it,
                            color = tColor,
                            fontSize = tSize.sp,
                            lineHeight = (tSize + 16).sp
                        )
                    }
                }

                idiomEntity.example?.let { example ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "示例", color = tColor)

                        val text = buildAnnotatedString {
                            example.text?.let { append(it) }
                            example.book?.let {
                                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                    append("《${it}》")
                                }
                            }
                        }

                        Text(
                            text = text,
                            color = tColor,
                            fontSize = tSize.sp,
                            lineHeight = (tSize + 16).sp
                        )
                    }
                }

                idiomEntity.similar?.let {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "同义成语", color = tColor)
                        Text(
                            text = it.joinToString("、"),
                            color = tColor,
                            fontSize = tSize.sp,
                            lineHeight = (tSize + 16).sp
                        )
                    }
                }

                idiomEntity.opposite?.let {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "反义成语", color = tColor)
                        Text(
                            text = it.joinToString("、"),
                            color = tColor,
                            fontSize = tSize.sp,
                            lineHeight = (tSize + 16).sp
                        )
                    }
                }

                idiomEntity.story?.let { list ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "成语故事", color = tColor)
                        list.forEach {
                            Text(
                                text = it,
                                color = tColor,
                                fontSize = tSize.sp,
                                lineHeight = (tSize + 16).sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Title(text: String, color: Color) {
    Text(
        text = text,
        modifier = Modifier
            .border(width = 1.dp, color = color)
            .padding(16.dp, 8.dp),
        color = color,
        style = MaterialTheme.typography.titleSmall,
    )
}