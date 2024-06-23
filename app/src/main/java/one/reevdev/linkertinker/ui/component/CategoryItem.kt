package one.reevdev.linkertinker.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.linkertinker.R
import one.reevdev.linkertinker.ui.theme.LinkerTinkerTheme

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryName: String,
    links: List<String>,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing,
                )
            ),
    ) {
        Column(
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                    text = categoryName
                )
                val iconId = if (isExpanded)
                    R.drawable.baseline_keyboard_arrow_up_24
                else
                    R.drawable.baseline_keyboard_arrow_down_24
                Icon(
                    modifier = Modifier
                        .clickable {
                            isExpanded = !isExpanded
                        },
                    painter = painterResource(id = iconId),
                    contentDescription = null
                )
            }
            if (links.isNotEmpty() && isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surface)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    links.forEachIndexed { index, it ->
                        if (index > 0) Divider()
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = it,
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CategoryItemPreview() {
    LinkerTinkerTheme {
        CategoryItem(
            categoryName = "Test Category",
            links = listOf("Category 1", "Category 2", "Category 3", "Category 4")
        )
    }
}

@Preview
@Composable
private fun CategoryItemExpandedPreview() {
    LinkerTinkerTheme {
        CategoryItem(
            categoryName = "Test Category",
            links = listOf("Category 1", "Category 2", "Category 3", "Category 4"),
        )
    }
}