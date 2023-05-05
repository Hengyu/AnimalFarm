package com.artlvr.animalfarm.poetry

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
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
import com.artlvr.animalfarm.ui.theme.Shapes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import java.text.SimpleDateFormat
import kotlin.math.min

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PoetryPage(
    poetry: Poetry,
    initialSection: Int = 0,
    onSectionChanged: (Int) -> Unit = {},
    onLongPress: (Offset) -> Unit = {}
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val pagerState =
            rememberPagerState(initialPage = min(initialSection, poetry.sections.count()))

        LaunchedEffect(key1 = poetry.hashCode()) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                onSectionChanged(page)
            }
        }

        val (pager, indicator) = createRefs()
        val size = remember { mutableStateOf(IntSize.Zero) }

        HorizontalPager(
            count = poetry.sections.count(),
            modifier = Modifier
                .constrainAs(pager) {
                    top.linkTo(parent.top, margin = 24.dp)
                    bottom.linkTo(parent.bottom, margin = 24.dp)
                }
                .onSizeChanged {
                    size.value = it
                }
                .semantics {
                    contentDescription = "pager"
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

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.constrainAs(indicator) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                centerHorizontallyTo(parent)
            }
        )
    }
}
