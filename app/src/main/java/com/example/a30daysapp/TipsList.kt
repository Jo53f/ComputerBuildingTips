package com.example.a30daysapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.DampingRatioNoBouncy
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysapp.model.Tip
import com.example.a30daysapp.model.TipsDataSource.tips
import com.example.a30daysapp.ui.theme._30DaysAppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TipList(
    tips : List<Tip>,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp),

) {
    val visibility = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibility,
        enter = fadeIn(
            animationSpec = spring(
                dampingRatio = DampingRatioLowBouncy
            )
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn (contentPadding = contentPaddingValues) {
            itemsIndexed(tips) {index, tip ->
                TipItem(
                    tip = tip,
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    dampingRatio = DampingRatioNoBouncy,
                                    stiffness = StiffnessLow
                                ),
                                initialOffsetY = { it * (index + 1) }
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun TipItem(tip: Tip ,modifier: Modifier = Modifier){
    var expanded by remember {
        mutableStateOf(false)
    }

    Card (modifier = modifier,
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column (
            modifier = Modifier
                .animateContentSize()
                .clickable {
                    expanded = !expanded
                }
        ) {
            Text(
                text = stringResource(id = tip.tipTitle),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(10.dp)
            )
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = tip.tipPicture),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
            if (expanded) {
                Text(
                    text = stringResource(id = tip.tipContent),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun TipListPreview(){
    _30DaysAppTheme(darkTheme = false){
        TipList(tips = tips)
    }
}

@Preview
@Composable
fun TipItemLightPreview(){
    _30DaysAppTheme(darkTheme = false){
        TipItem(tips.first())
    }
}

@Preview
@Composable
fun TipItemDarkPreview(){
    _30DaysAppTheme(darkTheme = true){
        TipItem(tips.first())
    }
}