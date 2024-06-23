package one.reevdev.linkertinker.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun LinkItem(
    modifier: Modifier = Modifier,
    linkName: String,
    linkUrl: String? = null,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            ) {
                Column {
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        text = linkName
                    )
                    linkUrl?.let {
                        Text(
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Light),
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}