/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.wisecrack

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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun ChineseWisecrackCaptureRoute(
    viewModel: WisecrackCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val chineseWisecrackEntity by viewModel.chineseWisecrackEntity.collectAsState()

    ChineseWisecrackCaptureScreen(
        onBackClick = onBackClick,
        chineseWisecrackEntity = chineseWisecrackEntity,
    )
}

@Composable
private fun ChineseWisecrackCaptureScreen(
    onBackClick: () -> Unit,
    chineseWisecrackEntity: WisecrackEntity?,
) {
    CaptureScaffold(
        onBackClick = onBackClick,
    ) { tColor, tSize ->

        chineseWisecrackEntity?.let { entity ->
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = entity.riddle,
                    color = tColor,
                    fontSize = tSize.sp
                )
                Text(
                    text = "—— ${entity.answer}",
                    color = tColor,
                    fontSize = tSize.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}