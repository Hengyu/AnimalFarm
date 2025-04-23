package com.artlvr.animalfarm.poetry

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.artlvr.animalfarm.R
import com.artlvr.animalfarm.ui.theme.Shapes
import java.text.SimpleDateFormat
import kotlin.math.min

@Composable
fun PoetryPage(
    poetry: Poetry,
    initialSection: Int = 0,
    onSectionChanged: (Int) -> Unit = {},
    onLongPress: (Offset) -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val pagerState =
            rememberPagerState(initialPage = min(initialSection, poetry.sections.count())) {
                poetry.sections.count()
            }

        LaunchedEffect(key1 = poetry.hashCode()) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                onSectionChanged(page)
            }
        }

        val (pager, indicator) = createRefs()
        val size = remember { mutableStateOf(IntSize.Zero) }
        val description = stringResource(R.string.poetry_page_description)

        HorizontalPager(
            modifier = Modifier
                .constrainAs(pager) {
                    top.linkTo(parent.top, margin = 24.dp)
                    bottom.linkTo(parent.bottom, margin = 24.dp)
                }
                .onSizeChanged {
                    size.value = it
                }
                .semantics {
                    contentDescription = description
                }
                .pointerInput(Unit) {
                    detectTapGestures(onLongPress = onLongPress)
                },
            state = pagerState
        ) { page ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                val section = poetry.sections[page]
                Spacer(modifier = Modifier.height((size.value.height / 16).dp))
                ConstraintLayout {
                    val (card, text) = createRefs()
                    Card(
                        modifier = Modifier
                            .height(8.dp)
                            .constrainAs(card) {
                                start.linkTo(text.start)
                                end.linkTo(text.end)
                                bottom.linkTo(text.bottom)
                                width = Dimension.fillToConstraints
                            },
                        shape = Shapes.small,
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                    }
                    Text(
                        text = section.title,
                        modifier = Modifier.constrainAs(text) {},
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        letterSpacing = 2.sp,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = section.content,
                    modifier = Modifier
                        .fillMaxWidth()
                        .semantics {
                            contentDescription = section.content
                        },
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Default
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM)
                        .format(section.date),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height((size.value.height / 24).dp))
            }
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .constrainAs(indicator) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                centerHorizontallyTo(parent)
            },
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val alpha = if (pagerState.currentPage == iteration) ContentAlpha.high else ContentAlpha.disabled
                val color = LocalContentColor.current.copy(alpha = alpha)
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(12.dp)
                )
            }
        }
    }
}
